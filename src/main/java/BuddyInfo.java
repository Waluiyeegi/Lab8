package main.java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BuddyInfo implements Serializable {

    private String address;
    private String name;
    private String phoneNumber;

    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getAddress() {
        return address;
    }

    public BuddyInfo(String name, String phoneNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object bInfo) {
        BuddyInfo newBuddy = (BuddyInfo) bInfo;
        if(this.getName().equals(newBuddy.getName()) && this.getAddress().equals(newBuddy.getAddress()) && this.getPhoneNumber() == newBuddy.getPhoneNumber()){
            return true;
        }
        return false;
    }

    public String toXML(){
        String string = "<BuddyInfo>\n";
        String[] buddyStrings = new String[3];
        buddyStrings = toString().split("#");
        string += "<Name>" + buddyStrings[0] + "</Name>\n";
        string += "<Address>" + buddyStrings[1] + "</Address>\n";
        string += "<PhoneNumber>" + buddyStrings[2] + "</PhoneNumber>\n";
        string += "</BuddyInfo>\n";

        return string;
    }

    public String toString(){
        return this.getName() + "#" + this.getAddress() + "#" + this.getPhoneNumber();
    }


    static public BuddyInfo importBuddyInfo(String buddyFromFile){

        String[] buddyStrings = new String[3];
        buddyStrings = buddyFromFile.split("#");
        System.out.println(Arrays.toString(buddyStrings));
        String name = buddyStrings[0];
        String address = buddyStrings[1];
        String phoneNum = (buddyStrings[2]);
        return new BuddyInfo(name, phoneNum, address);
    }


}