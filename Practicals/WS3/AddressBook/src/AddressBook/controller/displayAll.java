package AddressBook.controller;
import java.util.*;
import AddressBook.model.*; // Gets both AddressBook.java and Entry.java as reference.
// Manually we would do import AddressBook.model.AddressBook; & import AddressBook.model.Entry;
/**
 * Contains functionality to display all contents within our address book 
 * ~ names & emails
 * 
 * @author Bharath Sukesh
 */

public class displayAll implements Option
{
    // + doOption(s: String): String

    private AddressBook ab;

    public displayAll(AddressBook inBook)
    {
      ab = inBook;
    }

		// Lec Slides:

    /*  Lec Notes
    for(KeyClass key : myMap.keySet()) // Java
    {
      ValueClass value = myMap.get(key);
      ...
    } // Replace 'KeyClass' and 'ValueClass' as appropriate */

    public String doOption(String name)
    {		
		// ===============
		String export = "";
		
		Map<String, Entry> allAddresses = ab.getNames(); // of the form: Name, Entry
		
    // Reference: For each loop usage for Maps studied from GeeksforGeeks
		for(Map.Entry<String,Entry> entry : allAddresses.entrySet()) // https://www.geeksforgeeks.org/iterate-map-java/
		{
			export += entry.getValue().toString();
		}
        
        return export;
    }


    public boolean requiresText()
    {
      return false;
    }
    
}
