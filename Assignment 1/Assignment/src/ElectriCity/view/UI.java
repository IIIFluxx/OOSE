package view;
import java.util.*;
/**
 * A view/user interface class that provides output methods.
 */
public class UI 
{
    public void print(String inToString)
    {
        System.out.println(inToString);
    }

    public void printError(String action, String cause)
    {
        spacer();
        System.out.println("ERROR: " + action + " -- cause: -- " + cause);
    }
    
    public void printPower(Map<String,Double>totalPowerMap)
    {
        spacer();
        System.out.println("\n \t" + "Weekday Morning   \t| " + totalPowerMap.get("dm") +"\n");
        System.out.println("\t" + "Weekday Afternoon \t| " + totalPowerMap.get("da") +"\n");
        System.out.println("\t" + "Weekday Evening   \t| " + totalPowerMap.get("de") +"\n");
        System.out.println("\t" + "Weekend Morning   \t| " + totalPowerMap.get("em") +"\n");
        System.out.println("\t" + "Weekend Afternoon \t| " + totalPowerMap.get("ea") +"\n");
        System.out.println("\t" + "Weekday Evening   \t| " + totalPowerMap.get("ee") +"\n");
        System.out.println("\t" + "Heatwave          \t| " + totalPowerMap.get("h") +"\n");
        System.out.println("\t" + "Special Event     \t| " + totalPowerMap.get("s"));
        spacer();
    }

    public void spacer()
    {
        //System.out.println("\n \t" + "" + "\n \t")
        System.out.println("\n==================================\n");
    }
}
