import javax.swing.*;
import java.awt.*;

public class InView extends JFrame {

    public InView(){
        JFrame jf = new JFrame();

        jf.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setResizable(true);
        jf.setTitle("Welcome to your Class-Control-Center");

        jf.setVisible(true);
    }
}
