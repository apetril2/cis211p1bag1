
public class Artist<T> implements ArtistInterface<T>
{

    private final T[] artist;
    private int numberOfEntries;
    private int artistID;
    private String artistName;
    private boolean initialized = false;

    public Artist(T[] artist)
    {
        this.artist = artist;
    }



    public Artist(T artistID, T artistName)
    {
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempArtist = (T[]) new Object[2]; // Unchecked cast
        tempArtist[0] = artistID;
        tempArtist[1] = artistName;
        artist = tempArtist;
        numberOfEntries = 0;
        initialized = true;
    }
    public int getID()
    {
        return this.artistID;
    }



    public String getName()
    {
        return this.artistName;
    }

    public String toString()
    {
        checkInitialization();
        String str = (this.artist[0] + "\t" + this.artist[1]);
        return str;
    }
    private void checkInitialization()
    {
        if (!initialized)
            throw new SecurityException("ArrayArtist object is not initialized properly.");
    }
}
