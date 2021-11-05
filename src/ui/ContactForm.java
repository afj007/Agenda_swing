package ui;

import business.ContactBusiness;

import javax.swing.*;
import java.awt.*;

public class ContactForm extends JFrame {

    private JPanel rootPanel;
    private JTextField textName;
    private JTextField textPhone;
    private JButton buttonSave;
    private JButton buttonCancel;

    private final ContactBusiness mcontactBusiness;


    public ContactForm() {
        setContentPane(this.rootPanel);
        setSize(500,250);
        setVisible(Boolean.TRUE);
        setLocation(0,450);

        final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dimension.width / 2 - getSize().width / 2, dimension.height / 2 - getSize().height / 2);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mcontactBusiness = new ContactBusiness();

        setListeners();
    }

    private void setListeners() {
        this.buttonSave.addActionListener(actionEvent -> {
            try {
                final String name = this.textName.getText();
                final String phone = this.textPhone.getText();

                mcontactBusiness.save(name, phone);

                new MainForm();
                dispose();
            } catch (Exception e){
                JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
            }

        });


        this.buttonCancel.addActionListener(actionEvent -> {
            new MainForm();
            dispose();
        });
    }

}
