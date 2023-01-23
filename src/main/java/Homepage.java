import javax.swing.*;
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
    private JRadioButton nameSearchButton;
    private JRadioButton idRadioButton;
    private JRadioButton componentTypeRadioButton;
    private JTable cartTable;
    private JButton addToCartButton;
    private JButton deleteFromCartButton;
    private JLabel cartPriceLabel;
    private JLabel cartTitleLabel;
    private JButton cartRemoveAllItemsButton;
    private JButton cartBuyButton;
    private JButton searchProductsButton;
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
    private JLabel memoryEfectiveFreqLabel;
    private JTextField memoryEfectiveFreqField;
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
        memoryEfectiveFreqLabel.setVisible(false);
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
        memoryEfectiveFreqField.setVisible(false);
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
                memoryEfectiveFreqLabel.setVisible(true);
                memoryEfectiveFreqField.setVisible(true);
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
        memoryEfectiveFreqField.setEnabled(false);
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
        memoryEfectiveFreqField.setEnabled(true);
    }

    public Homepage(ApplicationInterface applicationInterface) {
        hideAllProperties();
        showProperties(componentTypeComboBox.getSelectedItem().toString()); // show properties for the selected component type
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
                    startsWithRadioButton.setSelected(false);
            }
        });
        startsWithRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    containsRadioButton.setSelected(false);
            }
        });
        nameSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idRadioButton.setSelected(false);
                componentTypeRadioButton.setSelected(false);
            }
        });
        idRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameSearchButton.setSelected(false);
                componentTypeRadioButton.setSelected(false);
            }
        });
        componentTypeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameSearchButton.setSelected(false);
                idRadioButton.setSelected(false);
            }
        });
        allDataCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(allDataCheckBox.isSelected()) {
                    nameSearchButton.setEnabled(false);
                    idRadioButton.setEnabled(false);
                    componentTypeRadioButton.setEnabled(false);
                    findByWordField.setEnabled(false);
                    containsRadioButton.setEnabled(false);
                    startsWithRadioButton.setEnabled(false);
                    searchProductsButton.setEnabled(false);
                }
                else {
                    nameSearchButton.setEnabled(true);
                    idRadioButton.setEnabled(true);
                    componentTypeRadioButton.setEnabled(true);
                    findByWordField.setEnabled(true);
                    containsRadioButton.setEnabled(true);
                    startsWithRadioButton.setEnabled(true);
                    searchProductsButton.setEnabled(true);
                }
            }
        });
    }

}
