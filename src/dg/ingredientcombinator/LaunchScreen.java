package dg.ingredientcombinator;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.content.Intent;

public class LaunchScreen extends Activity {
	
	AnimationDrawable sandwichAnimation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch_screen);
		
		
	}
	@Override
	public boolean onTouchEvent(MotionEvent event){ 
	        
	    int action = MotionEventCompat.getActionMasked(event);
	        
	    switch(action) {
	        case (MotionEvent.ACTION_DOWN) :
	        	Intent intent = new Intent(this, MainActivity.class);
	        	startActivity(intent);
	            return true;
	        
	        default : 
	            return super.onTouchEvent(event);
	    }      
}
}
