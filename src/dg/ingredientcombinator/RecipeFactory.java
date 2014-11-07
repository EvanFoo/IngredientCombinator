package dg.ingredientcombinator;

import java.util.ArrayList;
import java.io.InputStream;

public class RecipeFactory {
	// Store ingredient characteristics
	// Currently the ingredients.txt file only lists ingredient characteristics
	// We could expand the ingredients.txt file to also indicate whether or not the
	// ingredient is in stock to use in our app instead of saving another list of
	// available ingredients
	public static ArrayList<Ingredient> getIngredientsFromStream(InputStream ingredient_txt_contents) {
		ArrayList<ArrayList<String>> ingredient_attrs = FileIO.readCommaDelimStr(ingredient_txt_contents);
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		for (int i=0; i<ingredient_attrs.size(); i++) {
			Ingredient ingred = new Ingredient(ingredient_attrs.get(i));
			ingredients.add(ingred);
		}
		return ingredients;
	}
	
	// Helper function to search through a list of ingredients by name
	private static Ingredient findIngredient(String ingred_name, ArrayList<Ingredient> ingreds) {
		for (int i=0; i<ingreds.size(); i++) {
			if (ingred_name.equals(ingreds.get(i).getName())) {
				return ingreds.get(i);
			}
		}
		return null;
	}
	
	// Construct all recipes from the Recipe.txt file
	public static ArrayList<Recipe> createRecipes(InputStream recipe_txt_contents, ArrayList<Ingredient> ingreds) {
		ArrayList<ArrayList<String>> recipe_data = FileIO.readCommaDelimStr(recipe_txt_contents);
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		for (int i=0; i<recipe_data.size(); i++)
		{
			ArrayList<Ingredient> recipe_ingreds = new ArrayList<Ingredient>();
			for (int j=1; j<recipe_data.get(i).size(); j++)
			{
				Ingredient ingred = findIngredient(recipe_data.get(i).get(j), ingreds);
				if (ingred != null) {
					recipe_ingreds.add(ingred);
				}
			}
			Recipe recipe = new Recipe(recipe_data.get(i).get(0), recipe_ingreds);
			recipes.add(recipe);
		}
		
		return recipes;
	}
	
	private RecipeFactory() {};
}
