package com.memeworks.spinblocks;

import com.memeworks.spinblocks.R;
import com.scoreninja.adapter.ScoreNinjaAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainMenu extends Activity {
    
	private boolean finishing = false;
	private Button button_timed;
	private Button button_endless;
	private Button button_puzzle;
	
	private Button button_options;
	private Button button_high_scores;
	private Button button_instructions;
	
	private ScoreNinjaAdapter score_ninja_adapter;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setup fonts
        SpinBlocks.FONT = Typeface.createFromAsset(getAssets(), "fonts/komikax.ttf");
        
        setContentView(R.layout.mainmenu);
        
        if (!SpinBlocks.FULL_VERSION)
        {
        	findViewById(R.id.mainmenulayout).setBackgroundResource(R.drawable.backgrounddemo);
        }
        
        finishing = false;
        score_ninja_adapter = new ScoreNinjaAdapter(this, "commemeworksspinblox", "0D0722E7A5240670BBE1A128026A4275");
        
        button_timed = (Button) findViewById(R.id.buttontimedmode);
        button_timed.setFocusable(false);
        button_timed.setTypeface(SpinBlocks.FONT);
        button_timed.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				button_timed.setPressed(true);
				
				if (!finishing)
				{
					finishing = true;
					SpinBlocks.Gametype = SpinBlocks.GAMETYPE_TIMED;
			        Intent gameIntent = new Intent(MainMenu.this, DifficultySelector.class);
			        MainMenu.this.startActivity(gameIntent);
			        MainMenu.this.finish();
				}
				return true;
			}
		}); 
        
        button_endless = (Button) findViewById(R.id.buttonendlessmode);
        button_endless.setFocusable(false);
        button_endless.setTypeface(SpinBlocks.FONT);
        button_endless.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				if (SpinBlocks.FULL_VERSION)
				{
					button_endless.setPressed(true);
					
					if (!finishing)
					{
						finishing = true;
						SpinBlocks.Gametype = SpinBlocks.GAMETYPE_ENDLESS;
				        Intent gameIntent = new Intent(MainMenu.this, DifficultySelector.class);
				        MainMenu.this.startActivity(gameIntent);
				        MainMenu.this.finish();
					}
				}
				else
				{
					Toast.makeText(MainMenu.this, "Only available in the full version :(", Toast.LENGTH_SHORT).show();
				}
				return true;
			}
		});

        button_puzzle = (Button) findViewById(R.id.buttonpuzzlemode);
        button_puzzle.setFocusable(false);
        button_puzzle.setTypeface(SpinBlocks.FONT);
        button_puzzle.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				button_puzzle.setPressed(true);
				
				if (!finishing)
				{
					finishing = true;
					SpinBlocks.Gametype = SpinBlocks.GAMETYPE_PUZZLE;
			        Intent gameIntent = new Intent(MainMenu.this, PuzzleSelector.class);
			        MainMenu.this.startActivity(gameIntent);
			        MainMenu.this.finish();
				}
				return true;
			}
		});
        
        button_options = (Button) findViewById(R.id.buttonoptions);
        button_options.setFocusable(false);
        button_options.setTypeface(SpinBlocks.FONT);
        button_options.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				button_options.setPressed(true);
				
				if (!finishing)
				{
					finishing = true;
			        Intent gameIntent = new Intent(MainMenu.this, Options.class);
			        MainMenu.this.startActivity(gameIntent);
			        MainMenu.this.finish();
				}
				return true;
			}
		});
        
        button_high_scores = (Button) findViewById(R.id.buttonhighscores);
        button_high_scores.setFocusable(false);
        button_high_scores.setTypeface(SpinBlocks.FONT);
        button_high_scores.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				button_high_scores.setPressed(true);
				
				if (!finishing)
				{
					finishing = true;
			        Intent gameIntent = new Intent(MainMenu.this, HighScores.class);
			        MainMenu.this.startActivity(gameIntent);
			        MainMenu.this.finish();
				}
				return true;
			}
		});
        
        button_instructions = (Button) findViewById(R.id.buttoninstructions);
        button_instructions.setFocusable(false);
        button_instructions.setTypeface(SpinBlocks.FONT);
        button_instructions.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				button_instructions.setPressed(true);
				
				if (!finishing)
				{
					finishing = true;
			        Intent gameIntent = new Intent(MainMenu.this, Instructions.class);
			        MainMenu.this.startActivity(gameIntent);
			        MainMenu.this.finish();
				}
				return true;
			}
		});
        
        if (SpinBlocks.LAST_SCORE != -1 && SpinBlocks.Gametype == SpinBlocks.GAMETYPE_TIMED)
        {
        	switch (SpinBlocks.Difficulty)
        	{
        	case SpinBlocks.DIFFICULTY_EASY:
        		if (SpinBlocks.EASY_SCORES != null)
        		{
        			int worst_score = Integer.parseInt(SpinBlocks.EASY_SCORES[SpinBlocks.EASY_SCORES.length - 1].split(" ")[2]);
        			
        			if (SpinBlocks.LAST_SCORE > worst_score || SpinBlocks.EASY_SCORES.length < 10)
        			{
        				ShowHighScoreDialog();
        			}
        		}
        		else
        		{
        			ShowHighScoreDialog();
        		}
        		break;
        	case SpinBlocks.DIFFICULTY_MEDIUM:
        		if (SpinBlocks.MEDIUM_SCORES != null)
        		{
        			int worst_score = Integer.parseInt(SpinBlocks.MEDIUM_SCORES[SpinBlocks.MEDIUM_SCORES.length - 1].split(" ")[2]);
        			
        			if (SpinBlocks.LAST_SCORE > worst_score || SpinBlocks.MEDIUM_SCORES.length < 10)
        			{
        				ShowHighScoreDialog();
        			}
        		}
        		else
        		{
        			ShowHighScoreDialog();
        		}
        		break;
        	case SpinBlocks.DIFFICULTY_HARD:
        		if (SpinBlocks.HARD_SCORES != null)
        		{
        			int worst_score = Integer.parseInt(SpinBlocks.HARD_SCORES[SpinBlocks.HARD_SCORES.length - 1].split(" ")[2]);
        			
        			if (SpinBlocks.LAST_SCORE > worst_score || SpinBlocks.HARD_SCORES.length < 10)
        			{
        				ShowHighScoreDialog();
        			}
        		}
        		else
        		{
        			ShowHighScoreDialog();
        		}
        		break;
        	}
        }

    }
    
    @Override
    protected void onActivityResult(
        int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      score_ninja_adapter.onActivityResult(requestCode, resultCode, data);
    }

	/**
     * Standard override to get key-press events.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent msg) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            MainMenu.this.finish();
        }
        
        return true;
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
        MainMenu.this.finish();
    }

	private void ShowHighScoreDialog() {
		final FrameLayout fl = new FrameLayout(this);
		final EditText input = new EditText(this);
		input.setGravity(Gravity.CENTER);

		fl.addView(input, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));

		input.setText(SpinBlocks.LAST_SCORE_NAME);
		new AlertDialog.Builder(this)
		     .setView(fl)
		     .setTitle("New High Score! Enter Name:")
		     .setPositiveButton("Local", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					String text = input.getText().toString();
					if (text.length() > 0) {
						SpinBlocks.LAST_SCORE_NAME = text;
					}
					
					SpinBlocks.InsertHighScore();
					SaveHighScores();
					SpinBlocks.LAST_SCORE = -1;
					dialog.dismiss();
				}
		     })
		     .setNeutralButton("Online", new DialogInterface.OnClickListener() {
		    	 public void onClick(DialogInterface dialog, int which) {
		    		 
		    		 	if (SpinBlocks.FULL_VERSION)
		    		 	{
							String text = input.getText().toString();
							if (text.length() > 0) {
								SpinBlocks.LAST_SCORE_NAME = text;
							}
							
							String difficulty = "easy";
							if (SpinBlocks.Difficulty == SpinBlocks.DIFFICULTY_MEDIUM)
							{
								difficulty = "medium";
							}
							else if (SpinBlocks.Difficulty == SpinBlocks.DIFFICULTY_HARD)
							{
								difficulty = "hard";
							}
							
							score_ninja_adapter.show(SpinBlocks.LAST_SCORE, null, difficulty);
							
							SpinBlocks.InsertHighScore();
							SaveHighScores();
							SpinBlocks.LAST_SCORE = -1;
							dialog.dismiss();
		    		 	}
		    		 	else
		    		 	{
		    		 		Toast.makeText(MainMenu.this, "Only available in the full version :(", Toast.LENGTH_SHORT).show();
		    		 	}
					}
		     })
		     .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					SpinBlocks.LAST_SCORE = -1;
					dialog.dismiss();
				}
			}).create().show();
	}
	
	private void SaveHighScores()
	{
        SharedPreferences settings = getSharedPreferences("prefs", 0);		        
        SharedPreferences.Editor editor = settings.edit();
        
        switch (SpinBlocks.Difficulty)
        {
        case SpinBlocks.DIFFICULTY_EASY:
            for (int i = 0; i < SpinBlocks.EASY_SCORES.length; i++)
            {
            	editor.putString("easyscore" + i, SpinBlocks.EASY_SCORES[i]);
            }
        	break;
        case SpinBlocks.DIFFICULTY_MEDIUM:
            for (int i = 0; i < SpinBlocks.MEDIUM_SCORES.length; i++)
            {
            	editor.putString("mediumscore" + i, SpinBlocks.MEDIUM_SCORES[i]);
            }
        	break;
        case SpinBlocks.DIFFICULTY_HARD:
            for (int i = 0; i < SpinBlocks.HARD_SCORES.length; i++)
            {
            	editor.putString("hardscore" + i, SpinBlocks.HARD_SCORES[i]);
            }
        	break;
        }
        
        editor.putString("scorename", SpinBlocks.LAST_SCORE_NAME);

        editor.commit();
	}

}
