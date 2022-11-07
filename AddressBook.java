package com.bridgelabz.addressbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.*;
import java.util.stream.Collectors;

public class AddressBook {
    Scanner scanner = new Scanner(System.in);
    List<ContactPerson> list = new ArrayList<>();
    AddressBookManager addressBookManager = new AddressBookManager();
    public HashMap<String, ArrayList<ContactPerson>> personByState = new HashMap<String,ArrayList<ContactPerson> >();
    public HashMap<String, ArrayList<ContactPerson>> personByCity = new HashMap<String, ArrayList<ContactPerson>>();


    //@createAddBook --> it creates a addressbook with a user input name
    public void createAddBook() {
        addressBookManager.createAddressBook();
    }
    //this Method is used to get the contacts from the user entered AddressBook..
    public void getContacts() {
        System.out.println("From which addressbook you want to find details?? ");
        String addressBookName = scanner.next();
        addressBookManager.getContactByAddressBook(addressBookName);
    }

    // To add the contacts in addressBook and returns the list of contacts
    public List<ContactPerson> add() {
        System.out.println("Add new Contact to ADDRESS BOOK:--");
        System.out.println("Available address books are: "+ addressBookManager.addressbook.keySet());

        System.out.println("Enter the AddressBook Name to which you want to add contacts: ");
        String addressBookName = scanner.next();
        System.out.println("enter the number of people you want to add in addressbook: ");
        int numberOfUser =scanner.nextInt();

        for (int i = 0; i < numberOfUser; i++) {
            ContactPerson person = new ContactPerson();
            System.out.println("First Name: ");
            String firstName = scanner.next();
            person.setFirstName(firstName);
            if (checkDuplicate(firstName)) {
                System.out.println("Person name already exist");
            } else {

                System.out.println("Last Name: ");
                String lastName = scanner.next();
                person.setLastName(lastName);

                System.out.println("City: ");
                String city = scanner.next();
                person.setCity(city);

                System.out.println("State: ");
                String state = scanner.next();
                person.setState(state);

                System.out.println("EmailID: ");
                String email = scanner.next();
                person.setEmail(email);

                System.out.println("Zip: ");
                int zip = scanner.nextInt();
                person.setZip(zip);

                System.out.println("PhoneNumber: ");
                Long phoneNo = scanner.nextLong();
                person.setPhoneNumber(phoneNo);

                addressBookManager.addContact(addressBookName, person);
                list.add(person);

            }
        }
        return list;

    }
    // @edit is used to edit the contact details of the user

    public List<ContactPerson> edit() {
        System.out.println("Enter the AddressBook Name to which you want to edit contacts: ");
        String addressBookName = scanner.next();

        System.out.println("Editing a contact");
        System.out.println("Enter the name of contact you want to edit: ");
        Scanner input = new Scanner(System.in);
        String editContact = input.next();
        for (ContactPerson i : list) {
            if (i.getFirstName().equals(editContact)) {
                System.out.println("which field you want to edit :" + "\n" + "[1] : Edit firstName" + "\n" + "[2] : Edit LastName" + "\n" + "[3]: Edit Address" + "\n" + "[4] : Edit City " + "\n" + "[5]: Enter zipCode" + "\n" + "[6]: Enter MobileNo" + "\n" + "[7]: Enter EmailId");
                switch (input.nextInt()) {
                    case 1:
                        System.out.println("Change the First Name: ");
                        String editfName = input.next();
                        i.firstName = editfName;
                        break;
                    case 2:
                        System.out.println("Change the Last Name: ");
                        String editlName = input.next();
                        i.lastName = editlName;
                        break;
                    case 3:
                        System.out.println("Change the Address: ");
                        String editAddress = input.next();
                        i.address = editAddress;
                        break;
                    case 4:
                        System.out.println("Change the City: ");
                        String editCity = input.next();
                        i.city = editCity;
                        break;
                    case 5:
                        System.out.println("Change the zip Code: ");
                        int editZipCode = input.nextInt();
                        i.zip = editZipCode;
                        break;
                    case 6:
                        System.out.println("Change the Phone Number: ");
                        Long editPhoneNum = input.nextLong();
                        i.phoneNumber = editPhoneNum;
                        break;
                    case 7:
                        System.out.println("Change the Email ID: ");
                        String editEmail = input.next();
                        i.email = editEmail;
                        break;
                }
                addressBookManager.editContact(addressBookName, i);
                list.add(i);

            }
        }
        return list;
    }
    // @deleteContact is used to delete the contact in a adddressbook
    public boolean deleteContact(String name) {
        int flag = 0;
        for (ContactPerson contact : list) {
            if (contact.getFirstName().equals(name)) {
                list.remove(contact);
                System.out.println("Contact left are " +list);
                flag = 1;
                break;
            }
        }
        return flag == 1;
    }
    // @CheckDuplicat  restricts the user to enter the same first name again in a particular address book .
    public boolean checkDuplicate(String fname) {
        int flag = 0;
        for (ContactPerson p : list) {
            if (p.getFirstName().equals(fname)) {
                flag = 1;
                break;
            }
        }
        return flag == 1;
    }
    // used to filter firstname and lastname of the contact belonging to same state .
    public void getPersonNameByState(String State) {
        List<ContactPerson> details = list.stream().filter(contactName -> contactName.getState().equals(State))
                .collect(Collectors.toList());
        for (ContactPerson contact : details) {
            for (Map.Entry<String, LinkedList> entry : addressBookManager.addressbook.entrySet()) {
                LinkedList value = entry.getValue();
                System.out.println("The Address Book: " + entry.getKey());
            }
            System.out.println("First Name: " + contact.getFirstName());
            System.out.println("Last Name: " + contact.getLastName());
        }
    }
    // used to filter firstname and lastname of the contact belonging to same city .
    public void getPersonNameByCity(String cityName) {
        List<ContactPerson> details2 = list.stream().filter(contactName -> contactName.getCity().equals(cityName))
                .collect(Collectors.toList());
        for (ContactPerson contact : details2) {
            for (Map.Entry<String, LinkedList> entry : addressBookManager.addressbook.entrySet()) {
                LinkedList value = entry.getValue();
                System.out.println("The Address Book: " + entry.getKey());
            }
            System.out.println("First Name: " + contact.getFirstName());
            System.out.println("Last Name: " + contact.getLastName());
        }
    }
    public void CountByCity(String city) {
        int countPersonInCity = 0;
        for (Map.Entry<String, LinkedList> entry : addressBookManager.addressbook.entrySet()) {
            for (int index = 0; index <list.size(); index++){
                if (list.get(index).getCity().equals(city)){
                    System.out.println(list.get(index) );
                    countPersonInCity = index;
                }
            }
        }
        System.out.println("Total number of people in this city " + city + ": " + countPersonInCity);
    }
    public void sortByName(){
        System.out.println("AddressBook present are" + addressBookManager.addressbook.keySet());
        List<ContactPerson> contactPersonList = list.stream().sorted((o1, o2) -> o1.getFirstName().compareTo(o2.getFirstName())).collect(Collectors.toList());
        contactPersonList.forEach(System.out::println);
    }

}