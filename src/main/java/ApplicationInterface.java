import javax.swing.*;

public class ApplicationInterface {

    private JPanel applicationPanel;
    JFrame applicationFrame = new JFrame("BD2 Project");
    SignInPage signInPage = new SignInPage(this);
    SignUpPage signUpPage = new SignUpPage(this);
    Homepage homepage = new Homepage(this);
    ClientsAndReceiptsPage clientsAndReceiptsPage = new ClientsAndReceiptsPage(this);

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
    public void showHomePage(){
        applicationFrame.setContentPane(homepage.homePageParentPanel);
        applicationFrame.pack();
    }

    public void showClientsAndReceiptsPage(){
        applicationFrame.setContentPane(clientsAndReceiptsPage.clientsAndReceiptsParentPanel);
        applicationFrame.pack();
    }
}
