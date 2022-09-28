package controller;
import exceptions.*;
/**
 * Strategy Method implementation ~ for reading.
 * @author Bharath Sukesh
 */
public class readFile implements Option
{
    private Controller control;
        
    public readFile(Controller inControl)
    {
        control = inControl;
    }
    
    @Override
    public void doOption(String filename) throws FileIOException
    {
        control.getCity().readFile(filename);
        //System.out.println("Read called");
    }

    @Override
    public boolean requiresFile() {
        return true;
    }
}


