package controller;
import exceptions.*;
/**
 * Strategy Method implementation ~ for writing.
 * @author Bharath Sukesh
 */
public class writeFile implements Option
{
    private Controller control;
        
    public writeFile(Controller inControl)
    {
        control = inControl;
    }
    
    @Override
    public void doOption(String filename) throws FileIOException
    {
        control.getCity().writeFile(filename);
        //System.out.println("Write called");
    }

    @Override
    public boolean requiresFile() {
        return true;
    }
}


