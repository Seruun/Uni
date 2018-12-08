import java.text.DecimalFormat;
import java.util.EmptyStackException;
import java.util.Observable;

public class ZDModel extends Observable {
    int n; //Wurzelgr��e
    double eingabe;
    double ausgabe;
    double d;//Genauigkeit
    //Getter und Setter
    public void setA(double a) {
        this.eingabe = a;
    }

    public double getA() {
        return this.eingabe;
    }

    public void setB(int b) {
        this.n = b;
    }

    public int getB() {
        return this.n;
    }

    public void setX(int x) {
        this.d = (double)x;
    }

    public double getX() {
        return this.d;
    }

    public void setZiel(double ziel) {
        this.ausgabe = ziel;
    }

    public double getZiel() {
        return this.ausgabe;
    }
    //Konstrutoren
    public ZDModel(double a, int b, int c) {
        this.eingabe = a;
        this.n = b;
        this.d = (double)c;
    }

    public ZDModel() {
        this.eingabe = 0.0D;
        this.n = 0;
        this.d = 0.0D;
    }

    public void umrechnGedoens() {
        //vermerkt zu gro�e Genauigkeit. Setzt diese auf 10 und schmei�t nach Berechnung Exception.
        boolean fail = false;
        //vermerkt zu gro�e Wurzel. Setzt diese auf 150 und schmei�t nach Berechnung Exception.
        boolean kaputt = false;
        if(eingabe>= Integer.MAX_VALUE) {
            //Eingabe gr��er als Max Int
            throw new NegativeArraySizeException();
        }

        if (this.d > 10.0D) {
            this.d = 10.0D;
            fail = true;
        }

        if (this.n > 150) {
            this.n = 150;
            kaputt = true;
        }

        if (this.eingabe >= 0.0D && this.n >= 0 && this.d >= 0.0D) {
            if (this.n < 2) {
                throw new ArithmeticException();
            } else if (this.d == 0.0D) {
                throw new ClassCastException();
            } else {
                double x0 = 1.0D;

                double x1;
                //Formel solange bis min. 10 Nachkommastellen zum vorherigen Ergebniss gleich
                for(boolean accurate = false; !accurate; x0 = x1) {
                    x1 = 1.0D / (double)this.n * ((double)(this.n - 1) * x0 + this.eingabe / Math.pow(x0, (double)(this.n - 1)));
                    accurate = this.accurate(x0, x1);
                }

                this.setZiel(x0);
                this.setChanged();
                this.notifyObservers();
                if (fail) {
                    throw new NullPointerException();	//siehe oben
                } else if (kaputt) {
                    throw new ArrayStoreException();	//siehe oben
                }
            }
        } else {
            throw new EmptyStackException();
        }
    }
    //pr�ft auf 10 Nachkommastellen genau
    private boolean accurate(double x0, double x1) {
        return Math.abs(x1 - x0) < 1.0D / Math.pow(10.0D, 10.0D);
    }

    public String scale(double x) {
        StringBuilder sb = new StringBuilder();
        sb.append("#.");

        for(int i = 0; (double)i < this.d; ++i) {
            sb.append("0");
        }

        String p = (new DecimalFormat(sb.toString())).format(x);
        return p;
    }
}