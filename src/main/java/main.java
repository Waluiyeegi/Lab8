package main.java;

public class main {
    public static void main(String[] args) {
        AddressBook book = new AddressBook();

        AddressBookView view = new AddressBookView(book);

        AddressBookController controller = new AddressBookController(book, view);
    }
}
