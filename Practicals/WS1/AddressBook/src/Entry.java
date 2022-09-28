import java.util.*;
        
/**
 * Represents a single address book entry.
 * 
 * @author Bharath Sukesh
 */
public class Entry 
{
    // Insert your code here.
    //      Bharath Sukesh:randomemail@gmail.com
    //      Stephen Curry:stephcurry@hotmail.com
    //      Lionel Messi:liomessi@yahoo.com

    String name;
    HashSet<String> address;

    public Entry()
    {
        name = "";
        // We want MULTIPLE addresses so we use a List or Set.
        // We have UNIQUE addresses. This means no duplication, hence we use Sets (and not Lists).
        // Note: This prac encourages us not to use Arrays.
        address = new HashSet<String>();
    }

    // Now we need getters and setters for this. -- getName, getAddress, setName, setAddress
    // Created by VSCode.

    public String getName() { return name; }

    public Set<String> getAddress() { return address; }

    public void setName(String inName) { this.name = inName; }

    // mySet.add("buffalo");

    public void setAddress(String inAddress) {
        address.add(inAddress);
    }
}
