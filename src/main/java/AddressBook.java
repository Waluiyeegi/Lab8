package main.java;

import javax.management.ObjectName;
import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.*;

import java.util.*;
import java.io.*;

public class AddressBook extends DefaultListModel implements Serializable{

    private ArrayList<BuddyInfo> buddies;

    public AddressBook() {
        buddies = new ArrayList<>();
    }

    public int getBookSize(){
        return buddies.size();
    }

    public ArrayList<BuddyInfo> getAddressBookBuddies(){
        return this.buddies;
    }

    public DefaultListModel<BuddyInfo> getDefaultListModel(){
        return this;
    }

    public String toXML(){
        String string = "<addressbook>\n";

        for(BuddyInfo buddy: buddies){
            string += buddy.toXML();
        }
        string += "</addressbook>\n";
        return string;
    }

    public void addBuddy(BuddyInfo buddy) {

        if (buddy != null){
            buddies.add(buddy);
            this.addElement(buddy);
        }
    }

    public void removeBuddy(BuddyInfo buddy) {
        int index = buddies.indexOf(buddy);
        if (index != -1){
            buddies.remove(index);
            this.removeElement(buddy);
        }
    }

    public void save(String fileName){

        try {
            OutputStream outputStream = new FileOutputStream(fileName);

            ArrayList<String> buddyStrings = new ArrayList<>();
            for (BuddyInfo b : buddies) {
                buddyStrings.add(b.toString());
            }
            for (String str : buddyStrings) {
                byte[] buddyBytes = str.getBytes();
                outputStream.write(buddyBytes);
            }
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    static public AddressBook importAddressBook(String fileName){
        Scanner sc = null;
        try {
            sc = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<String> infoStrings = new ArrayList<>();
        while(sc.hasNext()){
            infoStrings.add(sc.nextLine());
        }
        AddressBook book = new AddressBook();
        for (String str : infoStrings) {
            book.addBuddy(BuddyInfo.importBuddyInfo(str));
        }
        return book;
    }



    public void saveSerializable(String fileName){

        try {
            OutputStream outputStream = new FileOutputStream(fileName);
            ObjectOutputStream obOut = new ObjectOutputStream(outputStream);

            obOut.writeObject(this);

            outputStream.flush();
            outputStream.close();
            obOut.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    static public AddressBook importAddressBookSerializable(String fileName){
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fileIn);
            AddressBook book = (AddressBook) ois.readObject();
            ois.close();
            fileIn.close();
            return book;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static AddressBook importAddressBookXML(String fileName){
        SAXParserFactory spf = SAXParserFactory.newInstance();
        try {
            SAXParser s = spf.newSAXParser();
            AddressBookXMLParser parse = new AddressBookXMLParser();
            s.parse(new FileInputStream(fileName), parse);
            return parse.getAddressBook();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void exportAddressBookXML(String fileName) throws IOException{
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.toXML());
        BufferedWriter stream = new BufferedWriter(new FileWriter(fileName));
        stream.write(stringBuffer.toString());
        stream.close();
    }

    public static void main(String[] args) {

    }

}
