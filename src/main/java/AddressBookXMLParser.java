package main.java;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;

public class AddressBookXMLParser extends DefaultHandler {

    BuddyInfo buddy;
    AddressBook buddys = new AddressBook();

    boolean budName = false;
    boolean budNumb = false;
    boolean budAddr = false;

    @Override
    public void startElement(String u, String ln, String qName, Attributes a) throws SAXException {
        if (qName.equalsIgnoreCase("BuddyInfo")) {
            buddy = new BuddyInfo("","","");
        }
        if (qName.equalsIgnoreCase("Name")) {
            budName = true;
        }
        if (qName.equalsIgnoreCase("PhoneNumber")) {
            budNumb = true;
        }
        if (qName.equalsIgnoreCase("Address")) {
            budAddr = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if(qName.equalsIgnoreCase("BuddyInfo")){
            buddys.addBuddy(buddy);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if(budName){
            System.out.println("Name : " + new String(ch, start, length));
            buddy.setName(new String(ch, start, length));
            budName = false;
        }
        if(budNumb){
            System.out.println("Phone Number : " + new String(ch, start, length));
            buddy.setPhoneNumber(new String(ch, start, length));
            budNumb = false;
        }
        if(budAddr){
            System.out.println("Address : " + new String(ch, start, length));
            buddy.setAddress(new String(ch, start, length));
            budAddr = false;
        }
    }

    public AddressBook getAddressBook(){
        return buddys;
    }

}


