
import java.io.*;


public class ArrayArtistDemo {

    public static void main (String [] args) throws IOException
    {
        int count = getCountArtists();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("p1artists.txt"));
        String str;
        int fileIndex = 0;
        Artist[] artists = new Artist[count];
        String[] piece = new String[2];
        while ((str = bufferedReader.readLine()) != null)
        {
            for (int i = 0; i < 2; i++)
                piece =  str.split("\t"); //piece is a "piece" of the line, made into an array of each split
            artists[fileIndex] = new Artist(piece[0], piece[1]);
            fileIndex++;
        }


        //for (int i = 0; i < artists.length; i++)
        //  System.out.println(artists[i]);
        writeArtistFile(artists);
        int artCount = getCountArts();
        String[][] arts = new String[artCount][4];
        arts = readArtsFile(artCount);
        writeArtsFile(arts, artists);
        bufferedReader.close();
    }


    public static int getCountArtists() throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("p1artists.txt"));
        String str;
        int count = 0;
        while ((str = bufferedReader.readLine()) != null)
            count++;
        bufferedReader.close();
        return count;
    }

    public static int getCountArts() throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("p1arts.txt"));
        String str;
        int count = 0;
        while ((str = bufferedReader.readLine()) != null)
            count++;
        bufferedReader.close();
        return count;
    }

    public static void writeArtistFile(Artist[] artists) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("p1artists_out1.txt"));
        bufferedWriter.write("ArtistID\tArtistName");
        bufferedWriter.newLine();
        for (int i = 0; i < artists.length; i++)
        {
            bufferedWriter.write(artists[i]+"");
            bufferedWriter.newLine();

        }
        bufferedWriter.close();
    }

    public static String[][] readArtsFile(int count) throws IOException {
        String str;
        int fileIndex = 0;

        String[][] arts = new String[count][4];
        BufferedReader bufferedReader2 = new BufferedReader(new FileReader("p1arts.txt"));
        while ((str = bufferedReader2.readLine()) != null)
        {
            String[] piece = new String[2];
            for (int i = 0; i < 2; i++)
                piece =  str.split("\t"); //piece is a "piece" of the line, made into an array of each split
            for (int i = 0; i < 4; i++)
                arts[fileIndex][i] = piece[i];
            fileIndex++;
        }
        bufferedReader2.close();
        return arts;
    }

    //writes p1arts_out1.txt ~ both arts and artists are fed in
    public static void writeArtsFile(String[][] arts, Artist[] artists) throws IOException
    {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("p1arts_out1.txt"));
        int appraised = 0;

        bufferedWriter.write("ArtID\tTitle\tArtistID\tArtistName\tAppraised Value");
        bufferedWriter.newLine();

        for (int i = 0; i < arts.length; i++)
        {
            for (int j = 0; j < 4; j++) {
                if (j == 2)
                {
                    if (artists[Integer.valueOf(arts[i][j]) - 1] == null)
                        throw new SecurityException("Cannot match to artistID");
                    else
                        bufferedWriter.write(artists[Integer.valueOf(arts[i][j]) - 1] + "\t");
                }
                else if (j == 3)
                {
                    appraised += Integer.valueOf(arts[i][j]);
                    bufferedWriter.write(arts[i][j] + "\t");
                }
                else
                    bufferedWriter.write(arts[i][j]+"\t");
            }

            bufferedWriter.newLine();
        }
        bufferedWriter.newLine();
        bufferedWriter.write(arts.length +" total works of art");
        bufferedWriter.newLine();
        bufferedWriter.write(artists.length +" total artists");
        bufferedWriter.newLine();
        bufferedWriter.write("$"+ appraised + " total appraised value");
        bufferedWriter.close();

    }
}
