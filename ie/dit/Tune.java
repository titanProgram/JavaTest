package ie.dit;

public class Tune implements Player
{
	// data
	private int x;
	private String title;
	private String altTitle;
	private String notation;

	// accessor methods
	// ----------------
	// getters

	public int getX()
	{
		return x;
	}

	public String getTitle()
	{
		return title;
	}

	public String getAltTitle()
	{
		return altTitle;
	}

	public String getNotation()
	{
		return notation;
	}

	// setters

	public void setX( int val )
	{
		x = val;
	}

	public void setTitle( String str )
	{
		title = str;
	}

	public void setAltTitle( String str )
	{
		altTitle = str;
	}

	public void setNotation( String str )
	{
		notation = str;
	}

	// methods

	public String toString()
	{
		String text;

		if ( altTitle != null )
		{
			text = x + ", " + title + ", " + altTitle + ".";
		}
		else
		{
			text = x + ", " + title + ".";
		}

		return text;
	}

	public void play()
	{
		System.out.println( notation );
	}
}