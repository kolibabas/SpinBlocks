package com.memeworks.spinblocks;

import android.graphics.Canvas;

public class Gametype {
	
	private int last_launcher = SpinBlocks.DIRECTION_DOWN;
	
	private float launch_time_easy = 6;
	private float launch_time_medium = 5;
	private float launch_time_hard = 4;
	
	private float next_level_timer_init = 1;
	private float next_level_timer = 1;
	
	private FloatText overlay_text;
	
	public Gametype() {
		overlay_text = new FloatText();
		
		switch (SpinBlocks.Difficulty)
		{
		case SpinBlocks.DIFFICULTY_EASY:
			SpinBlocks.ORB_GRID.Launcher_Bottom.Launch_Timer_Init = launch_time_easy;
			SpinBlocks.ORB_GRID.Launcher_Bottom.Launch_Timer = launch_time_easy;
			SpinBlocks.ORB_GRID.Launcher_Top.Launch_Timer_Init = launch_time_easy;
			SpinBlocks.ORB_GRID.Launcher_Top.Launch_Timer = launch_time_easy;
			break;
		case SpinBlocks.DIFFICULTY_MEDIUM:
			SpinBlocks.ORB_GRID.Launcher_Bottom.Launch_Timer_Init = launch_time_medium;
			SpinBlocks.ORB_GRID.Launcher_Bottom.Launch_Timer = launch_time_medium;
			SpinBlocks.ORB_GRID.Launcher_Top.Launch_Timer_Init = launch_time_medium;
			SpinBlocks.ORB_GRID.Launcher_Top.Launch_Timer = launch_time_medium;
			break;
		case SpinBlocks.DIFFICULTY_HARD:
			SpinBlocks.ORB_GRID.Launcher_Bottom.Launch_Timer_Init = launch_time_hard;
			SpinBlocks.ORB_GRID.Launcher_Bottom.Launch_Timer = launch_time_hard;
			SpinBlocks.ORB_GRID.Launcher_Top.Launch_Timer_Init = launch_time_hard;
			SpinBlocks.ORB_GRID.Launcher_Top.Launch_Timer = launch_time_hard;
			break;
		}
	}
	
	public void Draw(Canvas c)
	{
		overlay_text.Draw(c);
	}
	
	public void Frame_Started(float frame_time)
	{
		overlay_text.Frame_Started(frame_time);
		
		switch(SpinBlocks.Gametype)
		{
		case SpinBlocks.GAMETYPE_TIMED:
		case SpinBlocks.GAMETYPE_ENDLESS:
			if (!SpinBlocks.ORB_GRID.Launcher_Top.Orb_Loaded && !SpinBlocks.ORB_GRID.Launcher_Bottom.Orb_Loaded && SpinBlocks.ORB_GRID.Current_Orb_Count != 1 && SpinBlocks.Can_Launch)
			{
				if (last_launcher == SpinBlocks.DIRECTION_DOWN)
				{
					SpinBlocks.ORB_GRID.CreateRandomLauncherOrb(SpinBlocks.DIRECTION_UP);
					last_launcher = SpinBlocks.DIRECTION_UP;
					SpinBlocks.Can_Launch = false;
				}
				else
				{
					SpinBlocks.ORB_GRID.CreateRandomLauncherOrb(SpinBlocks.DIRECTION_DOWN);
					last_launcher = SpinBlocks.DIRECTION_DOWN;
					SpinBlocks.Can_Launch = false;
				}
			}
		case SpinBlocks.GAMETYPE_PUZZLE:
			if (SpinBlocks.ORB_GRID.Current_Orb_Count != 1 && SpinBlocks.Can_Launch && !SpinBlocks.ORB_GRID.Launcher_Bottom.Orb_Loaded)
			{
				SpinBlocks.ORB_GRID.CreateLauncherOrb(SpinBlocks.DIRECTION_UP, SpinBlocks.ORB_GRID.Launcher_Bottom.Next_Orb_Color);
				SpinBlocks.ORB_GRID.Launcher_Bottom.SetNextOrb(SpinBlocks.Current_Puzzle.GetNextOrbColor());
				SpinBlocks.Can_Launch = false;
			}
			break;
			
		default:
			break;
		}
		
		if (SpinBlocks.ORB_GRID.Current_Orb_Count == 1 && next_level_timer > 0)
		{
			next_level_timer -= frame_time;
		}
		else if (SpinBlocks.ORB_GRID.Current_Orb_Count == 1 && next_level_timer <= 0)
		{
			if (SpinBlocks.Gametype == SpinBlocks.GAMETYPE_PUZZLE)
			{
				String moves = " Moves";
				if ((int)SpinBlocks.UI_MANAGER.Score_Keeper.Current_Score == 1)
				{
					moves = " Move!";
				}
		        
				SpinBlocks.LAST_PUZZLE_RESULT = SpinBlocks.PUZZLE_COMPLETE;
				GameOver((int)SpinBlocks.UI_MANAGER.Score_Keeper.Current_Score + moves);
				SpinBlocks.SOUND_MANAGER.playSound(SpinBlocks.SOUND_LEVEL_UP);
			}
			else
			{
				SpinBlocks.ORB_GRID.StartNewLevel();
				SpinBlocks.UI_MANAGER.Score_Keeper.NotifyNextLevel();
			}
			next_level_timer = next_level_timer_init;
		}
	}
	 
	public void GameOver(String reason)
	{
		if (SpinBlocks.Gametype != SpinBlocks.GAMETYPE_ENDLESS)
		{
			SpinBlocks.LAST_SCORE = (int) SpinBlocks.UI_MANAGER.Score_Keeper.Current_Score;
			SpinBlocks.Can_Launch = false;
			SpinBlocks.Can_Rotate = false;
			
			SpinBlocks.Game_Over_Reason = reason;
			SpinBlocks.MAIN_THREAD.State = SpinBlocks.STATE_GAMEOVER;
			
			if (SpinBlocks.LAST_PUZZLE_RESULT != SpinBlocks.PUZZLE_COMPLETE)
			{
				SpinBlocks.SOUND_MANAGER.playSound(SpinBlocks.SOUND_GAME_OVER);
				SpinBlocks.LAST_PUZZLE_RESULT = SpinBlocks.PUZZLE_FAILED;
			}
		}
		else
		{
			overlay_text.Show(SpinBlocks.SCREEN_CENTER_X, SpinBlocks.SCREEN_CENTER_Y, "Out of Bounds!", 30, 100);
			SpinBlocks.ORB_GRID.StartNewLevel();
		}
	}
}
