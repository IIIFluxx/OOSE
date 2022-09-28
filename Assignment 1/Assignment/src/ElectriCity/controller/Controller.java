package controller;
import view.UI;
import model.City;
import model.CityComponent;
import java.util.*;

/**
 * City Controller for City Model ~ mainly responsible for holding a City,
 * allowing us to manipulate it on its own as if it were one particular 
 * version of the data set. We can create a string for display or calculate 
 * power values for 'this' City.
 * @author Bharath Sukesh
 */

public class Controller {
    private City city;
    private UI ui = new UI();

    public Controller(City city)
    {
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    // ========================================
    // THIS ITERATES THROUGH THE ENTIRE CITY i.e. the entire TreeSet. 
    public void printAll()
    {
        String export = "";
        for(CityComponent c : city.getSuburbSet())
        {
            export = export + c.cityToString();
        }
        ui.print(export);
    }

    // THIS ITERATES THROUGH THE ENTIRE CITY i.e. the entire TreeSet. 
    public void calcTotalPower()
    {
        Double dm= 0.0, da= 0.0, de= 0.0, em= 0.0, ea= 0.0, ee= 0.0, h = 0.0, s = 0.0;
        Map<String,Double> totalPowerMap = new HashMap<>();
        for(CityComponent c : city.getSuburbSet()) // For all nodes in the Tree 
        {
            Map<String,Double> suburbPowerMap = new HashMap<>();
            suburbPowerMap = c.calcPower(); // == 1 composite node


            dm += suburbPowerMap.get("dm"); // --- dmSum = dmSum + <DMValueFromChildsMap>
            da += suburbPowerMap.get("da");
            de += suburbPowerMap.get("de");
            em += suburbPowerMap.get("em"); // --- emSum = emSum + <EMValueFromChildsMap> 
            ea += suburbPowerMap.get("ea");
            ee += suburbPowerMap.get("ee");
            h += suburbPowerMap.get("h");
            s += suburbPowerMap.get("s");
                // On one iteration, it will do 1 composite node's summations.
                // This is then looped n times, where n = number of nodes in Tree (for loop above).
        }

        totalPowerMap.put("dm", dm);
        totalPowerMap.put("da", da);
        totalPowerMap.put("de", de);
        totalPowerMap.put("em", em);
        totalPowerMap.put("ea", ea);
        totalPowerMap.put("ee", ee);
        totalPowerMap.put("h", h);
        totalPowerMap.put("s", s);

        ui.printPower(totalPowerMap);
       //ui.print("Total power: " + totalPowerMap.toString());        
    }
        // Now suburb power map has all info about ALL its children in one container i.e. 1 Suburb has all the info it needs in 1 container.

        // This calculation is for ONE composite Node. In a Tree there's many composite nodes.
        // Controller iterates over those many composite nodes, and calls <compositeNode>.calcPower() thus calculating power for THAT Comp Node.
        // i.e. We pass suburbPowerMap to Controller for it to sum every category of ALL COMPOSITE NODES.

        // Say the tree has 12 nodes, then Controller will have 12x suburbPower Map's (1 suburbPower Map for each node.
        // Controller's job will then have to be summing up those 12 Maps' values.    
}