/**
 * Contains functionality to display contents 
 * within our address book based on user input
 * @author Bharath Sukesh
 */

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
          return "Name: " + newUser.name + "\n Emails: \n" + newUser.address.toString();
        }
    }


    public boolean requiresText()
    {
      return true;
    }
    
}
