package model;
import java.util.*;
import exceptions.FileIOException;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Storage class that a Controller takes in. 
 * This class contains the main containers that represent the Tree network.
 * @author Bharath Sukesh
 */

public class City // Storage class. Helpful because Controller takes it in, and can call whatever.
{
    private Set<CityComponent> cityTree = new LinkedHashSet<>(); // Data Structure representing Tree structure
    private Map<String,CityComponent> cityMap = new HashMap<>(); // Used for searching within Tree
    private Random randDouble = new Random();
    public City() 
    {}

    public Set<CityComponent> getSuburbSet()
    { // Returns the TreeSet (Tree hierarchy). Can be iterated through.
        return Collections.unmodifiableSet(cityTree);
    }

    public Map<String, CityComponent> getCityMap() {
        return cityMap;
    }

    public CityComponent getSuburb(String name) //WHEREAS THIS SEARCHES THE MAP
    {
        return cityMap.get(name);
    }

    // Adding functions -- used from File IO 
    public void addNode(CityComponent inNode) // Adds a CityComponent i.e. either a Suburb or Building
    {
        if(cityTree.contains(inNode))
        {
            throw new IllegalArgumentException("CityComponent node already exists");
        }
        
        cityTree.add(inNode); // Adding to the Tree (City network) hierarchy.
            
        cityMap.put(inNode.getName(), inNode); // Add to Map for ease for searching elsewhere.
    }

    //  ============  FILE IO  =============

    // File IO: Reading a file in
    public void readFile(String filename) throws FileIOException
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String line = reader.readLine();           
            while(line != null)
            {
                if(line.trim().length() > 0) // Ignore blank lines
                {
                    String[] parts = line.split(","); 
                    // Debug: System.out.println("Parts.Length  = " + parts.length);                  
                    if(parts.length == 1) // First line -- we only have one element, no commas --- so root. e.g. city (no comma)
                    {
                        parseSuburb(parts[0],"");
                    }
                    else if(parts.length == 2) // One comma - non-root node, but also not a leaf node e.g. southside, city
                    {
                        parseSuburb(parts[0], parts[1]);
                    }

                    else if(parts.length >= 3 && parts.length <= 10) // Else if it is a Leaf node containing Power Values
                    {
                        Map<String,Double> powerMap = generateMap(); // Empty initialised map.
                         // Loop through  from parts[2] (aka length = 3) (min for leaf only tree)
                         // until num of power details (max 8 details) so parts[7]/length = 8.
                        for(int ii = 2;ii<parts.length;ii++) // Start from the 2nd index onwards. parts[1] = parent name.
                        { // i.e. ii from 2 until 8. 
                            // Split again based on comma but our range in For Loop is only after [parent] i.e. parts[1].
                            String[] categories = parts[ii].split("=");
                            // Now we only have ---> [s] = [444.2] , [em] = [540.1], [da] = [97.9]
                            if(Double.valueOf(categories[1]) < 0)
                            {
                                throw new FileIOException(String.format("Provided power value '%s' must be non-negative ", categories[1]));                                    
                            }
                            else
                            {
                                switch(categories[0])
                                {
                                    case "dm": 
                                        powerMap.replace("dm", Double.valueOf(categories[1]));
                                        break;
                                    case "da": 
                                        powerMap.replace("da", Double.valueOf(categories[1]));
                                        break;
                                    case "de": 
                                        powerMap.replace("de", Double.valueOf(categories[1]));
                                        break;
                                    case "em": 
                                        powerMap.replace("em", Double.valueOf(categories[1]));
                                        break;
                                    case "ea": 
                                        powerMap.replace("ea", Double.valueOf(categories[1]));
                                        break;
                                    case "ee": 
                                        powerMap.replace("ee", Double.valueOf(categories[1]));
                                        break;
                                    case "h": 
                                        powerMap.replace("h", Double.valueOf(categories[1]));
                                        break;
                                    case "s": 
                                        powerMap.replace("s", Double.valueOf(categories[1]));
                                        break;
                                    default: 
                                        //throw new FileIOException("Provided power value are invalid");
                                        throw new FileIOException(String.format("Provided power value '%s' is invalid ", categories[0]));                                    
                                }
                            }
                        }
                        parseBuilding(parts[0], parts[1], powerMap);  // parts[0] = name, parts[1] = parent
                    }
                    else
                    {
                        throw new FileIOException("Invalid line length provided in data file.");
                    } // End else
                } // End if
                line = reader.readLine();
            } //End while
        } // End try
        catch(FileIOException e)
        {
            throw new FileIOException("" + e.getMessage(), e);
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            throw new FileIOException("Parsing error ~ invalid arguments provided -- " + e.getMessage(), e);
        }
        catch(FileNotFoundException e)
        {
            throw new FileIOException(String.format("Filename '%s' cannot be found -- " + e.getMessage(), filename), e);
        }
        catch(IOException e)
        {
            throw new FileIOException(
                String.format("IO error occurred while reading '%s': '%s'",
                filename, e.getMessage()));
        } // End try-catch        
    }// End method
    
    // Searches the Map for duplicates.
    public boolean isUnique(String name)
    {
        boolean export = false;
        // Shouldn't be able to add node with same name/existing name  ~ serach Map if exists
        CityComponent entry = cityMap.get(name); // map contains <String, CityComponent>
        // Get the entry by name, if returns null, then unique.
        if(entry == null)  // If it return null, then we want to allow entry.
        { export = true; }
        else { export = false; }
        return export;
    }
    // Names must only be letters/numbers with no spaces or special characters.
    public boolean isValid(String name)
    {
        boolean export = false;
        for(int ii=0;ii<name.length();ii++)
        {
            if (name.contains(" ") || (Character.isLetterOrDigit(name.charAt(ii)) == false)) 
            {
                export = false;
                break;
            }
            else
            {
                export = true;
            }
        }
        if(name.equals(""))
        {
            export = true;
        }
        return export;
    }

    // Responsible for reading a given Suburb entry from file
    // and performing relevant error handling if need be. 
    public void parseSuburb(String name, String parent) throws FileIOException
    {
        CityComponent newSub;
        if(name == null || parent == null)
        {
            throw new FileIOException("Provided Suburb name/parent is invalid");
        }
        if((isValid(name) && isValid(parent)))
        {
            // Case 1: Suburb has no parent == Root.
            if(parent.equals(""))
            {
                if(isUnique(name))
                {
                    newSub = new Suburb(name);
                    addNode(newSub);
                }
            }
            else if (!(parent.equals(""))) // Case 2: Suburb has parent - not root.
            {
                if(isUnique(name) && isUnique(parent) == false)
                {
                    newSub = new Suburb(name,parent);
                    addNode(newSub);
                }
                else
                {
                    throw new FileIOException(
                        String.format("Error in Parsing ~ Suburb line '%s,%s' is not unique", name, parent));
                }
            }
            else
            {
                throw new FileIOException(String.format("Error in Parsing ~ Suburb line '%s,%s' is not unique", name, parent));
            }
        }
        else
        {
            throw new FileIOException(
                String.format("Error in Parsing Suburb line ~ Provided names are not valid. See line '%s' , '%s'", name, parent));
        }
    }

    // Responsible for reading a given Building entry from file
    // and performing relevant error handling if need be.
    public void parseBuilding(String name, String parent, Map<String,Double> inMap) throws FileIOException
    {
        CityComponent newBuilding;
        if(name == null || parent == null)
        {
            throw new FileIOException("Provided Building name/parent is invalid");
        }
        if((isValid(name) && isValid(parent)))
        {
            // Case 1: Building/Leaf has no parent == Root.
            if(parent.equals(""))
            {
                if(isUnique(name)) // It has no parent, so we don't check if parent is unique.
                {
                    newBuilding = new Building(name, inMap);
                    addNode(newBuilding);
                }
            }
            else if(!(parent.equals("")))// Case 2: Building/Leaf has parent == Regular Leaf.
            {
                if(isUnique(name) && (isUnique(parent) == false)) // We must check that parent exists
                { // If the names are valid + name to add Node is unique, proceed.
                        newBuilding = new Building(name, parent, inMap);
                        addNode(newBuilding);
                }
                else
                {
                    throw new FileIOException(String.format("Error in Parsing ~ Building line '%s,%s' is not unique", name, parent));
                }
            }
            else
            {
                throw new FileIOException(String.format("Error in Parsing ~ Building line '%s,%s' is not unique", name, parent));
            }
        }
        else
        {
            throw new FileIOException(
                String.format("Error in Parsing Building line ~ Provided names are not valid. See line '%s','%s'", name, parent));
        }
    }

    // File IO: Writing the Tree network to a file specified by the filename.
    public void writeFile(String filename) throws FileIOException
    {
        FileOutputStream fileStrm = null;
        PrintWriter pw;
        // Iterate over our Tree container - LinkedHashMap stores the order in which it was read in.
        String export = "";
        try
        {
            fileStrm = new FileOutputStream(filename);
            pw = new PrintWriter(fileStrm);
            for(CityComponent c : cityTree)
            {
                export += c.cityToFile();
            }
            pw.println(export);
            pw.close();
        }
        catch(IllegalArgumentException e)
        {
            throw new FileIOException("Error within network: " + e.getMessage());
        }
        catch(IOException e)
        {
            throw new FileIOException(
                String.format("IO error occurred while reading '%s': '%s'",
                filename, e.getMessage()));
        } // End try-catch        
        catch(ArrayIndexOutOfBoundsException e)
        {
            throw new FileIOException("Parsing error ~ invalid arguments provided", e);   
        }
    }
    
    // Generates a random City network.
    public void generateCity()
    {
        // Step 1: Generate a random tree depth between 1 and 5 (inclusive)
        CityComponent rootNode;
        //Random rng = new Random();
        Random random = new Random();
        Set<CityComponent> buildingsToAdd = new LinkedHashSet<>(); 
        int treeDepth;
        treeDepth = random.nextInt(6 - 1) + 1; // (max - min) + min; max - exclusive, min - inclusive.
        int numChildren;
        numChildren = random.nextInt(6 - 2) + 2;
        
        // Step 2: Generate root node and generate a name for it --- call Suburb(String) :)
        String root = generateName(0);
        if(treeDepth != 1) // Composite Node = Root AND has Children.
        {
            rootNode = new Suburb(root); // Add Suburb("q");
            addNode(rootNode);
        }
        else // i.e. if depth = 1, we only have 1 level therefore Root node = Leaf node <-----
        {
            Map<String,Double> newMap = generateMap(); // Empty initialised map.
            newMap = generateValues(newMap); // This will populate the map with random values.
            rootNode = new Building(root, newMap); // Root node with random power values.
            addNode(rootNode);
            newMap = null;
        }
        /*
        Step 3: Until the tree has reached the required depth... ~ For each node ~ gen ran
            Do { Generate random num. of random nodes (between 2 and 5 inclusive) } until depth reached
            Do { Generate name for each node } until depth reached  */
        for(int ii=0;ii<=treeDepth-2;ii++) //Can't be 1 to treeDepth; 1 occupied by the Root (as we call ii)
        { 
            // for ii = {0} to {depth -2} is like doing say depth = 5: 2...3..4..5 (depth = 5, therefore 0 to (5-2) = 0 to 3.
            // ==========Suburb creation =========
            /****We want to make AT LEAST [treeDepth - 1] Composite Nodes to ensure we have reached tree depth. ****/
            CityComponent tempNode = new Suburb(generateName(ii+1),generateName(ii)); //name, parent
            //System.out.println("TempNode name: " + generateName(ii+1));
            // So this ^^ will make Suburb(1, 0) first time. Then when ii = 1, make Suburb (2,1); -- This is handled fine.
            
            // ==========Building creation =========
            for(int jj=0; jj<numChildren;jj++)
            {
                Map<String,Double> newMap = generateMap(); // Empty initialised map.
                newMap = generateValues(newMap); // This will populate the map with random values.
                //We make Suburb(1, 0) first time. Then when ii = 1, make Suburb (2,1);
                // Thus we need Building's to have the right parent, but a unique name.
                CityComponent tempBuilding = new Building(genBuildingName(jj), generateName(ii), newMap); // 
                // This ^ would make Building(0, 1). When ii = 1, makes Building (1, ii)
                buildingsToAdd.add(tempBuilding);
            }
            addNode(tempNode);    
        } // End for
        
        for(CityComponent c : buildingsToAdd)
        {
            addNode(c);
        }
    }

    public Map<String,Double> generateMap()
    {
        Map<String,Double> powerMap = new HashMap<>();
        powerMap.put("dm", 0.0);
        powerMap.put("da", 0.0);
        powerMap.put("de", 0.0);
        powerMap.put("em", 0.0);
        powerMap.put("ea", 0.0);
        powerMap.put("ee", 0.0);
        powerMap.put("h", 0.0);
        powerMap.put("s", 0.0);
        return powerMap;
    }
    
     private Map<String,Double> generateValues(Map<String,Double> powerMap)
    {
        powerMap.replace("dm", rngDouble());
        powerMap.replace("da", rngDouble());
        powerMap.replace("de", rngDouble());
        powerMap.replace("em", rngDouble());
        powerMap.replace("ea", rngDouble());
        powerMap.replace("ee", rngDouble());
        powerMap.replace("h", rngDouble());
        powerMap.replace("s", rngDouble());
        return powerMap;
    }

    // ASSUMPTION: The double values will be rounded to 3 decimal places as seen in the Spec
    public Double rngDouble()
    {
        Double randomNumber, random;
        randomNumber = 1 + (1001 - 1) * randDouble.nextDouble();
        // =======
        // =======
        BigDecimal bd = new BigDecimal(randomNumber).setScale(3, RoundingMode.HALF_UP);
        random = bd.doubleValue();
        return random;
    }

    private String generateName(int index) 
    {
        String[] namePool =
        { "q","w","e","r","t","y","u","i","o","p","a","s","d","f"
            ,"g","h","j","k","l","z","x","c","v",
            "b","n","m",
            "1", "2", "3", "4","5", "6", "7", "8", "9"};
        return namePool[index];
    }

    private String genBuildingName(int index)
    {
        String[] namePool = {"one", "two", "three", "four","five", "six", "seven", "eight", "nine"};
        return namePool[index];
    }
}