package com.memeworks.spinblocks;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;

public class UIManager {
	

	//ScoreBoard
	public ScoreKeeper Score_Keeper = new ScoreKeeper();
	
	public int State = SpinBlocks.UI_STATE_NORMAL;

	public Rect Pause_Toggle_Rect = new Rect();	
	public Rect Sound_Toggle_Rect = new Rect();
	public Rect Menu_Button_Rect = new Rect();
	public Rect Restart_Button_Rect = new Rect();
	
	//Bounds
	private Rect bounds = new Rect();
	private float opacity = 255;
	private int opacity_fade_direction = 1;
	private Paint paint_bound;

	//Combo meter
	private Paint paint_combo_back = SpinBlocks.PAINT_BLACK;
	private Paint paint_combo_front = SpinBlocks.PAINT_BLACK;
	private Rect combo_meter_back_rect = new Rect();
	private Rect combo_meter_front_rect = new Rect();
	
	//Game Timer
	public float Current_Game_Time = 3000;
	public float Max_Game_Time = 3000;
	private float game_timer_burn_rate = 10;
	private Rect game_timer_rect = new Rect();
	private Paint paint_game_timer = new Paint();
	private int game_timer_height = 0;
	
	//Mode Text
	public StringBuilder Mode_Text = new StringBuilder();
	private final char mode_chars[] = new char[100];
	private Paint mode_paint = new Paint();
	private Paint mode_outline_paint = new Paint();
	private float mode_text_size = 21;
	
	//Level Text
	public StringBuilder Level_Text = new StringBuilder();
	private final char level_chars[] = new char[100];
	private Paint level_paint = new Paint();
	private Paint level_outline_paint = new Paint();
	private float level_text_size = 18;

	public UIManager()
	{
		//Bounds
		bounds.top = (int) (SpinBlocks.CENTER_CORE_Y + 5.5 * SpinBlocks.GRID_CELL_SIZE);
		bounds.bottom = (int) (SpinBlocks.CENTER_CORE_Y - 5.5 * SpinBlocks.GRID_CELL_SIZE);
		bounds.left = (int) (SpinBlocks.CENTER_CORE_X - 5.5 * SpinBlocks.GRID_CELL_SIZE);
		bounds.right = (int) (SpinBlocks.CENTER_CORE_X + 5.5 * SpinBlocks.GRID_CELL_SIZE);
		
		paint_bound = new Paint();
		paint_bound.setARGB((int)opacity, 0, 255, 0);
		paint_bound.setStyle(Style.STROKE);
		
		//Combo Meter
		SpinBlocks.PAINT_BLACK.setARGB(255, 0, 0, 0);
		SpinBlocks.PAINT_BLACK.setStyle(Style.FILL);
		
		SpinBlocks.PAINT_RED.setARGB(255, 255, 0, 0);
		SpinBlocks.PAINT_RED.setStyle(Style.FILL);
		
		SpinBlocks.PAINT_GREEN.setARGB(255, 0, 255, 0);
		SpinBlocks.PAINT_GREEN.setStyle(Style.FILL);
		
		SpinBlocks.PAINT_BLUE.setARGB(255, 0, 0, 255);
		SpinBlocks.PAINT_BLUE.setStyle(Style.FILL);
		
		SpinBlocks.PAINT_ORANGE.setARGB(255, 232, 116, 0);
		SpinBlocks.PAINT_ORANGE.setStyle(Style.FILL);
		
		SpinBlocks.PAINT_PURPLE.setARGB(255, 89, 0, 105);
		SpinBlocks.PAINT_PURPLE.setStyle(Style.FILL);
		
		combo_meter_back_rect.left = SpinBlocks.SCREEN_WIDTH - (int)(SpinBlocks.BITMAP_FRAME_RIGHT.getWidth() * 0.25f);
		combo_meter_back_rect.right = SpinBlocks.SCREEN_WIDTH;
		combo_meter_back_rect.top = (int) (SpinBlocks.SCREEN_HEIGHT * 0.15f);
		combo_meter_back_rect.bottom = (int) (SpinBlocks.SCREEN_HEIGHT * 0.9375f);
		
		combo_meter_front_rect = new Rect(combo_meter_back_rect);
		
		//Game Timer
		game_timer_rect.bottom = (int) (SpinBlocks.SCREEN_HEIGHT * 0.84375f);
		game_timer_rect.top = (int) (SpinBlocks.SCREEN_HEIGHT * 0.21875);
		game_timer_rect.left = 0;
		game_timer_rect.right = (int) (SpinBlocks.BITMAP_FRAME_LEFT.getWidth() * 0.25f);
		game_timer_height = game_timer_rect.height();
		paint_game_timer.setARGB(255, 0, 255, 0);
		paint_game_timer.setStyle(Style.FILL);
		
		//Mode Text
		mode_paint.setARGB(255, 255, 255, 255);
		mode_paint.setTypeface(SpinBlocks.FONT);
		mode_paint.setTextSize(mode_text_size);
		mode_paint.setTextAlign(Paint.Align.LEFT);
		mode_paint.setAntiAlias(true);
		
		mode_outline_paint.setARGB(255, 0, 0, 0);
		mode_outline_paint.setTextAlign(Paint.Align.LEFT);
		mode_outline_paint.setTextSize(mode_text_size);
		mode_outline_paint.setTypeface(SpinBlocks.FONT);
		mode_outline_paint.setStyle(Paint.Style.STROKE);
		mode_outline_paint.setStrokeWidth(3);
		mode_outline_paint.setAntiAlias(true);
		
		Mode_Text.setLength(0);
		switch (SpinBlocks.Gametype)
		{
		case SpinBlocks.GAMETYPE_TIMED:
			Mode_Text.append("TIMED");
			break;
		case SpinBlocks.GAMETYPE_ENDLESS:
			Mode_Text.append("ENDLESS");
			break;
		case SpinBlocks.GAMETYPE_PUZZLE:
			Mode_Text.append("PUZZLE");
			break;
		}
		
		//Level Text
		level_paint.setARGB(255, 255, 255, 255);
		level_paint.setTypeface(SpinBlocks.FONT);
		level_paint.setTextSize(level_text_size);
		level_paint.setTextAlign(Paint.Align.LEFT);
		level_paint.setAntiAlias(true);
		
		level_outline_paint.setARGB(255, 0, 0, 0);
		level_outline_paint.setTextAlign(Paint.Align.LEFT);
		level_outline_paint.setTextSize(level_text_size);
		level_outline_paint.setTypeface(SpinBlocks.FONT);
		level_outline_paint.setStyle(Paint.Style.STROKE);
		level_outline_paint.setStrokeWidth(3);
		level_outline_paint.setAntiAlias(true);
		
		//Menu Button
		Menu_Button_Rect.left = 5;
		Menu_Button_Rect.top = (SpinBlocks.SCREEN_HEIGHT - SpinBlocks.BITMAP_BUTTON_PAUSE.getHeight()) - 5;
		Menu_Button_Rect.right = Menu_Button_Rect.left + SpinBlocks.BITMAP_BUTTON_PAUSE.getWidth();
		Menu_Button_Rect.bottom = Menu_Button_Rect.top + SpinBlocks.BITMAP_BUTTON_PAUSE.getHeight();
		
		//Sound Button
		Sound_Toggle_Rect.left = 10 + SpinBlocks.BITMAP_BUTTON_PAUSE.getWidth();
		Sound_Toggle_Rect.top = (SpinBlocks.SCREEN_HEIGHT - SpinBlocks.BITMAP_BUTTON_PAUSE.getHeight()) - 5;
		Sound_Toggle_Rect.right = Sound_Toggle_Rect.left + SpinBlocks.BITMAP_BUTTON_PAUSE.getWidth();
		Sound_Toggle_Rect.bottom = Sound_Toggle_Rect.top + SpinBlocks.BITMAP_BUTTON_PAUSE.getHeight();
		
		//Pause Button
		Pause_Toggle_Rect.left = 15 + (SpinBlocks.BITMAP_BUTTON_PAUSE.getWidth() * 2);
		Pause_Toggle_Rect.top = (SpinBlocks.SCREEN_HEIGHT - SpinBlocks.BITMAP_BUTTON_PAUSE.getHeight()) - 5;
		Pause_Toggle_Rect.right = Pause_Toggle_Rect.left + SpinBlocks.BITMAP_BUTTON_PAUSE.getWidth();
		Pause_Toggle_Rect.bottom = Pause_Toggle_Rect.top + SpinBlocks.BITMAP_BUTTON_PAUSE.getHeight();
		
		//Restart Button
		Restart_Button_Rect.left = 3;
		Restart_Button_Rect.top = 35;
		Restart_Button_Rect.right = Restart_Button_Rect.left + SpinBlocks.BITMAP_BUTTON_RESTART.getWidth();
		Restart_Button_Rect.bottom = Restart_Button_Rect.top + SpinBlocks.BITMAP_BUTTON_RESTART.getHeight();
		
	}
	
	public void Draw(Canvas c)
	{
		//DrawBounds 
		c.drawRect(bounds, paint_bound);
		
		//Draw Combo Meter (under UI frames)
		c.drawRect(combo_meter_back_rect, paint_combo_back);
		c.drawRect(combo_meter_front_rect, paint_combo_front);
		
		//Draw Game Timer (under UI frames)
		c.drawRect(game_timer_rect, paint_game_timer);
		
		//Draw UI frames
		c.drawBitmap(SpinBlocks.BITMAP_FRAME_LEFT, 0, SpinBlocks.SCREEN_HEIGHT - SpinBlocks.BITMAP_FRAME_LEFT.getHeight(),	null);
		c.drawBitmap(SpinBlocks.BITMAP_FRAME_RIGHT, SpinBlocks.SCREEN_WIDTH - SpinBlocks.BITMAP_FRAME_RIGHT.getWidth(), 0,	null);
		
		//Draw Mode Text
		Mode_Text.getChars(0, Mode_Text.length(), mode_chars, 0);
		c.drawText(mode_chars, 0, Mode_Text.length(), 7, 23, mode_outline_paint);
		c.drawText(mode_chars, 0, Mode_Text.length(), 7, 23, mode_paint);
		
		//Draw Level Text
		if (SpinBlocks.Gametype != SpinBlocks.GAMETYPE_PUZZLE)
		{
			Level_Text.getChars(0, Level_Text.length(), level_chars, 0);
			c.drawText(level_chars, 0, Level_Text.length(), 7, 50, level_outline_paint);
			c.drawText(level_chars, 0, Level_Text.length(), 7, 50, level_paint);		
		}

		//Pause Button
		c.drawBitmap(SpinBlocks.BITMAP_BUTTON_PAUSE, null, Pause_Toggle_Rect, null);
		
		//Sound Button
		if (SpinBlocks.SOUND)
		{
			c.drawBitmap(SpinBlocks.BITMAP_BUTTON_SOUND_ON, null, Sound_Toggle_Rect, null);
		}
		else
		{
			c.drawBitmap(SpinBlocks.BITMAP_BUTTON_SOUND_OFF, null, Sound_Toggle_Rect, null);
		}
		
		//Menu Button
		c.drawBitmap(SpinBlocks.BITMAP_BUTTON_MENU, null, Menu_Button_Rect, null);
		
		//Restart Button
		if (SpinBlocks.Gametype == SpinBlocks.GAMETYPE_PUZZLE)
		{
			c.drawBitmap(SpinBlocks.BITMAP_BUTTON_RESTART, null, Restart_Button_Rect, null);
		}
		
		Score_Keeper.Draw(c);
	}
	
	public void Frame_Started(float frame_time)
	{
		//Determine bound color
		if (SpinBlocks.ORB_GRID.Grid_Bound_Top == -5 || 
				SpinBlocks.ORB_GRID.Grid_Bound_Bottom == 5 ||
				SpinBlocks.ORB_GRID.Grid_Bound_Left == -5 ||
				SpinBlocks.ORB_GRID.Grid_Bound_Right == 5)
		{
			
			opacity += opacity_fade_direction * frame_time * 300;
			
			if (opacity >= 255 && opacity_fade_direction == 1)
			{
				opacity = 255;
				opacity_fade_direction = -1;
			}
			else if (opacity <= 0 && opacity_fade_direction == -1)
			{
				opacity = 0;
				opacity_fade_direction = 1;
			}

			paint_bound.setARGB(255, (int)opacity, (int)opacity, 0);
		}
		else if (SpinBlocks.ORB_GRID.Grid_Bound_Top <= -6 || 
				SpinBlocks.ORB_GRID.Grid_Bound_Bottom >= 6 ||
				SpinBlocks.ORB_GRID.Grid_Bound_Left <= -6 ||
				SpinBlocks.ORB_GRID.Grid_Bound_Right >= 6)
		{
			opacity = 255;
			paint_bound.setARGB((int)opacity, 255, 0, 0);
			
			SpinBlocks.GAMETYPE.GameOver("Out of Bounds!");
		}
		else
		{
			opacity = 255;
			paint_bound.setARGB((int)opacity, 0, 100, 0);
		}
		
		//Update score
		Score_Keeper.Frame_Started(frame_time);
		
		//Determine Combo color
		switch(Score_Keeper.Current_Multiplier - 1)
		{
		case SpinBlocks.ORB_GREEN:
			paint_combo_back = SpinBlocks.PAINT_BLACK;
			paint_combo_front = SpinBlocks.PAINT_GREEN;
			break;
			
		case SpinBlocks.ORB_BLUE:
			paint_combo_back = SpinBlocks.PAINT_GREEN;
			paint_combo_front = SpinBlocks.PAINT_BLUE;
			break;
			
		case SpinBlocks.ORB_PURPLE:
			paint_combo_back = SpinBlocks.PAINT_BLUE;
			paint_combo_front = SpinBlocks.PAINT_PURPLE;
			break;
			
		case SpinBlocks.ORB_ORANGE:
			paint_combo_back = SpinBlocks.PAINT_PURPLE;
			paint_combo_front = SpinBlocks.PAINT_ORANGE;
			break;
			
		case SpinBlocks.ORB_RED:
		default:
			paint_combo_back = SpinBlocks.PAINT_ORANGE;
			paint_combo_front = SpinBlocks.PAINT_RED;
			break;
		}
		
		combo_meter_front_rect.top = (int) (combo_meter_back_rect.top + (combo_meter_back_rect.height() * (200 - Score_Keeper.Multiplier_Level % 200) / 200));
		
		//Game Timer
		if (SpinBlocks.Gametype != SpinBlocks.GAMETYPE_ENDLESS)
		{
			if (Current_Game_Time > Max_Game_Time)
			{
				Current_Game_Time = Max_Game_Time;
			}
			game_timer_burn_rate = 25 * Score_Keeper.Current_Level;
			Current_Game_Time -= game_timer_burn_rate * frame_time;
			game_timer_rect.top = (int) (game_timer_rect.bottom - (game_timer_height * (Current_Game_Time / Max_Game_Time)));
			paint_game_timer.setARGB(255, (int)(255 * (Max_Game_Time - Current_Game_Time) / Max_Game_Time), (int)(255 * (Current_Game_Time / Max_Game_Time)), 0);
			
			if (Current_Game_Time < 0)
			{
				SpinBlocks.GAMETYPE.GameOver("Time up!");
			}
		}
		
		//Level Text
		if (SpinBlocks.Gametype != SpinBlocks.GAMETYPE_PUZZLE)
		{
			Level_Text.setLength(0);
			Level_Text.append("Level " + Score_Keeper.Current_Level);
		}
	}
	
}
