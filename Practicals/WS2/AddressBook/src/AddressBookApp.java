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
        
        System.out.print("Enter address book filename: ");
        fileName = input.nextLine();

        optionMap = new HashMap<Integer,Option>();
        
        try
        {
            AddressBook addressBook = readAddressBook(fileName);

            // Fill our options Map with hard-coded values 
            //so that we can refer to '1' as option 1, '2' as option 2 etc.
            // (1) Search by name, (2) Search by email, (3) Quit");
            addOptions(addressBook);
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

            // File IO:
            Entry newEntry = new Entry(); // contains name + HashSet for Addresses.

            for(int ii=0;ii<length;ii++)
            {
                switch(ii) // Switch for adding any further elements (say another detail re: the user e.g. their middle name)
                        // without needing to refactor everything.
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
        //Entry newUser = new Entry();
        //int selection;
        Option chosen;
        String output = "";
        int menuChoice;
        String export = "";

        while(!done)
        {
            System.out.println("(1) Search by name, (2) Search by email, (3) Display all fields, (4) Quit");
            
            try
            {
                menuChoice = Integer.parseInt(input.nextLine());
                chosen = optionMap.get(menuChoice);

                if(chosen.requiresText() == true)
                {
					System.out.print("Please enter a term: ");
                    export = input.nextLine();
                }
                output = chosen.doOption(export);
                System.out.println("===========\n");
                System.out.println(output + "\n");
                System.out.println("===========\n");

                /* What I had for question (a):
                switch(Integer.parseInt(input.nextLine()))
                {
                    case 1:

                        System.out.print("Enter name: ");
                        String name = input.nextLine();
                        
                        chosen = optionMap.get(1); // 1 Hardcoded to equal SearchByName
                        output = chosen.doOption(name);
                        System.out.println("===========\n");
                        System.out.println(output + "\n");
                        System.out.println("===========\n");
                        break;
                        
                    case 2:
                        System.out.print("Enter email address: ");
                        String email = input.nextLine();
                        
                        chosen = optionMap.get(2); // 2 Hardcoded to equal SearchByEmail
                        output = chosen.doOption(email);
                        System.out.println("===========\n");
                        System.out.println(output + "\n");
                        System.out.println("===========\n");
                        break;                        
                    case 3:
                        done = true;
                        break;
                }*/
            
            }
            catch(NumberFormatException e)
            {
                // The user entered something non-numerical.
                System.out.println("Enter a number");
            }

            catch(NullPointerException e)
            {
                done = true;
            }
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
    }*/

    private static void addOptions(AddressBook inAddressBook)
    {
        optionMap.put(1,new SearchByName(inAddressBook));
        optionMap.put(2,new SearchByEmail(inAddressBook));
        optionMap.put(3,new displayAll(inAddressBook));
    }
}
