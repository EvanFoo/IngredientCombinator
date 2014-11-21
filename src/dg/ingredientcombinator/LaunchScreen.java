package dg.ingredientcombinator;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;

public class LaunchScreen extends Activity {
	
	AnimationDrawable sandwichAnimation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch_screen);
		
		// Pause for 5 seconds, then finish the activity
		// For the final product, we would likely create a thread to run the loading
		// and then wait for that thread to complete before finishing
		Handler pauser = new Handler();
		pauser.postDelayed(new Runnable() {public void run() {finish();}}, 5);
	}
}
