import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Exception.BenutzernameBereitsVergebenException;
import Exception.FehlgeschlageneBerechnungException;
import Exception.KeinBenutzernameException;
import Exception.UngültigerLoginnemException;

/*�bung 4: Fiat Shamir Protokoll
 * Version 1.1
 * Gruppenmitglieder: Adrian Schuh, Dominik Benning, Christopher H�bner
 *
 */

public class ZDView extends JPanel implements ActionListener, Observer {
    private static final long serialVersionUID = 1L;
    ZDModel model;
    ZDFrame frame;
    private boolean reset; // Wenn Fehler entsteht =true
    JButton login = new JButton("Login"); // �ffnet Login Fenster
    JButton submit = new JButton("Submit"); // Im Login Fenster
    JButton submitr = new JButton("Submit"); // Im Login Fenster
    JButton submit2 = new JButton("Submit"); // im Kontrolllogin Fenster
    JButton register = new JButton("Registrieren"); // �ffnet registrier Fenster
    JButton registera = new JButton("Registrieren"); // Registriert Nutzer
    JTextField a = new JTextField("", 10);
    JTextField b = new JTextField("", 10);

    public ZDView(ZDModel model, ZDFrame frame, int a) {
        this.model = model;
        this.frame = frame;
        reset = false;
        model.addObserver(this);
        this.setBackground(Color.cyan);
        // Auswahl welches neue Fenster gestartet wird
        switch (a) {
            case 1:
                this.startWindow();
                break;
            case 2:
                this.registerWindow();
                break;
            case 3:
                this.rWindow();
                break;
            case 4:
                this.rsWindow();
                break;
            case 5:
                this.loginStartWindow();
                break;
            case 6:
                this.newRWindow();
                break;
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.login) {
            this.frame.windowChange(5);
        }

        if (e.getSource() == this.register) {
            a.setText(null);
            this.frame.windowChange(2);
        }

        if (e.getSource() == this.registera) {
            this.register();
            this.frame.windowChange(1);
        }

        if (e.getSource() == this.submit) {
            readInputStart();
            if (reset) {
                reset = false;
                this.frame.windowChange(1);
            } else {
                if (this.model.heads == true) {
                    info();
                    this.frame.windowChange(3);
                } else if (this.model.heads == false) {
                    info();
                    this.frame.windowChange(4);
                }
            }

        }
        // Wenn die ausge�hlte Durchlauf Zahl erfolgreich erreicht wird, Ausgabe Erfolg
        // und r�ckkehr zum Anfangsfenster
        if (e.getSource() == this.submit2 && this.model.durchlauf == this.model.getMaxdurchlauf()) {
            readInput();
            if (!reset) {
                JOptionPane.showMessageDialog(this, "Erfolgreich eingeloggt", "Bestanden!", 1);
                this.frame.windowChange(1);
            } else {
                this.frame.windowChange(6);
            }

        } else if (e.getSource() == this.submit2 && this.model.durchlauf < this.model.getMaxdurchlauf()) {
            readInput();
            if (reset) {
                reset = false;
                this.frame.windowChange(1);
            } else {
                JOptionPane.showMessageDialog(this, "Bitte neues R� mod N eingeben",
                        "Durchlauf:" + this.model.getDurchlauf() + "/" + this.model.getMaxdurchlauf(), 1);
                this.frame.windowChange(6);
            }
        }
        if (e.getSource() == this.submitr) {
            readR();
            if (reset) {
                reset = false;
                this.frame.windowChange(1);
            } else {
                if (this.model.heads == true) {
                    info();
                    this.frame.windowChange(3);
                } else if (this.model.heads == false) {
                    info();
                    this.frame.windowChange(4);
                }
            }
        }

    }

    private void info() {
        if (this.model.heads == true) {
            JOptionPane.showMessageDialog(this, "Bitte gib mir R",
                    "Durchlauf:" + this.model.getDurchlauf() + "/" + this.model.getMaxdurchlauf(), 1);
        } else if (this.model.heads == false) {
            JOptionPane.showMessageDialog(this, "Bitte gib mir RS mod N",
                    "Durchlauf:" + this.model.getDurchlauf() + "/" + this.model.getMaxdurchlauf(), 1);
        }
    }

    private void readR() {
        try {
            this.model.changeR(Integer.valueOf(this.b.getText()));
        } catch (NumberFormatException var2) {
            reset = true;
            JOptionPane.showMessageDialog(this, "Falsches Zahlenformat", "Eingabefehler", 0);
        } catch (InputMismatchException var8) {
            reset = true;
            JOptionPane.showMessageDialog(this, "Ung�ltige Zahleneingabe", "Eingabefehler", 0);
        }
    }

    private void readInput() {
        try {
            this.model.setB(Integer.valueOf(this.b.getText()));
            this.model.login();
        } catch (NumberFormatException var2) {
            JOptionPane.showMessageDialog(this, "Falsches Zahlenformat", "Eingabefehler", 0);
            reset = true;
        } catch (FehlgeschlageneBerechnungException var6) {
            reset = true;
            JOptionPane.showMessageDialog(this, "Failed", "Eingabefehler", 0);
        } catch (InputMismatchException var8) {
            reset = true;
            JOptionPane.showMessageDialog(this, "Ung�ltige Zahleneingabe", "Eingabefehler", 0);
        }
    }

    private void readInputStart() {
        try {
            this.model.setA(this.a.getText());
            this.model.setB(Integer.valueOf(this.b.getText()));
            this.model.loginstart();
        } catch (NumberFormatException var2) {
            reset = true;
            JOptionPane.showMessageDialog(this, "Falsches Zahlenformat", "Eingabefehler", 0);
        } catch (UngültigerLoginnemException var7) {
            reset = true;
            JOptionPane.showMessageDialog(this, "Ung�ltiger Loginname", "Eingabefehler", 0);
        } catch (InputMismatchException var8) {
            reset = true;
            JOptionPane.showMessageDialog(this, "Ung�ltige Zahleneingabe", "Eingabefehler", 0);
        }

    }

    private void register() {
        try {
            ZDPerson user = new ZDPerson();
            user.setName(this.a.getText());
            user.setV(Integer.valueOf(this.b.getText()));
            this.model.addPerson(user);
            JOptionPane.showMessageDialog(this, "Erfolgreich Registriert!", "Erfolg!", 3);
        } catch (NumberFormatException var2) {
            reset = true;
            JOptionPane.showMessageDialog(this, "Falsches Zahlenformat", "Eingabefehler", 0);
        } catch (InputMismatchException var8) {
            reset = true;
            JOptionPane.showMessageDialog(this, "Ung�ltige Zahleneingabe", "Eingabefehler", 0);
        } catch (BenutzernameBereitsVergebenException var3) {
            reset = true;
            JOptionPane.showMessageDialog(this, "Benutzername bereits vergeben", "Eingabefehler", 0);
        } catch (KeinBenutzernameException var4) {
            reset = true;
            JOptionPane.showMessageDialog(this, "Benutzername muss vorhanden sein!", "Eingabefehler", 0);
        }
    }

    public void update(Observable arg0, Object arg1) {
    }

    // Alle Fenster
    public Component startWindow() {
        Box box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
        box.add(Box.createVerticalStrut(15));
        this.login.addActionListener(this);
        this.login.setAlignmentX(0.0F);
        box.add(this.login);
        this.add(box);
        box.add(Box.createVerticalStrut(15));
        this.register.addActionListener(this);
        this.register.setAlignmentX(0.0F);
        box.add(this.register);
        this.add(box);
        return box;
    }

    public Component registerWindow() {
        Box box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
        JLabel label0 = new JLabel("  Registrierung");
        label0.setAlignmentY(0.0F);
        box.add(label0);
        box.add(Box.createVerticalStrut(5));
        JLabel label1 = new JLabel("  Benutzername");
        label1.setAlignmentY(0.0F);
        box.add(label1);
        box.add(Box.createVerticalStrut(5));
        this.a.setAlignmentX(0.0F);
        box.add(this.a);
        box.add(Box.createVerticalStrut(20));
        box.add(new JLabel("  S� mod 101"));
        box.add(Box.createVerticalStrut(5));
        this.b.setAlignmentX(0.0F);
        box.add(this.b);
        box.add(Box.createVerticalStrut(15));
        this.registera.addActionListener(this);
        this.registera.setAlignmentX(0.0F);
        box.add(this.registera);
        this.add(box);
        return box;
    }

    public Component rWindow() {
        Box box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
        box.add(Box.createVerticalStrut(20));
        box.add(new JLabel("R"));
        box.add(Box.createVerticalStrut(10));
        this.b.setAlignmentX(0.0F);
        box.add(this.b);
        box.add(Box.createVerticalStrut(5));
        this.submit2.addActionListener(this);
        this.submit2.setAlignmentX(0.0F);
        box.add(this.submit2);
        this.add(box);
        return box;
    }

    public Component rsWindow() {
        Box box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
        box.add(Box.createVerticalStrut(20));
        box.add(new JLabel("  RS mod 101"));
        box.add(Box.createVerticalStrut(10));
        this.b.setAlignmentX(0.0F);
        box.add(this.b);
        box.add(Box.createVerticalStrut(5));
        this.submit2.addActionListener(this);
        this.submit2.setAlignmentX(0.0F);
        box.add(this.submit2);
        this.add(box);
        return box;
    }

    public Component loginStartWindow() {
        Box box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
        JLabel label0 = new JLabel("  Login");
        label0.setAlignmentY(0.0F);
        box.add(label0);
        box.add(Box.createVerticalStrut(5));
        JLabel label1 = new JLabel("  Benutzername");
        label1.setAlignmentY(0.0F);
        box.add(label1);
        box.add(Box.createVerticalStrut(5));
        this.a.setAlignmentX(0.0F);
        box.add(this.a);
        box.add(Box.createVerticalStrut(20));
        box.add(new JLabel("  R� mod 101"));
        box.add(Box.createVerticalStrut(5));
        this.b.setAlignmentX(0.0F);
        box.add(Box.createVerticalStrut(5));
        box.add(this.b);
        box.add(Box.createVerticalStrut(15));
        this.submit.addActionListener(this);
        this.submit.setAlignmentX(0.0F);
        box.add(this.submit);
        this.add(box);
        return box;
    }

    public Component newRWindow() {
        Box box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
        box.add(Box.createVerticalStrut(20));
        box.add(new JLabel("  R� mod 101"));
        box.add(Box.createVerticalStrut(5));
        this.b.setAlignmentX(0.0F);
        box.add(Box.createVerticalStrut(5));
        box.add(this.b);
        box.add(Box.createVerticalStrut(15));
        this.submitr.addActionListener(this);
        this.submitr.setAlignmentX(0.0F);
        box.add(this.submitr);
        this.add(box);
        return box;
    }
}
