package dg.ingredientcombinator;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;
import java.io.InputStream;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class MainActivity extends ActionBarActivity {
    ArrayList<Recipe> suggested_recipes = new ArrayList<Recipe>();
	ArrayList<Ingredient> all_ingredients = new ArrayList<Ingredient>();
	
	private Button constructButton(String label, int id) {
		Button button = new Button(this);
		button.setText(label);
		button.setId(id);
		return button;
	}
	
	private LinearLayout constructButtonFrame() {
		LinearLayout button_frame = new LinearLayout(this);
		button_frame.setOrientation(LinearLayout.HORIZONTAL);
		
		// Set up buttons
		button_frame.addView(constructButton("My Recipes", 0));
		button_frame.addView(constructButton("My Ingredients", 1));
		
		return button_frame;
	}
	
	private ScrollView constructRecipeList() {
		ScrollView recipe_scroll_list = new ScrollView(this);
		LinearLayout recipe_list = new LinearLayout(this);
		recipe_list.setOrientation(LinearLayout.VERTICAL);
		
		// Add buttons to recipe list
		// For now, they'll be generic buttons. We can format them
		// to be fancy at a later date.
		for (int i=0; i<suggested_recipes.size(); i++) {
			Button new_button = constructButton(suggested_recipes.get(i).get_name(), i);
			recipe_list.addView(new_button);
		}
		
		recipe_scroll_list.addView(recipe_list);
		return recipe_scroll_list;
	}
	
	private LinearLayout constructMainFrame() {
		LinearLayout main_frame = new LinearLayout(this);
		main_frame.setOrientation(LinearLayout.VERTICAL);
		main_frame.addView(constructRecipeList());
		//main_frame.addView(constructButtonFrame());
		
		return main_frame;
	}
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        
        /*
        // Temporary populate lists code
        // Get data from resources
        InputStream recipes = getResources().openRawResource(R.raw.recipe);
        InputStream ingreds = getResources().openRawResource(R.raw.ingredient);
        all_ingredients = RecipeFactory.getIngredientsFromStream(ingreds);
        suggested_recipes = RecipeFactory.createRecipes(recipes, all_ingredients);
        */
        
        // Launch the splash screen
        Intent splash = new Intent(this, LaunchScreen.class);
        startActivity(splash);
        
        // Spawn a thread to load information from database
        
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
