package controller;

/**
 * Strategy Method implementation ~ for displaying
 * @author Bharath Sukesh
 */
public class displayFile implements Option
{
    private Controller control;
        
    public displayFile(Controller inControl)
    {
        control = inControl;
    }
    
    @Override
    public void doOption(String filename)
    {
        control.printAll();
        control.calcTotalPower();
        //System.out.println("Display called");
    }

    @Override
    public boolean requiresFile() {
        return false;
    }
}


