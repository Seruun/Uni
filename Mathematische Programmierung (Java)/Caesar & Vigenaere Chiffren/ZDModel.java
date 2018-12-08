
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Observable;

/*�bung 7: Gau�-Algorithmus
 * Version 1.2
 * Gruppenmitglieder: Adrian Schuh, Dominik Benning, Christopher H�bner
 *
 */

public class ZDModel extends Observable {
    private int c; //Caesar
    private String text,v,result; //Text=texteingabe v=Vigen�re-chiffre result=ausgabetext
    private boolean encr,caesar,datei;
    private File file;
    //Getter und Setter
    public ZDModel(String a, int b) {
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isEncr() {
        return encr;
    }

    public void setEncr(boolean encr) {
        this.encr = encr;
    }

    public boolean isCaesar() {
        return caesar;
    }

    public void setCaesar(boolean caesar) {
        this.caesar = caesar;
    }

    public boolean isDatei() {
        return datei;
    }

    public void setDatei(boolean datei) {
        this.datei = datei;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public ZDModel() {
    }
    //Konstrutoren

    public void execute() throws IOException {
        if(datei) {
            text=readFile();
        }
        text=normalizeText(text);
        if(!caesar) {
            v=normalizeVigenere(v);
        }
        crypt();
    }

    public String readFile() throws IOException {
        String line;
        StringBuilder sb = new StringBuilder();
        BufferedReader textFile = new BufferedReader(new FileReader(file));
        while((line = textFile.readLine()) != null){
            sb.append(line);
        }
        textFile.close();
        return sb.toString();
    }

    public String normalizeText(String a) {
        a=a.toLowerCase();
        StringBuilder sb = new StringBuilder();
        a=a.replaceAll("�", "ss");
        a=a.replaceAll("�", "ue");
        a=a.replaceAll("�", "oe");
        a=a.replaceAll("�", "ae");
        a=a.replaceAll(" ", "");
        for(int i = 0; i < a.length(); ++i) {
            if ((a.charAt(i) >= 97 && a.charAt(i) <= 122)) {
                sb.append(a.charAt(i));
            }
        }
        if(sb.toString().equals("")){
            throw new InputMismatchException();
        }
        return sb.toString();
    }

    public String normalizeVigenere(String a) {
        a=a.toLowerCase();
        a=a.replaceAll("�", "ss");
        a=a.replaceAll("�", "ue");
        a=a.replaceAll("�", "oe");
        a=a.replaceAll("�", "ae");
        a=a.replaceAll(" ", "");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < a.length(); ++i) {
            if ((a.charAt(i) >= 97 && a.charAt(i) <= 122)) {
                sb.append(a.charAt(i));
            }
        }
        if(sb.toString().equals("")){
            throw new InputMismatchException();
        }
        a=sb.toString();
        a=a.toUpperCase();
        return a;
    }

    public void crypt() {
        StringBuilder sb = new StringBuilder();
        int p=0;
        for(int i = 0; i < text.length(); ++i) {
            if(caesar) {
                char t;
                if(encr) {
                    t = (char) (text.charAt(i) + c);
                }else {
                    t = (char) (text.charAt(i) - c);
                }
                while (t>122) {
                    t-=26;
                }while (t<97) {
                    t+=26;
                }
                sb.append(t);

            }else {
                char t;
                if(encr) {
                    t = (char) (text.charAt(i) + (v.charAt(p)-65));
                }else {
                    t = (char) (text.charAt(i) - (v.charAt(p)-65));
                }
                while (t>122) {
                    t-=26;
                }while (t<97) {
                    t+=26;
                }
                sb.append(t);
                p++;
                if(p==v.length()) {
                    p=0;
                }
            }
        }
        result=sb.toString().toUpperCase();
    }

}
