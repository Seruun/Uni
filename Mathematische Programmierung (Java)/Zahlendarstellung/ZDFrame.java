import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class ZDFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    public ZDFrame() {
        super("Zahlendarstellung");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        ZDModel model = new ZDModel();
        ZDView view = new ZDView(model);
        this.getContentPane().add(view);
        this.setSize(500, 200);
        this.pack();
    }

    public static void main(String[] args) {
        ZDFrame ef = new ZDFrame();
        ef.setVisible(true);
    }
}