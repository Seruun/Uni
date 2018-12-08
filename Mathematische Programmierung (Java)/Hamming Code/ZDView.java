/*�bung 7: Hamming Code
 * Version 1.0
 * Gruppenmitglieder: Adrian Schuh, Dominik Benning, Christopher H�bner
 *
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class ZDView extends JPanel implements ActionListener, Observer {
    private static final long serialVersionUID = 1L;
    ZDModel model;
    ZDFrame frame;
    JButton codieren = new JButton("Codieren");
    JButton stoeren = new JButton("Flip");
    JButton decodieren = new JButton("Decodieren");
    JFileChooser chooser = new JFileChooser();
    JTextArea klartext = new JTextArea("", 1, 25);
    JTextArea codiert = new JTextArea("", 1, 25);
    JTextArea empfang = new JTextArea("", 1, 25);
    JTextArea decodiert = new JTextArea("", 1, 25);
    Color bg;

    public ZDView(ZDModel model, ZDFrame frame) {
        this.bg = Color.white;
        this.model = model;
        this.frame = frame;
        model.addObserver(this);
        this.klartext.setBackground(Color.lightGray);
        this.klartext.setLineWrap(true);
        this.codiert.setBackground(Color.lightGray);
        this.codiert.setLineWrap(true);
        this.empfang.setBackground(Color.lightGray);
        this.empfang.setLineWrap(true);
        this.decodiert.setBackground(Color.lightGray);
        this.decodiert.setLineWrap(true);
        this.setBackground(this.bg);
        executeWindow();

    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == this.codieren) {
                startEncode();
            }

            if (e.getSource() == this.stoeren) {
                flip();
            }

            if (e.getSource() == this.decodieren) {
                startDecode();
            }
        } catch (NumberFormatException var6) {
            JOptionPane.showMessageDialog(this, "Ungueltige Eingabe!", "Eingabefehler", 0);
        }

    }

    public void update(Observable arg0, Object arg1) {
    }

    public Component executeWindow() {
        Box box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
        JLabel label0 = new JLabel("  Klartext:");
        label0.setAlignmentY(0.0F);
        box.add(label0);
        box.add(Box.createVerticalStrut(5));
        this.klartext.setAlignmentX(0.0F);
        box.add(this.klartext);
        JLabel label1 = new JLabel("  Codiert:");
        label1.setAlignmentY(0.0F);
        box.add(label1);
        box.add(Box.createVerticalStrut(5));
        this.codiert.setAlignmentX(0.0f);
        box.add(this.codiert);
        box.add(Box.createVerticalStrut(20));
        this.codieren.addActionListener(this);
        this.codieren.setAlignmentX(0.0F);
        box.add(this.codieren);
        box.add(Box.createVerticalStrut(20));
        this.stoeren.addActionListener(this);
        this.stoeren.setAlignmentX(0.0F);
        box.add(this.stoeren);
        box.add(Box.createVerticalStrut(20));
        JLabel label2 = new JLabel("  Empfangen:");
        label2.setAlignmentY(0.0F);
        box.add(label2);
        box.add(Box.createVerticalStrut(5));
        this.empfang.setAlignmentX(0.0F);
        box.add(this.empfang);
        JLabel label3 = new JLabel("  Decodiert:");
        label3.setAlignmentY(0.0F);
        box.add(label3);
        box.add(Box.createVerticalStrut(5));
        this.decodiert.setAlignmentX(0.0f);
        box.add(this.decodiert);
        box.add(Box.createVerticalStrut(20));
        this.decodieren.addActionListener(this);
        this.decodieren.setAlignmentX(0.0F);
        box.add(this.decodieren);
        this.add(box);
        return box;
    }

    public void startEncode() {
        model.setKlartext(klartext.getText());
        model.encode();
        codiert.setText(model.getCode());
    }

    public void flip() {
        model.flip();
        empfang.setText(model.getLinecode());
    }
    public void startDecode() {
        model.setLinecode(empfang.getText());
        model.decode();
        decodiert.setText(model.getDecode());
    }
}
