import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInPage {
    public JPanel signInParentPanel;
    private JPanel signInPanel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton singInButton;
    private JButton createAccountButton;
    private JLabel passwordLabel;
    private JLabel emailLabel;

    public SignInPage(ApplicationInterface applicationInterface) {
        singInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Sign in button clicked");
            }
        });
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applicationInterface.showSignUpPage();
                System.out.println("Create account button clicked");
            }
        });
    }
}
