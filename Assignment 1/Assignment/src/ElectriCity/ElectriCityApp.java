import java.util.*;
import controller.*;
import exceptions.FileIOException;
import model.*;
import view.UI;

/**
 * Application for City Electricity network.
 * @author Bharath Sukesh
 */

public class ElectriCityApp
{
    private static HashMap<String,Option> optionMap;

    public static void main(String[] args)
    {
        City city = new City();
        Controller control = new Controller(city);
        UI ui = new UI();
        optionMap = new HashMap<String,Option>();
        // Populates our Map with Option objects
        addOptions(control);
        int optionIndex = 0;
        Option chosenOption1, chosenOption2;

        try
        {
            if(args.length == 0)
            {
                throw new IllegalArgumentException("Please provide some arguments after 'java -jar dist/ElectriCity.jar'");
            }
            //  ================ Error Handle #1: Check if arg1 == "r" || "g" ~~ Works ================
            if(! (   args[0].equals("-r") || args[0].equals("-g") ) ) // If arg[0] != 
            {
                throw new IllegalArgumentException("Loading arguments from command-line -- first argument must be -r or -g");
            }
            // ================ Error Handle #2: If both -r and -g provided. ~~ Works ================
            if(  ( (args[0].equals("-r") && args[2].equals("-g"))  // <r> <filename> <g> -- 0, 1, 2
            || (args[0].equals("-g") && args[1].equals("-r")) ) )   // <g> <r> <filename> -- 0, 1, 2
            {
                throw new IllegalArgumentException("Loading arguments from command-line -- must not provide *both* -g AND -r");
            }

            // ================ Error Handle #3: If -r or -w have no file name provided afterwards. ================
            City newCity = new City();
            Controller newCon = new Controller(newCity);
            //System.out.println("Arg length: " + args.length);
            for(int ii=0; ii<args.length;ii++) // Go through all args.
            {
                //System.out.println("ii = " + ii);
                //System.out.println("arg = " + args[ii]);
                if(   (args[ii].equals("-r")) || (args[ii].equals("-w"))  ) // Find -r or -w
                {
                    try
                    {
                        //System.out.println("Entered try");
                        if(args[ii].equals("-r"))
                        {
                            //System.out.println("Entered " +args[ii]);
                            newCon.getCity().readFile(args[ii+1]);
                        }
                        else if(args[ii].equals("-w"))
                        {
                            //System.out.println("Entered " +args[ii]);
                            newCon.getCity().writeFile(args[ii+1]);
                        }
                        //System.out.println("Okay" +args[ii]);
                    }
                    catch(FileIOException e)
                    {
                        //throw new IllegalArgumentException("File IO/Loading File --- No filename provided after -r or -w");
                        throw new IllegalArgumentException("" + e.getMessage());
                    }
                    catch(IndexOutOfBoundsException e)
                    {
                        throw new IllegalArgumentException("Please provide a correct argument format - " + e.getMessage());
                    }
                }
                // else
                // {
                //     System.out.println("Test");
                // }
            }
            //System.out.println("Test outside");
            // ================ Error Handle #3: If both filenames provided alongside -r/-w are the same. ================
            //check if args[1] == args[3] || args [3] == args[1]
            // -r <filename> -w <filename>
            // 0     1        2     3
            if(args.length > 3) // >= 3 or > 3?
            {
                if(args[1].equals(args[3])) // Works.
                {
                    throw new IllegalArgumentException("Loading arguments from command-line --- filename must be different if reading and writing");
                }
            }

            // Now we can run the program -- after all error checks.
            
            // First arg MUST equal -g or -r, thus we can ensure first option = arg[0]
            chosenOption1 = optionMap.get(args[0]); // this decides which option is to be run. 

            if(chosenOption1.requiresFile()) // requires file i.e. -r
            {
                chosenOption1.doOption(args[1]);
                optionIndex = 2; // Indicates what we're up to
            }
            else // doesn't require filename e.g. -g
            {
                chosenOption1.doOption("");
                optionIndex = 1; // What we're up to for next option.
            }

            // E.g.
                    // If we have -g -d --- option Index = 1 i.e. -g = [0], -d = [1] (where we want to read in)
                    // If we have -g -w [fn] --- option Index = 1 i.e. -g = [0], -w = [1] (where we want to read in)
                    // If we have -r [fn] -d --- option Index = 2 i.e. we want -r = [0], fn = [1], -d = [2] (where we want to read in)
                    // If we have -r [fn] -w [fn] --- same as above except -r = [0], fn = [1], -w = [2] (where we want to read in) 
            chosenOption2 = optionMap.get(args[optionIndex]);
            if(chosenOption2.requiresFile())
            {
                chosenOption2.doOption(args[optionIndex+1]);
            }
            else
            {
                chosenOption2.doOption("");
            }            
        }

        catch(IllegalArgumentException e)
        {
            ui.printError("", e.getMessage());
        }

        catch(IndexOutOfBoundsException e)
        {
            ui.printError("Please provide a correct argument format", e.getMessage());
        }

        catch(Exception e) // Last resort Exception handling.
        {
            try
            { 
                ui.printError("Fatal error has occurred", e.getMessage());}
            catch(Exception r) 
            {
                System.out.println("Fatal error has occurred" + e.getMessage());
            }
        }
    }

    private static void addOptions(Controller inControl)
    {
        optionMap.put("-r", new readFile(inControl));
        optionMap.put("-w", new writeFile(inControl));
        optionMap.put("-d", new displayFile(inControl));
        optionMap.put("-g", new generateFile(inControl));
    }
}