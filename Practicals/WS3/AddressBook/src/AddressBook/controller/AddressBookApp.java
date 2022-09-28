package AddressBook.controller;
import AddressBook.model.AddressBook;  // Gets all of option methods imported in - both subclass methods and superclass interface.                               
import AddressBook.model.FileIO; // Reads file.
import AddressBook.view.showUserMenu; // Displays menu - View method.

import java.io.*;
import java.util.*;


/**
 * A simple address book application.
 * @author Dave and Bharath Sukesh
 */

public class AddressBookApp
{

    private static HashMap<Integer,Option> optionMap; // Integer = Label. Option = Whatever method is called.


    /** Used to obtain user input. */
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args)
    {
        String fileName, entryName;
        showUserMenu menu = new showUserMenu();
        FileIO fileio = new FileIO();
        System.out.print("Enter address book filename: ");
        fileName = input.nextLine();

        optionMap = new HashMap<Integer,Option>();
        
        try
        {
            AddressBook addressBook = fileio.readAddressBook(fileName);

            // Fill our options Map with hard-coded values 
            //so that we can refer to '1' as option 1, '2' as option 2 etc.
            // (1) Search by name, (2) Search by email, (3) Quit");
            addOptions(addressBook);
            menu.showMenu(optionMap);
            
        }
        catch(IOException e)
        {
            System.out.println("Could not read from " + fileName + ": " + e.getMessage());
        }
    }
    
    

    /* From WS1 
    private static void display(Entry inE)
    {
        System.out.println("Name: " + inE.name);
        System.out.println("Emails:");                       
        for(String s: inE.address)
        {
            System.out.println(s + " // ");
        }
    }
    ======
    */

    // I could include this in main but if I had say 6 more options to add, 
    // it would make the method look very messy. 
    private static void addOptions(AddressBook inAddressBook)
    {
        optionMap.put(1,new SearchByName(inAddressBook));
        optionMap.put(2,new SearchByEmail(inAddressBook));
        optionMap.put(3,new displayAll(inAddressBook));
    }
}
