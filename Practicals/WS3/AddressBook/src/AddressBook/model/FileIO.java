package AddressBook.model;
import java.io.*;
import java.util.*;

public class FileIO {

    /**
     * Read the address book file, containing all the names and email addresses.
     *
     * @param fileName The name of the address book file.
     * @return A new AddressBook object containing all the information.
     * @throws IOException If the file cannot be read.
     */
    public AddressBook readAddressBook(String fileName) throws IOException
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
    
}
