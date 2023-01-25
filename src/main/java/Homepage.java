import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    DefaultTableModel dataTableModel = (DefaultTableModel) dataTable.getModel();
    DefaultTableModel cartTableModel = (DefaultTableModel) cartTable.getModel();
    Processor processor = new Processor("Ryzen 5 3600", "Procesor", "AM4", "642", "DDR2", "64", "3900", "32323YT5", "32", "4500", "50");
    Processor processor1 = new Processor("Ryzen 7 3700X", "Procesor", "AM4", "643", "DDR3", "64", "3900", "32323YT6", "32", "4500", "50");
    Processor processor2 = new Processor("Ryzen 9 3900X", "Procesor", "AM4", "644", "DDR4", "64", "3900", "32323YT7", "32", "4500", "50");
    Memory memory = new Memory("Corsair Vengeance LPX", "Memorie RAM", "DDR4", "64daw8dty", "3900", "8");
    Memory memory1 = new Memory("Kingston", "Memorie RAM", "DDR3", "63daw8dty", "3200", "16");
    Memory memory2 = new Memory("HyperX", "Memorie RAM", "DDR2", "62daw8dty", "4500", "32");
    Object[] productsDataObject = {
            processor.processorObject(),
            processor1.processorObject(),
            processor2.processorObject(),
            memory.memoryObject(),
            memory1.memoryObject(),
            memory2.memoryObject(),
    };
    private JRadioButton nameOrderRadioButton;
    private JRadioButton seriesOrderRadioButton;
    private JRadioButton componentTypeOrderRadioButton;
    private JLabel memoryEffectiveFreqLabel;
    private JTextField memoryEffectiveFreqField;


    public Homepage(ApplicationInterface applicationInterface) {
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
                if(allDataCheckBox.isSelected()) {
                    nameOrderRadioButton.setEnabled(false);
                    seriesOrderRadioButton.setEnabled(false);
                    componentTypeOrderRadioButton.setEnabled(false);
                    findByWordField.setEnabled(false);
                    containsRadioButton.setEnabled(false);
                    startsWithRadioButton.setEnabled(false);
                }
                else {
                    nameOrderRadioButton.setEnabled(true);
                    seriesOrderRadioButton.setEnabled(true);
                    componentTypeOrderRadioButton.setEnabled(true);
                    findByWordField.setEnabled(true);
                    containsRadioButton.setEnabled(true);
                    startsWithRadioButton.setEnabled(true);
                }
            }
        });
        viewClientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applicationInterface.showClientsAndReceiptsPage();
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
}


