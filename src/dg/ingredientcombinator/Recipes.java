package dg.ingredientcombinator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Recipes extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipes);
	}
	 public void on_click_newRecipe(View view) {
	    	Intent intent = new Intent(this, CreateNewRecipe.class);
	    	startActivity(intent);
	    }
}
