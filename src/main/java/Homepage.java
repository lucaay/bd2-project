import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Comparator;

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
    private JLabel cartPriceLabel;
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
    private JTextField slotsNumberField;
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
    private JRadioButton seriesOrderRadioButton;
    private JRadioButton componentTypeOrderRadioButton;
    private JLabel memoryEffectiveFreqLabel;
    private JTextField memoryEffectiveFreqField;
    private JLabel numberOfProductsLabel;

    DefaultTableModel dataTableModel = (DefaultTableModel) dataTable.getModel();
    DefaultTableModel cartTableModel = (DefaultTableModel) cartTable.getModel();

    Processor processor = new Processor("Ryzen 5 3600", "Procesor", "AM4", "642", "DDR2", "64", "3900", "32323YT5", "32", "4500", "50");
    Processor processor1 = new Processor("Ryzen 7 3700X", "Procesor", "AM4", "643", "DDR3", "64", "3900", "32323YT6", "32", "4500", "50");
    Processor processor2 = new Processor("Ryzen 9 3900X", "Procesor", "AM4", "644", "DDR4", "64", "3900", "32323YT7", "32", "4500", "50");
    Memory memory = new Memory("Corsair Vengeance LPX", "Memorie RAM", "DDR4", "64daw8dty", "3900", "8");
    Memory memory1 = new Memory("Kingston", "Memorie RAM", "DDR3", "63daw8dty", "3200", "16");
    Memory memory2 = new Memory("HyperX", "Memorie RAM", "DDR2", "62daw8dty", "4500", "32");
    Motherboard motherboard = new Motherboard("AS-Rock", "Placa de baza", "AM4", "644", "DDR4", "128", "2", "4200");
    Ssd ssd = new Ssd("WD-blue", "Stocare SSD", "56Y9DAWVB", "512", "M.2", "1500", "600");
    Psu psu = new Psu("MYRIA", "Sursa", "Da", "580");
    Gpu gpu = new Gpu("NVIDIA", "Placa video", "ANSEL", "DDR6", "12500", "6dahjwgdjua","780","12", "Activ");

    Object[] productsDataObject = {
            processor,
            processor1,
            processor2,
            memory,
            memory1,
            memory2,
            motherboard,
            ssd,
            psu,
            gpu,
    };
    Object[][] productsRawDataObject;

    private boolean errors = false;

    public Homepage(ApplicationInterface applicationInterface) {
        dataTable.setDefaultEditor(Object.class, null);
        cartTable.setDefaultEditor(Object.class, null);
        hideAllProperties();
        createButtonGroups();
        addHeadersToDataTableAndCartTable();
        backToLoginPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applicationInterface.showLogInPage();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enableAllFields();
                saveButton.setEnabled(true);
                addButton.setEnabled(false);
                modifyButton.setEnabled(false);
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
                disableAllFields();
                saveButton.setEnabled(false);
                addButton.setEnabled(true);
                modifyButton.setEnabled(true);
                showProperties(componentTypeComboBox.getSelectedItem().toString()); // show properties for the selected component type
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
        seriesOrderRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        componentTypeOrderRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                populateFields(dataTable.getModel().getValueAt(dataTable.getSelectedRow(), 1).toString());
                showProperties(componentTypeComboBox.getSelectedItem().toString());
                modifyButton.setEnabled(true);
                saveButton.setEnabled(false);
                addButton.setEnabled(false);
            }
        });
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
        slotsNumberField.setVisible(false);
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
                slotsNumberField.setVisible(true);
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
        slotsNumberField.setEnabled(false);
        memoryFreqField.setEnabled(false);
        seriesField.setEnabled(false);
        memoryEffectiveFreqField.setEnabled(false);
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
        slotsNumberField.setEnabled(true);
        memoryFreqField.setEnabled(true);
        seriesField.setEnabled(true);
        memoryEffectiveFreqField.setEnabled(true);
    }

    private void createButtonGroups() {
        filterTypeClientsButtonGroup.add(containsRadioButton);
        filterTypeClientsButtonGroup.add(startsWithRadioButton);

        orderTypeClientsButtonGroup.add(nameOrderRadioButton);
        orderTypeClientsButtonGroup.add(seriesOrderRadioButton);
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
        seriesOrderRadioButton.setEnabled(false);
        componentTypeOrderRadioButton.setEnabled(false);
        findByWordField.setEnabled(false);
        containsRadioButton.setEnabled(false);
        startsWithRadioButton.setEnabled(false);
    }
    private void enableFilterProperties(){
        nameOrderRadioButton.setEnabled(true);
        seriesOrderRadioButton.setEnabled(true);
        componentTypeOrderRadioButton.setEnabled(true);
        findByWordField.setEnabled(true);
        containsRadioButton.setEnabled(true);
        startsWithRadioButton.setEnabled(true);
    }

    private void createRowDataObject() {
        productsRawDataObject = new Object[productsDataObject.length][];
        for (int i = 0; i < productsDataObject.length; i++) {
            if (productsDataObject[i] instanceof Processor){
                productsRawDataObject[i] = ((Processor) productsDataObject[i]).processorObject();
            }else if (productsDataObject[i] instanceof Memory){
                productsRawDataObject[i] = ((Memory) productsDataObject[i]).memoryObject();
            }else if (productsDataObject[i] instanceof Motherboard){
                productsRawDataObject[i] = ((Motherboard) productsDataObject[i]).motherboardObject();
            }else if (productsDataObject[i] instanceof Ssd){
                productsRawDataObject[i] = ((Ssd) productsDataObject[i]).ssdObject();
            } else if (productsDataObject[i] instanceof Psu){
                productsRawDataObject[i] = ((Psu) productsDataObject[i]).psuObject();
            } else if (productsDataObject[i] instanceof Gpu){
                productsRawDataObject[i] = ((Gpu) productsDataObject[i]).gpuObject();
            }
        }
    }

    private void addDataToDataObject() {
        createRowDataObject();
        hideAllProperties();
        dataTableModel.setRowCount(0);
        backToDefaultSort();
        addButton.setEnabled(false);
        modifyButton.setEnabled(false);
        saveButton.setEnabled(false);
        Object[][] tempRowDataObject = productsRawDataObject;
        if (nameOrderRadioButton.isSelected()){
            Arrays.sort(tempRowDataObject, new Comparator<Object[]>() {
                @Override
                public int compare(Object[] o1, Object[] o2) {
                    return ((String) o1[1]).compareToIgnoreCase((String) o2[1]);
                }
            });
        }
        // ordonare dupa serie
//        else if (alphabeticalReverseOrderClientsRadioButton.isSelected()){
//            Arrays.sort(tempRowDataObject, new Comparator<Object[]>() {
//                @Override
//                public int compare(Object[] o1, Object[] o2) {
//                    return -((String) o1[1]).compareToIgnoreCase((String) o2[1]);
//                }
//            });
//        }
        //ordonare dupa tip componenta

        for (int i = 0; i < tempRowDataObject.length; i++){
            if (containsRadioButton.isSelected() && !findByWordField.equals("")){
                if (tempRowDataObject[i][1].toString().toLowerCase().contains(findByWordField.getText().toLowerCase()) || tempRowDataObject[i][2].toString().toLowerCase().contains(findByWordField.getText().toLowerCase())){
                    addDataAtSpecificIndex(i, tempRowDataObject);
                }
            } else if (startsWithRadioButton.isSelected() && !findByWordField.equals("")){
                if (tempRowDataObject[i][1].toString().toLowerCase().startsWith(findByWordField.getText()) || tempRowDataObject[i][2].toString().toLowerCase().startsWith(findByWordField.getText().toLowerCase())){
                    addDataAtSpecificIndex(i, tempRowDataObject);
                }
            } else{
                addDataAtSpecificIndex(i, tempRowDataObject);
            }
        }
        numberOfProductsLabel.setText(dataTableModel.getRowCount() + " produse");
    }
    private void addDataAtSpecificIndex(int i, Object[][] tempRowDataObject){
            if (tempRowDataObject[i][1].toString().equals("Procesor")) {
                int row = dataTableModel.getRowCount();
                dataTableModel.addRow(new Object[] {});
                dataTableModel.setValueAt(tempRowDataObject[i][0].toString(), row, 0); //name
                dataTableModel.setValueAt(tempRowDataObject[i][1].toString(), row, 1); //component type
                dataTableModel.setValueAt(tempRowDataObject[i][2].toString(), row, 2); // chipset
                dataTableModel.setValueAt(tempRowDataObject[i][3].toString(), row, 3); // socket
                dataTableModel.setValueAt(tempRowDataObject[i][4].toString(), row, 4); //memory type
                dataTableModel.setValueAt(tempRowDataObject[i][5].toString(), row, 5); //max memory
                dataTableModel.setValueAt(tempRowDataObject[i][6].toString(), row, 7); //memory freq
                dataTableModel.setValueAt(tempRowDataObject[i][7].toString(), row, 10); // series
                dataTableModel.setValueAt(tempRowDataObject[i][8].toString(), row, 11); // number of cores
                dataTableModel.setValueAt(tempRowDataObject[i][9].toString(), row, 12); // frequency
                dataTableModel.setValueAt(tempRowDataObject[i][10].toString(), row, 13); // power

            } else if (tempRowDataObject[i][1].toString().equals("Memorie RAM")) {
                int row = dataTableModel.getRowCount();
                dataTableModel.addRow(new Object[] {});
                dataTableModel.setValueAt(tempRowDataObject[i][0].toString(), row, 0); //name
                dataTableModel.setValueAt(tempRowDataObject[i][1].toString(), row, 1); //component type
                dataTableModel.setValueAt(tempRowDataObject[i][2].toString(), row, 4); // memory type
                dataTableModel.setValueAt(tempRowDataObject[i][3].toString(), row, 10); // series
                dataTableModel.setValueAt(tempRowDataObject[i][4].toString(), row, 12); // freq
                dataTableModel.setValueAt(tempRowDataObject[i][5].toString(), row, 14); // capacity
            } else if (tempRowDataObject[i][1].toString().equals("Placa de baza")) {
                int row = dataTableModel.getRowCount();
                dataTableModel.addRow(new Object[] {});
                dataTableModel.setValueAt(tempRowDataObject[i][0].toString(), row, 0); //name
                dataTableModel.setValueAt(tempRowDataObject[i][1].toString(), row, 1); //component type
                dataTableModel.setValueAt(tempRowDataObject[i][2].toString(), row, 2); // chipset
                dataTableModel.setValueAt(tempRowDataObject[i][3].toString(), row, 3); // socket
                dataTableModel.setValueAt(tempRowDataObject[i][4].toString(), row, 4); // memory type
                dataTableModel.setValueAt(tempRowDataObject[i][5].toString(), row, 5); //max memory
                dataTableModel.setValueAt(tempRowDataObject[i][6].toString(), row, 6); //memory slots
                dataTableModel.setValueAt(tempRowDataObject[i][7].toString(), row, 7); //memory freq


            } else if (tempRowDataObject[i][1].toString().equals("Stocare SSD")) {
                int row = dataTableModel.getRowCount();
                dataTableModel.addRow(new Object[] {});
                dataTableModel.setValueAt(tempRowDataObject[i][0].toString(), row, 0); //name
                dataTableModel.setValueAt(tempRowDataObject[i][1].toString(), row, 1); //component type
                dataTableModel.setValueAt(tempRowDataObject[i][2].toString(), row, 10); // series
                dataTableModel.setValueAt(tempRowDataObject[i][3].toString(), row, 14); // capacity
                dataTableModel.setValueAt(tempRowDataObject[i][4].toString(), row, 15); // ssd type
                dataTableModel.setValueAt(tempRowDataObject[i][5].toString(), row, 16); // max read
                dataTableModel.setValueAt(tempRowDataObject[i][6].toString(), row, 17); // max write

            } else if (tempRowDataObject[i][1].toString().equals("Sursa")) {
                int row = dataTableModel.getRowCount();
                dataTableModel.addRow(new Object[] {});
                dataTableModel.setValueAt(tempRowDataObject[i][0].toString(), row, 0); //name
                dataTableModel.setValueAt(tempRowDataObject[i][1].toString(), row, 1); //component type
                dataTableModel.setValueAt(tempRowDataObject[i][2].toString(), row, 8); //modularity
                dataTableModel.setValueAt(tempRowDataObject[i][3].toString(), row, 13); // power
            } else if (tempRowDataObject[i][1].toString().equals("Placa video")) {
                int row = dataTableModel.getRowCount();
                dataTableModel.addRow(new Object[] {});
                dataTableModel.setValueAt(tempRowDataObject[i][0].toString(), row, 0); //name
                dataTableModel.setValueAt(tempRowDataObject[i][1].toString(), row, 1); //component type
                dataTableModel.setValueAt(tempRowDataObject[i][2].toString(), row, 2); //chipset
                dataTableModel.setValueAt(tempRowDataObject[i][3].toString(), row, 4); //memory type
                dataTableModel.setValueAt(tempRowDataObject[i][4].toString(), row, 9); // memory effective freq
                dataTableModel.setValueAt(tempRowDataObject[i][5].toString(), row, 10); //series
                dataTableModel.setValueAt(tempRowDataObject[i][6].toString(), row, 13); //power
                dataTableModel.setValueAt(tempRowDataObject[i][7].toString(), row, 18); //memory size
                dataTableModel.setValueAt(tempRowDataObject[i][8].toString(), row, 19); //cooling system
            }
    }
    private void backToDefaultSort(){
        //compare names (String)
        Arrays.sort(productsRawDataObject, new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                return o1[0].toString().compareTo(o2[0].toString());
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
                    slotsNumberField.setText(tempRowDataObject[selectedRow][6].toString());
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
                    powerField.setText(tempRowDataObject[selectedRow][2].toString());

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
}


