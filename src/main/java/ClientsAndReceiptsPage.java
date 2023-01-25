import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.Normalizer;
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
    Client client1 = new Client("1", "Ion", "Popescu", "test@email.com", "0722222222", "București", "Sector 2", "Str. Test 1");
    Client client2 = new Client("2", "Gion", "aLECU", "test2@email.com", "0722234222", "Galați", "Barcea", "Str. Test 2");
    Client client3 = new Client("3", "alex", "munteanu", "test3@email.com", "07222223232", "Brașov", "Bod", "Str. Test 3");
    Client[] clientsDataObject = {
            client1,
            client2,
            client3,
    };
    Object[][] clientsRowDataObject;
    Receipt receipt1 = new Receipt("121", "256", "2020-01-01", "100", "PLACA DE BAZA, PROCESOR, RAM");
    Receipt receipt2 = new Receipt("256", "354", "2020-01-02", "200", "PLACA DE BAZA, PROCESOR, RAM, HDD");
    Receipt receipt3 = new Receipt("334", "359", "2020-01-05", "300", "PLACA DE BAZA, PROCESOR, RAM, HDD, SSD");
    Receipt[] receiptsDataObject = {
            receipt1,
            receipt2,
            receipt3,
    };
    Object[][] receiptsRowDataObject;
    private boolean errors = false;

    public ClientsAndReceiptsPage(ApplicationInterface applicationInterface) {
        clientDataTable.setDefaultEditor(Object.class, null);
        receiptDataTable.setDefaultEditor(Object.class, null);
        stateComboBox.getModel().setSelectedItem("Selecteaza judetul");
        hideAllProperties();
        addHeadersToClientTable();
        addHeadersToReceiptTable();
        createButtonGroups();
        setComboBoxesData();

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
                populateReceiptFields();
                disableReceiptProperties();
                modifyReceiptButton.setEnabled(true);
                saveReceiptButton.setEnabled(false);
                modifyClientButton.setEnabled(false);
                saveClientButton.setEnabled(false);
            }
        });
        clientDataTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                showClientProperties();
                populateClientFields();
                disableClientProperties();
                modifyClientButton.setEnabled(true);
                saveClientButton.setEnabled(false);
                modifyReceiptButton.setEnabled(false);
                saveReceiptButton.setEnabled(false);
            }
        });
        modifyReceiptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enableReceiptProperties();
                saveReceiptButton.setEnabled(true);
                modifyReceiptButton.setEnabled(false);
            }
        });
        modifyClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enableClientProperties();
                saveClientButton.setEnabled(true);
                modifyClientButton.setEnabled(false);
            }
        });
        saveReceiptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveReceiptChanges();
                saveReceiptButton.setEnabled(false);
                modifyReceiptButton.setEnabled(true);
                disableReceiptProperties();
                addDataToReceiptTable();
            }
        });
        saveClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveClientChanges();
                saveClientButton.setEnabled(false);
                modifyClientButton.setEnabled(true);
                disableClientProperties();
                addDataToClientTable();
            }
        });
        stateComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

    private void createClientsRowDataObject() {
        clientsRowDataObject = new Object[clientsDataObject.length][];
        for (int i = 0; i < clientsDataObject.length; i++) {
            clientsRowDataObject[i] = clientsDataObject[i].clientObject();
        }
    }

    private void createReceiptsRowDataObject() {
        receiptsRowDataObject = new Object[receiptsDataObject.length][];
        for (int i = 0; i < receiptsDataObject.length; i++) {
            receiptsRowDataObject[i] = receiptsDataObject[i].receiptObject();
        }
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
        createClientsRowDataObject();
        hideAllProperties();
        clientTableModel.setRowCount(0);
        backToDefaultClientsSort();
        modifyClientButton.setEnabled(false);
        saveClientButton.setEnabled(false);
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
    private void addDataToReceiptTable() {
        createReceiptsRowDataObject();
        receiptTableModel.setRowCount(0);
        backToDefaultReceiptsSort();
        modifyReceiptButton.setEnabled(false);
        saveReceiptButton.setEnabled(false);
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

    private void setComboBoxesData(){
        JSONArray stateData = locationData.getStateData();
        for (int i = 0; i < stateData.size(); i++) {
            JSONObject temp = (JSONObject) stateData.get(i);
            stateComboBox.addItem(temp.get("nume").toString());
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
    }

    private void normalizeComboBoxValues(JComboBox stateComboBox, JComboBox cityComboBox, Object[][] tempRowDataObject, int selectedRow){
        String stateWithDiacritics = tempRowDataObject[selectedRow][5].toString();
        String stateWithoutDiacritics = Normalizer.normalize(stateWithDiacritics, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");

        for (int j = 0; j < stateComboBox.getItemCount(); j++) {
            String comboBoxStateWithDiacritics = stateComboBox.getItemAt(j).toString();
            String comboBoxStateWithoutDiacritics = Normalizer.normalize(comboBoxStateWithDiacritics, Normalizer.Form.NFD)
                    .replaceAll("[^\\p{ASCII}]", "");
            if (comboBoxStateWithoutDiacritics.equals(stateWithoutDiacritics)){
                stateComboBox.setSelectedIndex(j);
                break;
            }
        }
        String cityWithDiacritics = tempRowDataObject[selectedRow][6].toString();
        String cityWithoutDiacritics = Normalizer.normalize(cityWithDiacritics, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");

        for (int j = 0; j < cityComboBox.getItemCount(); j++) {
            String comboBoxCityWithDiacritics = cityComboBox.getItemAt(j).toString();
            String comboBoxCityWithoutDiacritics = Normalizer.normalize(comboBoxCityWithDiacritics, Normalizer.Form.NFD)
                    .replaceAll("[^\\p{ASCII}]", "");
            if (comboBoxCityWithoutDiacritics.equals(cityWithoutDiacritics)) {
                cityComboBox.setSelectedIndex(j);
                break;
            }
        }
    }

    private void populateClientFields() {
        int selectedRow = clientDataTable.getSelectedRow();
        Object[][] tempRowDataObject = clientsRowDataObject;
        if (selectedRow != -1) {
            for (int i = 0; i < tempRowDataObject.length; i++) {
                if (tempRowDataObject[selectedRow][0].toString().equals(clientTableModel.getValueAt(selectedRow, 0).toString())) {
                    propertiesTitleLabel.setText("Se incarca...");
                    idField.setText(tempRowDataObject[selectedRow][0].toString());
                    nameField.setText(tempRowDataObject[selectedRow][1].toString());
                    lastNameField.setText(tempRowDataObject[selectedRow][2].toString());
                    emailField.setText(tempRowDataObject[selectedRow][3].toString());
                    phoneField.setText(tempRowDataObject[selectedRow][4].toString());
                    normalizeComboBoxValues(stateComboBox, cityComboBox, tempRowDataObject, selectedRow);
                    addressField.setText(tempRowDataObject[selectedRow][7].toString());
                }
            }
        }
        propertiesTitleLabel.setText("Detalii inregistrare");
    }

    private void populateReceiptFields() {
        int selectedRow = receiptDataTable.getSelectedRow();
        Object[][] tempRowDataObject = receiptsRowDataObject;
        if (selectedRow != -1) {
            for (int i = 0; i < tempRowDataObject.length; i++) {
                if (tempRowDataObject[selectedRow][0].toString().equals(receiptTableModel.getValueAt(selectedRow, 0).toString())) {
                    propertiesTitleLabel.setText("Se incarca...");
                    idField.setText(tempRowDataObject[selectedRow][0].toString());
                    clientIdField.setText(tempRowDataObject[selectedRow][1].toString());
                    receiptDateField.setText(tempRowDataObject[selectedRow][2].toString());
                    totalPriceField.setText(tempRowDataObject[selectedRow][3].toString());
                    String tempArea = tempRowDataObject[selectedRow][4].toString();
                    receiptProductsTextArea.setText(tempArea);
                }
            }
        }
        propertiesTitleLabel.setText("Detalii inregistrare");
    }

    private void saveClientChanges() {
        String id = idField.getText();
        String name = nameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String state = stateComboBox.getSelectedItem().toString();
        String city = cityComboBox.getSelectedItem().toString();
        String address = addressField.getText();
        errors = true;
        if (id.equals("") || name.equals("") || lastName.equals("") || email.equals("") || phone.equals("") || state.equals("") || city.equals("") || address.equals("")) {
            JOptionPane.showMessageDialog(null, "Toate campurile sunt obligatorii!");
        } else if (!email.contains("@") && !email.contains(".")) {
            JOptionPane.showMessageDialog(null, "Adresa de email nu este valida!");
        } else if (phone.length() != 10) {
            JOptionPane.showMessageDialog(null, "Numarul de telefon trebuie sa contina 10 cifre!");
        }else{
            errors = false;
        }
        if (!errors) {
            for (int i = 0; i < clientsDataObject.length; i++) {
                if (clientsDataObject[i].getId().equals(id)) {
                    clientsDataObject[i].updateClient(id, name, lastName, email, phone, state, city, address);
                    JOptionPane.showMessageDialog(null, "Datele au fost actualizate cu succes!");
                    errors = false;
                }
            }
        }
    }

    private void saveReceiptChanges() {
        String id = idField.getText();
        String clientId = clientIdField.getText();
        String receiptDate = receiptDateField.getText();
        String totalPrice = totalPriceField.getText();
        String receiptProducts = receiptProductsTextArea.getText();
        errors = true;
        if (id.equals("") || clientId.equals("") || receiptDate.equals("") || totalPrice.equals("") || receiptProducts.equals("")) {
            JOptionPane.showMessageDialog(null, "Toate campurile sunt obligatorii!");
        } else {
            for (int i = 0; i < receiptsDataObject.length; i++) {
                if (receiptsDataObject[i].getId().equals(id)) {
                    receiptsDataObject[i].updateReceipt(id, clientId, receiptDate, totalPrice, receiptProducts);
                    JOptionPane.showMessageDialog(null, "Datele au fost actualizate cu succes!");
                    errors = false;
                }
            }
            if (errors) {
                JOptionPane.showMessageDialog(null, "A aparut o eroare!");
                saveReceiptButton.setEnabled(false);
                modifyReceiptButton.setEnabled(false);
                allDataCheckBox.setEnabled(false);
                receiptTableModel.setRowCount(0);
            }
        }
        errors = true;
    }

}
