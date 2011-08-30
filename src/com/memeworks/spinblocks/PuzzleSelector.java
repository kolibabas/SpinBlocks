package com.memeworks.spinblocks;

import com.memeworks.spinblocks.R;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener; 

public class PuzzleSelector extends ListActivity {

	private boolean finishing = false;
	private PuzzleListAdapter puzzle_array_adapter;
	private Button back_button;
	private TextView text;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.puzzleselector);
        
        puzzle_array_adapter = new PuzzleListAdapter(this, PuzzleList.Puzzles);
        setListAdapter(puzzle_array_adapter);
        
        finishing = false;
        
        text = (TextView) findViewById(R.id.puzzletext);
        text.setTypeface(SpinBlocks.FONT);
        
        getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
        		if (!finishing)
        		{
	        		finishing = false;
	        		SpinBlocks.Puzzle_Level = position;
	        		SpinBlocks.Current_Puzzle = PuzzleList.Puzzles[position];
	        		SpinBlocks.Current_Puzzle.Reset();
	        		
			        Intent gameIntent = new Intent(PuzzleSelector.this, Game.class);
			        PuzzleSelector.this.startActivity(gameIntent);
			        PuzzleSelector.this.finish();
        		}
			}
        });

        back_button = (Button) findViewById(R.id.buttonpuzzleback);
        back_button.setFocusable(false);
        back_button.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				back_button.setPressed(true);
				
				if (!finishing)
				{
					finishing = true;
			        Intent gameIntent = new Intent(PuzzleSelector.this, MainMenu.class);
			        PuzzleSelector.this.startActivity(gameIntent);
			        PuzzleSelector.this.finish();
				}
				return true;
			}
		});
        
        if (SpinBlocks.LAST_SCORE != -1 && SpinBlocks.Gametype == SpinBlocks.GAMETYPE_PUZZLE)
        {
        	String[] puzzle_values = SpinBlocks.PUZZLE_RECORDS[SpinBlocks.Puzzle_Level].split(" ");
        	int puzzle_status = Integer.parseInt(puzzle_values[0]);
        	int puzzle_moves = Integer.parseInt(puzzle_values[1]);
        	
        	if (puzzle_status == SpinBlocks.PUZZLE_COMPLETE && (SpinBlocks.LAST_PUZZLE_RESULT == SpinBlocks.PUZZLE_FAILED || SpinBlocks.LAST_PUZZLE_RESULT == SpinBlocks.PUZZLE_INCOMPLETE))
        	{
        		return;
        	}
        	else if (SpinBlocks.LAST_SCORE < puzzle_moves || puzzle_moves != 0)
        	{
        		SpinBlocks.PUZZLE_RECORDS[SpinBlocks.Puzzle_Level] = SpinBlocks.LAST_PUZZLE_RESULT + " " + SpinBlocks.LAST_SCORE;
        	}
        	else if (SpinBlocks.LAST_PUZZLE_RESULT == SpinBlocks.PUZZLE_COMPLETE && (puzzle_status == SpinBlocks.PUZZLE_FAILED || puzzle_status == SpinBlocks.PUZZLE_INCOMPLETE))
        	{
        		SpinBlocks.PUZZLE_RECORDS[SpinBlocks.Puzzle_Level] = SpinBlocks.LAST_PUZZLE_RESULT + " " + SpinBlocks.LAST_SCORE;
        	}

            SharedPreferences settings = getSharedPreferences("prefs", 0);		        
            SharedPreferences.Editor editor = settings.edit();
            
            editor.putString("puzzle" + SpinBlocks.Puzzle_Level, SpinBlocks.PUZZLE_RECORDS[SpinBlocks.Puzzle_Level]);
            
            editor.commit();
        }
        
        try {
        	getListView().setSelection(SpinBlocks.Puzzle_Level);
        }
        catch (Exception e) {}
        
        if (!SpinBlocks.FULL_VERSION)
        {
        	Toast.makeText(this, "More Puzzles Available in the full version!", Toast.LENGTH_SHORT).show();
        }

    }
    
	/**
     * Standard override to get key-press events.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent msg) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
	        Intent gameIntent = new Intent(PuzzleSelector.this, MainMenu.class);
	        PuzzleSelector.this.startActivity(gameIntent);
	        PuzzleSelector.this.finish();
        }
        
        return true;
    }
}
