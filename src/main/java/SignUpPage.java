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


        JSONArray stateData = getStateData();
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
                JSONArray cityData = getCityData(currentInitials);
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


    private JSONArray getStateData(){
        try {

            URL url = new URL("https://roloca.coldfuse.io/judete");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int response = conn.getResponseCode();

            if (response != 200) {
                throw new RuntimeException("HttpResponseCode: " + response);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                //Close the scanner
                scanner.close();

                //Using the JSON simple library parse the string into a json object
                JSONParser parse = new JSONParser();
                JSONArray data_arr = (JSONArray) parse.parse(inline);


//                System.out.println(data_arr);
                return data_arr;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private JSONArray getCityData(String stateInitials){
        try {

            URL url = new URL("https://roloca.coldfuse.io/orase/" + stateInitials);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int response = conn.getResponseCode();

            if (response != 200) {
                throw new RuntimeException("HttpResponseCode: " + response);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                //Close the scanner
                scanner.close();

                //Using the JSON simple library parse the string into a json object
                JSONParser parse = new JSONParser();
                JSONArray data_arr = (JSONArray) parse.parse(inline);


//                System.out.println(data_arr);
                return data_arr;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
