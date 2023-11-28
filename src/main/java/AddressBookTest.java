/*
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookTest {

    @org.junit.jupiter.api.Test
    void fullTestCase(){
        AddressBook adBook = new AddressBook();
        BuddyInfo budInfo1 = new BuddyInfo("Caleb", "123", "Cool");
        BuddyInfo budInfo2 = new BuddyInfo("Joe", "123", "Mama");

        adBook.addBuddy(budInfo1);
        adBook.addBuddy(budInfo2);

        try {
            adBook.exportAddressBookXML("output.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        AddressBook adBook2 = AddressBook.importAddressBookXML("output.xml");
        System.out.println("divider");
        System.out.println(adBook2.toXML());
        System.out.println("divider");
        assertEquals(adBook.toXML(), adBook2.toXML());
    }
}

 */