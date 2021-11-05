package ui;

import business.ContactBusiness;
import entity.ContactEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainForm extends JFrame{
    private JPanel rootPanel;
    private JButton buttonNewContact;
    private JButton buttonRemove;
    private JTable tableContacts;
    private JLabel labelContactsCount;

    private final ContactBusiness mContactBusiness;
    private String mNameSelected = "";
    private String mPhoneSelected = "";

    public MainForm() {
        setContentPane(this.rootPanel);
        setSize(500,250);
        setVisible(Boolean.TRUE);

        final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        setLocation(dimension.width / 2 - getSize().width / 2, dimension.height / 2 - getSize().height / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mContactBusiness = new ContactBusiness();

        setListeners();
        loadContacts();
    }

    private void loadContacts() {
        final List<ContactEntity> contactList = mContactBusiness.getList();

        String[] columnNames = {"Nome","Telefone"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(new Object[0][0], columnNames);

        for (ContactEntity contact : contactList) {
            final Object[] o = new Object[2];

            o[0] = contact.getName();
            o[1] = contact.getPhone();

            defaultTableModel.addRow(o);
        }

        this.tableContacts.clearSelection();
        this.tableContacts.setModel(defaultTableModel);

        this.labelContactsCount.setText(mContactBusiness.getContactsCountDescription());
    }

    private void setListeners() {
        this.buttonNewContact.addActionListener(actionEvent -> {
            new ContactForm();
            dispose();
        });

        this.tableContacts.getSelectionModel().addListSelectionListener(listSelectionEvent -> {
            if (listSelectionEvent.getValueIsAdjusting()){
                if (this.tableContacts.getSelectedRow() != -1){
                    mNameSelected = this.tableContacts.getValueAt(this.tableContacts.getSelectedRow(), 0).toString();
                    mPhoneSelected = this.tableContacts.getValueAt(this.tableContacts.getSelectedRow(), 1).toString();
                }
            }
        });

        this.buttonRemove.addActionListener(actionEvent -> {
            try {
                mContactBusiness.delete(mNameSelected, mPhoneSelected);
                loadContacts();

                mNameSelected = "";
                mPhoneSelected = "";

            } catch (Exception e){
                JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
            }
        });

    }
}
