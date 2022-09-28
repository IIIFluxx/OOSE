package controller;
import exceptions.*;

/**
 * Interface for the relevant user option. 
 * Used for Reading, Writing, Generating, Displaying.
 * @author Bharath Sukesh
 */

public interface Option
{
    void doOption(String s) throws FileIOException;
    boolean requiresFile();
} 