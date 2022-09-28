import java.io.*;
import java.util.*;

/**
 * A simple address book application.
 * @author Dave and Bharath Sukesh
 */
public class AddressBookApp
{
    /** Used to obtain user input. */
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args)
    {
        String fileName, entryName;
        
        System.out.print("Enter address book filename: ");
        fileName = input.nextLine();
        
        try
        {
            AddressBook addressBook = readAddressBook(fileName);
            showMenu(addressBook);
        }
        catch(IOException e)
        {
            System.out.println("Could not read from " + fileName + ": " + e.getMessage());
        }
    }
    
    /**
     * Read the address book file, containing all the names and email addresses.
     *
     * @param fileName The name of the address book file.
     * @return A new AddressBook object containing all the information.
     * @throws IOException If the file cannot be read.
     */
    private static AddressBook readAddressBook(String fileName) throws IOException
    {
        AddressBook addressBook = new AddressBook();
        
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        while(line != null)
        {
            String[] parts = line.split(":");
            int length = parts.length;
            // Insert your code here to add a new address book entry.
            // Note: 
            // parts[0] contains the person's name.
            // parts[1], parts[2], etc. contain the person's email address(es).

            // ========
            // File IO:
            Entry newEntry = new Entry(); // contains name + HashSet for Addresses.

            for(int ii=0;ii<length;ii++)
            {
                switch(ii) // Switch for adding any further elements without needing to refactor.
                {
                    case 0:
                        newEntry.setName(parts[ii]);
                        break;

                    default:
                        newEntry.setAddress(parts[ii]);
                        break;
                }
            }

            addressBook.insert(newEntry);
            // ========
            line = reader.readLine();
        }
        reader.close();
        
        return addressBook;
    }
    
    /**
     * Show the main menu, offering the user options to (1) search entries by 
     * name, (2) search entries by email, or (3) quit.
     *
     * @param addressBook The AddressBook object to search.
     */
    private static void showMenu(AddressBook addressBook)
    {
        boolean done = false;
        Entry newUser = new Entry();
        while(!done)
        {
            int option;
            System.out.println("(1) Search by name, (2) Search by email, (3) Quit");
            
            try
            {
                switch(Integer.parseInt(input.nextLine()))
                {
                    case 1:
                        System.out.print("Enter name: ");
                        String name = input.nextLine();
                        
                        // We've given a name, therefore display all emails.
                        //addressBook.findN(name); 
                        //System.out.println(addressBook.findN(name));

                        // Input: String 
                        // Return: Entry associated with that name.
                        newUser = addressBook.findN(name);
                        // Output: 
                        display(newUser);

                        break;
                        
                    case 2:
                        System.out.print("Enter email address: ");
                        String email = input.nextLine();
                        // Insert your code here to find an entry by email and display it.
                        //System.out.println(addressBook.findE(email));
                        newUser = addressBook.findE(email);
                        // We now have an Entry object filled with a HashSet of Strings. 
                        // Just print it now - logic from Lec Slides
                        display(newUser);
                        break;
                        
                    case 3:
                        done = true;
                        break;
                }
            }
            catch(NumberFormatException e)
            {
                // The user entered something non-numerical.
                System.out.println("Enter a number");
            }
        }
    }

    private static void display(Entry inE)
    {
        System.out.println("Name: " + inE.name);
        System.out.println("Emails:");                       
        for(String s: inE.address)
        {
            System.out.println(s + " // ");
        }
    }

}
