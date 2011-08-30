package com.memeworks.spinblocks;

import com.memeworks.spinblocks.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class HighScores extends ListActivity {

	private boolean finishing = false;
	private ArrayAdapter<String> score_array;
	private ArrayAdapter<CharSequence> difficulty_array;
	private Button back_button;
	private Spinner difficulty_spinner;
	private TextView no_scores_text_view;
		
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.highscores);
        no_scores_text_view = (TextView) findViewById(R.id.noscorestextview);
        no_scores_text_view.setTypeface(SpinBlocks.FONT);
        finishing = false;
        
        if (SpinBlocks.EASY_SCORES != null)
        {
        	no_scores_text_view.setVisibility(View.GONE);
        	score_array = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, SpinBlocks.EASY_SCORES);
        	setListAdapter(score_array);
        }
        else
        {
        	this.getListView().setVisibility(View.GONE);
        	no_scores_text_view.setVisibility(View.VISIBLE);
        	no_scores_text_view.setText("No Recorded Scores for Easy");
        }
        
        difficulty_spinner = (Spinner) findViewById(R.id.highscoredifficultyspinner);
        difficulty_array = ArrayAdapter.createFromResource(this, R.array.difficulties, R.layout.spinner); 
        	//new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, difficulties);
        difficulty_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficulty_spinner.setAdapter(difficulty_array);
        difficulty_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				try{
				switch (position) {
				case 0: //Easy
			        if (SpinBlocks.EASY_SCORES != null)
			        {
			        	no_scores_text_view.setVisibility(View.GONE);
			        	HighScores.this.getListView().setVisibility(View.VISIBLE);
			        	score_array = new ArrayAdapter<String>(HighScores.this, android.R.layout.simple_list_item_1, SpinBlocks.EASY_SCORES);
			        	setListAdapter(score_array);
			        }
			        else
			        {
			        	HighScores.this.getListView().setVisibility(View.GONE);
			        	no_scores_text_view.setVisibility(View.VISIBLE);
			        	no_scores_text_view.setText("No Recorded Scores for Easy");
			        }
					break;
				case 1: //Medium
			        if (SpinBlocks.MEDIUM_SCORES != null)
			        {
			        	no_scores_text_view.setVisibility(View.GONE);
			        	HighScores.this.getListView().setVisibility(View.VISIBLE);
			        	score_array = new ArrayAdapter<String>(HighScores.this, android.R.layout.simple_list_item_1, SpinBlocks.MEDIUM_SCORES);
			        	setListAdapter(score_array);
			        }
			        else
			        {
			        	HighScores.this.getListView().setVisibility(View.GONE);
			        	no_scores_text_view.setVisibility(View.VISIBLE);
			        	no_scores_text_view.setText("No Recorded Scores for Medium");
			        }
					break;
				case 2: //Hard
			        if (SpinBlocks.HARD_SCORES != null)
			        {
			        	no_scores_text_view.setVisibility(View.GONE);
			        	HighScores.this.getListView().setVisibility(View.VISIBLE);
			        	score_array = new ArrayAdapter<String>(HighScores.this, android.R.layout.simple_list_item_1, SpinBlocks.HARD_SCORES);
			        	setListAdapter(score_array);
			        }
			        else
			        {
			        	HighScores.this.getListView().setVisibility(View.GONE);
			        	no_scores_text_view.setVisibility(View.VISIBLE);
			        	no_scores_text_view.setText("No Recorded Scores for Hard");
			        }
					break;
				}
				}
				catch (Exception ex) {
					Log.e("Highscores", ex.toString());
				}
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				difficulty_spinner.setSelection(0);
			}

        });
        
        back_button = (Button) findViewById(R.id.buttonscoresback);
        back_button.setFocusable(false);
        back_button.setTypeface(SpinBlocks.FONT);
        back_button.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				back_button.setPressed(true);
				
				if (!finishing)
				{
					finishing = true;
			        Intent gameIntent = new Intent(HighScores.this, MainMenu.class);
			        HighScores.this.startActivity(gameIntent);
			        HighScores.this.finish();
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
	        Intent gameIntent = new Intent(HighScores.this, MainMenu.class);
	        HighScores.this.startActivity(gameIntent);
	        HighScores.this.finish();
        }
        
        return true;
    }
}
