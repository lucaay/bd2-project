import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Logs  implements ActionListener {


    private JTable logsTable;

    String userName;
    Date Date;
    String action;

    DefaultTableModel tableModel = (DefaultTableModel) logsTable.getModel();
    public Logs() {
        addHeadersTable();
    }

    private void addHeadersTable() {
        Object[] headerObject = new String[] {
                "Username", "Date", "Action"
        };

        for (int i = 0; i < headerObject.length; i++){
            tableModel.addColumn(headerObject[i]);
            tableModel.addColumn(headerObject[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Logs logs = new Logs();
    }
}
