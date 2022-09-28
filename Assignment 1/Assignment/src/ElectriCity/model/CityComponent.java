package model;
import java.util.*;

// Inteface to put Leaf and Composite nodes under the same hood; to City, they are the same.

public abstract class CityComponent implements Comparable<CityComponent>
{

    protected String name; // All nodes would be parented to this name. 
    // i.e. all CityComponents hence Composite/Leaf nodes have a name, and instead of having name as a classfield in both of them,
    // we just do protected name here and super() in the subclasses, so when we make a CityComponent, we know that node will have this name.
    
    public CityComponent(String inName)
    {
        this.name = inName;
    }
    public String getName() { return name; }

    // The entire tree is identifiable by their name.
    @Override 
    public abstract boolean equals(Object obj);

    // Stop myself from calling the wrong constructor.
    // Should be overriden by leaf and printed from there.
    public void addNode(CityComponent c)
    {
        throw new UnsupportedOperationException("Cannot add a Building to this type");
    }

    @Override public int hashCode()
    {
        return name.hashCode();
    }

    // Determines which of two objects is "bigger" - Code Ref: Lecture Slides.
    @Override
    public int compareTo(CityComponent building)
    {
        int export = name.compareTo(building.name); // This is a String comparison. 
        // We want to be able to compare different parts of the Tree based on SOME FACTOR present in both.
        // Comparing by *something* to say "parent is bigger"
        // if export == 0 ..... ?
                /*
                    returns 0 if equal
                    returns -ve if this < building
                    returns +ve if this > building.
                */
        return export;
    }   

    // Should be overriden by composite/leaf and printed from there -- Works.
    public String cityToString()
    {
        throw new UnsupportedOperationException("Cannot print from this type");
    }

    public String cityToFile()
    {
        throw new UnsupportedOperationException("Cannot print from this type");
    }

    public Map<String, Double> calcPower()
    {
        throw new UnsupportedOperationException("Cannot calc from this type");
    }
    
}


