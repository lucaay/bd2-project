import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

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
    private JRadioButton receiptRadioButton;
    private JRadioButton clientRadioButton;
    LocationData locationData = new LocationData();
    DefaultTableModel clientTableModel = (DefaultTableModel) clientDataTable.getModel();
    DefaultTableModel receiptTableModel = (DefaultTableModel) receiptDataTable.getModel();
    ButtonGroup tableTypeButtonGroup = new ButtonGroup();
    ButtonGroup filterTypeButtonGroup = new ButtonGroup();
    ButtonGroup orderTypeButtonGroup = new ButtonGroup();

    public ClientsAndReceiptsPage(ApplicationInterface applicationInterface) {
        clientDataTable.setDefaultEditor(Object.class, null);
        receiptDataTable.setDefaultEditor(Object.class, null);
        stateComboBox.getModel().setSelectedItem("Selecteaza judetul");
        hideAllProperties();
        addHeadersToClientTable();
        addHeadersToReceiptTable();
        createButtonGroups();


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
                    receiptRadioButton.setEnabled(false);
                    clientRadioButton.setEnabled(false);
                    filterTypeButtonGroup.clearSelection();
                    orderTypeButtonGroup.clearSelection();
                    tableTypeButtonGroup.clearSelection();
                    findByWordField.setText("");
                    addDataToClientTable();
                    addDataToReceiptTable();
                } else {
                    containsRadioButton.setEnabled(true);
                    startsWithRadioButton.setEnabled(true);
                    findByWordField.setEnabled(true);
                    idRadioButton.setEnabled(true);
                    alphabeticalOrderButton.setEnabled(true);
                    receiptRadioButton.setEnabled(true);
                    clientRadioButton.setEnabled(true);
                    clientTableModel.setRowCount(0);
                    receiptTableModel.setRowCount(0);
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

            }
        });
        startsWithRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        idRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        alphabeticalOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
        modifyClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enableClientProperties();
            }
        });
        saveClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disableClientProperties();
            }
        });
        modifyReceiptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enableReceiptProperties();
            }
        });
        saveReceiptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disableReceiptProperties();
            }
        });
        receiptRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientTableModel.setRowCount(0);
                addDataToReceiptTable();
            }
        });
        clientRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                receiptTableModel.setRowCount(0);
                addDataToClientTable();
            }
        });
        containsRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clientRadioButton.isSelected()){
                    addDataToClientTable();
                } else if (receiptRadioButton.isSelected()){
                    addDataToReceiptTable();
                }
            }
        });
        startsWithRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clientRadioButton.isSelected()){
                    addDataToClientTable();
                } else if (receiptRadioButton.isSelected()){
                    addDataToReceiptTable();
                }
            }
        });
        idRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clientRadioButton.isSelected()){
                    addDataToClientTable();
                } else if (receiptRadioButton.isSelected()){
                    addDataToReceiptTable();
                }
            }
        });
        alphabeticalOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clientRadioButton.isSelected()){
                    addDataToClientTable();
                } else if (receiptRadioButton.isSelected()){
                    addDataToReceiptTable();
                }
            }
        });
        findByWordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (clientRadioButton.isSelected()){
                    addDataToClientTable();
                } else if (receiptRadioButton.isSelected()){
                    addDataToReceiptTable();
                }
            }
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

    private void enableClientProperties() {
        idField.setEnabled(true);
        nameField.setEnabled(true);
        lastNameField.setEnabled(true);
        emailField.setEnabled(true);
        phoneField.setEnabled(true);
        stateComboBox.setEnabled(true);
        if (stateComboBox.getSelectedItem() != null) {
            cityComboBox.setEnabled(true);
        }
        addressField.setEnabled(true);
    }
    private void disableClientProperties() {
        idField.setEnabled(false);
        nameField.setEnabled(false);
        lastNameField.setEnabled(false);
        emailField.setEnabled(false);
        phoneField.setEnabled(false);
        stateComboBox.setEnabled(false);
        cityComboBox.setEnabled(false);
        addressField.setEnabled(false);
    }

    private void enableReceiptProperties() {
        idField.setEnabled(true);
        clientIdField.setEnabled(true);
        receiptDateField.setEnabled(true);
        totalPriceField.setEnabled(true);
    }
    private void disableReceiptProperties() {
        idField.setEnabled(false);
        clientIdField.setEnabled(false);
        receiptDateField.setEnabled(false);
        totalPriceField.setEnabled(false);
    }


    private void addHeadersToClientTable() {
        Object[] headerObject = new String[] {
                "ID", "Nume", "Prenume", "Email", "Telefon", "Judet", "Oras", "Adresa"
        };

        for (int i = 0; i < headerObject.length; i++){
            clientTableModel.addColumn(headerObject[i]);
        }
    }

    private void addHeadersToReceiptTable() {
        Object[] headerObject = new String[] {
                "ID", "ID Client", "Data", "Total"
        };

        for (int i = 0; i < headerObject.length; i++){
            receiptTableModel.addColumn(headerObject[i]);
        }
    }
    private void addDataToClientTable() {
        hideAllProperties();
        clientTableModel.setRowCount(0);
        Object[][] rowDataObject = {
                {"1", "Ion", "Popescu", "test@email.com", "0722222222", "Bucuresti", "Sector 2", "Str. Test 1"},
                {"2", "Gion", "aLECU", "test2@email.com", "0722234222", "gALATI", "tECUCI", "Str. Test 2"},
                {"3", "alex", "munteanu", "test3@email.com", "07222223232", "brasov", "centru", "Str. Test 3"},
        };
        for (int i = 0; i < rowDataObject.length; i++){
            if (containsRadioButton.isSelected() && !findByWordField.equals("")){
                if (rowDataObject[i][1].toString().toLowerCase().contains(findByWordField.getText().toLowerCase()) || rowDataObject[i][2].toString().toLowerCase().contains(findByWordField.getText().toLowerCase())){
                    clientTableModel.addRow(rowDataObject[i]);
                }
            } else if (startsWithRadioButton.isSelected() && !findByWordField.equals("")){
                if (rowDataObject[i][1].toString().toLowerCase().startsWith(findByWordField.getText()) || rowDataObject[i][2].toString().toLowerCase().startsWith(findByWordField.getText().toLowerCase())){
                    clientTableModel.addRow(rowDataObject[i]);
                }
            } else{
                clientTableModel.addRow(rowDataObject[i]);
            }
        }
    }
    private void addDataToReceiptTable() {
        receiptTableModel.setRowCount(0);
        Object[][] rowDataObject = {
                {"1", "256", "2020-01-01", "100"},
                {"2", "354", "2020-01-02", "200"},
        };
        for (int i = 0; i < rowDataObject.length; i++){
            receiptTableModel.addRow(rowDataObject[i]);
        }
    }

    private void createButtonGroups() {
        tableTypeButtonGroup.add(clientRadioButton);
        tableTypeButtonGroup.add(receiptRadioButton);
        filterTypeButtonGroup.add(containsRadioButton);
        filterTypeButtonGroup.add(startsWithRadioButton);
        orderTypeButtonGroup.add(idRadioButton);
        orderTypeButtonGroup.add(alphabeticalOrderButton);
    }
}
