import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Random;

import Exception.BenutzernameBereitsVergebenException;
import Exception.FehlgeschlageneBerechnungException;
import Exception.KeinBenutzernameException;
import Exception.UngültigerLoginnemException;

/*�bung 4: Fiat Shamir Protokoll
 * Version 1.1
 * Gruppenmitglieder: Adrian Schuh, Dominik Benning, Christopher H�bner
 *
 */

public class ZDModel extends Observable {
    int p,x; // X=Login Zahl (r� mod 101) p = Eingabe r/rs mod 101
    String name;
    int durchlauf = 1;
    int maxdurchlauf; // zuf�llig generierte Anzahl an Durchl�ufen
    final int n=101;
    boolean heads = true;
    ArrayList<ZDPerson> persons = new ArrayList<ZDPerson>();
    ZDPerson user;
    //Getter und Setter
    public void setA(String a) {
        this.name = a;
    }

    public String getA() {
        return this.name;
    }

    public void setB(int b) {
        this.p = b;
    }

    public int getB() {
        return this.p;
    }

    public int getDurchlauf() {
        return durchlauf;
    }

    public void setDurchlauf(int durchlauf) {
        this.durchlauf = durchlauf;
    }

    public int getMaxdurchlauf() {
        return maxdurchlauf;
    }

    public void setMaxdurchlauf(int maxdurchlauf) {
        this.maxdurchlauf = maxdurchlauf;
    }

    // erneute Eingabe von R
    public void changeR(int v){
        if(v<=0||v>100) {
            throw new InputMismatchException();
        }
        x=v;
    }

    // Person hinzuf�gen
    public void addPerson(ZDPerson user) throws KeinBenutzernameException, BenutzernameBereitsVergebenException {
        if(user.getName().equals("")) {
            throw new KeinBenutzernameException();
        }
        for(int i=0; i<persons.size();i++) {
            if(persons.get(i).getName().matches(user.getName())){
                throw new BenutzernameBereitsVergebenException();
            }
        }
        if(user.getV()<=0||user.getV()>100) {
            throw new InputMismatchException();
        }
        persons.add(user);
    }

    //Konstrutoren
    public ZDModel(String a, int b) {
        this.name = a;
        this.p = b;
    }

    public ZDModel() {
        this.name = null;
        this.p = 0;
    }
    // Eingabe und �berpr�fung von Benutzername und ob 'r' im richtigen Zahlenbereich ist.
    public void loginstart() throws UngültigerLoginnemException {

        user=null;
        for(int i=0; i<persons.size();i++) {
            if(persons.get(i).getName().matches(name)){
                user=persons.get(i);
            }
        }
        if(user==null) {
            throw new UngültigerLoginnemException();
        }if(p>100) {
            throw new InputMismatchException();
        }
        x=p;
        durchlauf=1;
        toss();
        // generiert Anzahl an Durchl�ufen
        Random r = new Random();
        int z =r.nextInt(5);
        maxdurchlauf=5+z;
    }
    public void login() throws FehlgeschlageneBerechnungException {

        if (p<=0||p>100) {
            durchlauf=1;
            throw new InputMismatchException();

        }

        if(heads) {
            if(((p*p)%n)==(x%n)){
                toss();
                durchlauf++;
            }else {
                durchlauf=1;
                throw new FehlgeschlageneBerechnungException();
            }
        }else {
            if(((p*p)%n)==((x*user.getV())%n)){
                toss();
                durchlauf++;
            }else {
                durchlauf=1;
                throw new FehlgeschlageneBerechnungException();
            }
        }
    }

    // Ob r/rs als n�chstes gefordert wird
    private void toss() {

        Random r = new Random();
        int z =r.nextInt(2);
        if(z==0) {
            heads =true;
        }else if(z==1) {
            heads=false;
        }
    }

}
