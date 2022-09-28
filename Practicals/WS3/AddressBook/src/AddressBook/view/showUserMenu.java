package AddressBook.view;
import AddressBook.model.AddressBook;
import AddressBook.controller.Option;
import java.util.*;



public class showUserMenu 
{
    /**
     * Show the main menu, offering the user options to (1) search entries by 
     * name, (2) search entries by email, or (3) quit.
     *
     * @param addressBook The AddressBook object to search.
     */
    
    public void showMenu(HashMap<Integer,Option> optionMap)
    {
        boolean done = false;
        //Entry newUser = new Entry();
        //int selection;
        Option chosen;
        String output = "";
        int menuChoice;
        String export = "";
        Scanner input = new Scanner(System.in);

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
    
}
