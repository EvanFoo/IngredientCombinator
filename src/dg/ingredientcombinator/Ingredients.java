package dg.ingredientcombinator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Ingredients extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(1);
		
		
		LinearLayout lettuceListing = createIngredientListing("Lettuce");
		LinearLayout mayoListing = createIngredientListing("Mayo");
		linearLayout.addView(lettuceListing);
		linearLayout.addView(mayoListing);
				
		setContentView(linearLayout);
		
		//setContentView(R.layout.activity_ingredients);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ingredients, menu);
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
	
	public LinearLayout createIngredientListing(String ingredient){
		LinearLayout ingredientLayout = new LinearLayout(this);
		ingredientLayout.setOrientation(0);

		TextView ingredientName = new TextView(this);
		ingredientName.setText(ingredient);
		ingredientLayout.addView(ingredientName);
		
		Button addNew = new Button(this);
		addNew.setText("ADD");
		addNew.setId(1);
		ingredientLayout.addView(addNew);
		
		Button subtractOld = new Button(this);
		subtractOld.setText("SUBTRACT");
		subtractOld.setId(2);
		ingredientLayout.addView(subtractOld);
		
		return(ingredientLayout);
		
	}
}
