package dg.ingredientcombinator;

import java.util.ArrayList;

public class Recipe 
{
	private String name = new String();
	private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
	
	public Recipe(String n, ArrayList<Ingredient> i) {
		name = n;
		ingredients = i;
	}
	
	public String get_name() {
		return name;
	}
	
	public ArrayList<Ingredient> ingredients() {
		return ingredients;
	}
	
	/*public Recipe(String[] recipe)
	{
		name = recipe[0];
		for(int i = 1; i < recipe.length; i++)
		{
			ingredients.add(new Ingredient(recipe[i]));
		}
	}*/

}
