import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ZDView extends JPanel implements ActionListener, Observer {
    private static final long serialVersionUID = 1L;
    ZDModel model;
    JButton compute = new JButton("Berechnen");
    JTextField a = new JTextField("", 10);
    JTextField b = new JTextField("", 10);
    JTextField c = new JTextField("", 10);
    JTextField g = new JTextField("", 15);

    public ZDView(ZDModel model) {
        this.model = model;
        model.addObserver(this);
        this.setBackground(Color.lightGray);
        Box box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
        JLabel label1 = new JLabel("  Ausgangszahl");
        label1.setAlignmentY(0.0F);
        box.add(label1);
        box.add(Box.createVerticalStrut(5));
        this.a.setAlignmentX(0.0F);
        box.add(this.a);
        box.add(Box.createVerticalStrut(20));
        box.add(new JLabel("  N-te Wurzel"));
        box.add(Box.createVerticalStrut(5));
        this.b.setAlignmentX(0.0F);
        box.add(this.b);
        box.add(Box.createVerticalStrut(10));
        box.add(new JLabel(" Genauigkeit"));
        box.add(Box.createVerticalStrut(5));
        this.c.setAlignmentX(0.0F);
        box.add(this.c);
        box.add(Box.createVerticalStrut(15));
        this.compute.addActionListener(this);
        this.compute.setAlignmentX(0.0F);
        box.add(this.compute);
        this.add(box);
        Box box2 = Box.createVerticalBox();
        box2.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        box2.add(new JLabel("  Ergebnis"));
        box2.add(Box.createVerticalStrut(5));
        this.g.setAlignmentX(0.0F);
        this.g.setEditable(false);
        box2.add(this.g);
        this.add(box2);
    }

    private void readInput() {
        try {
            this.model.setA(Double.valueOf(this.testText(this.a.getText())));
            this.model.setB(Integer.valueOf(this.b.getText()));
            this.model.setX(Integer.valueOf(this.c.getText()));
            this.model.umrechnGedoens();
        } catch (NumberFormatException var2) {
            JOptionPane.showMessageDialog(this, "Falsches Zahlenformat", "Eingabefehler", 0);
        } catch (ArithmeticException var3) {
            JOptionPane.showMessageDialog(this, "mindestens 2-te Wurzel", "Eingabefehler", 0);
        } catch (EmptyStackException var4) {
            JOptionPane.showMessageDialog(this, "Negative Werte nicht zul�ssig", "Eingabefehler", 0);
        } catch (NullPointerException var5) {
            JOptionPane.showMessageDialog(this, "Genauigkeit zu Gro�, wird auf 10 gesetzt", "Eingabefehler", 0);
        } catch (ClassCastException var6) {
            JOptionPane.showMessageDialog(this, "Genauigkeit draf nicht null sein!", "Eingabefehler", 0);
        } catch (ArrayStoreException var7) {
            JOptionPane.showMessageDialog(this, "Wurzel zu Gro�, wird auf 150 gesetzt", "Eingabefehler", 0);
        }catch (NegativeArraySizeException var8) {
            JOptionPane.showMessageDialog(this, "Ausgangszahl zu gro�! \nNeueingabe erforderlich!", "Eingabefehler", 0);
        }

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.compute) {
            this.readInput();
        }

        this.model.setZiel(0.0D);
    }

    public void update(Observable arg0, Object arg1) {
        this.g.setText(this.model.scale(this.model.getZiel()));
    }

    public String testText(String a) {
        a = a.replaceAll(",", ".");

        for(int i = 0; i < a.length(); ++i) {
            if (a.charAt(i) == '-') {
                throw new EmptyStackException();
            }

            if ((a.charAt(i) < '0' || a.charAt(i) > '9') && a.charAt(i) != '.') {
                throw new NumberFormatException();
            }
        }

        return a;
    }
}