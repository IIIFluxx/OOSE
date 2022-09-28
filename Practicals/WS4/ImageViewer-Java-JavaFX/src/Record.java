// Common interface for BOTH base implementations and decorators.

// Base implementations inherit Record - these classes provide basic functionality.
// Decorators uniquely change how 'something' works, in our case, Decorators differ on Rating/Date/GPS.

public interface Record {
    public String getFilename();
    
    public String getInfo();
}

