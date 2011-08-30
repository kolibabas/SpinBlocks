package com.memeworks.spinblocks;

import com.memeworks.spinblocks.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Instructions extends Activity {

	private boolean finishing = false;
	private TextView text;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.instructions);
         
         text = (TextView) findViewById(R.id.instructionstext);
         text.setTypeface(SpinBlocks.FONT);
         
         finishing = false;
         Button backButton = (Button) findViewById(R.id.buttoninstructionsback);
         backButton.setTypeface(SpinBlocks.FONT);
         backButton.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				if (finishing == false) {
					finishing = true;
					Intent gameIntent = new Intent(Instructions.this, MainMenu.class);
		        	Instructions.this.startActivity(gameIntent);
		        	Instructions.this.finish();
				}
				return true;
			}
		});
    } 
    
    /**
     * Standard override to get key-press events.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent msg) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	Intent gameIntent = new Intent(Instructions.this, MainMenu.class);
        	Instructions.this.startActivity(gameIntent);
        	Instructions.this.finish();
        }
        
        return true;
    }

}