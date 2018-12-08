
/*
 * Uebung 7: Hamming Code
 * Version 1.0
 * Gruppenmitglieder: Adrian Schuh, Dominik Benning, Christopher Huebner *
 */

import java.util.Observable;
import java.util.Random;

public class ZDModel extends Observable {
    private String klartext;
    private String linecode;
    private String code;
    private String decode;

    public String getKlartext() {
        return klartext;
    }

    public void setKlartext(String klartext) {
        this.klartext = klartext;
    }

    public String getLinecode() {
        return linecode;
    }

    public void setLinecode(String linecode) {
        this.linecode = linecode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDecode() {
        return decode;
    }

    public void setDecode(String decode) {
        this.decode = decode;
    }

    public ZDModel() {
    }

    public void encode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < klartext.length(); i++) {
            String p = Integer.toBinaryString((int) klartext.charAt(i));
            if(p.length()>8) {
                throw new NumberFormatException();
            }
            while (p.length() % 8 != 0) {
                sb.append("0");
                p = p + "0";
            }
            sb.append(Integer.toBinaryString((int) klartext.charAt(i)));
        }
        while ((sb.length() % 16) != 0) {
            sb.append("0");
        }
        int[][] hamming = new int[sb.length() / 16][21];
        int x = 0;
        for (int p = 0; p < hamming.length; p++) {
            int t = 1;
            for (int i = 0; i < hamming[p].length; i++) {
                if (i != t - 1) {
                    hamming[p][i] = (int) sb.toString().charAt(x) - '0';
                    x++;
                } else {
                    t *= 2;
                }
            }
            t = 1;
        }
        for (int p = 0; p < hamming.length; p++) {
            int t = 1;
            x = 0;
            for (int i = 0; i < hamming[p].length; i++) {
                if (i == t - 1) {
                    int g = t;
                    boolean act = false;
                    for (int z = g - 1; z < hamming[p].length; z++) {
                        if ((z + 1) % g == 0) {
                            act = !act;
                        }
                        if (act) {
                            hamming[p][i] = (hamming[p][i] + hamming[p][z]) % 2;
                        }
                    }
                    x++;
                    t *= 2;
                }
            }
        }
        StringBuilder tb = new StringBuilder();
        for (int p = 0; p < hamming.length; p++) {
            for (int i = 0; i < hamming[p].length; i++) {
                tb.append(hamming[p][i]);
            }
        }
        code = tb.toString();
    }


    public void flip() {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(code.length());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<code.length();i++) {
            if(i!=randomInt) {
                sb.append(code.charAt(i));
            }else {
                sb.append(((code.charAt(i)-'0')+1)%2);
            }
        }
        linecode=sb.toString();
    }

    public void decode() {
        int[][] hamming = new int[linecode.length() / 21][21];
        int x = 0;
        for (int p = 0; p < hamming.length; p++) {
            for (int i = 0; i < hamming[p].length; i++) {
                hamming[p][i] = (int) linecode.toString().charAt(x) - '0';
                x++;
            }
        }
        for (int p = 0; p < hamming.length; p++) {
            int flippedbit=0;
            int t = 1;
            for (int i = 0; i < 21; i++) {
                if (i == t - 1) {
                    int g = t;
                    int q=0;
                    boolean act = false;
                    for (int z = g - 1; z < hamming[p].length; z++) {
                        if ((z + 1) % g == 0) {
                            act = !act;
                        }
                        if (act) {
                            if(z!=g-1) {
                                q = (q + hamming[p][z]) % 2;
                            }
                        }
                    }
                    if(hamming[p][i]!=q) {
                        flippedbit+=(i+1);
                    }
                    t *= 2;
                }
            }
            if(flippedbit!=0) {
                hamming[p][flippedbit-1]=(hamming[p][flippedbit-1]+1)%2;
            }
        }
        StringBuilder sb = new StringBuilder();
        int[]letters=new int[hamming.length*2];
        int o=0;
        x=0;
        for (int p = 0; p < hamming.length; p++) {
            int t = 1;
            for (int i = 0; i < hamming[p].length; i++) {
                if (i != t - 1) {
                    letters[o]=letters[o]*2+hamming[p][i];
                    x++;
                    if(x%8==0) o++;
                } else {
                    t *= 2;
                }
            }
            t = 1;
        }
        for(int p=0;p<letters.length;p++) {
            sb.append((char)letters[p]);
        }
        decode=sb.toString();
    }
}
