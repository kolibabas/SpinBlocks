package com.memeworks.spinblocks;

import com.memeworks.spinblocks.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DifficultySelector extends Activity {
    
	private boolean finishing = false;
	private Button button_easy;
	private Button button_medium;
	private Button button_hard;
	private Button button_back;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.difficultyselector);
        
        if (!SpinBlocks.FULL_VERSION)
        {
        	findViewById(R.id.mainmenulayout).setBackgroundResource(R.drawable.backgrounddemo);
        }
        
        finishing = false;

        
        button_easy = (Button) findViewById(R.id.buttoneasy);
        button_easy.setFocusable(false);
        button_easy.setTypeface(SpinBlocks.FONT);
        button_easy.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				button_easy.setPressed(true);
				
				if (!finishing)
				{
					finishing = true;
					SpinBlocks.Difficulty = SpinBlocks.DIFFICULTY_EASY;
			        Intent gameIntent = new Intent(DifficultySelector.this, Game.class);
			        DifficultySelector.this.startActivity(gameIntent);
			        DifficultySelector.this.finish();
				}
				return true;
			}
		});
        
        button_medium = (Button) findViewById(R.id.buttonmedium);
        button_medium.setFocusable(false);
        button_medium.setTypeface(SpinBlocks.FONT);
        button_medium.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				button_medium.setPressed(true);
				
				if (!finishing)
				{
					finishing = true;
					SpinBlocks.Difficulty = SpinBlocks.DIFFICULTY_MEDIUM;
			        Intent gameIntent = new Intent(DifficultySelector.this, Game.class);
			        DifficultySelector.this.startActivity(gameIntent);
			        DifficultySelector.this.finish();
				}
				return true;
			}
		});
        
        button_hard = (Button) findViewById(R.id.buttonhard);
        button_hard.setFocusable(false);
        button_hard.setTypeface(SpinBlocks.FONT);
        button_hard.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				if (SpinBlocks.FULL_VERSION)
				{
					button_hard.setPressed(true);
					
					if (!finishing)
					{
						finishing = true;
						SpinBlocks.Difficulty = SpinBlocks.DIFFICULTY_HARD;
				        Intent gameIntent = new Intent(DifficultySelector.this, Game.class);
				        DifficultySelector.this.startActivity(gameIntent);
				        DifficultySelector.this.finish();
					} 
				}
				else
				{
					Toast.makeText(DifficultySelector.this, "Only available in the full version :(", Toast.LENGTH_SHORT).show();
				}
				return true;
			}
		});
        
        button_back = (Button) findViewById(R.id.buttonback);
        button_back.setFocusable(false);
        button_back.setTypeface(SpinBlocks.FONT);
        button_back.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				button_back.setPressed(true);
				
				if (!finishing)
				{
					finishing = true;
			        Intent gameIntent = new Intent(DifficultySelector.this, MainMenu.class);
			        DifficultySelector.this.startActivity(gameIntent);
			        DifficultySelector.this.finish();
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
	        Intent gameIntent = new Intent(DifficultySelector.this, MainMenu.class);
	        DifficultySelector.this.startActivity(gameIntent);
	        DifficultySelector.this.finish();
        }
        
        return true;
    }

}
