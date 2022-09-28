import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

/**
 * Main class representing the entry point (and controller) of the application.
 */
public class MainApplication extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args); // Run JavaFX
        // This will effectively do 'new MainApplication()' and then call 'start(...)'.
    }
    
    /**
     * Loads an image album and then creates a window to display it.
     */
    @Override
    public void start(Stage stage)
    {
        // Construct an album object.
        Album album = new Album();
        
        // Make a new window.
        MainWindow window = new MainWindow(album, stage);        
        
        // Choose which album to load.
        File albumFile = window.chooseAlbumFile();
        
        if(albumFile == null)
        {
            Platform.exit(); // Otherwise JavaFX keeps the program open, doing nothing.
        }
        else
        {
            try
            {
                // Attempt to read an album file.
                readAlbumFile(albumFile, album);
                
                // Display the window.
                window.show();
            }
            catch(IOException e)
            {
                System.err.println("Error while reading " + albumFile);
                Platform.exit();
            }
        }
    }
    
    /**
     * Reads an album file, given a filename and an Album object. Returns true if
     * successful, or false if the file could not be read.
     *
     * @param albumFile The file storing the list of image filenames and their captions.
     * @param album An Album object to populate.
     * 
     * @throws IOException If the file could not be read.
     */
    private static void readAlbumFile(File albumFile, Album album) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(albumFile));
        String line = reader.readLine();
        int x = 0, y = 0, z = 0;
        while(line != null)
        {
            if(line.trim().length() > 0) // Ignore blank lines
            {
                String[] parts = line.split(":");
                
                String imageFilename = albumFile.getParent() + File.separatorChar + parts[0];
                String imageCaption = "";
                

                if(parts.length >= 2)
                {
                    imageCaption = parts[1];
                }
                else
                {
                    imageCaption = "Caption missing";
                }

                Record newImg = new ImageRecord(imageFilename, imageCaption);
                
                if(parts.length >= 2) // I.E. the line contains labels.
                { // Split based on the type of label.
                    for (int ii = 2; ii < parts.length; ii++) // Start from the second index onwards.
                    {
                        String[] labels = parts[ii].split("=");
                        switch(labels[0].toUpperCase())
                        {
                            case "DATE": // If we have a Date, call the Date decorator and make the new image.
                                //  public DateDecorator(String inDate, Record next)
                                newImg = new DateDecorator(labels[1], newImg);
                                break;

                            case "RATING": //public RatingDecorator(int inRating, Record next)
                                newImg = new RatingDecorator(Integer.parseInt(labels[1]), newImg);
                                break;

                            case "GPS": // public GPSDecorator(int inX, int inY, int inZ, Record next)
                                labels = labels[1].split(" "); // Split by spaces for the three integers
                                x = Integer.parseInt(labels[0]);
                                y = Integer.parseInt(labels[1]);
                                z = Integer.parseInt(labels[2]);
                                newImg = new GPSDecorator(x, y, z, newImg); // X, Y, Z, Next
                                break;
                        } // End switch
                    }// End for
                } // End if

                // Insert your code here to add a new image to the album
				//album.addImage(new ImageRecord(imageFilename, imageCaption));
                album.addImage(newImg);

            } // End if
                        
            line = reader.readLine();
        } // End while
        
        reader.close();
    } // End while

} // End class
