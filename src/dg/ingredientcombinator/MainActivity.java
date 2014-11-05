package dg.ingredientcombinator;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Launch the splash screen
        Intent splash = new Intent(this, LaunchScreen.class);
        startActivity(splash);
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
    	Intent intent = new Intent(this, Recipes.class);
    	startActivity(intent);
    }
   
	// To be called when the Sandwich button is clicked		
    public void on_click_results(View view) {
    	//Intent intent = new Intent(this, MainActivity.class);
    	//startActivity(intent);
    }
}
