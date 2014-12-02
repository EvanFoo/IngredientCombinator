package dg.ingredientcombinator;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.view.View.OnClickListener;
import android.content.Context;
import java.util.ArrayList;

/*
 * Example code of how to pass an object between Activities with an Intent
Intent test_intent = new Intent(this, MainActivity.class);
test_intent.putExtra("test", all_ingredients);
ArrayList<Ingredient> a_ingredients = ((ArrayList<Ingredient>)test_intent.getExtras().get("test"));
for (int i=0; i<a_ingredients.size(); i++) {
	Log.d("printing", a_ingredients.get(i).getName());
}
*/

public class MainActivity extends ActionBarActivity {
	// Keys for extras
	final public String INGRED_ALL_KEY = "ingredients_all";
	final public String INGRED_EXIST_KEY = "ingredients_exist";
	final public String RECIPE_ALL_KEY = "recipes_all";
	
	// Lists
	private ArrayList<Recipe> all_recipes = new ArrayList<Recipe>();
    private ArrayList<Recipe> suggested_recipes;
    // Ingredient lists will be pushed into the bundle in this order: existing, all
	private ArrayList<Ingredient> all_ingredients = new ArrayList<Ingredient>();
	private ArrayList<Ingredient> existing_ingredients = new ArrayList<Ingredient>();
	private Context context = this;
	
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
		//these buttons should now show recipes that you can make 
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
	
	/*-------------------------
	Method to create test files
	-------------------------*/
	private void create_and_load_test_files() { 
        // Test data added to files
        ArrayList<Ingredient> a_i = new ArrayList<Ingredient>();
        ArrayList<Ingredient> e_i = new ArrayList<Ingredient>();
        
        ArrayList<String> r1i1a = new ArrayList<String>();
        r1i1a.add("r1i1");
        r1i1a.add("r1i1a1");
        r1i1a.add("r1i1a2");
        r1i1a.add("r1i1a3");
        
        ArrayList<String> r1i2a = new ArrayList<String>();
        r1i2a.add("r1i2");
        r1i2a.add("r1i2a1");
        r1i2a.add("r1i2a2");
        r1i2a.add("r1i2a3");
        
        ArrayList<String> r1i3a = new ArrayList<String>();
        r1i3a.add("r1i3");
        r1i3a.add("r1i3a1");
        r1i3a.add("r1i3a2");
        r1i3a.add("r1i3a3");
        
        Ingredient r1i1 = new Ingredient(r1i1a);
        Ingredient r1i2 = new Ingredient(r1i2a);
        Ingredient r1i3 = new Ingredient(r1i3a);
        ArrayList<Ingredient> r1i = new ArrayList<Ingredient>();
        r1i.add(r1i1); a_i.add(r1i1); e_i.add(r1i1);
        r1i.add(r1i2); a_i.add(r1i2); e_i.add(r1i2);
        r1i.add(r1i3); a_i.add(r1i3); e_i.add(r1i3);
        
        Recipe r1 = new Recipe("r1", r1i);
        
        ArrayList<String> r2i1a = new ArrayList<String>();
        r2i1a.add("r2i1");
        r2i1a.add("r2i1a1");
        r2i1a.add("r2i1a2");
        r2i1a.add("r2i1a3");
        
        ArrayList<String> r2i2a = new ArrayList<String>();
        r2i2a.add("r2i2");
        r2i2a.add("r2i2a1");
        r2i2a.add("r2i2a2");
        r2i2a.add("r2i2a3");
        
        ArrayList<String> r2i3a = new ArrayList<String>();
        r2i3a.add("r2i3");
        r2i3a.add("r2i3a1");
        r2i3a.add("r2i3a2");
        r2i3a.add("r2i3a3");
        
        Ingredient r2i1 = new Ingredient(r2i1a);
        Ingredient r2i2 = new Ingredient(r2i2a);
        Ingredient r2i3 = new Ingredient(r2i3a);
        ArrayList<Ingredient> r2i = new ArrayList<Ingredient>();
        r2i.add(r2i1); a_i.add(r2i1); e_i.add(r2i1);
        r2i.add(r2i2); a_i.add(r2i2); e_i.add(r2i2);
        r2i.add(r2i3); a_i.add(r2i3); e_i.add(r2i3);
        
        Recipe r2 = new Recipe("r2", r2i);
      
        ArrayList<String> r3i1a = new ArrayList<String>();
        r3i1a.add("r3i1");
        r3i1a.add("r3i1a1");
        r3i1a.add("r3i1a2");
        r3i1a.add("r3i1a3");
        
        ArrayList<String> r3i2a = new ArrayList<String>();
        r3i2a.add("r3i2");
        r3i2a.add("r3i2a1");
        r3i2a.add("r3i2a2");
        r3i2a.add("r3i2a3");
        
        ArrayList<String> r3i3a = new ArrayList<String>();
        r3i3a.add("r3i3");
        r3i3a.add("r3i3a1");
        r3i3a.add("r3i3a2");
        r3i3a.add("r3i3a3");
        
        Ingredient r3i1 = new Ingredient(r3i1a);
        Ingredient r3i2 = new Ingredient(r3i2a);
        Ingredient r3i3 = new Ingredient(r3i3a);
        ArrayList<Ingredient> r3i = new ArrayList<Ingredient>();
        r3i.add(r3i1); a_i.add(r3i1); e_i.add(r3i1);
        r3i.add(r3i2); a_i.add(r3i2); e_i.add(r3i2);
        r3i.add(r3i3); a_i.add(r3i3); //e_i.add(r3i3);
        
        Recipe r3 = new Recipe("r3", r3i);
        
        ArrayList<Recipe> rs = new ArrayList<Recipe>();
        rs.add(r1);
        rs.add(r2);
        rs.add(r3);
        
        FileIO.write_data_to_files(this, a_i,  e_i,  rs);
	}
	
	/*----------------
	 Android methods
	----------------*/
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        
        // Launch the splash screen
        Intent splash = new Intent(this, LaunchScreen.class);
        startActivity(splash);
        
        // Spawn a thread to load information from database

        // Generate test files
    	create_and_load_test_files();
    	
        // Get data from resources
        FileIO.load_data_from_files(this, existing_ingredients, all_ingredients, all_recipes);

        suggested_recipes = get_available_recipes();
        
        // Set Content View after constructing the Main Frame
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
}
