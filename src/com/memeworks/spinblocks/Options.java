package com.memeworks.spinblocks;

import com.memeworks.spinblocks.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class Options extends Activity {
	
	private boolean finishing = false;
	private Button button_toggle_sound;
	private Button button_toggle_colorblind;
	private Button button_clear_scores;
	private Button button_clear_puzzles;
	private Button button_options_back;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.options);
        
        finishing = false;
        
        
        button_toggle_sound = (Button) findViewById(R.id.buttontogglesound);
        button_toggle_sound.setFocusable(false);
        button_toggle_sound.setTypeface(SpinBlocks.FONT);
        button_toggle_sound.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				SpinBlocks.SOUND = !SpinBlocks.SOUND;
				
				if (SpinBlocks.SOUND)
				{
					button_toggle_sound.setText("Sound is ON");
				}
				else
				{
					button_toggle_sound.setText("Sound is OFF");
				}
				
		    	// Save user preferences.
		        SharedPreferences settings = getSharedPreferences("prefs", 0);
		        SharedPreferences.Editor editor = settings.edit();
		        editor.putBoolean("sound", SpinBlocks.SOUND);
		        editor.commit();
				return true;
			}
		});
		if (SpinBlocks.SOUND)
		{
			button_toggle_sound.setText("Sound is ON");
		}
		else
		{
			button_toggle_sound.setText("Sound is OFF");
		}
        
        button_toggle_colorblind = (Button) findViewById(R.id.buttontogglecolorblind);
        button_toggle_colorblind.setFocusable(false);
        button_toggle_colorblind.setTypeface(SpinBlocks.FONT);
        button_toggle_colorblind.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				SpinBlocks.COLORBLIND_MODE = !SpinBlocks.COLORBLIND_MODE;
				
				if (SpinBlocks.COLORBLIND_MODE)
				{
					button_toggle_colorblind.setText("Colorblind mode is ON");
				}
				else
				{
					button_toggle_colorblind.setText("Colorblind mode is OFF");
				}
				
		    	// Save user preferences.
		        SharedPreferences settings = getSharedPreferences("prefs", 0);
		        SharedPreferences.Editor editor = settings.edit();
		        editor.putBoolean("colorblind", SpinBlocks.COLORBLIND_MODE);
		        editor.commit();
				return true;
			}
		});
		if (SpinBlocks.COLORBLIND_MODE)
		{
			button_toggle_colorblind.setText("Colorblind mode is ON");
		}
		else
		{
			button_toggle_colorblind.setText("Colorblind mode is OFF");
		}

		button_clear_scores = (Button) findViewById(R.id.buttonoptionsclearscores);
		button_clear_scores.setFocusable(false);
		button_clear_scores.setTypeface(SpinBlocks.FONT);
		button_clear_scores.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {

		        SharedPreferences settings = getSharedPreferences("prefs", 0);		        
		        SharedPreferences.Editor editor = settings.edit();
		        
		        for (int i = 0; i < 10; i++)
		        {
		        	editor.putString("easyscore" + i, "0");
		        	editor.putString("mediumscore" + i, "0");
		        	editor.putString("hardscore" + i, "0");
		        }
		        editor.commit();
		        
		        SpinBlocks.EASY_SCORES = null;
		        SpinBlocks.MEDIUM_SCORES = null;
		        SpinBlocks.HARD_SCORES = null;
		        
		        button_clear_scores.setText("Scores Cleared!");
				return true;
			}
		});
		
		button_clear_puzzles = (Button) findViewById(R.id.buttonoptionsclearpuzzles);
		button_clear_puzzles.setFocusable(false);
		button_clear_puzzles.setTypeface(SpinBlocks.FONT);
		button_clear_puzzles.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {

		        SharedPreferences settings = getSharedPreferences("prefs", 0);		        
		        SharedPreferences.Editor editor = settings.edit();
		        
		        for (int i = 0; i < SpinBlocks.PUZZLE_COUNT; i++)
		        {
		        	editor.putString("puzzle" + i, SpinBlocks.PUZZLE_INCOMPLETE + " 0");
		        }
		        editor.commit();
		        
		        SpinBlocks.PUZZLE_RECORDS = new String[SpinBlocks.PUZZLE_COUNT];
		        for (int i = 0; i < SpinBlocks.PUZZLE_COUNT; i++)
		        {
		        	SpinBlocks.PUZZLE_RECORDS[i] = SpinBlocks.PUZZLE_INCOMPLETE + " 0";
		        }
		        
		        button_clear_puzzles.setText("Puzzle Records Cleared!");
				return true;
			}
		});
		
        button_options_back = (Button) findViewById(R.id.buttonoptionsback);
        button_options_back.setFocusable(false);
        button_options_back.setTypeface(SpinBlocks.FONT);
        button_options_back.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				button_options_back.setPressed(true);
				
				if (!finishing)
				{
					finishing = true;
			        Intent gameIntent = new Intent(Options.this, MainMenu.class);
			        Options.this.startActivity(gameIntent);
			        Options.this.finish();
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
        	finishing = true;
	        Intent gameIntent = new Intent(Options.this, MainMenu.class);
	        Options.this.startActivity(gameIntent);
	        Options.this.finish();
        }
        
        return true;
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
        Options.this.finish();
    }

}