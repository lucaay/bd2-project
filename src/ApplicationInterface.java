import pages.SignInPage;
import pages.SignUpPage;

import javax.swing.*;

public class ApplicationInterface {

    private JPanel applicationPanel;
    JFrame applicationFrame = new JFrame("BD2 Project");
    SignInPage signInPage = new SignInPage();
    SignUpPage signUpPage = new SignUpPage();

    public ApplicationInterface() {
        applicationFrame.setContentPane(applicationPanel);
        applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        applicationFrame.pack();
        applicationFrame.setVisible(true);
    }

//    private  void setAllPanelsInvisible(){
//        signInPage.signInParentPanel.setVisible(false);
//        signUpPage.signUpParentPanel.setVisible(false);
//    }

    public void showSignUpPage(){
        applicationFrame.setContentPane(signUpPage.signUpParentPanel);
    }
    public void showLogInPage(){
        applicationFrame.setContentPane(signInPage.signInParentPanel);
    }
}
