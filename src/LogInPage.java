import javax.swing.*;

public class LogInPage {
    private JLabel test_text;
    private JPanel panel1;

    public LogInPage() {
        JFrame frame = new JFrame("SignUpPage");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
