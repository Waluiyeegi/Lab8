import javax.swing.*;
import java.util.ArrayList;

public class AddressBookView extends JFrame{
    AddressBook model;
    private JList<BuddyInfo> buddiesJList;
    DefaultListModel<BuddyInfo> listModel;

    JMenuItem newAddressBookItem, removeBuddyItem, addBuddyItem, save, getFile, saveSerialized, getFileSerialized;
    JMenuBar menuBar;
    JMenu addressMenu, buddyMenu, export, impor;

    public AddressBookView(AddressBook addressModel){
        model = addressModel;
        listModel = model;
        menuBar = new JMenuBar();
        addressMenu = new JMenu("Address");

        buddyMenu = new JMenu("Buddy");

        export = new JMenu("Export");

        impor = new JMenu("Import");

        newAddressBookItem = new JMenuItem("Make New Address Book");
        addBuddyItem = new JMenuItem("Add Buddy");
        removeBuddyItem = new JMenuItem("Remove Buddy");
        save = new JMenuItem("Save");
        getFile = new JMenuItem("Import");
        saveSerialized = new JMenuItem("Save Serialized");
        getFileSerialized = new JMenuItem("Import Serialized");


        addressMenu.add(newAddressBookItem);
        buddyMenu.add(addBuddyItem);
        buddyMenu.add(removeBuddyItem);
        export.add(save);
        impor.add(getFile);
        export.add(saveSerialized);
        impor.add(getFileSerialized);

        menuBar.add(addressMenu);
        menuBar.add(buddyMenu);
        menuBar.add(export);
        menuBar.add(impor);

        buddiesJList = new JList<>(listModel);
        this.setContentPane(new JScrollPane(buddiesJList));

        this.setJMenuBar(menuBar);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Address Book");
        this.setSize(500,500);
        this.setVisible(true);
    }

    public JList<BuddyInfo> getBuddiesJList(){
        return buddiesJList;
    }

}
