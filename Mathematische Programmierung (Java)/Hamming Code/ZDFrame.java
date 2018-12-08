/*�bung 7: Hamming Code
 * Version 1.0
 * Gruppenmitglieder: Adrian Schuh, Dominik Benning, Christopher H�bner
 *
 */

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class ZDFrame extends JFrame {
    ZDModel model;
    private static final long serialVersionUID = 1L;
    ZDView view;

    public ZDFrame(ZDModel modela) {
        super("Hamming");
        this.model = modela;
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.view = new ZDView(this.model, this);
        JScrollPane jScrollPane = new JScrollPane(view);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(jScrollPane);
        this.getContentPane().add(this.view);
        this.setSize(300, 230);
        this.pack();
    }

    public static void main(String[] args) {
        ZDModel model = new ZDModel();
        ZDFrame ef = new ZDFrame(model);
        ef.setVisible(true);
    }
}
