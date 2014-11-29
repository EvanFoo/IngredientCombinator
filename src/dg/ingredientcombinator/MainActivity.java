package dg.ingredientcombinator;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.io.InputStream;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.view.View.OnClickListener;
import android.content.Context;

public class MainActivity extends ActionBarActivity {
	// Keys for extras
	final public String INGRED_ALL_KEY = "ingredients_all";
	final public String INGRED_EXIST_KEY = "ingredients_exist";
	final public String RECIPE_ALL_KEY = "recipes_all";
	
	// Lists
	ArrayList<Recipe> all_recipes = new ArrayList<Recipe>();
    ArrayList<Recipe> suggested_recipes = new ArrayList<Recipe>();
    // Ingredient lists will be pushed into the bundle in this order: existing, all
	ArrayList<Ingredient> all_ingredients = new ArrayList<Ingredient>();
	ArrayList<Ingredient> existing_ingredients = new ArrayList<Ingredient>();
	Context context = this;
	
	//Recipe availability methods
	private boolean recipe_available(Recipe recipe) {
		ArrayList<Ingredient> recipe_ingreds = recipe.ingredients();
		for (int i=0; i<recipe_ingreds.size(); i++) {
			if (!existing_ingredients.contains(recipe_ingreds.get(i))) {
				return false;
			}
		}
		
		return true;
	}
	
	private ArrayList<Recipe> get_available_recipes() {
		ArrayList<Recipe> ret = new ArrayList<Recipe>();
		for (int i=0; i<all_recipes.size(); i++) {
			if (recipe_available(all_recipes.get(i))) {
				ret.add(all_recipes.get(i));
			}
		}
		
		return ret;
	}
	
	// Interface construction methods
	private Button constructButton(String label, int id, OnClickListener on_click) {
		Button button = new Button(this);
		button.setText(label);
		button.setId(id);
		button.setOnClickListener(on_click);
		return button;
	}
	
	private LinearLayout constructButtonFrame() {
		LinearLayout button_frame = new LinearLayout(this);
		button_frame.setOrientation(LinearLayout.HORIZONTAL);
		
		// Set up buttons
		// Recipes Screen opener button
		button_frame.addView(constructButton("My Recipes", 0, new OnClickListener() 
		{	 
			public void onClick(View view) {
				Intent intent = new Intent(context, RecipesScreen.class);
				intent.putExtra(RECIPE_ALL_KEY, all_recipes);
				startActivity(intent);
			}
		}
		));
		
		// Ingredients Screen opener button
		button_frame.addView(constructButton("My Ingredients", 1, new OnClickListener() 
		{	 
			public void onClick(View view) {
				Intent intent = new Intent(context, IngredientsScreen.class);
				intent.putExtra(INGRED_ALL_KEY, all_ingredients);
				intent.putExtra(INGRED_EXIST_KEY, existing_ingredients);
				startActivity(intent);
			}
		}
		
		));
		
		return button_frame;
	}
	
	private ScrollView constructRecipeList() {
		ScrollView recipe_scroll_list = new ScrollView(this);
		LinearLayout recipe_list = new LinearLayout(this);
		recipe_list.setOrientation(LinearLayout.VERTICAL);
		
		// Add buttons to recipe list
		//these buttons should now show reciepes that you can make 
		//given that you have all of the possible ingredients
		
		for (int i=0; i < suggested_recipes.size(); i++) {
			OnClickListener on_click = new OnClickListener() { public void onClick(View view) {} };
			Button new_button = constructButton(suggested_recipes.get(i).get_name(), i, on_click);
			recipe_list.addView(new_button);
		}
		
		recipe_scroll_list.addView(recipe_list);
		
		return recipe_scroll_list;	
	}
	
	private LinearLayout constructMainFrame() {
		LinearLayout main_frame = new LinearLayout(this);
		main_frame.setOrientation(LinearLayout.VERTICAL);
		main_frame.addView(constructRecipeList());
		main_frame.addView(constructButtonFrame());
		
		return main_frame;
	}
	
	// Android methods
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        
        // Launch the splash screen
        Intent splash = new Intent(this, LaunchScreen.class);
        startActivity(splash);
        
        // Spawn a thread to load information from database
        ///*
        // Temporary populate lists code
        // Get data from resources
        InputStream recipes = getResources().openRawResource(R.raw.recipe);
        InputStream existing_ingreds = getResources().openRawResource(R.raw.existing_ingredients);
        InputStream all_ingreds = getResources().openRawResource(R.raw.all_ingredients);
        all_ingredients = RecipeFactory.getIngredientsFromStream(all_ingreds);
        existing_ingredients = RecipeFactory.getIngredientsFromStream(existing_ingreds);
        all_recipes = RecipeFactory.createRecipes(recipes, all_ingredients);
        //*/
        
        suggested_recipes = get_available_recipes();
        // Test code for suggested_recipes
        for (int i=0; i<suggested_recipes.size(); i++) {
        	Log.d("printing", suggested_recipes.get(i).get_name());
        }
        
        /*
         * Example code of how to pass an object between Activities with an Intent
        Intent test_intent = new Intent(this, MainActivity.class);
        test_intent.putExtra("test", all_ingredients);
        ArrayList<Ingredient> a_ingredients = ((ArrayList<Ingredient>)test_intent.getExtras().get("test"));
        for (int i=0; i<a_ingredients.size(); i++) {
        	Log.d("printing", a_ingredients.get(i).getName());
        }
        */
        
        // Set Content View after constructing the Main Frame
        //setContentView(R.layout.activity_main);
        setContentView(constructMainFrame());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
   
	// To be called when the Ingredients button is clicked	
    public void on_click_ingredients(View view) {
    	//Intent intent = new Intent(this, Ingredients.class);
    	//startActivity(intent);
    }
   
	// To be called when the Recipes button is clicked	
    public void on_click_recipes(View view) {
    	Intent intent = new Intent(this, RecipesScreen.class);
    	startActivity(intent);
    }
   
	// To be called when the Sandwich button is clicked		
    public void on_click_results(View view) {
    	//Intent intent = new Intent(this, MainActivity.class);
    	//startActivity(intent);
    }
}
