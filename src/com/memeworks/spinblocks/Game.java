package com.memeworks.spinblocks;

import com.memeworks.spinblocks.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;

public class Game extends Activity {

    /** A handle to the View in which the game is running. */
    private MainView main_view;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setup fonts
        SpinBlocks.FONT = Typeface.createFromAsset(getAssets(), "fonts/komikax.ttf");
        
        // tell system to use the layout defined in our XML file
        setContentView(R.layout.main);

        // Use volume buttons to control sound
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        // get handles to the MainView from XML, and its MainThread 
        main_view = (MainView) findViewById(R.id.game);
        SpinBlocks.MAIN_THREAD = main_view.getThread(); 
        SpinBlocks.MAIN_THREAD.setGamePointer(Game.this);
       
    } 
	
    /**
     * Invoked when the Activity loses user focus.
     */
    @Override
    protected void onPause() {
        super.onPause();
        SpinBlocks.MAIN_THREAD.setRunning(false);
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	if (!SpinBlocks.MAIN_THREAD.Finishing)
    	{
    		SpinBlocks.MAIN_THREAD.Finishing = true;
	    	SpinBlocks.MAIN_THREAD.setRunning(false);
	    	SpinBlocks.MAIN_THREAD.State = SpinBlocks.STATE_GAMEOVER;
	    	Intent gameIntent = new Intent(Game.this, MainMenu.class);
	        Game.this.startActivity(gameIntent);
	        Game.this.finish();
    	}
    }
    
    /**
     * Standard override to get key-press events.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent msg) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	if (!SpinBlocks.MAIN_THREAD.Finishing)
        	{
        		SpinBlocks.MAIN_THREAD.Finishing = true;
	        	SpinBlocks.MAIN_THREAD.pause();
	        	Intent gameIntent;
	        	if (SpinBlocks.Gametype != SpinBlocks.GAMETYPE_PUZZLE)
	        	{
	        		gameIntent = new Intent(Game.this, MainMenu.class);
	        	}
	        	else
	        	{
	        		gameIntent = new Intent(Game.this, PuzzleSelector.class);
	        	}
	        	
	            Game.this.startActivity(gameIntent);
	            Game.this.finish();
        	}
        }
        
        return true;
    }

}
