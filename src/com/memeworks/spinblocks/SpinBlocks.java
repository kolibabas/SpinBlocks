package com.memeworks.spinblocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;

import com.memeworks.spinblocks.MainView.MainThread;
import com.memeworks.spinblocks.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SpinBlocks extends Activity {
 
	//Static enums
	public static final boolean FULL_VERSION = true;
	
	public static final long SPLASH_TIME = 2000;
	
	public static final int PUZZLE_COUNT = PuzzleList.Puzzles.length;
	public static final int PUZZLE_INCOMPLETE = 1;
	public static final int PUZZLE_COMPLETE = 2;
	public static final int PUZZLE_FAILED = 3;
	
	public static final int GAMETYPE_TIMED = 1;
	public static final int GAMETYPE_ENDLESS = 2;
	public static final int GAMETYPE_PUZZLE = 3;

	public static final int DIFFICULTY_EASY = 3;
	public static final int DIFFICULTY_MEDIUM = 5;
	public static final int DIFFICULTY_HARD = 7;

	public static final int STATE_GAMEOVER = 1;
	public static final int STATE_PAUSE = 2;
	public static final int STATE_READY = 3;
	public static final int STATE_RUNNING = 4;

	public static final int UI_STATE_NORMAL = 1;
	public static final int UI_STATE_WARNING = 2;
	public static final int UI_STATE_CRITICAL = 3;
	
	public static final int MOVABLE_STATE_ALIVE = 1;
	public static final int MOVABLE_STATE_DEATH_SCHEDULED = 2;
	public static final int MOVABLE_STATE_DYING = 3;
	public static final int MOVABLE_STATE_DEAD = 4;
	
	public static final int ORB_CORE = -1;
	public static final int ORB_GREEN = 0;
	public static final int ORB_BLUE = 1;
	public static final int ORB_PURPLE = 2;
	public static final int ORB_ORANGE = 3;
	public static final int ORB_RED = 4;
	public static final int ORB_LIME = 5;
	public static final int ORB_GRAY = 6;
	
	public static final int ORB_POWERUP_MARKER = 100;
	public static final int ORB_BOMB = 101;
	public static final int ORB_WILDCARD = 102;
	
	public static Paint PAINT_BLACK = new Paint();
	public static Paint PAINT_RED = new Paint();
	public static Paint PAINT_GREEN = new Paint();
	public static Paint PAINT_BLUE = new Paint();
	public static Paint PAINT_ORANGE = new Paint();
	public static Paint PAINT_PURPLE = new Paint();
	
	public static Bitmap BITMAP_ORB_CORE;
	public static Bitmap BITMAP_ORB_RED;
	public static Bitmap BITMAP_ORB_GREEN;
    public static Bitmap BITMAP_ORB_BLUE;
    public static Bitmap BITMAP_ORB_ORANGE;
    public static Bitmap BITMAP_ORB_PURPLE;
    public static Bitmap BITMAP_ORB_LIME;
    public static Bitmap BITMAP_ORB_GRAY;
    public static Bitmap BITMAP_ORB_BOMB;
    public static Bitmap BITMAP_ORB_WILDCARD;
    
    public static Bitmap BITMAP_LAUNCHER_TOP;
    public static Bitmap BITMAP_LAUNCHER_BOTTOM;
    public static Bitmap BITMAP_ARROW_LEFT;
    public static Bitmap BITMAP_ARROW_RIGHT;
    
    public static Bitmap BITMAP_FRAME_RIGHT;
    public static Bitmap BITMAP_FRAME_LEFT;
    
    public static Bitmap BITMAP_BUTTON_SOUND_OFF;
    public static Bitmap BITMAP_BUTTON_SOUND_ON;
    public static Bitmap BITMAP_BUTTON_PAUSE;
    public static Bitmap BITMAP_BUTTON_MENU;
    public static Bitmap BITMAP_BUTTON_RESTART;
    
	public static final int SOUND_MUSIC = 0;
	public static final int SOUND_BLOCK_POP = 1;
	public static final int SOUND_BLOCK_ATTACH = 2;
	public static final int SOUND_ORB_GRID_ROTATE = 3;
	public static final int SOUND_LAUNCHER_MOVE = 4;
	public static final int SOUND_LAUNCHER_FIRE = 5;
	public static final int SOUND_BOMB_EXPLODE = 6;
	public static final int SOUND_LEVEL_UP = 7;
	public static final int SOUND_GAME_OVER = 8;
    
	public static final int ROTATE_NONE = 0;
	public static final int ROTATE_LEFT = -1;
	public static final int ROTATE_RIGHT = 1;
	
	public static final int DIRECTION_NONE = 0;
	public static final int DIRECTION_UP = 1;
	public static final int DIRECTION_DOWN = 2;
	public static final int DIRECTION_LEFT = 3;
	public static final int DIRECTION_RIGHT = 4;
	
	public static final int ORB_COUNT_MAX = 81;
	
	public static int SCREEN_WIDTH = 0;
	public static int SCREEN_HEIGHT = 0;
	public static int SCREEN_CENTER_X = 0;
	public static int SCREEN_CENTER_Y = 0;
	public static int GRID_CELL_SIZE = 0;
	public static float CENTER_CORE_X = 0;
	public static float CENTER_CORE_Y = 0;
	
	public static final float HALF_PI = (float)(Math.PI / 2);
	public static final float PI = (float)(Math.PI);
	public static final float THREE_HALF_PI = (float)(3 * Math.PI / 2);
	
	//Members and Settings
	public static boolean SOUND = false;
    public static SoundManager SOUND_MANAGER;
	public static boolean COLORBLIND_MODE = false;
	public static MainThread MAIN_THREAD;
	public static String Game_Over_Reason = "";
	public static int Difficulty = DIFFICULTY_MEDIUM;
	
	public static int Gametype = GAMETYPE_TIMED;
	public static Gametype GAMETYPE;
	public static OrbGrid ORB_GRID;
	public static boolean Orb_In_Air = false;
	public static boolean Can_Launch = true;
	public static boolean Can_Rotate = true;
	public static UIManager UI_MANAGER;
	public static Typeface FONT;
	
	public static String[] EASY_SCORES;
	public static String[] MEDIUM_SCORES;
	public static String[] HARD_SCORES;
	public static int LAST_SCORE = -1;
	public static String LAST_SCORE_NAME = "Anonymous";
	
	public static String[] PUZZLE_RECORDS = new String[PUZZLE_COUNT];
	public static Puzzle Current_Puzzle = PuzzleList.Puzzles[0];
	public static int Puzzle_Level = 0;
	public static int LAST_PUZZLE_RESULT = PUZZLE_INCOMPLETE;
	public static Activity game_holder = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.splash);
         
         // Restore preferences and scores
         SharedPreferences settings = getSharedPreferences("prefs", 0);
         SOUND = settings.getBoolean("sound", false);
         COLORBLIND_MODE = settings.getBoolean("colorblind", false);
         LAST_SCORE = -1;
         LAST_SCORE_NAME = settings.getString("scorename", "Anonymous");
         
         //Setup fonts
         SpinBlocks.FONT = Typeface.createFromAsset(getAssets(), "fonts/komikax.ttf");
         
         ParseScores();
         ParsePuzzles();
         
         //New Handler to start the Menu-Activity and close this Splash-Screen after some seconds.
         new Handler().postDelayed(new Runnable(){

              public void run() {
            	   ImageView splash = (ImageView) findViewById(R.id.splashscreen);
            	   splash.startAnimation(AnimationUtils.loadAnimation(SpinBlocks.this, android.R.anim.fade_out));
                   //Create an Intent that will start the Menu-Activity
                   Intent mainMenuIntent = new Intent(SpinBlocks.this, MainMenu.class);
                   SpinBlocks.this.startActivity(mainMenuIntent);
                   SpinBlocks.this.finish();
              }
         }, SPLASH_TIME);

    }
    
    private void ParseScores()
    {
    	SharedPreferences settings = getSharedPreferences("prefs", 0);
    	ArrayList<String> holder = new ArrayList<String>();
    	
    	for (int i = 0; i < 10; i++)
    	{
    		String score = settings.getString("easyscore" + i, "0");
    		if (!score.equals("0"))
    		{
    			holder.add(score);
    		}
    	}
    	if (holder.size() > 0)
    	{
    		EASY_SCORES = holder.toArray(new String[0]);
	    	holder.clear();
    	}
    	
    	for (int i = 0; i < 10; i++)
    	{
    		String score = settings.getString("mediumscore" + i, "0");
    		if (!score.equals("0"))
    		{
    			holder.add(score);
    		}
    	}
    	if (holder.size() > 0)
    	{
    		MEDIUM_SCORES = holder.toArray(new String[0]);
    		holder.clear();
    	}
    		
    	for (int i = 0; i < 10; i++)
    	{
    		String score = settings.getString("hardscore" + i, "0");
    		if (!score.equals("0"))
    		{
    			holder.add(score);
    		}
    	}
    	if (holder.size() > 0)
    	{
    		HARD_SCORES = holder.toArray(new String[0]);
    		holder.clear();
    	}
    }
    
    private void ParsePuzzles()
    {
    	SharedPreferences settings = getSharedPreferences("prefs", 0);
    	PUZZLE_RECORDS = new String[PUZZLE_COUNT];
    	
    	for (int i = 0; i < PUZZLE_COUNT; i++)
    	{
    		PUZZLE_RECORDS[i] = settings.getString("puzzle" + i, PUZZLE_INCOMPLETE + " 0");
    	}
    }
    
	public static int World_To_Grid_X(int pos_x)
	{
		return (int) ((pos_x - SpinBlocks.CENTER_CORE_X) / SpinBlocks.GRID_CELL_SIZE);
	}
	
	public static int World_To_Grid_Y(int pos_y)
	{
		return (int) ((pos_y - SpinBlocks.CENTER_CORE_Y) / SpinBlocks.GRID_CELL_SIZE);
	}
	
	public static void InsertHighScore()
	{
		ArrayList<String> holder;
		Calendar calendar = Calendar.getInstance();
		
		switch (Difficulty)
		{
		case DIFFICULTY_EASY:
			if (EASY_SCORES != null)
			{
				holder = new ArrayList<String>(Arrays.asList(EASY_SCORES));
			}
			else
			{
				holder = new ArrayList<String>();
			}			
			holder.add(LAST_SCORE_NAME + " - " + LAST_SCORE + " (" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DATE) + "/" + calendar.get(Calendar.YEAR) + ")");
			
			EASY_SCORES = SortScores(holder);
			break;
		case DIFFICULTY_MEDIUM:
			if (MEDIUM_SCORES != null)
			{
				holder = new ArrayList<String>(Arrays.asList(MEDIUM_SCORES));
			}
			else
			{
				holder = new ArrayList<String>();
			}
			holder.add(LAST_SCORE_NAME + " - " + LAST_SCORE + " (" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DATE) + "/" + calendar.get(Calendar.YEAR) + ")");
			
			MEDIUM_SCORES = SortScores(holder);
			break;
		case DIFFICULTY_HARD:
			if (HARD_SCORES != null)
			{
				holder = new ArrayList<String>(Arrays.asList(HARD_SCORES));
			}
			else
			{
				holder = new ArrayList<String>();
			}
			holder.add(LAST_SCORE_NAME + " - " + LAST_SCORE + " (" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DATE) + "/" + calendar.get(Calendar.YEAR) + ")");
			
			HARD_SCORES = SortScores(holder);
			break;
		}
	}
	
	private static String[] SortScores(ArrayList<String> list)
	{
		String[] slist = list.toArray(new String[0]);
		
		Arrays.sort(slist, new ScoreComparator());
		
		if (slist.length > 10)
		{
			String[] temp_list = slist.clone();
			slist = new String[10];
			
			for (int i = 0; i < 10; i++)
			{
				slist[i] = temp_list[i];
			}
		}
		return (slist);
	}
    
}

class ScoreComparator implements Comparator<String>
{
	@Override
	public int compare(String arg0, String arg1) {
		arg0 = arg0.split(" ")[2];
		arg1 = arg1.split(" ")[2];
		
		int score0 = Integer.parseInt(arg0);
		int score1 = Integer.parseInt(arg1);
		
		if (score0 < score1)
		{
			return 1;
		}
		else if (score0 > score1)
		{
			return -1;
		}
		else
		{
			return 0;
		}

	}
}