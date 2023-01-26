import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

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

    LocationData locationData = new LocationData();

    MysqlCon mysqlCon = new MysqlCon();
    String adminDefaultCode = "1357";


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
        stateComboBox.getModel().setSelectedItem("Selecteaza judetul");
        cityComboBox.getModel().setSelectedItem("Selecteaza localitatea");
        accountTypeComboBox.getModel().setSelectedItem("Selecteaza tipul de cont");


        JSONArray stateData = locationData.getStateData();
        for (int i = 0; i < stateData.size(); i++) {
            JSONObject temp = (JSONObject) stateData.get(i);
            stateComboBox.addItem(temp.get("nume"));
        }

        stateComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentInitials = "";
                for (int i = 0; i < stateData.size(); i++) {
                    JSONObject temp = (JSONObject) stateData.get(i);
                    if (temp.get("nume").equals(stateComboBox.getSelectedItem())) {
                        currentInitials = (String) temp.get("auto");
                    }
                }
                if (!currentInitials.equals("")) {
                    cityLabel.setVisible(true);
                    cityComboBox.setVisible(true);
                }
                JSONArray cityData = locationData.getCityData(currentInitials);
                cityComboBox.removeAllItems();
                for (int i = 0; i < cityData.size(); i++) {
                    JSONObject temp = (JSONObject) cityData.get(i);
                    cityComboBox.addItem(temp.get("nume"));
                }
            }
        });


        haveAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applicationInterface.showLogInPage();
            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (accountTypeComboBox.getSelectedItem().equals("Administrator")) {
                    if (firstNameField.getText().equals("") || lastNameField.getText().equals("") || emailField.getText().equals("") || passwordField.getText().equals("") || phoneField.getText().equals("") || accessCodeAdminField.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Va rugam sa completati toate campurile!");
                    } else {
                        if (accessCodeAdminField.getText().equals(adminDefaultCode)) {
                            mysqlCon.createAdminAccount(firstNameField.getText(), lastNameField.getText(), phoneField.getText(),
                                    emailField.getText(), passwordField.getText(), accountTypeComboBox.getSelectedItem().toString());
                            JOptionPane.showMessageDialog(null, "Contul a fost creat cu succes!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Codul de acces este incorect!");
                        }
                    }
                } else if (accountTypeComboBox.getSelectedItem().equals("Client")) {
                    if (firstNameField.getText().equals("") || lastNameField.getText().equals("") || emailField.getText().equals("") || passwordField.getText().equals("") || phoneField.getText().equals("") || stateComboBox.getSelectedItem().equals("Selecteaza judetul") || cityComboBox.getSelectedItem().equals("Selecteaza localitatea") || addressField.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Va rugam sa completati toate campurile!");
                    } else {
                        mysqlCon.createClientAccount(firstNameField.getText(), lastNameField.getText(),
                                phoneField.getText(), stateComboBox.getSelectedItem().toString(),
                                cityComboBox.getSelectedItem().toString(), addressField.getText(), emailField.getText(), passwordField.getText(),
                                accountTypeComboBox.getSelectedItem().toString());
                        JOptionPane.showMessageDialog(null, "Contul a fost creat cu succes!");

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Va rugam sa selectati tipul de cont!");
                }

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
                    accessCodeAdminField.setEnabled(true);
                }else if (accountTypeComboBox.getSelectedItem().equals("Client")) {
                    setAllItemsVisible();
                    accessCodeAdminLabel.setVisible(false);
                    accessCodeAdminField.setVisible(false);
                    cityLabel.setVisible(false);
                    cityComboBox.setVisible(false);
                    accessCodeAdminField.setEnabled(false);
                } else {
                    setAllItemsInvisible();
                    accountTypeLabel.setVisible(true);
                    accountTypeComboBox.setVisible(true);
                    haveAccountButton.setVisible(true);
                }
            }
        });
        cityComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}
