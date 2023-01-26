import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.cj.xdevapi.Table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

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
    private JButton exportProducts;
    private JButton exportCart;

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
                seriesField.setEnabled(true);
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
                populateFields();
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
        exportProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Document document = new Document(PageSize.A4, 50, 50, 50, 50);
                try {
                    PdfWriter.getInstance(document, new FileOutputStream("iTextTable.pdf"));
                } catch (DocumentException ex) {
                    throw new RuntimeException(ex);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                document.open();
                PdfPTable table = new PdfPTable(3);
                addPDFTableHeader(table);
                addPDFRows(table, dataTableModel);


                try {
                    document.add(table);
                } catch (DocumentException ex) {
                    throw new RuntimeException(ex);
                }
                document.close();
                JOptionPane.showMessageDialog(null, "PDF-ul a fost generat cu succes!");
            }
        });
        exportCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Document document = new Document(PageSize.A4, 50, 50, 50, 50);
                try {
                    PdfWriter.getInstance(document, new FileOutputStream("iTextTable.pdf"));
                } catch (DocumentException ex) {
                    throw new RuntimeException(ex);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                document.open();
                PdfPTable table = new PdfPTable(3);
                addPDFTableHeader(table);
                addPDFRows(table, cartTableModel);


                try {
                    document.add(table);
                } catch (DocumentException ex) {
                    throw new RuntimeException(ex);
                }
                document.close();
                JOptionPane.showMessageDialog(null, "PDF-ul a fost generat cu succes!");
            }
        });
    }

    private void addPDFTableHeader(PdfPTable table) {
        Stream.of("Denumire", "Tip Componenta", "Chipset", "Socket", "Tip memorie", "Memorie maxima", "Numar sloturi", "Frecventa memorie",
                        "Modulara", "Frecventa memorie efectiva", "Serie", "Numar nuclee", "Frecventa", "Putere", "Capacitate", "Tip SSD", "Citire maxima",
                        "Scriere maxima", "Dimensiune memorie", "Sistem racire")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }
    private void addPDFRows(PdfPTable table, TableModel dataTable) {
        for (int i = 0; i < dataTable.getRowCount(); i++) {
            for (int j = 0; j < dataTable.getColumnCount(); j++) {
                if (Objects.equals(dataTable.getValueAt(i, j).toString(), "null")){
                    table.addCell("");
                }else{
                    table.addCell(dataTable.getValueAt(i, j).toString());
                }
            }
        }
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
                seriesLabel.setVisible(true);
                seriesField.setVisible(true);
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
                seriesLabel.setVisible(true);
                seriesField.setVisible(true);

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
        seriesField.setEnabled(false);
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
            productsRawDataObject[i] = (Object[]) productsDataObject[i];
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

    private void addtotablesample(int i ,Object[][] dataObject, DefaultTableModel tableModel){
        int row = tableModel.getRowCount();
        tableModel.addRow(new Object[] {});
        tableModel.setValueAt(dataObject[i][0].toString(), row, 0);
        tableModel.setValueAt(dataObject[i][1].toString(), row, 1);
        tableModel.setValueAt(dataObject[i][2].toString(), row, 2);
        tableModel.setValueAt(dataObject[i][3].toString(), row, 3);
        tableModel.setValueAt(dataObject[i][4].toString(), row, 4);
        tableModel.setValueAt(dataObject[i][5].toString(), row, 5);
        tableModel.setValueAt(dataObject[i][6].toString(), row, 6);
        tableModel.setValueAt(dataObject[i][7].toString(), row, 7);
        tableModel.setValueAt(dataObject[i][8].toString(), row, 8);
        tableModel.setValueAt(dataObject[i][9].toString(), row, 9);
        tableModel.setValueAt(dataObject[i][10].toString(), row, 10);
        tableModel.setValueAt(dataObject[i][11].toString(), row, 11);
        tableModel.setValueAt(dataObject[i][12].toString(), row, 12);
        tableModel.setValueAt(dataObject[i][13].toString(), row, 13);
        tableModel.setValueAt(dataObject[i][14].toString(), row, 14);
        tableModel.setValueAt(dataObject[i][15].toString(), row, 15);
        tableModel.setValueAt(dataObject[i][16].toString(), row, 16);
        tableModel.setValueAt(dataObject[i][17].toString(), row, 17);
        tableModel.setValueAt(dataObject[i][18].toString(), row, 18);
        tableModel.setValueAt(dataObject[i][19].toString(), row, 19);

    }
    private void addtofieldssample(Object[][] dataObject){
        int row = dataTable.getSelectedRow();
        nameField.setText(dataObject[row][0].toString());
        componentTypeComboBox.setSelectedItem(dataObject[row][1].toString());
        chipsetComboBox.setSelectedItem(dataObject[row][2].toString());
        socketField.setText(dataObject[row][3].toString());
        memoryTypeComboBox.setSelectedItem(dataObject[row][4].toString());
        maxMemoryField.setText(dataObject[row][5].toString());
        numberOfSlotsLabel.setText(dataObject[row][6].toString());
        memoryFreqField.setText(dataObject[row][7].toString());
        modulationCheckBox.setSelected(dataObject[row][8].toString() == "Da");
        memoryEffectiveFreqField.setText(dataObject[row][9].toString());
        seriesField.setText(dataObject[row][10].toString());
        numberOfCoresField.setText(dataObject[row][11].toString());
        freqField.setText(dataObject[row][12].toString());
        powerField.setText(dataObject[row][13].toString());
        capacityField.setText(dataObject[row][14].toString());
        ssdTypeComboBox.setSelectedItem(dataObject[row][15].toString());
        maxReadField.setText(dataObject[row][16].toString());
        maxWriteField.setText(dataObject[row][17].toString());
        memorySizeField.setText(dataObject[row][18].toString());
        coolingSystemComboBox.setSelectedItem(dataObject[row][19].toString());
    }
    private void addDataAtSpecificIndex(int i, Object[][] dataObject, DefaultTableModel tableModel){
        addtotablesample(i,dataObject,tableModel);
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

    private void populateFields(){
        Object[][] tempRowDataObject = productsRawDataObject;
        addtofieldssample(tempRowDataObject);
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
                if (productsRawDataObject[selectedRow][0].equals(dataTable.getValueAt(selectedRow, 0).toString())){
                    mysqlCon.updateProduct(name, chipset, socket, memoryType, maxMemory, null, memoryFreq, null, null, series, numberOfCores, freq, power, null, null, null, null, null, null);
                    JOptionPane.showMessageDialog(null, "Datele au fost actualizate cu succes!");
                    errors = false;
                }
            }
        } else if (productsRawDataObject[selectedRow][1].toString().equals("Placa de baza")) {
            if (name.equals("") || chipset.equals("") || socket.equals("") || memoryType.equals("") || maxMemory.equals("") || numberOfSlots.equals("") || memoryFreq.equals("") || series.equals("")) {
                JOptionPane.showMessageDialog(null, "Toate campurile sunt obligatorii!");
            } else {
                    if (productsRawDataObject[selectedRow][0].equals(dataTable.getValueAt(selectedRow, 0).toString())){
                        mysqlCon.updateProduct(name, chipset, socket, memoryType, maxMemory, numberOfSlots, memoryFreq, null, null, series, null, null, null, null, null, null, null, null, null);
                        JOptionPane.showMessageDialog(null, "Datele au fost actualizate cu succes!");
                        errors = false;
                    }
                if (errors) {
                    popUpInCaseOfError();
                }
            }
        } else if (productsRawDataObject[selectedRow][1].toString().equals("Stocare SSD")) {
            if (name.equals("") || series.equals("") || capacity.equals("") || ssdType.equals("") || maxRead.equals("") || maxWrite.equals("")) {
                JOptionPane.showMessageDialog(null, "Toate campurile sunt obligatorii!");
            } else {
                    if (productsRawDataObject[selectedRow][0].equals(dataTable.getValueAt(selectedRow, 0).toString())){
                        mysqlCon.updateProduct(name,null, null, null, null, null, null, null, null, series, null, null, null, capacity, ssdType, maxRead, maxWrite, null, null);
                        JOptionPane.showMessageDialog(null, "Datele au fost actualizate cu succes!");
                        errors = false;
                    }
                if (errors) {
                    popUpInCaseOfError();
                }
            }
        } else if (productsRawDataObject[selectedRow][1].toString().equals("Sursa")) {
            if (name.equals("") || power.equals("") || series.equals("")) {
                JOptionPane.showMessageDialog(null, "Toate campurile sunt obligatorii!");
            } else {
                    if (productsRawDataObject[selectedRow][0].equals(dataTable.getValueAt(selectedRow, 0).toString())){
                        mysqlCon.updateProduct(name, null, null, null, null, null, null, modulation, null, series, null, null, power, null, null, null, null, null, null);
                        JOptionPane.showMessageDialog(null, "Datele au fost actualizate cu succes!");
                        errors = false;
                    }
                if (errors) {
                    popUpInCaseOfError();
                }
            }
        } else if (productsRawDataObject[selectedRow][1].toString().equals("Memorie RAM")) {
            if (name.equals("") || memoryType.equals("") || series.equals("") || freq.equals("") || capacity.equals("")) {
                JOptionPane.showMessageDialog(null, "Toate campurile sunt obligatorii!");
            } else {
                    if (productsRawDataObject[selectedRow][0].equals(dataTable.getValueAt(selectedRow, 0).toString())){
                        mysqlCon.updateProduct(name,null, null, memoryType, null, null, null, modulation, null, series, null, null, null, capacity, null, null, null, null, null);
                        JOptionPane.showMessageDialog(null, "Datele au fost actualizate cu succes!");
                        errors = false;
                    }
                if (errors) {
                    popUpInCaseOfError();
                }
            }
        } else if(productsRawDataObject[selectedRow][1].toString().equals("Placa video")) {
            if (name.equals("") || chipset.equals("") || memoryType.equals("") || memoryEffectiveFreq.equals("") || series.equals("") || power.equals("") || memorySize.equals("") || coolingSystem.equals("")){
                JOptionPane.showMessageDialog(null, "Toate campurile sunt obligatorii!");
            } else {
                    if (productsRawDataObject[selectedRow][0].equals(dataTable.getValueAt(selectedRow, 0).toString())){
                        mysqlCon.updateProduct(name, chipset, null, memoryType, null, null, null, null, null, series, null, null, power, null, null, null, null, memorySize, coolingSystem);
                        JOptionPane.showMessageDialog(null, "Datele au fost actualizate cu succes!");
                        errors = false;
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
        seriesField.setEnabled(false);

        if (componentType.equals("Procesor")){
            mysqlCon.addProduct(name, "Procesor", chipset, socket, memoryType, maxMemory, null, memoryFreq, null, null, series, numberOfCores, freq, power, null, null, null, null, null, null);
        }else if (componentType.equals("Placa de baza")){
            mysqlCon.addProduct(name, "Placa de baza", chipset, socket, memoryType, maxMemory, numberOfSlots, memoryFreq, null, null, series, null, null, null, null, null, null, null, null, null);
        }else if (componentType.equals("Stocare SSD")) {
            mysqlCon.addProduct(name, "Stocare SSD", null, null, null, null, null, null, null, null, series, null, null, null, capacity, ssdType, maxRead, maxWrite, null, null);
        }else if (componentType.equals("Sursa")) {
            mysqlCon.addProduct(name, "Sursa", null, null, null, null, null, null, modulation, null, series, null, null, power, null, null, null, null, null, null);
        } else if (componentType.equals("Memorie RAM")) {
            mysqlCon.addProduct(name, "Memorie RAM", null, null, memoryType, null, null, null, modulation, null, series, null, null, null, capacity, null, null, null, null, null);
        } else if (componentType.equals("Placa video")) {
            mysqlCon.addProduct(name, "Placa video", chipset, null, memoryType, null, null, null, null, null, series, null, null, power, null, null, null, null, memorySize, coolingSystem);
        }
    }

    private void removeProduct(){
        String getSeries = dataTable.getValueAt(dataTable.getSelectedRow(), 10).toString();
        mysqlCon.deleteProduct(getSeries);
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


