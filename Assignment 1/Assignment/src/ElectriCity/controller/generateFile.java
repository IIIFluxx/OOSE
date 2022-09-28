package controller;
import exceptions.*;

/**
 * Strategy Method implementation ~ for generating a City.
 * @author Bharath Sukesh
 */

public class generateFile implements Option
{
    private Controller control;
      
    public generateFile(Controller inControl)
    {
        control = inControl;
    }
    
    @Override
    public void doOption(String filename) throws FileIOException
    {
        control.getCity().generateCity();
        //System.out.println("Gen called");
    }

    @Override
    public boolean requiresFile() {
        return false;
    }
}


