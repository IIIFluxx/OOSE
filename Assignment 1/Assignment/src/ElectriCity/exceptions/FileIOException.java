package exceptions;
/**
 * An exception thrown by File IO methods in City.java
 */
public class FileIOException extends Exception
{   
    public FileIOException(String message)
    {
        super(message);
    }

    public FileIOException(String message, Throwable cause)
    {
        super(message,cause);
    } 
}
    