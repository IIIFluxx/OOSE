/**
 * Interface for the relevant menu option.
 * 
 * @author Bharath Sukesh
 */

package AddressBook.controller;


public interface Option
{
    // + doOption(s: String): String
    String doOption(String s);
    boolean requiresText();
} 