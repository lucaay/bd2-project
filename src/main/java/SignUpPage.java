import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

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
                    cityLabel.setVisible(false);
                    cityComboBox.setVisible(false);
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
