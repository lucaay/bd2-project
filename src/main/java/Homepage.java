import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class Homepage {
    JPanel homePageParentPanel;
    private JPanel propertiesPanel;
    private JPanel dataPanel;
    private JPanel filtersPanel;
    private JPanel buttonsPanel;
    private JPanel cartPanel;
    private JButton backToLoginPage;
    private JButton viewClientsButton;
    private JButton addButton;
    private JButton modifyButton;
    private JButton saveButton;
    private JCheckBox allDataCheckBox;
    private JTextField findByWordField;
    private JLabel findByWordLabel;
    private JRadioButton containsRadioButton;
    private JRadioButton startsWithRadioButton;
    ButtonGroup filterTypeClientsButtonGroup = new ButtonGroup();
    private JTable cartTable;
    private JButton addToCartButton;
    private JButton deleteFromCartButton;
    private JLabel cartProductsLabel;
    private JLabel cartTitleLabel;
    private JButton cartRemoveAllItemsButton;
    private JButton cartBuyButton;
    private JScrollPane cartScrollPane;
    private JScrollPane dataScrollPane;
    private JTable dataTable;
    private JLabel propertiesTitleLabel;
    private JTextField nameField;
    private JLabel nameLabel;
    private JLabel componentTypeLabel;
    private JLabel chipsetMakerLabel;
    private JLabel socketLabel;
    private JLabel memoryTypeLabel;
    private JLabel maxMemoryLabel;
    private JLabel numberOfSlotsLabel;
    private JLabel memoryFreqLabel;
    private JLabel dataPanelTitleLabel;
    private JComboBox componentTypeComboBox;
    private JComboBox chipsetComboBox;
    private JComboBox memoryTypeComboBox;
    private JCheckBox modulationCheckBox;
    private JComboBox ssdTypeComboBox;
    private JComboBox coolingSystemComboBox;
    private JTextField socketField;
    private JTextField maxMemoryField;
    private JTextField numberOfSlotsField;
    private JTextField memoryFreqField;
    private JLabel seriesLabel;
    private JLabel powerLabel;
    private JLabel capacityLabel;
    private JLabel ssdTypeLabel;
    private JLabel maxReadLabel;
    private JLabel maxWriteLabel;
    private JLabel modulationLabel;
    private JLabel memorySizeLabel;
    ButtonGroup orderTypeClientsButtonGroup = new ButtonGroup();
    private JLabel coolingSystemLabel;
    private JTextField seriesField;
    private JTextField memorySizeField;
    private JTextField maxWriteField;
    private JTextField maxReadField;
    private JTextField capacityField;
    private JTextField powerField;
    private JLabel numberOfCoresLabel;
    private JLabel freqLabel;
    private JTextField numberOfCoresField;
    private JTextField freqField;
    private JRadioButton nameOrderRadioButton;
    private JRadioButton componentTypeOrderRadioButton;
    private JLabel memoryEffectiveFreqLabel;
    private JTextField memoryEffectiveFreqField;
    private JLabel numberOfProductsLabel;
    private JButton deleteButton;

    DefaultTableModel dataTableModel = (DefaultTableModel) dataTable.getModel();
    DefaultTableModel cartTableModel = (DefaultTableModel) cartTable.getModel();

    MysqlCon mysqlCon = new MysqlCon();

    Object[] productsDataObject = mysqlCon.retrieveProducts();
    Object[][] productsRawDataObject;
    Object[] cartDataObject = {};
    Object[][] cartRawDataObject;

    private boolean errors = false;
    private boolean addNewProductIsOn = false;

    public Homepage(ApplicationInterface applicationInterface) {
        dataTable.setDefaultEditor(Object.class, null);
        cartTable.setDefaultEditor(Object.class, null);
        hideAllProperties();
        createButtonGroups();
        addHeadersToDataTableAndCartTable();
        int isLogged = mysqlCon.currentLoggedInAccountType;
        if (isLogged == 0) {
            addButton.setVisible(false);
            deleteButton.setVisible(false);
            modifyButton.setVisible(false);
            saveButton.setVisible(false);
            viewClientsButton.setVisible(false);
        } else {
            addButton.setEnabled(true);
        }
        backToLoginPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applicationInterface.showLogInPage();
                addButton.setVisible(true);
                deleteButton.setVisible(true);
                modifyButton.setVisible(true);
                saveButton.setVisible(true);
                viewClientsButton.setVisible(true);
                addButton.setEnabled(false);
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restoreFieldsToDefault();
                enableAllFields();
                componentTypeComboBox.setEnabled(true);
                saveButton.setEnabled(true);
                addButton.setEnabled(false);
                modifyButton.setEnabled(false);
                showProperties(componentTypeComboBox.getSelectedItem().toString()); // show properties for the selected component type
                addNewProductIsOn = true;
                disableFilterProperties();
                allDataCheckBox.setSelected(false);
            }
        });
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enableAllFields();
                saveButton.setEnabled(true);
                addButton.setEnabled(false);
                modifyButton.setEnabled(false);
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addNewProductIsOn) {
                    addNewProduct();
                    addNewProductIsOn = false;
                }else{
                    saveProductChanges(componentTypeComboBox.getSelectedItem().toString());
                }
                saveButton.setEnabled(false);
                modifyButton.setEnabled(true);
                disableAllFields();
                addDataToDataObject();
                addButton.setEnabled(true);

            }
        });
        componentTypeOrderRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDataToDataObject();
            }
        });
        allDataCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disableFilterProperties();
                filterTypeClientsButtonGroup.clearSelection();
                orderTypeClientsButtonGroup.clearSelection();
                findByWordField.setText("");
                addDataToDataObject();
                if (!allDataCheckBox.isSelected()) {
                    enableFilterProperties();
                    dataTableModel.setRowCount(0);
                    numberOfProductsLabel.setText("0 produse");
                }
                addButton.setEnabled(true);
            }
        });
        viewClientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applicationInterface.showClientsAndReceiptsPage();
            }
        });
        dataTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                disableAllFields();
                populateFields(dataTable.getModel().getValueAt(dataTable.getSelectedRow(), 1).toString());
                showProperties(componentTypeComboBox.getSelectedItem().toString());
                modifyButton.setEnabled(true);
                saveButton.setEnabled(false);
                deleteButton.setEnabled(true);
                addToCartButton.setEnabled(true);
            }
        });
        componentTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showProperties(componentTypeComboBox.getSelectedItem().toString()); // show properties for the selected component type
            }
        });


        nameOrderRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDataToDataObject();
            }
        });
        findByWordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                addDataToDataObject();
            }
        });
        containsRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDataToDataObject();
            }
        });
        startsWithRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDataToDataObject();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeProduct();
            }
        });
        cartTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                addToCartButton.setEnabled(false);
                deleteFromCartButton.setEnabled(true);
            }
        });
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToCart();
            }
        });
        deleteFromCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFromCart();
            }
        });
        cartRemoveAllItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cartTableModel.setRowCount(0);
                cartProductsLabel.setText("0 produse");
            }
        });
    }

    private void restoreFieldsToDefault(){
        nameField.setText("");
        componentTypeComboBox.getModel().setSelectedItem("Selecteaza tipul componentei");
        chipsetComboBox.getModel().setSelectedItem("Selecteaza tipul chipsetului");
        socketField.setText("");
        memoryTypeComboBox.getModel().setSelectedItem("Selecteaza tipul memoriei");
        maxMemoryField.setText("");
        numberOfSlotsField.setText("");
        memoryFreqField.setText("");
        modulationCheckBox.setSelected(false);
        memoryEffectiveFreqField.setText("");
        seriesField.setText("");
        numberOfCoresField.setText("");
        freqField.setText("");
        powerField.setText("");
        capacityField.setText("");
        ssdTypeComboBox.getModel().setSelectedItem("Selecteaza tipul SSD-ului");
        maxReadField.setText("");
        maxWriteField.setText("");
        memorySizeField.setText("");
        coolingSystemComboBox.getModel().setSelectedItem("Selecteaza tipul sistemului de racire");
    }

    private void hideAllProperties() {
        nameField.setVisible(false);
        nameLabel.setVisible(false);
        componentTypeLabel.setVisible(false);
        chipsetMakerLabel.setVisible(false);
        socketLabel.setVisible(false);
        memoryTypeLabel.setVisible(false);
        maxMemoryLabel.setVisible(false);
        numberOfSlotsLabel.setVisible(false);
        memoryFreqLabel.setVisible(false);
        seriesLabel.setVisible(false);
        powerLabel.setVisible(false);
        capacityLabel.setVisible(false);
        ssdTypeLabel.setVisible(false);
        maxReadLabel.setVisible(false);
        maxWriteLabel.setVisible(false);
        modulationLabel.setVisible(false);
        memorySizeLabel.setVisible(false);
        memoryEffectiveFreqLabel.setVisible(false);
        coolingSystemLabel.setVisible(false);
        nameField.setVisible(false);
        memorySizeField.setVisible(false);
        maxWriteField.setVisible(false);
        maxReadField.setVisible(false);
        capacityField.setVisible(false);
        powerField.setVisible(false);
        numberOfCoresLabel.setVisible(false);
        freqLabel.setVisible(false);
        numberOfCoresField.setVisible(false);
        freqField.setVisible(false);
        componentTypeComboBox.setVisible(false);
        chipsetComboBox.setVisible(false);
        memoryTypeComboBox.setVisible(false);
        modulationCheckBox.setVisible(false);
        ssdTypeComboBox.setVisible(false);
        coolingSystemComboBox.setVisible(false);
        socketField.setVisible(false);
        maxMemoryField.setVisible(false);
        numberOfSlotsField.setVisible(false);
        memoryFreqField.setVisible(false);
        seriesField.setVisible(false);
        memoryEffectiveFreqField.setVisible(false);
    }

    private void showProperties(String componentType) {
        hideAllProperties();
        nameField.setVisible(true);
        nameLabel.setVisible(true);
        componentTypeLabel.setVisible(true);
        componentTypeComboBox.setVisible(true);
        switch (componentType) {
            case "Placa de baza":
                chipsetMakerLabel.setVisible(true);
                chipsetComboBox.setVisible(true);
                socketLabel.setVisible(true);
                socketField.setVisible(true);
                memoryTypeLabel.setVisible(true);
                memoryTypeComboBox.setVisible(true);
                maxMemoryLabel.setVisible(true);
                maxMemoryField.setVisible(true);
                numberOfSlotsLabel.setVisible(true);
                numberOfSlotsField.setVisible(true);
                memoryFreqLabel.setVisible(true);
                memoryFreqField.setVisible(true);
                break;
            case "Memorie RAM":
                seriesLabel.setVisible(true);
                seriesField.setVisible(true);
                memoryTypeLabel.setVisible(true);
                memoryTypeComboBox.setVisible(true);
                capacityLabel.setVisible(true);
                capacityField.setVisible(true);
                freqLabel.setVisible(true);
                freqField.setVisible(true);
                break;
            case "Stocare SSD":
                seriesLabel.setVisible(true);
                seriesField.setVisible(true);
                ssdTypeLabel.setVisible(true);
                ssdTypeComboBox.setVisible(true);
                capacityLabel.setVisible(true);
                capacityField.setVisible(true);
                maxReadLabel.setVisible(true);
                maxReadField.setVisible(true);
                maxWriteLabel.setVisible(true);
                maxWriteField.setVisible(true);
                break;
            case "Procesor":
                socketLabel.setVisible(true);
                socketField.setVisible(true);
                seriesLabel.setVisible(true);
                seriesField.setVisible(true);
                numberOfCoresLabel.setVisible(true);
                numberOfCoresField.setVisible(true);
                freqLabel.setVisible(true);
                freqField.setVisible(true);
                powerLabel.setVisible(true);
                powerField.setVisible(true);
                memoryTypeLabel.setVisible(true);
                memoryTypeComboBox.setVisible(true);
                maxMemoryLabel.setVisible(true);
                maxMemoryField.setVisible(true);
                memoryFreqLabel.setVisible(true);
                memoryFreqField.setVisible(true);
                chipsetMakerLabel.setVisible(true);
                chipsetComboBox.setVisible(true);

                break;
            case "Placa video":
                chipsetMakerLabel.setVisible(true);
                chipsetComboBox.setVisible(true);
                memoryTypeLabel.setVisible(true);
                memoryTypeComboBox.setVisible(true);
                memoryEffectiveFreqLabel.setVisible(true);
                memoryEffectiveFreqField.setVisible(true);
                seriesLabel.setVisible(true);
                seriesField.setVisible(true);
                powerLabel.setVisible(true);
                powerField.setVisible(true);
                memorySizeLabel.setVisible(true);
                memorySizeField.setVisible(true);
                coolingSystemLabel.setVisible(true);
                coolingSystemComboBox.setVisible(true);
                break;
            case "Sursa":
                modulationLabel.setVisible(true);
                modulationCheckBox.setVisible(true);
                powerLabel.setVisible(true);
                powerField.setVisible(true);

                break;
            default:
                System.out.println("Invalid component type");
        }
    }

    private void disableAllFields() {
        nameField.setEnabled(false);
        memorySizeField.setEnabled(false);
        maxWriteField.setEnabled(false);
        maxReadField.setEnabled(false);
        capacityField.setEnabled(false);
        powerField.setEnabled(false);
        numberOfCoresField.setEnabled(false);
        freqField.setEnabled(false);
        chipsetComboBox.setEnabled(false);
        memoryTypeComboBox.setEnabled(false);
        modulationCheckBox.setEnabled(false);
        ssdTypeComboBox.setEnabled(false);
        coolingSystemComboBox.setEnabled(false);
        socketField.setEnabled(false);
        maxMemoryField.setEnabled(false);
        numberOfSlotsField.setEnabled(false);
        memoryFreqField.setEnabled(false);
        seriesField.setEnabled(false);
        memoryEffectiveFreqField.setEnabled(false);
        componentTypeComboBox.setEnabled(false);
    }

    private void enableAllFields() {
        nameField.setEnabled(true);
        memorySizeField.setEnabled(true);
        maxWriteField.setEnabled(true);
        maxReadField.setEnabled(true);
        capacityField.setEnabled(true);
        powerField.setEnabled(true);
        numberOfCoresField.setEnabled(true);
        freqField.setEnabled(true);
        chipsetComboBox.setEnabled(true);
        memoryTypeComboBox.setEnabled(true);
        modulationCheckBox.setEnabled(true);
        ssdTypeComboBox.setEnabled(true);
        coolingSystemComboBox.setEnabled(true);
        socketField.setEnabled(true);
        maxMemoryField.setEnabled(true);
        numberOfSlotsField.setEnabled(true);
        memoryFreqField.setEnabled(true);
        seriesField.setEnabled(true);
        memoryEffectiveFreqField.setEnabled(true);
    }

    private void createButtonGroups() {
        filterTypeClientsButtonGroup.add(containsRadioButton);
        filterTypeClientsButtonGroup.add(startsWithRadioButton);

        orderTypeClientsButtonGroup.add(nameOrderRadioButton);
        orderTypeClientsButtonGroup.add(componentTypeOrderRadioButton);
    }

    private void addHeadersToDataTableAndCartTable() {
        Object[] headerObject = new String[] {
            "Denumire", "Tip Componenta", "Chipset", "Socket", "Tip memorie", "Memorie maxima", "Numar sloturi", "Frecventa memorie",
                "Modulara", "Frecventa memorie efectiva", "Serie", "Numar nuclee", "Frecventa", "Putere", "Capacitate", "Tip SSD", "Citire maxima",
                "Scriere maxima", "Dimensiune memorie", "Sistem racire"
        };

        for (int i = 0; i < headerObject.length; i++){
            dataTableModel.addColumn(headerObject[i]);
            cartTableModel.addColumn(headerObject[i]);
        }
    }

    private void disableFilterProperties(){
        nameOrderRadioButton.setEnabled(false);
        componentTypeOrderRadioButton.setEnabled(false);
        findByWordField.setEnabled(false);
        containsRadioButton.setEnabled(false);
        startsWithRadioButton.setEnabled(false);
    }
    private void enableFilterProperties(){
        nameOrderRadioButton.setEnabled(true);
        componentTypeOrderRadioButton.setEnabled(true);
        findByWordField.setEnabled(true);
        containsRadioButton.setEnabled(true);
        startsWithRadioButton.setEnabled(true);
    }

    private void createRowDataObject() {
        productsDataObject = mysqlCon.retrieveProducts();
        productsRawDataObject = new Object[productsDataObject.length][];
        for (int i = 0; i < productsDataObject.length; i++) {
            ArrayList<Object> tempList = new ArrayList<>(Arrays.asList((Object[]) productsDataObject[i]));
            tempList.removeIf(Objects::isNull); // remove null values from array
            productsRawDataObject[i] = tempList.toArray();
        }
        for (int i = 0; i < productsRawDataObject.length; i++) {
            System.out.println(Arrays.toString(productsRawDataObject[i]));
        }
    }

    private void addDataToDataObject() {
        createRowDataObject();
        hideAllProperties();
        dataTableModel.setRowCount(0);
        backToDefaultSort();
        modifyButton.setEnabled(false);
        saveButton.setEnabled(false);
        Object[][] tempRowDataObject = productsRawDataObject;
        if (nameOrderRadioButton.isSelected()){
            Arrays.sort(tempRowDataObject, new Comparator<Object[]>() {
                @Override
                public int compare(Object[] o1, Object[] o2) {
                    if (o1 != null && o2 != null){
                        return o1[0].toString().compareTo(o2[0].toString());
                    }else{
                        return 0;
                    }
                }
            });
        } else if (componentTypeOrderRadioButton.isSelected()){
            Arrays.sort(tempRowDataObject, new Comparator<Object[]>() {
                @Override
                public int compare(Object[] o1, Object[] o2) {
                    if (o1 != null && o2 != null){
                        return o1[0].toString().compareTo(o2[0].toString());
                    }else{
                        return 0;
                    }
                }
            });
        }

        for (int i = 0; i < tempRowDataObject.length; i++){
            if (containsRadioButton.isSelected() && !findByWordField.equals("")){
                    if (tempRowDataObject[i] != null){
                if (tempRowDataObject[i][0].toString().toLowerCase().contains(findByWordField.getText().toLowerCase())){
                        addDataAtSpecificIndex(i, tempRowDataObject, dataTableModel);
                }
                    }
            } else if (startsWithRadioButton.isSelected() && !findByWordField.equals("")){
                    if (tempRowDataObject[i] != null){
                if (tempRowDataObject[i][0].toString().toLowerCase().startsWith(findByWordField.getText())){
                        addDataAtSpecificIndex(i, tempRowDataObject, dataTableModel);
                }
                    }
            } else{
                if (tempRowDataObject[i] != null) {
                    addDataAtSpecificIndex(i, tempRowDataObject, dataTableModel);
                }
            }
        }
        numberOfProductsLabel.setText(dataTableModel.getRowCount() + " produse");
    }
    private void addDataAtSpecificIndex(int i, Object[][] dataObject, DefaultTableModel tableModel){
            if (dataObject[i][1].toString().equals("Procesor")) {
                int row = tableModel.getRowCount();
                tableModel.addRow(new Object[] {});
                tableModel.setValueAt(dataObject[i][0].toString(), row, 0); //name
                tableModel.setValueAt(dataObject[i][1].toString(), row, 1); //component type
                tableModel.setValueAt(dataObject[i][2].toString(), row, 2); // chipset
                tableModel.setValueAt(dataObject[i][3].toString(), row, 3); // socket
                tableModel.setValueAt(dataObject[i][4].toString(), row, 4); //memory type
                tableModel.setValueAt(dataObject[i][5].toString(), row, 5); //max memory
                tableModel.setValueAt(dataObject[i][6].toString(), row, 7); //memory freq
                tableModel.setValueAt(dataObject[i][7].toString(), row, 10); // series
                tableModel.setValueAt(dataObject[i][8].toString(), row, 11); // number of cores
                tableModel.setValueAt(dataObject[i][9].toString(), row, 12); // frequency
                tableModel.setValueAt(dataObject[i][10].toString(), row, 13); // power

            } else if (dataObject[i][1].toString().equals("Memorie RAM")) {
                int row = tableModel.getRowCount();
                tableModel.addRow(new Object[] {});
                tableModel.setValueAt(dataObject[i][0].toString(), row, 0); //name
                tableModel.setValueAt(dataObject[i][1].toString(), row, 1); //component type
                tableModel.setValueAt(dataObject[i][2].toString(), row, 4); // memory type
                tableModel.setValueAt(dataObject[i][3].toString(), row, 10); // series
                tableModel.setValueAt(dataObject[i][4].toString(), row, 12); // freq
                tableModel.setValueAt(dataObject[i][5].toString(), row, 14); // capacity
            } else if (dataObject[i][1].toString().equals("Placa de baza")) {
                int row = tableModel.getRowCount();
                tableModel.addRow(new Object[] {});
                tableModel.setValueAt(dataObject[i][0].toString(), row, 0); //name
                tableModel.setValueAt(dataObject[i][1].toString(), row, 1); //component type
                tableModel.setValueAt(dataObject[i][2].toString(), row, 2); // chipset
                tableModel.setValueAt(dataObject[i][3].toString(), row, 3); // socket
                tableModel.setValueAt(dataObject[i][4].toString(), row, 4); // memory type
                tableModel.setValueAt(dataObject[i][5].toString(), row, 5); //max memory
                tableModel.setValueAt(dataObject[i][6].toString(), row, 6); //memory slots
                tableModel.setValueAt(dataObject[i][7].toString(), row, 7); //memory freq


            } else if (dataObject[i][1].toString().equals("Stocare SSD")) {
                int row = tableModel.getRowCount();
                tableModel.addRow(new Object[] {});
                tableModel.setValueAt(dataObject[i][0].toString(), row, 0); //name
                tableModel.setValueAt(dataObject[i][1].toString(), row, 1); //component type
                tableModel.setValueAt(dataObject[i][2].toString(), row, 10); // series
                tableModel.setValueAt(dataObject[i][3].toString(), row, 14); // capacity
                tableModel.setValueAt(dataObject[i][4].toString(), row, 15); // ssd type
                tableModel.setValueAt(dataObject[i][5].toString(), row, 16); // max read
                tableModel.setValueAt(dataObject[i][6].toString(), row, 17); // max write

            } else if (dataObject[i][1].toString().equals("Sursa")) {
                int row = tableModel.getRowCount();
                tableModel.addRow(new Object[] {});
                tableModel.setValueAt(dataObject[i][0].toString(), row, 0); //name
                tableModel.setValueAt(dataObject[i][1].toString(), row, 1); //component type
                tableModel.setValueAt(dataObject[i][2].toString(), row, 8); //modularity
                tableModel.setValueAt(dataObject[i][3].toString(), row, 13); // power
            } else if (dataObject[i][1].toString().equals("Placa video")) {
                int row = tableModel.getRowCount();
                tableModel.addRow(new Object[] {});
                tableModel.setValueAt(dataObject[i][0].toString(), row, 0); //name
                tableModel.setValueAt(dataObject[i][1].toString(), row, 1); //component type
                tableModel.setValueAt(dataObject[i][2].toString(), row, 2); //chipset
                tableModel.setValueAt(dataObject[i][3].toString(), row, 4); //memory type
                tableModel.setValueAt(dataObject[i][4].toString(), row, 9); // memory effective freq
                tableModel.setValueAt(dataObject[i][5].toString(), row, 10); //series
                tableModel.setValueAt(dataObject[i][6].toString(), row, 13); //power
                tableModel.setValueAt(dataObject[i][7].toString(), row, 18); //memory size
                tableModel.setValueAt(dataObject[i][8].toString(), row, 19); //cooling system
            }
    }
    private void backToDefaultSort(){
        //compare names (String)
        Arrays.sort(productsRawDataObject, new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                if (o1 != null && o2 != null){
                    return o1[0].toString().compareTo(o2[0].toString());
                }else{
                    return 0;
                }
            }
        });
    }

    private void populateFields(String componentType){
        int selectedRow = dataTable.getSelectedRow();
        Object[][] tempRowDataObject = productsRawDataObject;
        if (selectedRow != -1) {
            propertiesTitleLabel.setText("Se incarca...");
            for (int i = 0; i < tempRowDataObject.length; i++) {
                if (tempRowDataObject[selectedRow][1].toString().equals("Procesor")) {

                    nameField.setText(tempRowDataObject[selectedRow][0].toString());
                    componentTypeComboBox.setSelectedItem(tempRowDataObject[selectedRow][1].toString());
                    chipsetComboBox.setSelectedItem(tempRowDataObject[selectedRow][2].toString());
                    socketField.setText(tempRowDataObject[selectedRow][3].toString());
                    memoryTypeComboBox.setSelectedItem(tempRowDataObject[selectedRow][4].toString());
                    maxMemoryField.setText(tempRowDataObject[selectedRow][5].toString());
                    memoryFreqField.setText(tempRowDataObject[selectedRow][6].toString());
                    seriesField.setText(tempRowDataObject[selectedRow][7].toString());
                    numberOfCoresField.setText(tempRowDataObject[selectedRow][8].toString());
                    freqField.setText(tempRowDataObject[selectedRow][9].toString());
                    powerField.setText(tempRowDataObject[selectedRow][10].toString());

                } else if (tempRowDataObject[selectedRow][1].toString().equals("Memorie RAM")) {

                    nameField.setText(tempRowDataObject[selectedRow][0].toString());
                    componentTypeComboBox.setSelectedItem(tempRowDataObject[selectedRow][1].toString());
                    memoryTypeComboBox.setSelectedItem(tempRowDataObject[selectedRow][2].toString());
                    seriesField.setText(tempRowDataObject[selectedRow][3].toString());
                    freqField.setText(tempRowDataObject[selectedRow][4].toString());
                    capacityField.setText(tempRowDataObject[selectedRow][5].toString());

                } else if (tempRowDataObject[selectedRow][1].toString().equals("Placa de baza")) {

                    nameField.setText(tempRowDataObject[selectedRow][0].toString());
                    componentTypeComboBox.setSelectedItem(tempRowDataObject[selectedRow][1].toString());
                    chipsetComboBox.setSelectedItem(tempRowDataObject[selectedRow][2].toString());
                    socketField.setText(tempRowDataObject[selectedRow][3].toString());
                    memoryTypeComboBox.setSelectedItem(tempRowDataObject[selectedRow][4].toString());
                    maxMemoryField.setText(tempRowDataObject[selectedRow][5].toString());
                    numberOfSlotsField.setText(tempRowDataObject[selectedRow][6].toString());
                    memoryFreqField.setText(tempRowDataObject[selectedRow][7].toString());


                } else if (tempRowDataObject[selectedRow][1].toString().equals("Stocare SSD")) {

                    nameField.setText(tempRowDataObject[selectedRow][0].toString());
                    componentTypeComboBox.setSelectedItem(tempRowDataObject[selectedRow][1].toString());
                    seriesField.setText(tempRowDataObject[selectedRow][2].toString());
                    capacityField.setText(tempRowDataObject[selectedRow][3].toString());
                    ssdTypeComboBox.setSelectedItem(tempRowDataObject[selectedRow][4].toString());
                    maxReadField.setText(tempRowDataObject[selectedRow][5].toString());
                    maxWriteField.setText(tempRowDataObject[selectedRow][6].toString());

                } else if (tempRowDataObject[selectedRow][1].toString().equals("Sursa")) {

                    nameField.setText(tempRowDataObject[selectedRow][0].toString());
                    componentTypeComboBox.setSelectedItem(tempRowDataObject[selectedRow][1].toString());
                    modulationCheckBox.setSelected(tempRowDataObject[selectedRow][2].toString().equals("Da"));
                    powerField.setText(tempRowDataObject[selectedRow][3].toString());

                } else if (tempRowDataObject[selectedRow][1].toString().equals("Placa video")) {

                    nameField.setText(tempRowDataObject[selectedRow][0].toString());
                    componentTypeComboBox.setSelectedItem(tempRowDataObject[selectedRow][1].toString());
                    chipsetComboBox.setSelectedItem(tempRowDataObject[selectedRow][2].toString());
                    memoryTypeComboBox.setSelectedItem(tempRowDataObject[selectedRow][3].toString());
                    memoryEffectiveFreqField.setText(tempRowDataObject[selectedRow][4].toString());
                    seriesField.setText(tempRowDataObject[selectedRow][5].toString());
                    powerField.setText(tempRowDataObject[selectedRow][6].toString());
                    memorySizeField.setText(tempRowDataObject[selectedRow][7].toString());
                    coolingSystemComboBox.setSelectedItem(tempRowDataObject[selectedRow][8].toString());

                }
            }
        }
        propertiesTitleLabel.setText("Detalii produs");
    }

    private void saveProductChanges(String componentType){
        int selectedRow = dataTable.getSelectedRow();
        String name = nameField.getText();
        String chipset = chipsetComboBox.getSelectedItem().toString();
        String socket = socketField.getText();
        String memoryType = memoryTypeComboBox.getSelectedItem().toString();
        String maxMemory = maxMemoryField.getText();
        String numberOfSlots = numberOfSlotsField.getText();
        String memoryFreq = memoryFreqField.getText();
        String modulation = modulationCheckBox.isSelected() ? "Da" : "Nu";
        String memoryEffectiveFreq = memoryEffectiveFreqField.getText();
        String series = seriesField.getText();
        String numberOfCores = numberOfCoresField.getText();
        String freq = freqField.getText();
        String power = powerField.getText();
        String capacity = capacityField.getText();
        String ssdType = ssdTypeComboBox.getSelectedItem().toString();
        String maxRead = maxReadField.getText();
        String maxWrite = maxWriteField.getText();
        String memorySize = memorySizeField.getText();
        String coolingSystem = coolingSystemComboBox.getSelectedItem().toString();
        errors = true;
        if (productsRawDataObject[selectedRow][1].toString().equals("Procesor")) {
            if (name.equals("") || chipset.equals("") || socket.equals("") || memoryType.equals("") || maxMemory.equals("") || memoryFreq.equals("") || series.equals("") || numberOfCores.equals("") || freq.equals("") || power.equals("")) {
                JOptionPane.showMessageDialog(null, "Toate campurile sunt obligatorii!");
            } else {
                for (int i = 0; i < productsDataObject.length; i++) {
                    if (productsDataObject[i] instanceof Processor && ((Processor) productsDataObject[i]).getName().equals(dataTable.getValueAt(selectedRow, 0).toString())){
                        ((Processor) productsDataObject[i]).updateProcessor(name, "Procesor" , chipset, socket, memoryType, maxMemory, memoryFreq, series, numberOfCores, freq, power);
                        JOptionPane.showMessageDialog(null, "Datele au fost actualizate cu succes!");
                        errors = false;
                    }
                }
                if (errors) {
                    popUpInCaseOfError();
                }
            }
        } else if (productsRawDataObject[selectedRow][1].toString().equals("Placa de baza")) {
            if (name.equals("") || chipset.equals("") || socket.equals("") || memoryType.equals("") || maxMemory.equals("") || numberOfSlots.equals("") || memoryFreq.equals("")) {
                JOptionPane.showMessageDialog(null, "Toate campurile sunt obligatorii!");
            } else {
                for (int i = 0; i < productsDataObject.length; i++) {
                    if (productsDataObject[i] instanceof Motherboard && ((Motherboard) productsDataObject[i]).getName().equals(dataTable.getValueAt(selectedRow, 0).toString())){
                        ((Motherboard) productsDataObject[i]).updateMotherboard(name, "Placa de baza", chipset, socket, memoryType, maxMemory, numberOfSlots, memoryFreq);
                        JOptionPane.showMessageDialog(null, "Datele au fost actualizate cu succes!");
                        errors = false;
                    }
                }
                if (errors) {
                    popUpInCaseOfError();
                }
            }
        } else if (productsRawDataObject[selectedRow][1].toString().equals("Stocare SSD")) {
            if (name.equals("") || series.equals("") || capacity.equals("") || ssdType.equals("") || maxRead.equals("") || maxWrite.equals("")) {
                JOptionPane.showMessageDialog(null, "Toate campurile sunt obligatorii!");
            } else {
                for (int i = 0; i < productsDataObject.length; i++) {
                    if (productsDataObject[i] instanceof Ssd && ((Ssd) productsDataObject[i]).getName().equals(dataTable.getValueAt(selectedRow, 0).toString())){
                        ((Ssd) productsDataObject[i]).updateSsd(name, "Stocare SSD", series, capacity, ssdType, maxRead, maxWrite);
                        JOptionPane.showMessageDialog(null, "Datele au fost actualizate cu succes!");
                        errors = false;
                    }
                }
                if (errors) {
                    popUpInCaseOfError();
                }
            }
        } else if (productsRawDataObject[selectedRow][1].toString().equals("Sursa")) {
            if (name.equals("") || power.equals("")) {
                JOptionPane.showMessageDialog(null, "Toate campurile sunt obligatorii!");
            } else {
                for (int i = 0; i < productsDataObject.length; i++) {
                    if (productsDataObject[i] instanceof Psu && ((Psu) productsDataObject[i]).getName().equals(dataTable.getValueAt(selectedRow, 0).toString())){
                        ((Psu) productsDataObject[i]).updatePsu(name, "Sursa", modulation, power);
                        JOptionPane.showMessageDialog(null, "Datele au fost actualizate cu succes!");
                        errors = false;
                    }
                }
                if (errors) {
                    popUpInCaseOfError();
                }
            }
        } else if (productsRawDataObject[selectedRow][1].toString().equals("Memorie RAM")) {
            if (name.equals("") || memoryType.equals("") || series.equals("") || freq.equals("") || capacity.equals("")) {
                JOptionPane.showMessageDialog(null, "Toate campurile sunt obligatorii!");
            } else {
                for (int i = 0; i < productsDataObject.length; i++) {
                    if (productsDataObject[i] instanceof Memory && ((Memory) productsDataObject[i]).getName().equals(dataTable.getValueAt(selectedRow, 0).toString())){
                        ((Memory) productsDataObject[i]).updateMemory(name, "Memorie RAM", memoryType, series, freq, capacity);
                        JOptionPane.showMessageDialog(null, "Datele au fost actualizate cu succes!");
                        errors = false;
                    }
                }
                if (errors) {
                    popUpInCaseOfError();
                }
            }
        } else if(productsRawDataObject[selectedRow][1].toString().equals("Placa video")) {
            if (name.equals("") || chipset.equals("") || memoryType.equals("") || memoryEffectiveFreq.equals("") || series.equals("") || power.equals("") || memorySize.equals("") || coolingSystem.equals("")){
                JOptionPane.showMessageDialog(null, "Toate campurile sunt obligatorii!");
            } else {
                for (int i = 0; i < productsDataObject.length; i++) {
                    if (productsDataObject[i] instanceof Gpu && ((Gpu) productsDataObject[i]).getName().equals(dataTable.getValueAt(selectedRow, 0).toString())){
                        ((Gpu) productsDataObject[i]).updateGpu(name, "Placa video", chipset, memoryType, memoryEffectiveFreq, series, power, memorySize, coolingSystem);
                        JOptionPane.showMessageDialog(null, "Datele au fost actualizate cu succes!");
                        errors = false;
                    }
                }
                if (errors) {
                    popUpInCaseOfError();
                }
            }
        }
        errors = true;
    }
    private void popUpInCaseOfError() {
        JOptionPane.showMessageDialog(null, "A aparut o eroare!");
        saveButton.setEnabled(false);
        modifyButton.setEnabled(false);
        allDataCheckBox.setEnabled(false);
        dataTableModel.setRowCount(0);
    }

    private void addNewProduct(){
        String name = nameField.getText();
        String componentType = componentTypeComboBox.getSelectedItem().toString();
        String chipset = chipsetComboBox.getSelectedItem().toString();
        String socket = socketField.getText();
        String memoryType = memoryTypeComboBox.getSelectedItem().toString();
        String maxMemory = maxMemoryField.getText();
        String numberOfSlots = numberOfSlotsField.getText();
        String memoryFreq = memoryFreqField.getText();
        String modulation = modulationCheckBox.isSelected() ? "Da" : "Nu";
        String memoryEffectiveFreq = memoryEffectiveFreqField.getText();
        String series = seriesField.getText();
        String numberOfCores = numberOfCoresField.getText();
        String freq = freqField.getText();
        String power = powerField.getText();
        String capacity = capacityField.getText();
        String ssdType = ssdTypeComboBox.getSelectedItem().toString();
        String maxRead = maxReadField.getText();
        String maxWrite = maxWriteField.getText();
        String memorySize = memorySizeField.getText();
        String coolingSystem = coolingSystemComboBox.getSelectedItem().toString();

        if (componentType.equals("Procesor")){
            Processor processor = new Processor(name, componentType, chipset, socket, memoryType, maxMemory, memoryFreq, series, numberOfCores, freq, power);
            productsDataObject = Arrays.copyOf(productsDataObject, productsDataObject.length + 1);
            productsDataObject[productsDataObject.length - 1] = processor;
        }else if (componentType.equals("Placa de baza")){
            Motherboard motherboard = new Motherboard(name, componentType, chipset, socket, memoryType, maxMemory, numberOfSlots, memoryFreq);
            productsDataObject = Arrays.copyOf(productsDataObject, productsDataObject.length + 1);
            productsDataObject[productsDataObject.length - 1] = motherboard;
        }else if (componentType.equals("Stocare SSD")) {
            Ssd ssd = new Ssd(name, componentType, series, capacity, ssdType, maxRead, maxWrite);
            productsDataObject = Arrays.copyOf(productsDataObject, productsDataObject.length + 1);
            productsDataObject[productsDataObject.length - 1] = ssd;
        }else if (componentType.equals("Sursa")) {
            Psu psu = new Psu(name, componentType, modulation, power);
            productsDataObject = Arrays.copyOf(productsDataObject, productsDataObject.length + 1);
            productsDataObject[productsDataObject.length - 1] = psu;
        } else if (componentType.equals("Memorie RAM")) {
            Memory memory = new Memory(name, componentType, memoryType, series, freq, capacity);
            productsDataObject = Arrays.copyOf(productsDataObject, productsDataObject.length + 1);
            productsDataObject[productsDataObject.length - 1] = memory;
        } else if (componentType.equals("Placa video")) {
            Gpu gpu = new Gpu(name, componentType, chipset, memoryType, memoryEffectiveFreq, series, power, memorySize, coolingSystem);
            productsDataObject = Arrays.copyOf(productsDataObject, productsDataObject.length + 1);
            productsDataObject[productsDataObject.length - 1] = gpu;
        }
    }

    private void removeProduct(){
        int selectedRow = dataTable.getSelectedRow();
        if (selectedRow == -1){
            JOptionPane.showMessageDialog(null, "Nu ati selectat niciun produs!");
        } else {
            for (int i = 0; i < productsDataObject.length; i++) {
                if (productsDataObject[i] instanceof Processor && ((Processor) productsDataObject[i]).getName().equals(dataTable.getValueAt(selectedRow, 0).toString())){
                    productsDataObject[i] = null;
                    productsDataObject = Arrays.stream(productsDataObject)
                            .filter(Objects::nonNull)
                            .toArray();
                    JOptionPane.showMessageDialog(null, "Produsul a fost sters cu succes!");
                } else if (productsDataObject[i] instanceof Motherboard && ((Motherboard) productsDataObject[i]).getName().equals(dataTable.getValueAt(selectedRow, 0).toString())){
                    productsDataObject[i] = null;
                    productsDataObject = Arrays.stream(productsDataObject)
                            .filter(Objects::nonNull)
                            .toArray();
                    JOptionPane.showMessageDialog(null, "Produsul a fost sters cu succes!");
                } else if (productsDataObject[i] instanceof Ssd && ((Ssd) productsDataObject[i]).getName().equals(dataTable.getValueAt(selectedRow, 0).toString())){
                    productsDataObject[i] = null;
                    productsDataObject = Arrays.stream(productsDataObject)
                            .filter(Objects::nonNull)
                            .toArray();
                    JOptionPane.showMessageDialog(null, "Produsul a fost sters cu succes!");
                } else if (productsDataObject[i] instanceof Psu && ((Psu) productsDataObject[i]).getName().equals(dataTable.getValueAt(selectedRow, 0).toString())){
                    productsDataObject[i] = null;
                    productsDataObject = Arrays.stream(productsDataObject)
                            .filter(Objects::nonNull)
                            .toArray();
                    JOptionPane.showMessageDialog(null, "Produsul a fost sters cu succes!");
                } else if (productsDataObject[i] instanceof Memory && ((Memory) productsDataObject[i]).getName().equals(dataTable.getValueAt(selectedRow, 0).toString())){
                    productsDataObject[i] = null;
                    productsDataObject = Arrays.stream(productsDataObject)
                            .filter(Objects::nonNull)
                            .toArray();
                    JOptionPane.showMessageDialog(null, "Produsul a fost sters cu succes!");
                } else if (productsDataObject[i] instanceof Gpu && ((Gpu) productsDataObject[i]).getName().equals(dataTable.getValueAt(selectedRow, 0).toString())){
                    productsDataObject[i] = null;
                    productsDataObject = Arrays.stream(productsDataObject)
                            .filter(Objects::nonNull)
                            .toArray();
                    JOptionPane.showMessageDialog(null, "Produsul a fost sters cu succes!");
                }
            }
        }
        addDataToDataObject();
    }


    private void addToCart() {
        int selectedRow = dataTable.getSelectedRow();
        addToCartButton.setEnabled(false);
        cartBuyButton.setEnabled(true);
        cartRemoveAllItemsButton.setEnabled(true);

        String[] tempObject = (String[]) dataTableModel.getDataVector().elementAt(selectedRow).toArray(new String[0]);
        cartTableModel.addRow(tempObject);

        cartProductsLabel.setText(cartTable.getRowCount() + " produse");
    }

    private void deleteFromCart() {
        int selectedRow = cartTable.getSelectedRow();
        if (selectedRow == -1){
            JOptionPane.showMessageDialog(null, "Nu ati selectat niciun produs!");
        } else {
            cartTableModel.removeRow(selectedRow);
            cartProductsLabel.setText(cartTable.getRowCount() + " produse");
        }
        if (cartTable.getRowCount() == 0){
            cartBuyButton.setEnabled(false);
            cartRemoveAllItemsButton.setEnabled(false);
        }
    }

}


