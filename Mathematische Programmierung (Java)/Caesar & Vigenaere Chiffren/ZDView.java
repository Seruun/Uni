
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import Exception.InvalidFileTypeException;

/*�bung 7: Gau�-Algorithmus
 * Version 1.2
 * Gruppenmitglieder: Adrian Schuh, Dominik Benning, Christopher H�bner
 *
 */
public class ZDView extends JPanel implements ActionListener, Observer {
    private static final long serialVersionUID = 1L;
    ZDModel model;
    ZDFrame frame;
    JButton start = new JButton("Start"); // �ffnet Eingabe Fenster
    JButton execute = new JButton("Ausf�hren");
    JButton restart = new JButton("Restart"); // im Ausgabe
    JButton cleart = new JButton("Clear"); 	//entleeren des Textes
    JButton clearc = new JButton("Clear");	//entleeren der Chiffre
    JRadioButton Jencr = new JRadioButton("Encrypt");
    JRadioButton Jdecr = new JRadioButton("Decrypt");
    JRadioButton Jcaesar = new JRadioButton("Caesar-Chiffre");
    JRadioButton Jving = new JRadioButton("Vigen�re-Chiffre");
    JRadioButton Jtext = new JRadioButton("Handeingabe");
    JRadioButton Jdatei = new JRadioButton("Textdatei");
    JFileChooser chooser = new JFileChooser();
    JTextArea a = new JTextArea("", 1,25);
    JTextArea b = new JTextArea("", 1,25);
    Color bg = Color.white;

    public ZDView(ZDModel model, ZDFrame frame, int a) {

        this.model = model;
        this.frame = frame;
        // reset = false;
        model.addObserver(this);
        this.a.setBackground(Color.lightGray);
        this.a.setLineWrap(true);
        this.b.setBackground(Color.lightGray);
        this.b.setLineWrap(true);
        this.setBackground(bg);
        // Auswahl welches neue Fenster gestartet wird
        switch (a) {
            case 1:
                this.startWindow();
                break;
            case 2:
                this.executeWindow();
                break;
            case 3:
                this.outputWindow();
                break;
        }
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == this.cleart) {
                this.a.setText("");
            }
            if (e.getSource() == this.clearc) {
                this.b.setText("");
            }
            if (e.getSource() == this.start) {
                model.setEncr(Jencr.isSelected());
                model.setCaesar(Jcaesar.isSelected());
                model.setDatei(Jdatei.isSelected());
                this.frame.windowChange(2);
            }

            if (e.getSource() == this.execute) {
                readInput();
                this.frame.windowChange(3);
            }

            if (e.getSource() == this.restart) {
                this.frame.windowChange(1);
            }
        } catch (InvalidFileTypeException ifte) {
            JOptionPane.showMessageDialog(this, "Ung�ltige Dateiauswahl!", "Eingabefehler", 0);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Ung�ltige Eingabe!", "Eingabefehler", 0);
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(this, "Bitte Datei ausw�hlen!", "Eingabefehler", 0);
        } catch (InputMismatchException ime) {
            JOptionPane.showMessageDialog(this, "Ung�ltige Eingabe!", "Eingabefehler", 0);
        }
    }

    public void update(Observable arg0, Object arg1) {
    }

    // Alle Fenster
    public Component startWindow() {
        Box box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 80));
        box.add(Box.createVerticalStrut(15));
        box.add(this.Jencr);
        Jencr.setBackground(bg);
        box.add(this.Jdecr);
        Jdecr.setBackground(bg);
        box.add(Box.createVerticalStrut(15));
        box.add(this.Jcaesar);
        Jcaesar.setBackground(bg);
        box.add(this.Jving);
        Jving.setBackground(bg);
        box.add(Box.createVerticalStrut(15));
        box.add(this.Jtext);
        Jtext.setBackground(bg);
        box.add(this.Jdatei);
        Jdatei.setBackground(bg);
        ButtonGroup groupa = new ButtonGroup();
        groupa.add(Jencr);
        groupa.add(Jdecr);
        ButtonGroup groupb = new ButtonGroup();
        groupb.add(Jcaesar);
        groupb.add(Jving);
        ButtonGroup groupc = new ButtonGroup();
        groupc.add(Jtext);
        groupc.add(Jdatei);
        Jencr.setSelected(true);
        Jcaesar.setSelected(true);
        Jtext.setSelected(true);
        box.add(Box.createVerticalStrut(15));
        this.start.addActionListener(this);
        this.start.setAlignmentX(0.0F);
        box.add(this.start);
        this.add(box);
        return box;
    }

    public Component executeWindow() {
        Box box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
        if (model.isDatei()) {
            JLabel label0 = new JLabel("  Textdatei:");
            label0.setAlignmentY(0.0F);
            box.add(label0);
            chooser.setAlignmentX(0.02f);
            FileFilter filter = new FileNameExtensionFilter("txt", "txt");
            chooser.addChoosableFileFilter(filter);
            box.add(chooser);
        } else {
            box.add(Box.createVerticalStrut(5));
            box.add(new JLabel("  Text:"));
            box.add(Box.createVerticalStrut(5));
            this.a.setAlignmentX(0.0F);
            box.add(this.a);
            this.cleart.addActionListener(this);
            box.add(cleart);
        }
        box.add(Box.createVerticalStrut(20));
        if (model.isCaesar()) {
            box.add(new JLabel("  Caesar-Key:"));
        } else {
            box.add(new JLabel("  Vigen�re-Key:"));
        }
        box.add(Box.createVerticalStrut(5));
        this.b.setAlignmentX(0.0F);
        box.add(this.b);
        this.clearc.addActionListener(this);
        box.add(clearc);
        box.add(Box.createVerticalStrut(15));
        this.execute.addActionListener(this);
        this.execute.setAlignmentX(0.0F);
        box.add(this.execute);
        this.add(box);
        box.add(Box.createVerticalStrut(5));
        this.restart.addActionListener(this);
        this.restart.setAlignmentX(0.0F);
        box.add(this.restart);
        if(model.isEncr()) {
            frame.setTitle("Encode");
        }else {
            frame.setTitle("Decode");
        }
        return box;
    }

    public Component outputWindow() {
        Box box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));

        box.add(Box.createVerticalStrut(20));
        box.add(Box.createVerticalStrut(5));
        box.add(new JLabel("  Text:"));
        box.add(Box.createVerticalStrut(5));
        this.a.setAlignmentX(0.0F);
        box.add(this.a);
        a.setText(model.getText());
        box.add(Box.createVerticalStrut(5));
        box.add(new JLabel("Chiffrat"));
        box.add(Box.createVerticalStrut(10));
        this.b.setAlignmentX(0.0F);
        box.add(this.b);
        b.setText(model.getResult());
        box.add(Box.createVerticalStrut(5));
        this.restart.addActionListener(this);
        this.restart.setAlignmentX(0.0F);
        box.add(this.restart);
        this.add(box);
        frame.setTitle("Chiffren");
        return box;
    }

    public void readInput() throws InvalidFileTypeException, IOException {
        if (model.isDatei()) {
            if (chooser.getSelectedFile() == null) {
                throw new IOException();
            } else if (chooser.getSelectedFile().getName().endsWith(".txt")) {
                model.setFile(chooser.getSelectedFile());
            } else {
                throw new InvalidFileTypeException();
            }
        } else {
            if(a.getText().equals("")){
                throw new InputMismatchException();
            }
            this.model.setText(this.a.getText());
        }
        if (model.isCaesar()) {
            this.model.setC(Integer.valueOf(this.b.getText()));
        } else {
            if(b.getText().equals("")){
                throw new InputMismatchException();
            }
            this.model.setV(this.b.getText());
        }
        model.execute();
    }
}
