package ie.dit;

import java.util.ArrayList;
import java.io.*;

public class TuneBook
{
	ArrayList tunes;

	// file reading variables
	BufferedReader inputStream;
	FileReader file;

	TuneBook( String fileName )
	{
		tunes = new ArrayList<Tune>();

		try
		{
			file = new FileReader( fileName );
		}
		catch ( FileNotFoundException e )
		{
			e.printStackTrace();
		}

		inputStream = new BufferedReader( file );
		loadFile();
	}

	void loadFile()
	{
		String line;
		String notation = "";

		boolean firstSong = true;
		boolean firstTitle = true;
		boolean secondTitle = true;

		Tune song = new Tune();

		try
		{
			while ( ( line = inputStream.readLine() ) != null )
			{
				// if fist line starts with X, create new tune
				if ( line.startsWith( "X" ) && firstSong )
				{
					song = new Tune();
					song.setX( Integer.parseInt( line.substring( 2 ) ) );
					firstSong = false;
				}
				else if ( line.startsWith( "X" ) )
				{
					// adding notation to previous tune
					song.setNotation( notation );
					// adding new Tune to arraylist
					tunes.add( song );
					// creating new tune
					song = new Tune();
					song.setX( Integer.parseInt( line.substring( 2 ) ) );
					notation = "";
					firstTitle = true;
					secondTitle = true;
				}

				if ( line.startsWith( "T" ) && firstTitle )
				{
					song.setTitle( line.substring( 2 ) );
					firstTitle = false;
				}
				else if ( line.startsWith( "T" ) && secondTitle )
				{
					song.setAltTitle( line.substring( 2 ) );
					secondTitle = false;
				}

				notation += line;

			}
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		finally 
		{
	    	if (inputStream != null) 
	    	{
		        try
		        {
		            inputStream.close();
		        }
		        catch(Exception e)
		        {
		            e.printStackTrace();
		        }
	    	}
	    }
	}

	public String toString()
	{
		int size = tunes.size();
		String str = "";
		Tune tune1;

		for ( int i = 0; i < size; i++ )
		{
			tune1 =  (Tune) tunes.get(i);
			str += tune1.getNotation();
		}

		return str;
	}	

	public Tune findTune( String title )
	{
		int size = tunes.size();
		Tune tune1;

		for ( int i = 0; i < size; i++ )
		{
			tune1 =  (Tune) tunes.get(i);

			if ( tune1.equals( title ) )
			{
				return tune1;
			}
		}

		return null;
	}

	public static void main( String[] args )
    {
        TuneBook tb = new TuneBook("hnj0.abc");
        System.out.println(tb);

        Tune t = tb.findTune("Scotsman over the Border");
        t.play();
    }
}