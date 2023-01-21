import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage {
    JPanel homePageParentPanel;
    private JPanel dataPanel;
    private JPanel propertiesPanel;
    private JPanel filtersPanel;
    private JPanel buttonsPanel;
    private JPanel cartPanel;
    private JButton backToLoginPage;
    private JButton configuratorButton;
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
    private JButton cautaProduseButton;


    public Homepage(ApplicationInterface applicationInterface) {
        backToLoginPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applicationInterface.showLogInPage();
            }
        });
    }

}
