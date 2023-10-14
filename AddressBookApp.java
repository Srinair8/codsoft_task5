package Address;

import java.util.ArrayList;
import java.util.Scanner;

class Contact {
    private String name;
    private String phoneNumber;
    private String email;
    private String address;

    public Contact(String name, String phoneNumber, String email, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setName(String newName) {
        name = newName;
    }

    public void setPhoneNumber(String newPhoneNumber) {
        phoneNumber = newPhoneNumber;
    }

    public void setEmail(String newEmail) {
        email = newEmail;
    }

    public void setAddress(String newAddress) {
        address = newAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + email + ", Address: " + address;
    }
}

class AddressBook {
    private ArrayList<Contact> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(String name) {
        contacts.removeIf(contact -> contact.getName().equals(name));
    }

    public ArrayList<Contact> searchContact(String name) {
        ArrayList<Contact> foundContacts = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getName().contains(name)) {
                foundContacts.add(contact);
            }
        }
        return foundContacts;
    }

    public ArrayList<Contact> getAllContacts() {
        return contacts;
    }
}

public class AddressBookApp {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nAddress Book System Menu:");
            System.out.println("1. Add a new contact");
            System.out.println("2. Remove a contact");
            System.out.println("3. Search for a contact");
            System.out.println("4. Display all contacts");
            System.out.println("5. Exit");
            System.out.println("6. Edit contact info");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();


                    if (name.isEmpty() || phoneNumber.isEmpty() || !isValidEmail(email) || !isValidPhoneNumber(phoneNumber)) {
                        System.out.println("Invalid input. Please check your details and try again.");
                    } else {
                        Contact newContact = new Contact(name, phoneNumber, email,address);
                        addressBook.addContact(newContact);
                        System.out.println("Contact added successfully.");
                    }
                    break;

                case "2":
                    System.out.print("Enter name to remove: ");
                    String nameToRemove = scanner.nextLine();
                    addressBook.removeContact(nameToRemove);
                    System.out.println("Contact removed successfully.");
                    break;

                case "3":
                    System.out.print("Enter name to search: ");
                    String nameToSearch = scanner.nextLine();
                    ArrayList<Contact> foundContacts = addressBook.searchContact(nameToSearch);
                    if (!foundContacts.isEmpty()) {
                        System.out.println("Found Contacts:");
                        for (Contact foundContact : foundContacts) {
                            System.out.println(foundContact);
                        }
                    } else {
                        System.out.println("No contacts found.");
                    }
                    break;

                case "4":
                    ArrayList<Contact> allContacts = addressBook.getAllContacts();
                    if (!allContacts.isEmpty()) {
                        System.out.println("All Contacts:");
                        for (Contact contact : allContacts) {
                            System.out.println(contact);
                        }
                    } else {
                        System.out.println("No contacts in the address book.");
                    }
                    break;

                case "5":
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                    break;
                case "6":
                    System.out.print("Enter name to edit: ");
                    String nameToEdit = scanner.nextLine();
                    ArrayList<Contact> contactsToEdit = addressBook.searchContact(nameToEdit);

                    if (!contactsToEdit.isEmpty()) {
                        if (contactsToEdit.size() > 1) {
                            System.out.println("Multiple contacts found with the same name. Please specify the phone number to edit:");
                            for (Contact contact : contactsToEdit) {
                                System.out.println(contact.getName() + " - " + contact.getPhoneNumber());
                            }
                            System.out.print("Enter phone number: ");
                            String phoneNumberToEdit = scanner.nextLine();

                            Contact contactToEdit = findContactByPhoneNumber(contactsToEdit, phoneNumberToEdit);
                            if (contactToEdit != null) {
                                editContactInfo(contactToEdit, scanner);
                                System.out.println("Contact edited successfully.");
                            } else {
                                System.out.println("No contact found with the specified phone number.");
                            }
                        } else {
                            Contact contactToEdit = contactsToEdit.get(0);
                            editContactInfo(contactToEdit, scanner);
                            System.out.println("Contact edited successfully.");
                        }
                    } else {
                        System.out.println("No contacts found with the specified name.");
                    }
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static Contact findContactByPhoneNumber(ArrayList<Contact> contacts, String phoneNumber) {
        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                return contact;
            }
        }
        return null;
    }

    private static void editContactInfo(Contact contact, Scanner scanner) {
        System.out.println("Edit Contact Information:");
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new phone number: ");
        String newPhoneNumber = scanner.nextLine();
        System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine();

        if (!newName.isEmpty() && !newPhoneNumber.isEmpty() && isValidEmail(newEmail)) {
            contact.setName(newName);
            contact.setPhoneNumber(newPhoneNumber);
            contact.setEmail(newEmail);
        } else {
            System.out.println("Invalid input. Please check your details and try again.");
        }
    }
    private static boolean isValidEmail(String email) {
        
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
       
        return phoneNumber.matches("^[0-9]{10}$");
    }
}
