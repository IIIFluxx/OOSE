/**
 * Represents an image in an album.
 */
public class ImageRecord implements Record
{
    private String filename;
    private String caption;
    
    public ImageRecord(String newFilename, String newCaption)
    {
        filename = newFilename;
        caption = newCaption;
    }
    
    @Override
    public String getFilename()
    {
        return filename;
    }
    
    @Override
    public String getInfo() {
        return getCaption();
    }
    
    public String getCaption()
    {
        return caption;
    }
}

