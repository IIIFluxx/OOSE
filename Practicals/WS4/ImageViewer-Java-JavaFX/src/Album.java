import java.util.*;

/**
 * Represents an album of images. Unchanged from Practical 01.
 */
public class Album 
{
	private LinkedList<Record> recordAlbum;

	public Album()
	{
		recordAlbum = new LinkedList<Record>();
	}

	public void addImage(Record newRecord)
	{
		// Add to container.
		recordAlbum.add(newRecord);	
		
	}

	public String getRecordFileNameAtIndex(int i)
	{
		return recordAlbum.get(i).getFilename();
	}

	public String getInfoAtIndex(int i)
	{
		return recordAlbum.get(i).getInfo();
	}

	/* 	public Record getImageAtIndex(int i)
	{
		return recordAlbum.get(i);
	} */

	public int iterateBack(int idx)
	{
		if(idx > 0)
        {
            idx = idx - 1;
        }
		else{ System.out.println("Cannot iterate any further backward"); }
		return idx;
	}

	public int iterateForward(int idx)
	{
		if(idx < recordAlbum.size() - 1)
        {
            idx = idx + 1;
        }
		else{ System.out.println("Cannot iterate any further forward"); }
		return idx;	
	}

}


