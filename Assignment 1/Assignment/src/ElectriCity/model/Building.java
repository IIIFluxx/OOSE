package model;

import java.util.*;
/**
 * Represents a Composite Node in the Composite Pattern structure 
 * for this City Tree Network. Responsible for holding child nodes 
 * & iterating over them, allowing us to perform whatever functions 
 * we wish from the Tree system.
 * @author Bharath Sukesh
 */
public class Building extends CityComponent
{
    private Map<String,Double> powerMap = new HashMap<>(); // Power Values associated with each Building
    private String whoIsParent = "";
    // Classfields - Container to store 

    // If Building has Parent i.e. Leaf node is not also Root node.
    public Building(String inName, String inParent, Map<String,Double> powerMap) {
        super(inName);
        this.whoIsParent = inParent;
        this.powerMap = powerMap;
    }
    
    public Building(String inName, Map<String,Double> powerMap) { // called when generating.
        super(inName);
        this.whoIsParent = "";
        this.powerMap = powerMap;
    }


    // Display method with 2x Indenting
     @Override
    public String cityToString()
    {
        String export = "";
        String indent = "     ";
        if(!whoIsParent.equals("")) // If it certainly has a parent
        {
            //export = indent.repeat(2) + "Suburb (L) name: " + name + " || Parent: " + whoIsParent + ".\n"; //Debug
            export = indent.repeat(2) + name + "\n";
        }
        else // If this is only node i.e. leaf node = root node = only node e.g. perth,,<values>
        {
            //export = "Suburb (L) name: " + name + " || Parent: " + whoIsParent + ".\n"; // Debug
            export = name + "\n";
        }        
        return export;
    }

    // Goal of this method -- return the NON-ZERO values, as a string separated by commas e.g.
    // [ h=675.015,dm=550.8 ] --- only used for File Writing.
    public String extract()
    {
        String export = "";
        for (HashMap.Entry<String, Double> entry: powerMap.entrySet()) 
        {
            if(Double.compare(entry.getValue(),0.0) != 0)
            {
                export += entry.getKey() + "=" + entry.getValue() + ",";
            }
        }
        export = export.substring(0, export.length() - 1);
        return export;   
    }

    @Override
    public String cityToFile()
    {
        String export = "";
        if(!whoIsParent.equals("")) // If it certainly has a parent
        {
            export = name + "," + whoIsParent + "," + extract() + "\n";
            // This would produce [building3,southside,ee=10956...]
        }
        else // If this is only node i.e. leaf node = root node = only node e.g. perth,,<values> 
        {
            export = name + "," + "," + extract() + "\n"; // has no parent
            // This would produce [city,,ee=....\n]
        }        
        return export;
    }

    public Map<String, Double> getPowerMap() {
        return this.powerMap;
    }

    
    public void setParent(String inParent) {
        this.whoIsParent = inParent;
    }

    // Functionality: It returns this leaf's power values.
    public Map<String, Double> calcPower()
    {
        return this.powerMap;
    }


    @Override
    public boolean equals(Object obj)
    {
        boolean isEqual = false;
        if(obj instanceof Building)
        {
            isEqual = name.equals(((Building)obj).name);
        }
        return isEqual;
    }   
    
}