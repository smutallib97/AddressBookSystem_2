package com.bridgelabz.addressbook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AddressBookFileIOService {

    public void writeData(List<ContactPerson> contactList , String addressBookName) {
        StringBuffer personBuffer = new StringBuffer();
        contactList.forEach( person-> {
            String contactDataString = person.toString().concat("\n");
            personBuffer.append(contactDataString);

        });
        try {
            Files.write(Paths.get(addressBookName + ".txt"),personBuffer.toString().getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static List<ContactPerson> readData (){
        List<ContactPerson> contactPersonList = new ArrayList<>();
        try {
            Files.lines(new File("Contact.txt").toPath())
                    .map(line -> line.trim())
                    .forEach(line->System.out.println(line));
        }catch (IOException e){
            e.printStackTrace();
        }
        return contactPersonList;
    }
    public void printData(){
        try {
            Files.lines(new File("Contact.txt").toPath())
                    .forEach(System.out::println);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}