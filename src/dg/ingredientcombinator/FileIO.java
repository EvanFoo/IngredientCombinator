package dg.ingredientcombinator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO 
{
	public static ArrayList<ArrayList<String>> readCommaDelimStr(InputStream file)
	{
		ArrayList<ArrayList<String>> entries = new ArrayList<ArrayList<String>>();
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) 
        {
            String line = sc.nextLine();
            String[] lineTokens = line.split(",");
            ArrayList<String> entry = new ArrayList<String>();
            for (int i = 0; i < lineTokens.length; i++)
            {
            	entry.add(lineTokens[i]);
            }
            entries.add(entry);
        }
        sc.close();
        
        return entries;
	}
	
	private FileIO() {};
}
