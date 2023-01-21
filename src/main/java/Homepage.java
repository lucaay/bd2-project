import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage {
    JPanel homePageParentPanel;
    private JPanel propertiesPanel;
    private JPanel dataPanel;
    private JPanel filtersPanel;
    private JPanel buttonsPanel;
    private JPanel cartButtonPanel;
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


    public Homepage(ApplicationInterface applicationInterface) {
        backToLoginPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applicationInterface.showLogInPage();
            }
        });
    }

}
