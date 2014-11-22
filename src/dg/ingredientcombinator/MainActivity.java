package dg.ingredientcombinator;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Hashtable;
import java.io.InputStream;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.view.View.OnClickListener;
import android.content.Context;

public class MainActivity extends ActionBarActivity {
    ArrayList<Recipe> suggested_recipes = new ArrayList<Recipe>();
    
    
    // Ingredient lists will be pushed into the bundle in this order: existing, all
	ArrayList<Ingredient> all_ingredients = new ArrayList<Ingredient>();
	ArrayList<Ingredient> existing_ingredients = new ArrayList<Ingredient>();
	ArrayList<Recipe> recipesYouCanMake = new ArrayList<Recipe>();
	Context context = this;
	
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
				startActivity(intent);
			}
		}
		));
		
		// Ingredients Screen opener button
		button_frame.addView(constructButton("My Ingredients", 1, new OnClickListener() 
		{	 
			public void onClick(View view) {
				Intent intent = new Intent(context, IngredientsScreen.class);
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
		// For now, they'll be generic buttons. We can format them
		// to be fancy at a later date.
		for (int i=0; i < suggested_recipes.size(); i++) {
			OnClickListener on_click = new OnClickListener() { public void onClick(View view) {} };
			Button new_button = constructButton(suggested_recipes.get(i).get_name(), i, on_click);
			recipe_list.addView(new_button);
		}
		
		recipe_scroll_list.addView(recipe_list);
		return recipe_scroll_list;
	}
	
	//this function will seach for recipes that can be made with the ingredients on hand
	private ArrayList<Recipe> searchFunction(){
		Hashtable hashtable = new Hashtable();
		ArrayList<Recipe> returnRecipes = null;
		
		
		for(int i = 0; i < all_ingredients.size(); i++){
			hashtable.put(all_ingredients.toArray()[i].getClass().getName(),1);
		}
		
		for(int i = 0; i < suggested_recipes.size(); i ++){
			ArrayList ingredients = suggested_recipes.get(i).ingredients();
			boolean canMake = true;
			for(int j = 0; j < ingredients.size(); j++){
				if(hashtable.get(ingredients.get(i).toString()) == null){
					j = ingredients.size();
					canMake = false;
				}
			}
			if(canMake == true){
				returnRecipes.add(i, suggested_recipes.get(i));
			}
		}
		
		return returnRecipes;
	}
	
	private LinearLayout constructMainFrame() {
		LinearLayout main_frame = new LinearLayout(this);
		main_frame.setOrientation(LinearLayout.VERTICAL);
		main_frame.addView(constructRecipeList());
		main_frame.addView(constructButtonFrame());
		
		return main_frame;
	}
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        
        ///*
        // Temporary populate lists code
        // Get data from resources
        InputStream recipes = getResources().openRawResource(R.raw.recipe);
        InputStream ingreds = getResources().openRawResource(R.raw.ingredient);
        all_ingredients = RecipeFactory.getIngredientsFromStream(ingreds);
        suggested_recipes = RecipeFactory.createRecipes(recipes, all_ingredients);
        //*/
        
        
        //finds which recipes you can make
        //// recipesYouCanMake = searchFunction(); 
        
        
        // Launch the splash screen
        Intent splash = new Intent(this, LaunchScreen.class);
        startActivity(splash);
        
        // Spawn a thread to load information from database
        
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
