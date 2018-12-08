/*�bung 7: Gauss Algorithmus
 * Version 1.2
 * Gruppenmitglieder: Adrian Schuh, Dominik Benning, Christopher H�bner
 *
 */
import Exception.InvalidFileContentException;
import Exception.InvalidFileTypeException;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ZDView extends JPanel implements ActionListener, Observer {
    private static final long serialVersionUID = 1L;
    ZDModel model;
    ZDFrame frame;
    JButton execute = new JButton("Ausfuehren");
    JButton restart = new JButton("Restart");
    JFileChooser chooser = new JFileChooser();
    JTextArea a = new JTextArea("", 1, 25);
    JTextArea b = new JTextArea("", 1, 25);
    Color bg;

    public ZDView(ZDModel model, ZDFrame frame, int a) {
        this.bg = Color.white;
        this.model = model;
        this.frame = frame;
        model.addObserver(this);
        this.a.setBackground(Color.lightGray);
        this.b.setBackground(Color.lightGray);
        this.setBackground(this.bg);
        switch(a) {
            case 1:
                this.executeWindow();
                break;
            case 2:
                this.outputWindow();
        }

    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == this.execute) {
                this.readInput();
                this.frame.windowChange(2);
            }

            if (e.getSource() == this.restart) {
                this.frame.windowChange(1);
            }
        } catch (InvalidFileTypeException var3) {
            JOptionPane.showMessageDialog(this, "Ungueltige Dateiauswahl!", "Eingabefehler", 0);
        } catch (InvalidFileContentException var4) {
            JOptionPane.showMessageDialog(this, "Ungueltiger DateiInhalt!", "Eingabefehler", 0);
        } catch (IOException var5) {
            JOptionPane.showMessageDialog(this, "Bitte Datei auswaehlen!", "Eingabefehler", 0);
        } catch (NumberFormatException var6) {
            JOptionPane.showMessageDialog(this, "Ungueltige Eingabe!", "Eingabefehler", 0);
        } catch (ArithmeticException var7) {
            JOptionPane.showMessageDialog(this, "Matrix nicht Invertierbar!", "Eingabefehler", 0);
        }

    }

    public void update(Observable arg0, Object arg1) {
    }

    public Component executeWindow() {
        Box box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
        JLabel label0 = new JLabel("  Textdatei:");
        label0.setAlignmentY(0.0F);
        box.add(label0);
        this.chooser.setAlignmentX(0.02F);
        FileFilter filter = new FileNameExtensionFilter("txt", new String[]{"txt"});
        this.chooser.addChoosableFileFilter(filter);
        box.add(this.chooser);
        box.add(Box.createVerticalStrut(20));
        this.execute.addActionListener(this);
        this.execute.setAlignmentX(0.0F);
        box.add(this.execute);
        this.add(box);
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
        this.a.setText(this.model.getUString());
        box.add(Box.createVerticalStrut(5));
        box.add(new JLabel("Da hast du!"));
        box.add(Box.createVerticalStrut(10));
        this.b.setAlignmentX(0.0F);
        box.add(this.b);
        this.b.setText(this.model.getNString());
        box.add(Box.createVerticalStrut(5));
        this.restart.addActionListener(this);
        this.restart.setAlignmentX(0.0F);
        box.add(this.restart);
        this.add(box);
        return box;
    }

    public void readInput() throws InvalidFileTypeException, IOException, InvalidFileContentException {
        if (this.chooser.getSelectedFile() == null) {
            throw new IOException();
        } else if (!this.chooser.getSelectedFile().getName().endsWith(".txt")) {
            throw new InvalidFileTypeException();
        } else {
            this.model.setFile(this.chooser.getSelectedFile());
            this.model.execute();
        }
    }
}
