import javax.swing.*;

public class SignUpPage {

    private JPanel panel1;
    private JTextField emailField;
    private JLabel emailLabel;
    private JLabel passlabel;
    private JPasswordField passField;

    public SignUpPage() {
        JFrame frame = new JFrame("SignUpPage");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
