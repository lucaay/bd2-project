import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Comparator;

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
    private JRadioButton containsClientsRadioButton;
    private JTextField findByWordClientsField;
    private JLabel searchClientsLabel;
    private JRadioButton startsWithClientsRadioButton;
    private JRadioButton alphabeticalOrderClientsRadioButton;
    private JRadioButton alphabeticalReverseOrderClientsRadioButton;
    private JComboBox stateComboBox;
    private JComboBox cityComboBox;
    private JTextField nameField;
    private JTextField lastNameField;
    private JTextField phoneField;
    private JLabel numberOfReceiptsLabel;
    private JButton saveClientButton;
    private JRadioButton receiptRadioButton;
    private JRadioButton clientRadioButton;
    private JLabel searchReceiptsLabel;
    private JTextArea receiptProductsTextArea;
    private JLabel receiptProductsLabel;
    private JRadioButton containsReceiptRadioButton;
    private JRadioButton startsWithReceiptRadioButton;
    private JTextField findByWordReceiptsField;
    private JRadioButton idOrderReceiptsRadioButton;
    private JRadioButton idReverseOrderReceiptsRadioButton;
    LocationData locationData = new LocationData();
    DefaultTableModel clientTableModel = (DefaultTableModel) clientDataTable.getModel();
    DefaultTableModel receiptTableModel = (DefaultTableModel) receiptDataTable.getModel();
    ButtonGroup tableTypeClientsButtonGroup = new ButtonGroup();
    ButtonGroup filterTypeClientsButtonGroup = new ButtonGroup();
    ButtonGroup orderTypeClientsButtonGroup = new ButtonGroup();
    ButtonGroup tableTypeReceiptsButtonGroup = new ButtonGroup();
    ButtonGroup filterTypeReceiptsButtonGroup = new ButtonGroup();
    ButtonGroup orderTypeReceiptsButtonGroup = new ButtonGroup();

    Object[][] clientsRowDataObject = {
            {"1", "Ion", "Popescu", "test@email.com", "0722222222", "Bucuresti", "Sector 2", "Str. Test 1"},
            {"2", "Gion", "aLECU", "test2@email.com", "0722234222", "gALATI", "tECUCI", "Str. Test 2"},
            {"3", "alex", "munteanu", "test3@email.com", "07222223232", "brasov", "centru", "Str. Test 3"},
    };
    Object[][] receiptsRowDataObject = {
            {"121", "256", "2020-01-01", "100", "PLACA DE BAZA, PROCESOR, RAM"},
            {"256", "354", "2020-01-02", "200", "PLACA DE BAZA, PROCESOR, RAM, HDD"},
            {"334", "359", "2020-01-05", "300", "PLACA DE BAZA, PROCESOR, RAM, HDD, SSD"},
    };

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
                    disableReceiptFilterProperties();
                    disableClientFilterProperties();
                    receiptRadioButton.setEnabled(false);
                    clientRadioButton.setEnabled(false);
                    filterTypeClientsButtonGroup.clearSelection();
                    orderTypeClientsButtonGroup.clearSelection();
                    tableTypeClientsButtonGroup.clearSelection();
                    filterTypeReceiptsButtonGroup.clearSelection();
                    orderTypeReceiptsButtonGroup.clearSelection();
                    tableTypeReceiptsButtonGroup.clearSelection();
                    modifyClientButton.setEnabled(false);
                    modifyReceiptButton.setEnabled(false);
                    saveClientButton.setEnabled(false);
                    saveReceiptButton.setEnabled(false);
                    findByWordClientsField.setText("");
                    addDataToClientTable();
                    addDataToReceiptTable();
                if (!allDataCheckBox.isSelected()) {
                    receiptRadioButton.setEnabled(true);
                    clientRadioButton.setEnabled(true);
                    clientTableModel.setRowCount(0);
                    receiptTableModel.setRowCount(0);
                    numberOfClientsLabel.setText("0 clienti");
                    numberOfReceiptsLabel.setText("0 facturi");
                }

            }
        });
        backToHomePageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applicationInterface.showHomePage();
            }
        });

        startsWithClientsRadioButton.addActionListener(new ActionListener() {
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
                numberOfClientsLabel.setText("0 clienti");
                addDataToReceiptTable();
                disableClientFilterProperties();
                enableReceiptFilterProperties();
                filterTypeClientsButtonGroup.clearSelection();
                orderTypeClientsButtonGroup.clearSelection();
            }
        });
        clientRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                receiptTableModel.setRowCount(0);
                numberOfReceiptsLabel.setText("0 facturi");
                addDataToClientTable();
                disableReceiptFilterProperties();
                enableClientFilterProperties();
                filterTypeReceiptsButtonGroup.clearSelection();
                orderTypeReceiptsButtonGroup.clearSelection();
            }
        });
        containsClientsRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDataToClientTable();
            }
        });
        startsWithClientsRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDataToClientTable();
            }
        });
        alphabeticalReverseOrderClientsRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDataToClientTable();
            }
        });
        alphabeticalOrderClientsRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDataToClientTable();
            }
        });
        findByWordClientsField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                addDataToClientTable();
            }
        });
        containsReceiptRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDataToReceiptTable();
            }
        });
        startsWithReceiptRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDataToReceiptTable();
            }
        });
        findByWordReceiptsField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                addDataToReceiptTable();
            }
        });
        idOrderReceiptsRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDataToReceiptTable();
            }
        });
        idReverseOrderReceiptsRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDataToReceiptTable();
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
        receiptProductsLabel.setVisible(true);
        receiptProductsTextArea.setVisible(true);
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
        receiptProductsTextArea.setEnabled(true);
    }
    private void disableReceiptProperties() {
        idField.setEnabled(false);
        clientIdField.setEnabled(false);
        receiptDateField.setEnabled(false);
        totalPriceField.setEnabled(false);
        receiptProductsTextArea.setEnabled(false);
    }

    private void enableClientFilterProperties() {
        containsClientsRadioButton.setEnabled(true);
        startsWithClientsRadioButton.setEnabled(true);
        alphabeticalOrderClientsRadioButton.setEnabled(true);
        alphabeticalReverseOrderClientsRadioButton.setEnabled(true);
        findByWordClientsField.setEnabled(true);
    }
    private void disableClientFilterProperties() {
        containsClientsRadioButton.setEnabled(false);
        startsWithClientsRadioButton.setEnabled(false);
        alphabeticalOrderClientsRadioButton.setEnabled(false);
        alphabeticalReverseOrderClientsRadioButton.setEnabled(false);
        findByWordClientsField.setEnabled(false);
    }

    private void enableReceiptFilterProperties() {
        containsReceiptRadioButton.setEnabled(true);
        startsWithReceiptRadioButton.setEnabled(true);
        idOrderReceiptsRadioButton.setEnabled(true);
        idReverseOrderReceiptsRadioButton.setEnabled(true);
        findByWordReceiptsField.setEnabled(true);
    }
    private void disableReceiptFilterProperties() {
        containsReceiptRadioButton.setEnabled(false);
        startsWithReceiptRadioButton.setEnabled(false);
        idOrderReceiptsRadioButton.setEnabled(false);
        idReverseOrderReceiptsRadioButton.setEnabled(false);
        findByWordReceiptsField.setEnabled(false);
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
        backToDefaultClientsSort();
        Object[][] tempRowDataObject = clientsRowDataObject;
        if (alphabeticalOrderClientsRadioButton.isSelected()){
            Arrays.sort(tempRowDataObject, new Comparator<Object[]>() {
                @Override
                public int compare(Object[] o1, Object[] o2) {
                    return ((String) o1[1]).compareToIgnoreCase((String) o2[1]);
                }
            });
        } else if (alphabeticalReverseOrderClientsRadioButton.isSelected()){
            Arrays.sort(tempRowDataObject, new Comparator<Object[]>() {
                @Override
                public int compare(Object[] o1, Object[] o2) {
                    return -((String) o1[1]).compareToIgnoreCase((String) o2[1]);
                }
            });
        }

        for (int i = 0; i < tempRowDataObject.length; i++){
            if (containsClientsRadioButton.isSelected() && !findByWordClientsField.equals("")){
                if (tempRowDataObject[i][1].toString().toLowerCase().contains(findByWordClientsField.getText().toLowerCase()) || tempRowDataObject[i][2].toString().toLowerCase().contains(findByWordClientsField.getText().toLowerCase())){
                    clientTableModel.addRow(tempRowDataObject[i]);
                }
            } else if (startsWithClientsRadioButton.isSelected() && !findByWordClientsField.equals("")){
                if (tempRowDataObject[i][1].toString().toLowerCase().startsWith(findByWordClientsField.getText()) || tempRowDataObject[i][2].toString().toLowerCase().startsWith(findByWordClientsField.getText().toLowerCase())){
                    clientTableModel.addRow(tempRowDataObject[i]);
                }
            } else{
                clientTableModel.addRow(tempRowDataObject[i]);
            }
        }
        numberOfClientsLabel.setText(clientTableModel.getRowCount() + " clienti");
    }

    private void backToDefaultClientsSort(){
        Arrays.sort(clientsRowDataObject, new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                return Integer.compare(Integer.parseInt(o1[0].toString()), Integer.parseInt(o2[0].toString()));
            }
        });
    }
    private void backToDefaultReceiptsSort(){
        Arrays.sort(receiptsRowDataObject, new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                return Integer.compare(Integer.parseInt(o1[0].toString()), Integer.parseInt(o2[0].toString()));
            }
        });
    }
    private void addDataToReceiptTable() {
        receiptTableModel.setRowCount(0);
        backToDefaultReceiptsSort();
        Object[][] tempRowDataObject = receiptsRowDataObject;
        if (idOrderReceiptsRadioButton.isSelected()){
            Arrays.sort(tempRowDataObject, new Comparator<Object[]>() {
                @Override
                public int compare(Object[] o1, Object[] o2) {
                    return Integer.compare(Integer.parseInt(o1[0].toString()), Integer.parseInt(o2[0].toString()));
                }
            });
        } else if (idReverseOrderReceiptsRadioButton.isSelected()){
            Arrays.sort(tempRowDataObject, new Comparator<Object[]>() {
                @Override
                public int compare(Object[] o1, Object[] o2) {
                    return -Integer.compare(Integer.parseInt(o1[0].toString()), Integer.parseInt(o2[0].toString()));
                }
            });
        }
        for (int i = 0; i < tempRowDataObject.length; i++){
            if (containsReceiptRadioButton.isSelected() && !findByWordReceiptsField.equals("")){
                if (tempRowDataObject[i][0].toString().toLowerCase().contains(findByWordReceiptsField.getText().toLowerCase())){
                    receiptTableModel.addRow(tempRowDataObject[i]);
                }
            } else if (startsWithReceiptRadioButton.isSelected() && !findByWordReceiptsField.equals("")){
                if (tempRowDataObject[i][0].toString().toLowerCase().startsWith(findByWordReceiptsField.getText().toLowerCase())){
                    receiptTableModel.addRow(tempRowDataObject[i]);
                }
            } else{
                receiptTableModel.addRow(tempRowDataObject[i]);
            }
        }
        numberOfReceiptsLabel.setText(receiptTableModel.getRowCount() + " facturi");
    }

    private void createButtonGroups() {
        tableTypeClientsButtonGroup.add(clientRadioButton);
        tableTypeClientsButtonGroup.add(receiptRadioButton);

        filterTypeClientsButtonGroup.add(containsClientsRadioButton);
        filterTypeClientsButtonGroup.add(startsWithClientsRadioButton);
        orderTypeClientsButtonGroup.add(alphabeticalOrderClientsRadioButton);
        orderTypeClientsButtonGroup.add(alphabeticalReverseOrderClientsRadioButton);

        tableTypeReceiptsButtonGroup.add(containsReceiptRadioButton);
        tableTypeReceiptsButtonGroup.add(startsWithReceiptRadioButton);
        filterTypeReceiptsButtonGroup.add(idOrderReceiptsRadioButton);
        filterTypeReceiptsButtonGroup.add(idReverseOrderReceiptsRadioButton);
    }
}
