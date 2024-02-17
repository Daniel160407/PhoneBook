package org.phonebook.phonebook.database;

import org.phonebook.phonebook.model.Contact;

import java.util.List;

public interface JDBCController {
    List<Contact> getContacts();

    void addNewContact(String name, String number);
}
