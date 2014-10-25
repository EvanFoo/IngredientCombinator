package dg.ingredientcombinator;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.content.Intent;

public class LaunchScreen extends Activity {
	
	AnimationDrawable sandwichAnimation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch_screen);
		
		ImageView sandwichImage = (ImageView) findViewById(R.drawable.sandwich_left);
		sandwichImage.setBackgroundResource(R.drawable.sandwich_left);
		sandwichAnimation = (AnimationDrawable) sandwichImage.getBackground();
		
		sandwichAnimation.start();
		
		//if (!sandwichAnimation.isRunning()) {
			Intent start_main = new Intent(this, MainActivity.class);
			startActivity(start_main);
		//}
	}
}
