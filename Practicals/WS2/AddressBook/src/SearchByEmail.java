/**
 * Contains functionality to display contents 
 * within our address book based on user input
 * @author Bharath Sukesh
 */

public class SearchByEmail implements Option
{
    // + doOption(s: String): String
    AddressBook ab;

    public SearchByEmail(AddressBook inBook)
    {
      ab = inBook;
    }


    public String doOption(String email)
    {
        Entry newUser = new Entry();

        if(email.equals(""))
        {
          return "The email address you provided is invalid. Please enter a valid address";
        }
        else
        {
          newUser = ab.findE(email);
          return "Name: " + newUser.name + "\n Emails: \n" + newUser.address.toString();
        }
    }

    public boolean requiresText()
    {
      return true;
    }
}
