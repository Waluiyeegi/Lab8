package main.java;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class AddressBookController implements ActionListener{

    // declaring the variables model and view

    private AddressBook model;
    private AddressBookView view;


    // constructor to initialize
    public AddressBookController(AddressBook model, AddressBookView view) {
        this.model = model;
        this.view = view;

        view.newAddressBookItem.addActionListener(this);
        view.addBuddyItem.addActionListener(this);
        view.save.addActionListener(this);
        view.getFile.addActionListener(this);
        view.getFileSerialized.addActionListener(this);
        view.saveSerialized.addActionListener(this);
    }

    public void addBuddyToAddressBook(BuddyInfo buddy){
        model.addBuddy(buddy);
    }

    private void update(){
        JList list = new JList<>(model);
        view.setContentPane(new JScrollPane(list));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == view.addBuddyItem){
            System.out.println("add buddy was clicked");
            String name = JOptionPane.showInputDialog("Enter Buddy Name: ");
            String address = JOptionPane.showInputDialog("Enter Buddy Address: ");
            String phone = JOptionPane.showInputDialog("Enter Buddy Phone Number: ");
            addBuddyToAddressBook(new BuddyInfo(name, phone, address));

            update();
        }

        if(e.getSource() == view.newAddressBookItem){
            model.clear();
            update();
        }

        if(e.getSource() == view.removeBuddyItem){
            model.removeElement(view.getBuddiesJList().getSelectedIndex());
            update();
        }

        if(e.getSource() == view.save){
            String fileName = JOptionPane.showInputDialog("enter name of file to be saved to: ").trim();
            model.save(fileName);
        }

        if(e.getSource() == view.getFile){
            String fileName = JOptionPane.showInputDialog("enter name of file to be imported: ").trim();
            model = AddressBook.importAddressBook(fileName);
            update();
        }

        if(e.getSource() == view.saveSerialized){
            String fileName = JOptionPane.showInputDialog("enter name of file to be saved to: ").trim();
            model.saveSerializable(fileName);
        }

        if(e.getSource() == view.getFileSerialized){
            String fileName = JOptionPane.showInputDialog("enter name of file to be imported: ").trim();
            model = AddressBook.importAddressBookSerializable(fileName);
            update();
        }

    }
}
