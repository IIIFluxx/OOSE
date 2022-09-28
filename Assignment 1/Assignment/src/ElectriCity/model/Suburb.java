package model;
import java.util.*;

// Composite node class.
// Iterates over its children (which can be Buildings also)
public class Suburb extends CityComponent 
{
    // Container to hold all *its* children.
    private Set<CityComponent> buildings = new LinkedHashSet<CityComponent>();  // Represents its children.

    //private String indent = "    ";
    private String whoIsParent;
    
    // Composite Node Constructor 
    public Suburb(String inName)
    {
        super(inName);
        this.whoIsParent = "";
        //this.buildings = new TreeSet<>(); // Initialize container to contain children.
    }

    public Suburb(String name, String parent)
    {
        super(name); // Sets name of itself
        this.whoIsParent = parent;
    }

    // Getter methods.
    // 1. Return Set classfield
    // 2. Index search the Set -- Catalog
    
    // Returns the classfield - Set containing this composite node's Children.
    public Set<CityComponent> getBuildings() {
        return Collections.unmodifiableSet(buildings);
    }

    public boolean hasNode(CityComponent c)
    {
        return buildings.contains(c);
    }

    @Override
    public void addNode(CityComponent c) {
        buildings.add(c); // THIS IS ADDING TO ONLY THIS NODES CHILDREN
    }

    // Display method with 2x Indenting
    @Override
    public String cityToString()
    {
        String export = "";
        String indent = "     ";
        // First addition to export is the CURRENT COMPOSITE Node's information - name. 
        //export = "Suburb (C) name : " + name + ".\n";

        if (!whoIsParent.equals("")) // If has parent -- differentiate parent Composite and parent Node?
        {
            // If Suburb ---> Suburb, it has no way of knowing who is Parent and indenting repeat(2);
            //export = indent.repeat(1) + "Suburb (C) name: " + name + " Parent:<" + whoIsParent + ">\n"; // Debug
            export = indent.repeat(1) + name + "\n";
        }

        else // If no parent i.e. Root
        {
            //export = "Suburb (C) name: " + name + "\n"; // Debug
            export = name + "\n";
        }
        // Then we add information about its children.
        for(CityComponent c : buildings)
        {
            //System.out.println("City: " + name); // remove
            //c.printCityDetails(); // remove
            export = export + c.cityToString();
        }

        return export;
    }

    @Override
    public String cityToFile()
    {
        String export = "";

        // First addition to export is the CURRENT COMPOSITE Node's information - name. 
        if (!whoIsParent.equals("")) // If has parent -- differentiate parent Composite and parent Node?
        {
            // If Suburb ---> Suburb, it has no way of knowing who is Parent and indenting repeat(2);
            export = name + "," + whoIsParent + "\n";
            // This would produce [northside,city\n]
        }

        else // If no parent i.e. Root
        {
            export = name + "\n";
            // This would produce [city\n]
        }
        // Then we add information about its children.
        for(CityComponent c : buildings)
        {
            export += c.cityToFile();
        }

        return export;
    }

    @Override
    public Map<String, Double> calcPower()
    {
        //double power = 0.0;
        Double dm= 0.0, da= 0.0, de= 0.0, em= 0.0, ea= 0.0, ee= 0.0, h = 0.0, s = 0.0;
        Map<String,Double> suburbPowerMap = new HashMap<>();

        for(CityComponent c : buildings) // Iterates over children. If 5 children, it repeats the code within 5 times.
        { // Goal: EM = Sum of EM in Children. DM = Sum of DM in Children.
            // So should it just receive the MAP from the Children, and then sum it all up here?
            
            // Each Leaf Node has 8 values. 
            // It's THIS class's responsibility to sum up everything into 8 big values (Node 1 em + Node 2 em).
            // For loop iterates over all its children -- we just need functionality to do sum up each category inside the for loop i.e. 
                // --- emSum = emSum + <EMValueFromChildsMap> 
                // --- dmSum = dmSum + <DMValueFromChildsMap>
                // ************
                // Then store emSum, dmSum into one (new) container -- new Map say call it suburbPower;
                // ************
                Map<String,Double> childMap = new HashMap<>(); // New map per child.
                childMap = c.calcPower();
                
                dm += childMap.get("dm"); // --- dmSum = dmSum + <DMValueFromChildsMap>
                da += childMap.get("da");
                de += childMap.get("de");
                em += childMap.get("em"); // --- emSum = emSum + <EMValueFromChildsMap> 
                ea += childMap.get("ea");
                ee += childMap.get("ee");
                h += childMap.get("h");
                s += childMap.get("s");
                // On one iteration, it will do 1 child's summations.
                // This is then looped n times, where n = number of children. Thus, we get summation of all children for 1 composite node. 
        }
        // Then store emSum, dmSum into one (new) container -- new Map say call it suburbPower;
        suburbPowerMap.put("dm", dm);
        suburbPowerMap.put("da", da);
        suburbPowerMap.put("de", de);
        suburbPowerMap.put("em", em);
        suburbPowerMap.put("ea", ea);
        suburbPowerMap.put("ee", ee);
        suburbPowerMap.put("h", h);
        suburbPowerMap.put("s", s);

        // Now suburb power map has all info about ALL its children in one container.

        // This calculation is for ONE composite Node. In a Tree there's many composite nodes.
        // Controller iterates over those many composite nodes, and calls <compositeNode>.calcPower() thus calculating power for THAT Comp Node.
        // i.e. We pass suburbPowerMap to Controller for it to sum every category up again.

        // Say the tree has 12 nodes, then Controller will have 12x suburbPower Map's (1 suburbPower Map for each node.
        // Controller's job will then have to be summing up those 12 Maps' values.
        return suburbPowerMap;
    }


    @Override
    public boolean equals(Object obj) 
    {
        boolean isEqual = false;
        if(obj instanceof Suburb)
        {
            isEqual = name.equals(((Suburb)obj).name);
        }
        return isEqual;
    }
}