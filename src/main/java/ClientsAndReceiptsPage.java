import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

public class ClientsAndReceiptsPage {
    JPanel clientsAndReceiptsParentPanel;
    private JPanel clientsAndReceiptsChildPanel;
    private JPanel propertiesPanel;
    private JLabel propertiesTitleLabel;
    private JTextField idField;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel lastNameLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JLabel stateLabel;
    private JLabel cityLabel;
    private JLabel addressLabel;
    private JTextField emailField;
    private JTextField addressField;
    private JLabel totalPriceLabel;
    private JTextField totalPriceField;
    private JLabel clientIdLabel;
    private JLabel receiptDateLabel;
    private JTextField clientIdField;
    private JTextField receiptDateField;
    private JPanel receiptDataPanel;
    private JScrollPane receiptDataScrollPane;
    private JTable receiptDataTable;
    private JLabel receiptDataPanelTitleLabel;
    private JPanel clientDataPanel;
    private JLabel clientDataTitleLabel;
    private JScrollPane clientDataScrollPane;
    private JTable clientDataTable;
    private JLabel numberOfClientsLabel;
    private JPanel buttonsPanel;
    private JButton backToHomePageButton;
    private JButton modifyReceiptButton;
    private JButton saveReceiptButton;
    private JButton modifyClientButton;
    private JButton searchRegsButton;
    private JPanel filtersPanel;
    private JCheckBox allDataCheckBox;
    private JRadioButton containsRadioButton;
    private JTextField findByWordField;
    private JLabel findByWordLabel;
    private JRadioButton startsWithRadioButton;
    private JRadioButton alphabeticalOrderButton;
    private JRadioButton idRadioButton;
    private JComboBox stateComboBox;
    private JComboBox cityComboBox;
    private JTextField nameField;
    private JTextField lastNameField;
    private JTextField phoneField;
    private JLabel numberOfReceiptsLabel;
    private JButton saveClientButton;
    LocationData locationData = new LocationData();
    DefaultTableModel clientTabelModel = (DefaultTableModel) clientDataTable.getModel();
    DefaultTableModel receiptTabelModel = (DefaultTableModel) receiptDataTable.getModel();

    public ClientsAndReceiptsPage(ApplicationInterface applicationInterface) {
        clientDataTable.setDefaultEditor(Object.class, null);
        receiptDataTable.setDefaultEditor(Object.class, null);
        stateComboBox.getModel().setSelectedItem("Selecteaza judetul");
        hideAllProperties();
        addDataToClientTable();
        addDataToReceiptTable();

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
                for (int i = 0; i < cityData.size(); i++) {
                    JSONObject temp = (JSONObject) cityData.get(i);
                    cityComboBox.addItem(temp.get("nume"));
                    cityComboBox.setEnabled(true);
                }
            }
        });

        allDataCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (allDataCheckBox.isSelected()) {
                    containsRadioButton.setEnabled(false);
                    startsWithRadioButton.setEnabled(false);
                    findByWordField.setEnabled(false);
                    idRadioButton.setEnabled(false);
                    alphabeticalOrderButton.setEnabled(false);
                    searchRegsButton.setEnabled(false);
                } else {
                    containsRadioButton.setEnabled(true);
                    startsWithRadioButton.setEnabled(true);
                    findByWordField.setEnabled(true);
                    idRadioButton.setEnabled(true);
                    alphabeticalOrderButton.setEnabled(true);
                    searchRegsButton.setEnabled(true);
                }

            }
        });
        backToHomePageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applicationInterface.showHomePage();
            }
        });

        containsRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startsWithRadioButton.setSelected(false);
            }
        });
        startsWithRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                containsRadioButton.setSelected(false);
            }
        });
        idRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alphabeticalOrderButton.setSelected(false);
            }
        });
        alphabeticalOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idRadioButton.setSelected(false);
            }
        });
        receiptDataTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                showReceiptProperties();
                modifyReceiptButton.setEnabled(true);
                modifyClientButton.setEnabled(false);
                saveClientButton.setEnabled(false);
            }
        });
        clientDataTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                showClientProperties();
                modifyClientButton.setEnabled(true);
                modifyReceiptButton.setEnabled(false);
                saveReceiptButton.setEnabled(false);
            }
        });
        modifyReceiptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveReceiptButton.setEnabled(true);
                modifyReceiptButton.setEnabled(false);
            }
        });
        modifyClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveClientButton.setEnabled(true);
                modifyClientButton.setEnabled(false);
            }
        });
        saveReceiptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveReceiptButton.setEnabled(false);
                modifyReceiptButton.setEnabled(true);
            }
        });
        saveClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveClientButton.setEnabled(false);
                modifyClientButton.setEnabled(true);
            }
        });
        stateComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        clientDataTable.addComponentListener(new ComponentAdapter() {
        });
    }

    private void hideAllProperties() {
        for (Component component : propertiesPanel.getComponents()) {
            component.setVisible(false);
            propertiesTitleLabel.setVisible(true);
        }
    }

    private void showClientProperties() {
        hideAllProperties();
        idLabel.setVisible(true);
        idField.setVisible(true);
        nameLabel.setVisible(true);
        nameField.setVisible(true);
        lastNameLabel.setVisible(true);
        lastNameField.setVisible(true);
        emailLabel.setVisible(true);
        emailField.setVisible(true);
        phoneLabel.setVisible(true);
        phoneField.setVisible(true);
        stateLabel.setVisible(true);
        stateComboBox.setVisible(true);
        cityLabel.setVisible(true);
        cityComboBox.setVisible(true);
        addressLabel.setVisible(true);
        addressField.setVisible(true);
    }
    private void showReceiptProperties() {
        hideAllProperties();
        idLabel.setVisible(true);
        idField.setVisible(true);
        clientIdLabel.setVisible(true);
        clientIdField.setVisible(true);
        receiptDateLabel.setVisible(true);
        receiptDateField.setVisible(true);
        totalPriceLabel.setVisible(true);
        totalPriceField.setVisible(true);
    }


    private void addDataToClientTable() {
        clientTabelModel.setRowCount(0);
        Object[] headerObject = new String[] {
                "ID", "Nume", "Prenume", "Email", "Telefon", "Judet", "Oras", "Adresa"
        };

        for (int i = 0; i < headerObject.length; i++){
            clientTabelModel.addColumn(headerObject[i]);
        }
        Object[][] rowDataObject = {
                {"1", "Ion", "Popescu", "test@email.com", "0722222222", "Bucuresti", "Sector 2", "Str. Test 1"},
                {"2", "Gion", "aLECU", "test2@email.com", "0722234222", "gALATI", "tECUCI", "Str. Test 2"},
        };
        for (int i = 0; i < rowDataObject.length; i++){
            clientTabelModel.addRow(rowDataObject[i]);
        }
    }
    private void addDataToReceiptTable() {
        receiptTabelModel.setRowCount(0);
        Object[] headerObject = {"ID", "ID Client", "Data", "Total"};
        for (int i = 0; i < headerObject.length; i++){
            receiptTabelModel.addColumn(headerObject[i]);
        }
        Object[][] rowDataObject = {
                {"1", "256", "2020-01-01", "100"},
                {"2", "354", "2020-01-02", "200"},
        };
        for (int i = 0; i < rowDataObject.length; i++){
            receiptTabelModel.addRow(rowDataObject[i]);
        }
    }
}
