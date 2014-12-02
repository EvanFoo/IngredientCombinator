package dg.ingredientcombinator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import android.content.Context;
import android.util.Log;

public class FileIO 
{
	// Filenames
	final private static String EXISTING_INGREDIENTS = "existing_ingredients.txt";
	final private static String ALL_INGREDIENTS = "all_ingredients.txt";
	final private static String ALL_RECIPES = "recipes.txt";	
	
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
	
	/*---------------------------------- 
	Methods for fetching data from files
	-----------------------------------*/
	private static boolean file_exists(Context c, String fn) {
		File f = c.getFileStreamPath(fn);
		if (f.exists()) {
			return true;
		}
		
		return false;
	}
	
	private static InputStream load_file(Context c, String fn) {
		try {
			if (file_exists(c, fn)) {
				Log.d("Testing", "Opened File");
				return c.openFileInput(fn);
			}
			else {
				FileOutputStream new_file = c.openFileOutput(fn, Context.MODE_PRIVATE);
				new_file.write("".getBytes());
				new_file.close();
				Log.d("Testing", "Created File");
				return c.openFileInput(fn);
			}
		}
		catch (IOException e) {
			Log.d("ERROR", "Unable to load files");
			return null;
		}
	}
	
	public static void load_data_from_files(Context c, ArrayList<Ingredient> e_i, ArrayList<Ingredient> a_i, ArrayList<Recipe> a_r) {
		InputStream existing_ingreds = load_file(c, EXISTING_INGREDIENTS);
        InputStream all_ingreds = load_file(c, ALL_INGREDIENTS);
        InputStream recipes = load_file(c, ALL_RECIPES);
        
        if (existing_ingreds == null || all_ingreds == null || recipes == null) {
        	// Probably later make a specific error message for each file unable to load
        	Log.d("ERROR", "Unable to load one of the data structures");
        }
        
        RecipeFactory.getIngredientsFromStream(all_ingreds, a_i);
        RecipeFactory.getIngredientsFromStream(existing_ingreds, e_i);
        RecipeFactory.createRecipes(recipes, a_i, a_r);
        
        try {
	        existing_ingreds.close();
	        all_ingreds.close();
	        recipes.close();
        }
        catch (IOException e) {
        	Log.d("ERROR", "Unable to close InputStreams when loading files");
        }
	}
	
	/*-------------------------------
	Methods for writing data to files
	-------------------------------*/
	private static void write_ingredients_to_file(Context c, String fn, ArrayList<Ingredient> ingreds) {
		try {
			FileOutputStream out = c.openFileOutput(fn, Context.MODE_PRIVATE);
			for (int i=0; i<ingreds.size(); i++) {
				out.write((ingreds.get(i).getName() + ',').getBytes());
				ArrayList<String> attrs = ingreds.get(i).getAttrs();
				for (int j=0; j<attrs.size()-1; j++) {
					out.write((attrs.get(j) + ',').getBytes());
				}
				out.write((attrs.get(attrs.size()-1) + '\n').getBytes());
			}
			out.close();
		}
		catch (IOException e) {
			Log.d("ERROR", "Unable to write data to file");
		}
	}
	
	private static void write_recipes_to_file(Context c, String fn, ArrayList<Recipe> recipes) {
		try {
			FileOutputStream out = c.openFileOutput(fn, Context.MODE_PRIVATE);
			for (int i=0; i<recipes.size(); i++) {
				out.write((recipes.get(i).get_name() + ',').getBytes());
				ArrayList<Ingredient> ingreds = recipes.get(i).ingredients();
				for (int j=0; j<ingreds.size()-1; j++) {
					out.write((ingreds.get(j).getName() + ',').getBytes());
				}
				out.write((ingreds.get(ingreds.size()-1).getName() + '\n').getBytes());
			}
			out.close();
		}
		catch (IOException e) {
			Log.d("ERROR", "Unable to write data to file");
		}
	}
	
	public static void write_data_to_files(Context c, ArrayList<Ingredient> a_i, ArrayList<Ingredient> e_i, ArrayList<Recipe> a_r) {
		write_ingredients_to_file(c, ALL_INGREDIENTS, a_i);
		write_ingredients_to_file(c, EXISTING_INGREDIENTS, e_i);
		write_recipes_to_file(c, ALL_RECIPES, a_r);
		
		// Debug
		Log.d("Testing", "Finished writing files");
	}
	
	private FileIO() {};
}
