/**
 * Contains functionality to display contents 
 * within our address book based on user input
 * @author Bharath Sukesh
 */

package AddressBook.controller;
import AddressBook.model.*;

public class SearchByName implements Option
{
    // + doOption(s: String): String

    AddressBook ab;

    public SearchByName(AddressBook inBook)
    {
      ab = inBook;
    }

    public String doOption(String name)
    {
        Entry newUser = new Entry();
        if(name.equals(""))
        {
          return "The name you provided is invalid. Please enter a valid name";
        }
        else
        {
          newUser = ab.findN(name);
          //System.out.println("Name: " + newUser.name);
          return "Name: " + newUser.getName() + "\n Emails: \n" + newUser.getAddress().toString();
        }
    }


    public boolean requiresText()
    {
      return true;
    }
    
}
