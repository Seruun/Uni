import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/*�bung 4: Fiat Shamir Protokoll
 * Version 1.1
 * Gruppenmitglieder: Adrian Schuh, Dominik Benning, Christopher H�bner
 *
 */

public class ZDFrame extends JFrame {
    ZDModel model;
    private static final long serialVersionUID = 1L;
    ZDView view;

    public ZDFrame(ZDModel modela, int a) {
        super("Fiat-Shamir");
        this.model = modela;
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.view = new ZDView(this.model, this, a);
        this.getContentPane().add(this.view);
        this.setSize(300, 230);
        this.pack();
    }

    // Neue View Abfrage und l�schen der alten
    public void windowChange(int a) {
        this.remove(this.view);
        this.view = new ZDView(this.model, this, a);
        this.getContentPane().add(this.view);
        this.setSize(300, 230);
        this.pack();
    }

    public static void main(String[] args) {
        ZDModel model = new ZDModel();
        ZDFrame ef = new ZDFrame(model, 1);
        ef.setVisible(true);
    }
}
