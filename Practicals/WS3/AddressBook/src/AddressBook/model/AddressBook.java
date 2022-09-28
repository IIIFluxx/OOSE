package AddressBook.model;
import java.util.*;
/**
 * Contains all the address book entries.
 * Nothing changed since P01 - used for P02/P03.
 * @author Bharath Sukesh
 */

public class AddressBook
{
    //	- an address book contains a collection of entries -- Container to store 
    // - where each entry can be retrieved by name -- Container to search.

    // Use Maps for Address Book.

    //	- Need to read a file INTO something. Instead of using an Array, use an ArrayList.

    //	- Need to SEARCH FOR ENTRIES. Therefore use a HashMap -- name/key values

    //Map<String,Integer> myMap = new HashMap<>();
    private Map<String, Entry> addresses; // of the form: Name, Entry
    private Map<String, Entry> names; // of the form: Name, Entry

    public AddressBook()
    {
        // Store the Addresses in one container.
        addresses = new HashMap<String, Entry>();
        // Store the Names in another container.
        names = new HashMap<String, Entry>();
    }
    // Methods we need:
    // 1. Insert into the Map -- both maps.
    // 2. Search in the Map -- both names and addresses

    public void insert(Entry inEntry) // Add an entry 
    {
        // Insert entry into Map.
        // of the form: Name, Entry. 

        names.put(inEntry.getName(), inEntry); //Name
        // Since we can't just enter a Set into our Map, we iterate over our Set, 
        // and add each individual element/String in the set to our Map.
        for(String temp : inEntry.getAddress())
        {
            // Put into our address Map.
            addresses.put(temp, inEntry); // temp = all email addresses. Entry = that entry object.
        }
        
    }
    
    public Entry findN(String inName)
    {
        /*
        So I have a name. I need to find an Entry object based on this inputted name, and return a String with that Entry's details.
        */

        Entry newEntry = new Entry(); // stores two things -- String name, HashSet<String> for addresses.
        
        if(inName != null)
        {
            newEntry = names.get(inName);
        }
        
        return newEntry;
    }

    // Pretty much identical method as above.
    public Entry findE(String inEmail)
    {
        Entry newEntry = new Entry(); 

        if(inEmail != null)
        {
            newEntry = addresses.get(inEmail);
        }
        return newEntry;
    }

    // Getters for classfields.
    public Map<String, Entry> getNames() {
        return names;
    }

    public Map<String, Entry> getAddresses() {
        return addresses;
    }

}
