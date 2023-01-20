import javax.swing.*;

public class ApplicationInterface {

    private JPanel applicationPanel;
    JFrame applicationFrame = new JFrame("BD2 Project");
    SignInPage signInPage = new SignInPage(this);
    SignUpPage signUpPage = new SignUpPage(this);

    public ApplicationInterface() {
        applicationFrame.setContentPane(applicationPanel);
        applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        applicationFrame.pack();
        applicationFrame.setVisible(true);
    }

    public void showSignUpPage(){
        applicationFrame.setContentPane(signUpPage.signUpParentPanel);
        applicationFrame.pack();
    }
    public void showLogInPage(){
        applicationFrame.setContentPane(signInPage.signInParentPanel);
        applicationFrame.pack();
    }
}
