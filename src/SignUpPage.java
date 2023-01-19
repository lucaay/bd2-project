import javax.swing.*;

public class SignUpPage {

    private JPanel panel1;
    private JPanel centerPanel;
    private JTextField textField1;
    private JLabel passwordLabel;
    private JPasswordField passwordField1;
    private JLabel emailLabel;
    private JButton signUpButton;
    private JButton haveAccountButton;
    private JLabel accountTypeLabel;
    private JComboBox accountTypeComboBox;

    public SignUpPage() {
        JFrame frame = new JFrame("BD2 Project - Sign Up Page");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
