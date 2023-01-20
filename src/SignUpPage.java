import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPage {
    public JPanel signUpParentPanel;
    private JPanel signUpPanel;
    private JLabel passwordLabel;
    private JLabel emailLabel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton signUpButton;
    private JButton haveAccountButton;
    private JLabel accountTypeLabel;
    private JComboBox accountTypeComboBox;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JLabel accessCodeAdminLabel;
    private JTextField accessCodeAdminField;
    private JLabel phoneLabel;
    private JTextField phoneField;
    private JComboBox stateComboBox;
    private JLabel stateLabel;
    private JLabel cityLabel;
    private JComboBox cityComboBox;
    private JLabel addressLabel;
    private JTextField addressField;


    public SignUpPage(){
        setAllItemsInvisible();
    }

    private void setAllItemsInvisible(){
        for (Component component : signUpPanel.getComponents()) {
            component.setVisible(false);
        }
    }
    private void setAllItemsVisible(){
        for (Component component : signUpPanel.getComponents()) {
            component.setVisible(true);
        }
    }

    public SignUpPage(ApplicationInterface applicationInterface) {
        setAllItemsInvisible();
        accountTypeLabel.setVisible(true);
        accountTypeComboBox.setVisible(true);
        haveAccountButton.setVisible(true);
        haveAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applicationInterface.showLogInPage();
            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Sign up button clicked");
            }
        });
        accountTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (accountTypeComboBox.getSelectedItem().equals("Administrator")) {
                    setAllItemsVisible();
                    stateLabel.setVisible(false);
                    stateComboBox.setVisible(false);
                    cityLabel.setVisible(false);
                    cityComboBox.setVisible(false);
                    addressLabel.setVisible(false);
                    addressField.setVisible(false);
                }else if (accountTypeComboBox.getSelectedItem().equals("Client")) {
                    setAllItemsVisible();
                    accessCodeAdminLabel.setVisible(false);
                    accessCodeAdminField.setVisible(false);
                } else {
                    setAllItemsInvisible();
                    accountTypeLabel.setVisible(true);
                    accountTypeComboBox.setVisible(true);
                    haveAccountButton.setVisible(true);
                }
            }
        });
    }
}
