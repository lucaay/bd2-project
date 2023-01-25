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

    MysqlCon mysqlCon = new MysqlCon();

    public SignInPage(ApplicationInterface applicationInterface) {
        singInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mysqlCon.verifyLogin(emailField.getText(), passwordField.getText()){
                    applicationInterface.showHomePage();
                }else{
                    JOptionPane.showMessageDialog(null, "Email sau parola gresita!");
                }
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
