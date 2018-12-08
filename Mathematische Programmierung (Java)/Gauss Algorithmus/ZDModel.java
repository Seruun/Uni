
/*�bung 7: Gauss Algorithmus
 * Version 1.2
 * Gruppenmitglieder: Adrian Schuh, Dominik Benning, Christopher H�bner
 *
 */
import Exception.InvalidFileContentException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;

public class ZDModel extends Observable {
    private Bruch[][] m;// Matrix zur rechnung
    private Bruch[][] n;// Inverse Matrix
    private Bruch[][] u;// Ursprungsmatrix
    private File file;

    public ZDModel(String a, int b) {
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Bruch[][] getM() {
        return this.m;
    }

    public String getMString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.m.length; ++i) {
            for (int p = 0; p < this.m.length; ++p) {
                sb.append(this.m[i][p].toBruch() + " \t");
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    public void setM(Bruch[][] m) {
        this.m = m;
    }

    public Bruch[][] getN() {
        return this.n;
    }

    public String getNString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.n.length; ++i) {
            for (int p = 0; p < this.n.length; ++p) {
                sb.append(this.n[i][p].toBruch() + " \t");
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    public void setN(Bruch[][] n) {
        this.n = n;
    }

    public Bruch[][] getU() {
        return this.u;
    }

    public void setU(Bruch[][] u) {
        this.u = u;
    }

    public String getUString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.u.length; ++i) {
            for (int p = 0; p < this.u.length; ++p) {
                sb.append(this.u[i][p].toBruch() + " \t");
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    public ZDModel() {
    }

    public void execute() throws IOException, InvalidFileContentException {
        this.readFile();
        // erstellen leerer matrix n
        this.n = new Bruch[this.m.length][this.m.length];
        for (int i = 0; i < this.m.length; ++i) {
            for (int p = 0; p < this.m.length; ++p) {
                this.n[i][p] = new Bruch(0.0D);
            }
        }
        // erster zeilentausch
        for (int i = 0; i < this.m.length; ++i) {
            if (this.m[i][i].getDividend().doubleValue() == 0.0D) {
                for (int p = i + 1; p < this.m.length; ++p) {
                    boolean w = false;
                    if (this.m[p][i].toDouble() != 0.0D && !w) {
                        Bruch g;
                        for (int t = 0; t < this.m.length; ++t) {
                            g = this.m[i][t];
                            this.m[i][t] = this.m[p][t];
                            this.m[p][t] = g;
                            g = this.n[i][t];
                            this.n[i][t] = this.n[p][t];
                            this.n[p][t] = g;
                        }
                    }
                }
            }
            // setze Schr�ge von n auf 1 ->Einheitsmatrix
            this.n[i][i] = new Bruch(1.0D);
        }
        // teile zeilen von m sodass Schr�ge 1
        for (int i = 0; i < this.m.length; ++i) {
            if (this.m[i][i].toDouble() != 1.0D) {
                Bruch g = new Bruch();
                g.setDividend(this.m[i][i].getDividend());
                g.setDivisor(this.m[i][i].getDivisor());

                for (int c = 0; c < this.m.length; ++c) {
                    this.m[i][c].divide(g);
                    this.n[i][c].divide(g);
                }
            }
        }
        for (int i = 0; i < this.m.length; ++i) {
            // falls notwendig erneutes tauschen von Zeilen
            if (this.m[i][i].toDouble() == 0.0D) {
                for (int p = i + 1; p < this.m.length; ++p) {
                    boolean w = false;
                    if (this.m[p][i].toDouble() != 0.0D) {
                        for (int c = 0; c < i; ++c) {
                            if (this.m[p][c].toDouble() != 0.0D) {
                                w = true;
                            }
                        }

                        if (!w) {
                            for (int t = 0; t < this.m.length; ++t) {
                                Bruch g = this.m[i][t];
                                this.m[i][t] = this.m[p][t];
                                this.m[p][t] = g;
                                g = this.n[i][t];
                                this.n[i][t] = this.n[p][t];
                                this.n[p][t] = g;
                            }
                        }
                    }
                }
            }

            // zeilen miteinander subtrahieren um 0 unter den pivot-elementen zu erschaffen
            for (int p = i + 1; p < this.m[1].length; ++p) {
                Bruch t = new Bruch();
                t.setDividend(this.m[p][i].getDividend());
                t.setDivisor(this.m[p][i].getDivisor());
                t = t.divide(this.m[i][i]);
                t = t.multiply(-1L);
                if (t.getDividend().doubleValue() != 0) {
                    for (int c = 0; c < this.m.length; ++c) {
                        Bruch g = new Bruch();
                        g.setDividend(this.m[i][c].getDividend());
                        g.setDivisor(this.m[i][c].getDivisor());
                        this.m[p][c] = this.m[p][c].add(g.multiply(t));
                        g.setDividend(this.n[i][c].getDividend());
                        g.setDivisor(this.n[i][c].getDivisor());
                        this.n[p][c] = this.n[p][c].add(g.multiply(t));
                    }
                }
            }
        }
        // erneutes auf 1 setzen der Pivot-elemente
        for (int i = 0; i < this.m.length; ++i) {
            if (this.m[i][i].toDouble() != 1.0D) {
                Bruch g = new Bruch();
                g.setDividend(this.m[i][i].getDividend());
                g.setDivisor(this.m[i][i].getDivisor());
                for (int c = 0; c < this.m.length; ++c) {
                    this.m[i][c].divide(g);
                    this.n[i][c].divide(g);
                }
            }
        }

        // zeilen miteinander subtrahieren um 0 ueber den pivot-elementen zu erschaffen
        for (int i = this.m.length - 1; i > -1; --i) {
            for (int p = i - 1; p > -1; --p) {
                Bruch t = new Bruch();
                t.setDividend(this.m[p][i].getDividend());
                t.setDivisor(this.m[p][i].getDivisor());
                t.multiply(-1L);

                for (int c = this.m.length - 1; c > -1; --c) {
                    Bruch g = new Bruch();
                    g.setDividend(this.m[i][c].getDividend());
                    g.setDivisor(this.m[i][c].getDivisor());
                    this.m[p][c] = this.m[p][c].add(g.multiply(t));
                    g.setDividend(this.n[i][c].getDividend());
                    g.setDivisor(this.n[i][c].getDivisor());
                    this.n[p][c] = this.n[p][c].add(g.multiply(t));
                }
            }
        }
        // check ob Einheitsmatrix entstanden
        for (int i = 0; i < this.m.length; ++i) {
            for (int p = 0; p < this.m.length; ++p) {
                if (i == p) {
                    if (this.m[i][p].toDouble() != 1.0D) {
                        throw new ArithmeticException();
                    }
                } else if (this.m[i][p].toDouble() != 0.0D) {
                    throw new ArithmeticException();
                }
            }
        }

    }

    public void readFile() throws IOException, InvalidFileContentException {
        BufferedReader textFile = new BufferedReader(new FileReader(this.file));
        int t = 0;
        String line = textFile.readLine();
        if (line == null) {
            textFile.close();
            throw new InvalidFileContentException();
        } else {
            String[] words = line.split(" ");
            int g = words.length;
            this.m = new Bruch[words.length][words.length];

            int i;
            do {
                words = line.split(" ");
                if (words.length != g || words.length == t) {
                    textFile.close();
                    throw new InvalidFileContentException();
                }

                for (i = 0; i < words.length; ++i) {
                    this.m[t][i] = new Bruch(Double.parseDouble(words[i]));
                }

                ++t;
            } while ((line = textFile.readLine()) != null);

            if (t != 0 && g == t) {
                this.u = new Bruch[this.m.length][this.m.length];

                for (i = 0; i < this.u.length; ++i) {
                    for (int p = 0; p < this.u.length; ++p) {
                        this.u[p][i] = new Bruch();
                        this.u[p][i].setDividend(this.m[p][i].getDividend());
                        this.u[p][i].setDivisor(this.m[p][i].getDivisor());
                    }
                }

                textFile.close();
            } else {
                textFile.close();
                throw new InvalidFileContentException();
            }
        }
    }
}
