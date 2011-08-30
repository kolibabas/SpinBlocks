package com.memeworks.spinblocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.FloatMath;

public class OrbGrid {

	public HashMap<String, Orb> Orbs = new HashMap<String, Orb>();
	public ArrayList<String> Matches = new ArrayList<String>();
	public ArrayList<String> Visited = new ArrayList<String>();
	public ArrayList<String> Popped_Waiting = new ArrayList<String>();
	public ArrayList<String> Disconnected_Candidates = new ArrayList<String>();
	public Orb Orb_Waiting = null;
	public int Current_Orb_Count = 0;
	public int Rotate_Direction = SpinBlocks.ROTATE_NONE;
	
	public OrbLauncher Launcher_Top;
	public OrbLauncher Launcher_Bottom;
	
	public int Grid_Bound_Left = 0;
	public int Grid_Bound_Right = 0;
	public int Grid_Bound_Top = 0;
	public int Grid_Bound_Bottom = 0;
		
	private int lifetime_orb_count = 0;
	private float rotated_angle = 0;
	private float rotate_rate = 4.0f;
	private Random rand = new Random();

	public OrbGrid()
	{
		if (SpinBlocks.Gametype != SpinBlocks.GAMETYPE_PUZZLE)
		{
			CreatePuzzleOrb(SpinBlocks.ORB_CORE, 0, 0, SpinBlocks.SCREEN_CENTER_X, SpinBlocks.SCREEN_CENTER_Y - 5, SpinBlocks.BITMAP_ORB_CORE);
			StartNewLevel();
		}
    	else
    	{
    		CreatePuzzle();
    	}
		
		Launcher_Top = new OrbLauncher(SpinBlocks.DIRECTION_DOWN, (int)SpinBlocks.CENTER_CORE_X, SpinBlocks.BITMAP_LAUNCHER_TOP.getHeight() / 2 - 1, SpinBlocks.BITMAP_LAUNCHER_TOP);
		Launcher_Bottom = new OrbLauncher(SpinBlocks.DIRECTION_UP, (int)SpinBlocks.CENTER_CORE_X, SpinBlocks.SCREEN_HEIGHT - (SpinBlocks.BITMAP_LAUNCHER_BOTTOM.getHeight() / 2), SpinBlocks.BITMAP_LAUNCHER_BOTTOM);
		
		ComputeGridBoundaries();
		ComputeNeighbors();
		
		Orbs.get("orb0").Position_Grid_X = 0;
		Orbs.get("orb0").Position_Grid_Y = 0;
	}
	
	public synchronized void CreatePuzzle()
	{
		Orbs.clear();
		
		CreatePuzzleOrb(SpinBlocks.ORB_CORE, 0, 0, SpinBlocks.SCREEN_CENTER_X, SpinBlocks.SCREEN_CENTER_Y - 5, SpinBlocks.BITMAP_ORB_CORE);
		for (PuzzleOrb o : SpinBlocks.Current_Puzzle.Initial_Orbs)
		{
			CreatePuzzleOrb(o.Grid_X, o.Grid_Y, o.Color);
		}
		
		SpinBlocks.Can_Launch = true;
		SpinBlocks.Can_Rotate = true;
		SpinBlocks.Orb_In_Air = false;
	}
	
	public void Draw(Canvas c)
	{
		if (SpinBlocks.Gametype != SpinBlocks.GAMETYPE_PUZZLE)
		{
			Launcher_Top.Draw(c);
		}
		Launcher_Bottom.Draw(c);
		
		for(Orb o : Orbs.values())
		{
			o.Draw(c);
		}
		
		if (Orb_Waiting != null)
		{
			Orbs.put(Orb_Waiting.Name, Orb_Waiting);
			Orb_Waiting = null;
		}
	}
	
	public void Frame_Started(float frame_time)
	{
		if (Rotate_Direction != SpinBlocks.ROTATE_NONE)
		{
			Rotate(frame_time);
		}
		else
		{
			for(Orb o : Orbs.values())
			{
				o.Frame_Started(frame_time);
			}
		}
		
		Launcher_Top.Frame_Started(frame_time);
		Launcher_Bottom.Frame_Started(frame_time);
		
		if (Popped_Waiting.size() > 0)
		{
			for (String s : Popped_Waiting)
			{
				for(Orb o : Orbs.values())
				{
					if (o.Neighbors.contains(s))
					{
						o.Neighbors.remove(s);
					}
				}
				Orbs.remove(s);
			}
			Current_Orb_Count = Orbs.size();
			//ComputeGridBoundaries();
			//Launcher_Top.Limit_To_Grid_Boundary();
			//Launcher_Bottom.Limit_To_Grid_Boundary();
			Popped_Waiting.clear();
		} 
	}
	
	public void Rotate(int direction)
	{
		if (Rotate_Direction == SpinBlocks.ROTATE_NONE && SpinBlocks.Can_Rotate)
		{
			Rotate_Direction = direction;
			SpinBlocks.Orb_In_Air = true;
			
			SpinBlocks.SOUND_MANAGER.playSound(SpinBlocks.SOUND_ORB_GRID_ROTATE);
			
			if (SpinBlocks.Gametype == SpinBlocks.GAMETYPE_PUZZLE)
			{
				SpinBlocks.UI_MANAGER.Score_Keeper.Current_Score++;
			}
		}		
	}
	
	private synchronized void Rotate(float frame_time)
	{	
		float rotate_amount = frame_time * rotate_rate * Rotate_Direction;
		rotated_angle += rotate_amount;
	
		if (rotated_angle > SpinBlocks.HALF_PI || rotated_angle < -SpinBlocks.HALF_PI)
		{
			Rotate_Direction = SpinBlocks.ROTATE_NONE;
			rotated_angle = 0;

			for(Orb o : Orbs.values())
			{
				if (o.Attached)
				{
					o.Snap_To_Grid();
					o.ComputeDistanceAngle();
				}
			}
			ComputeGridBoundaries();
			Launcher_Top.Limit_To_Grid_Boundary();
			Launcher_Bottom.Limit_To_Grid_Boundary();
			
			Compute_Target_Y(SpinBlocks.DIRECTION_UP);
			Compute_Target_Y(SpinBlocks.DIRECTION_DOWN);
			SpinBlocks.Orb_In_Air = false;
		}
		else 
		{
			for(Orb o : Orbs.values())
			{
				if (o.Attached)
				{
					o.Current_Angle += rotate_amount;
					
					o.Position_World_X = (int)(FloatMath.cos(o.Current_Angle) * o.Distance_From_Core) + SpinBlocks.CENTER_CORE_X;
					o.Position_World_Y = (int)(FloatMath.sin(o.Current_Angle) * o.Distance_From_Core) + SpinBlocks.CENTER_CORE_Y;
				}
			}
		}
	}
	
	private void ComputeGridBoundaries()
	{
		Grid_Bound_Left = 0;
		Grid_Bound_Right = 0;
		Grid_Bound_Top = 0;
		Grid_Bound_Bottom = 0;
		
		int attached_count = 0;
		
		for(Orb o : Orbs.values())
		{
			if (o.Attached)
			{
				if (o.Position_Grid_X > Grid_Bound_Right)
				{
					Grid_Bound_Right = o.Position_Grid_X;
				}
				else if (o.Position_Grid_X < Grid_Bound_Left)
				{
					Grid_Bound_Left = o.Position_Grid_X;
				}
				
				if (o.Position_Grid_Y > Grid_Bound_Bottom)
				{
					Grid_Bound_Bottom = o.Position_Grid_Y;
				}
				else if (o.Position_Grid_Y < Grid_Bound_Top)
				{
					Grid_Bound_Top = o.Position_Grid_Y;
				}
				
				attached_count++;
			}
		}
	}
	
	public void StartNewLevel()
	{
		SpinBlocks.SOUND_MANAGER.playSound(SpinBlocks.SOUND_LEVEL_UP);
		for (Orb o : Orbs.values())
		{
			if (o.Color != SpinBlocks.ORB_CORE)
			{
				o.Pop(0);
			}
		}
		
		CreateRandomPuzzleOrb(-1, 0);
		CreateRandomPuzzleOrb(-1, -1);
		CreateRandomPuzzleOrb(0, -1);
		CreateRandomPuzzleOrb(1, -1);
		CreateRandomPuzzleOrb(1, 0);
		CreateRandomPuzzleOrb(1, 1);
		CreateRandomPuzzleOrb(0, 1);
		CreateRandomPuzzleOrb(-1, 1);
		
		CreateRandomPuzzleOrb(-2, 2);
		CreateRandomPuzzleOrb(-2, 1);
		CreateRandomPuzzleOrb(-2, 0);
		CreateRandomPuzzleOrb(-2, -1);
		CreateRandomPuzzleOrb(-2, -2);
		CreateRandomPuzzleOrb(-1, -2);
		CreateRandomPuzzleOrb(0, -2);
		CreateRandomPuzzleOrb(1, -2);
		CreateRandomPuzzleOrb(2, -2);
		CreateRandomPuzzleOrb(2, -1);
		CreateRandomPuzzleOrb(2, 0);
		CreateRandomPuzzleOrb(2, 1);
		CreateRandomPuzzleOrb(2, 2);
		CreateRandomPuzzleOrb(1, 2);
		CreateRandomPuzzleOrb(0, 2);
		CreateRandomPuzzleOrb(-1, 2);
		
		SpinBlocks.Orb_In_Air = false;
		SpinBlocks.Can_Launch = true;
		SpinBlocks.Can_Rotate = true;
		
		ComputeGridBoundaries();

		if (Launcher_Top != null && Launcher_Bottom != null)
		{
			Compute_Target_Y(SpinBlocks.DIRECTION_UP);
			Compute_Target_Y(SpinBlocks.DIRECTION_DOWN);
		}
	}
	
	public void CreatePuzzleOrb(int color, int grid_x, int grid_y, int pos_x, int pos_y, Bitmap image)
	{
		if (Current_Orb_Count < SpinBlocks.ORB_COUNT_MAX)
		{
			String name = "orb" + lifetime_orb_count;
			
			Orbs.put(name, new Orb(name, color, pos_x, pos_y, image, grid_x, grid_y));
			Current_Orb_Count++;
			lifetime_orb_count++;
		}
	}
	
	public void CreatePuzzleOrb(int grid_x, int grid_y, int color)
	{
		switch(color)
		{
		case SpinBlocks.ORB_RED:
			CreatePuzzleOrb(SpinBlocks.ORB_RED, grid_x, grid_y, (int) (SpinBlocks.CENTER_CORE_X + (grid_x * SpinBlocks.GRID_CELL_SIZE)), 
					(int) (SpinBlocks.CENTER_CORE_Y + (grid_y * SpinBlocks.GRID_CELL_SIZE)), SpinBlocks.BITMAP_ORB_RED);
			break;
		case SpinBlocks.ORB_GREEN:
			CreatePuzzleOrb(SpinBlocks.ORB_GREEN, grid_x, grid_y, (int) (SpinBlocks.CENTER_CORE_X + (grid_x * SpinBlocks.GRID_CELL_SIZE)), 
					(int) (SpinBlocks.CENTER_CORE_Y + (grid_y * SpinBlocks.GRID_CELL_SIZE)), SpinBlocks.BITMAP_ORB_GREEN);
			break;
		case SpinBlocks.ORB_BLUE:
			CreatePuzzleOrb(SpinBlocks.ORB_BLUE, grid_x, grid_y, (int) (SpinBlocks.CENTER_CORE_X + (grid_x * SpinBlocks.GRID_CELL_SIZE)), 
					(int) (SpinBlocks.CENTER_CORE_Y + (grid_y * SpinBlocks.GRID_CELL_SIZE)), SpinBlocks.BITMAP_ORB_BLUE);
			break;
		case SpinBlocks.ORB_ORANGE:
			CreatePuzzleOrb(SpinBlocks.ORB_ORANGE, grid_x, grid_y, (int) (SpinBlocks.CENTER_CORE_X + (grid_x * SpinBlocks.GRID_CELL_SIZE)), 
					(int) (SpinBlocks.CENTER_CORE_Y + (grid_y * SpinBlocks.GRID_CELL_SIZE)), SpinBlocks.BITMAP_ORB_ORANGE);
			break;
		case SpinBlocks.ORB_PURPLE:
			CreatePuzzleOrb(SpinBlocks.ORB_PURPLE, grid_x, grid_y, (int) (SpinBlocks.CENTER_CORE_X + (grid_x * SpinBlocks.GRID_CELL_SIZE)), 
					(int) (SpinBlocks.CENTER_CORE_Y + (grid_y * SpinBlocks.GRID_CELL_SIZE)), SpinBlocks.BITMAP_ORB_PURPLE);
			break;	
		case SpinBlocks.ORB_LIME:
			CreatePuzzleOrb(SpinBlocks.ORB_LIME, grid_x, grid_y, (int) (SpinBlocks.CENTER_CORE_X + (grid_x * SpinBlocks.GRID_CELL_SIZE)), 
					(int) (SpinBlocks.CENTER_CORE_Y + (grid_y * SpinBlocks.GRID_CELL_SIZE)), SpinBlocks.BITMAP_ORB_LIME);
			break;	
		case SpinBlocks.ORB_GRAY:
			CreatePuzzleOrb(SpinBlocks.ORB_GRAY, grid_x, grid_y, (int) (SpinBlocks.CENTER_CORE_X + (grid_x * SpinBlocks.GRID_CELL_SIZE)), 
					(int) (SpinBlocks.CENTER_CORE_Y + (grid_y * SpinBlocks.GRID_CELL_SIZE)), SpinBlocks.BITMAP_ORB_GRAY);
			break;	
		}
	}
	
	public void CreateRandomPuzzleOrb(int grid_x, int grid_y)
	{
		int color = rand.nextInt(SpinBlocks.Difficulty);
		
		CreatePuzzleOrb(grid_x, grid_y, color);
	}
	

	public void CreateRandomLauncherOrb(int direction)
	{
		int color = rand.nextInt(SpinBlocks.Difficulty);
		
		//Limit new orb generations if it's obvious the player is about to clear
		if (Orbs.size() < 10)
		{
			for (Orb o : Orbs.values())
			{
				if (o.Color != SpinBlocks.ORB_CORE && o.Attached)
				{
					color = o.Color;
					break;
				}
			}
			
			if (direction == SpinBlocks.DIRECTION_DOWN && Launcher_Top.Next_Orb_Color < SpinBlocks.ORB_POWERUP_MARKER)
		    {
				Launcher_Top.SetNextOrb(color);
		    }
			else if (direction == SpinBlocks.DIRECTION_UP && Launcher_Top.Next_Orb_Color < SpinBlocks.ORB_POWERUP_MARKER)
		    {
				Launcher_Bottom.SetNextOrb(color);
		    }
		}
		
		//10% chance of power up
	    if (rand.nextFloat() > 0.90)
	    {
	    	if (rand.nextBoolean())
	    	{
	    		color = SpinBlocks.ORB_BOMB;
	    	}
	    	else
	    	{
	    		color = SpinBlocks.ORB_WILDCARD;
	    	}
	    }

	    if (direction == SpinBlocks.DIRECTION_DOWN)
	    {
	    	CreateLauncherOrb(direction, Launcher_Top.Next_Orb_Color);
	    	Launcher_Top.SetNextOrb(color);
	    }
	    else if (direction == SpinBlocks.DIRECTION_UP)
	    {
	    	CreateLauncherOrb(direction, Launcher_Bottom.Next_Orb_Color);
	    	Launcher_Bottom.SetNextOrb(color);
	    }
	}
	
	public void CreateLauncherOrb(int direction, int color)
	{
		switch(color)
		{
		case SpinBlocks.ORB_RED:
			CreateLauncherOrb(direction, color, SpinBlocks.BITMAP_ORB_RED);
			break;
		case SpinBlocks.ORB_GREEN:
			CreateLauncherOrb(direction, color, SpinBlocks.BITMAP_ORB_GREEN);
			break;
		case SpinBlocks.ORB_BLUE:
			CreateLauncherOrb(direction, color, SpinBlocks.BITMAP_ORB_BLUE);
			break;
		case SpinBlocks.ORB_ORANGE:
			CreateLauncherOrb(direction, color, SpinBlocks.BITMAP_ORB_ORANGE);
			break;
		case SpinBlocks.ORB_PURPLE:
			CreateLauncherOrb(direction, color, SpinBlocks.BITMAP_ORB_PURPLE);
			break;
		case SpinBlocks.ORB_LIME:
			CreateLauncherOrb(direction, color, SpinBlocks.BITMAP_ORB_LIME);
			break;	
		case SpinBlocks.ORB_GRAY:
			CreateLauncherOrb(direction, color, SpinBlocks.BITMAP_ORB_GRAY);
			break;
			
		case SpinBlocks.ORB_BOMB:
			CreateLauncherOrb(direction, color, SpinBlocks.BITMAP_ORB_BOMB);
			break;
		case SpinBlocks.ORB_WILDCARD:
			CreateLauncherOrb(direction, color, SpinBlocks.BITMAP_ORB_WILDCARD);
			break;
		}
	}
	
	public void CreateLauncherOrb(int direction, int color, Bitmap image)
	{
		if (Current_Orb_Count < SpinBlocks.ORB_COUNT_MAX)
		{
			String name = "orb" + lifetime_orb_count;
			int x = 0, y = 0;
			switch(direction)
			{
			case SpinBlocks.DIRECTION_DOWN:
				Launcher_Top.LoadOrb(name, color, image);
				x = (int)Launcher_Top.Position_World_X;
				y = (int)Launcher_Top.Position_World_Y + (SpinBlocks.BITMAP_ORB_CORE.getHeight() / 2);
				break;
			case SpinBlocks.DIRECTION_UP:	
				Launcher_Bottom.LoadOrb(name, color, image);
				x = (int)Launcher_Bottom.Position_World_X;
				y = (int)Launcher_Bottom.Position_World_Y - (SpinBlocks.BITMAP_ORB_CORE.getHeight() / 2);
				break;
			default:
				break;
			}
			
			Orb_Waiting = new Orb(name, color, x, y, 0, image);
			Current_Orb_Count++;
			lifetime_orb_count++;
		}
	}
	
	public synchronized void LaunchOrb(String name, int direction, int launcher_grid_position)
	{
		SpinBlocks.SOUND_MANAGER.playSound(SpinBlocks.SOUND_LAUNCHER_FIRE);
		Orb launch_orb = Orbs.get(name);
		launch_orb.Position_Grid_X = launcher_grid_position;
		
		int dest_pos_y = 0;
		switch(direction)
		{
		case SpinBlocks.DIRECTION_DOWN:
			dest_pos_y = 10000;
			for(Orb o : Orbs.values())
			{
				if (o.Name != launch_orb.Name && o.Position_Grid_X == launch_orb.Position_Grid_X && o.Position_World_Y < dest_pos_y && (o.Attached || o.Color == SpinBlocks.ORB_CORE))
				{
					dest_pos_y = (int)o.Position_World_Y;
				}
			}
			dest_pos_y = dest_pos_y - SpinBlocks.GRID_CELL_SIZE;
			break;
		case SpinBlocks.DIRECTION_UP:
			dest_pos_y = -10000;
			for(Orb o : Orbs.values())
			{
				if (o.Name != launch_orb.Name && o.Position_Grid_X == launch_orb.Position_Grid_X && o.Position_World_Y > dest_pos_y && (o.Attached || o.Color == SpinBlocks.ORB_CORE))
				{
					dest_pos_y = (int)o.Position_World_Y;
				}
			}
			dest_pos_y = dest_pos_y + SpinBlocks.GRID_CELL_SIZE;
			break;
		}
		
		launch_orb.Launch((int)launch_orb.Position_World_X, dest_pos_y, direction);
	}
	
	public synchronized void NotifyOrbAttached(Orb orb, int direction)
	{
		orb.Position_World_X = orb.Position_Destination_X;
		orb.Position_World_Y = orb.Position_Destination_Y;
		orb.Launching = false;
		orb.Attached = true;
		orb.Snap_To_Grid();
		orb.ComputeDistanceAngle();
		
		SpinBlocks.SOUND_MANAGER.playSound(SpinBlocks.SOUND_BLOCK_ATTACH);
		ComputeNeighbors();
		if (orb.Color == SpinBlocks.ORB_BOMB)
	    {
	        for (Orb o : Orbs.values())
	        {
	            if (
	            (o.Position_Grid_X == orb.Position_Grid_X - 1 && o.Position_Grid_Y == orb.Position_Grid_Y - 1) ||
	            (o.Position_Grid_X == orb.Position_Grid_X     && o.Position_Grid_Y == orb.Position_Grid_Y - 1) ||
	            (o.Position_Grid_X == orb.Position_Grid_X + 1 && o.Position_Grid_Y == orb.Position_Grid_Y - 1) ||
	            (o.Position_Grid_X == orb.Position_Grid_X + 1 && o.Position_Grid_Y == orb.Position_Grid_Y    ) ||
	            (o.Position_Grid_X == orb.Position_Grid_X + 1 && o.Position_Grid_Y == orb.Position_Grid_Y + 1) ||
	            (o.Position_Grid_X == orb.Position_Grid_X     && o.Position_Grid_Y == orb.Position_Grid_Y + 1) ||
	            (o.Position_Grid_X == orb.Position_Grid_X - 1 && o.Position_Grid_Y == orb.Position_Grid_Y + 1) ||
	            (o.Position_Grid_X == orb.Position_Grid_X - 1 && o.Position_Grid_Y == orb.Position_Grid_Y    ) )
	            {
	            	if (o.Color != SpinBlocks.ORB_CORE)
	            	{
	            		o.Pop(0);
	            	}
	            }
	        }
	   
		    orb.Pop(0); 
		}
		else if (orb.Color == SpinBlocks.ORB_WILDCARD)
		{
			for (String s : orb.Neighbors)
			{
				if ((direction == SpinBlocks.DIRECTION_UP && Orbs.get(s).Position_Grid_Y == orb.Position_Grid_Y - 1) ||
						direction == SpinBlocks.DIRECTION_DOWN && Orbs.get(s).Position_Grid_Y == orb.Position_Grid_Y + 1)
				{
					if (Orbs.get(s).Color == SpinBlocks.ORB_CORE)
					{
						orb.Color = SpinBlocks.ORB_BLUE;
						orb.Image = SpinBlocks.BITMAP_ORB_BLUE;
						
						NotifyOrbAttached(orb, direction);
					}
					else 
					{
						orb.Color = Orbs.get(s).Color;
						orb.Image = Orbs.get(s).Image;
						
						NotifyOrbAttached(orb, direction);
					}
				}
			}
		}
		else
		{
			ComputePopOrbs(orb);			
		}
	   
		DoPopOrbs();
		DoPopFloaters();
		Matches.clear();
		ComputeGridBoundaries();
		Launcher_Top.Limit_To_Grid_Boundary();
		Launcher_Bottom.Limit_To_Grid_Boundary();
		
		int attached_count = 0;
		for(Orb o : Orbs.values())
		{
			if (o.Attached)
			{
				attached_count++;
			}
		}
		
		if (attached_count == 0)
		{
			SpinBlocks.Can_Launch = false;
			for(Orb o : Orbs.values())
			{
				if (o.Color != SpinBlocks.ORB_CORE)
				{
					o.Points = SpinBlocks.UI_MANAGER.Score_Keeper.FLOATER_SCORE;
					o.Pop(0);
				}
			}
		}
		else
		{
			SpinBlocks.Orb_In_Air = false;
			SpinBlocks.Can_Launch = true;
			SpinBlocks.Can_Rotate = true;
		}
		
		if (SpinBlocks.Gametype == SpinBlocks.GAMETYPE_PUZZLE)
		{
			SpinBlocks.UI_MANAGER.Score_Keeper.Current_Score++;
		}

		Compute_Target_Y(SpinBlocks.DIRECTION_UP);
		Compute_Target_Y(SpinBlocks.DIRECTION_DOWN);
	}
	
	public void NotifyOrbPopped(Orb orb)
	{
		Popped_Waiting.add(orb.Name);
	}
	
	public void Compute_Target_Y(int direction)
	{
		int dest_pos_y;
		if (direction == SpinBlocks.DIRECTION_DOWN)
		{
			dest_pos_y = 10000;
			for(Orb o : Orbs.values())
			{
				if (o.Position_Grid_X == Launcher_Top.Position_Grid_X && o.Position_World_Y < dest_pos_y && (o.Attached || o.Color == SpinBlocks.ORB_CORE))
				{
					dest_pos_y = (int)o.Position_World_Y;
				}
			}
			Launcher_Top.Target_Y_Pos = dest_pos_y;
		}
		else if (direction == SpinBlocks.DIRECTION_UP)
		{
			dest_pos_y = -10000;
			for(Orb o : Orbs.values())
			{
				if (o.Position_Grid_X == Launcher_Bottom.Position_Grid_X && o.Position_World_Y > dest_pos_y && (o.Attached || o.Color == SpinBlocks.ORB_CORE))
				{
					dest_pos_y = (int)o.Position_World_Y;
				}
			}
			Launcher_Bottom.Target_Y_Pos = dest_pos_y;
		}
	}
	
	private void ComputeNeighbors()
	{
		for(Orb a : Orbs.values())
		{
			for(Orb b : Orbs.values())
			{
				if (a != b && a.IsAdjacentTo(b) && !a.Neighbors.contains(b.Name) && (b.Attached || b.Color == SpinBlocks.ORB_CORE))
				{
					a.Neighbors.add(b.Name);
				}
			}
		}
	}
	
	private void ComputePopOrbs(Orb orb)
	{
		if(!Matches.contains(orb.Name))
		{
			Matches.add(orb.Name);
			
			for (String s : orb.Neighbors)
			{
				if (Orbs.containsKey(s) && Orbs.get(s).Color == orb.Color)
				{
					//Found a match, now search s's neighbors...
					ComputePopOrbs(Orbs.get(s));
				}
			}
		}
	}
	
	private void DoPopOrbs()
	{
		if (Matches.size() > 2)
		{
			float delay = 0.05f;
			Orbs.get(Matches.get(0)).Points = SpinBlocks.UI_MANAGER.Score_Keeper.GetComboScore(Matches.size());
			
			for (String s : Matches)
			{
				Orbs.get(s).Pop(delay);
				delay += 0.05f;
			}
		}
	}
	
	private void DoPopFloaters()
	{
		float delay = 0.5f;
		for (Orb o : Orbs.values())
		{
			if (!Matches.contains(o.Name) && !IsConnected(o) && o.Color != SpinBlocks.ORB_CORE)
			{
				if (o.Points == 0)
				{
					o.Points = SpinBlocks.UI_MANAGER.Score_Keeper.FLOATER_SCORE;
				}
				Orbs.get(o.Name).Pop(delay);			
			}
		} 
	}
	
	private boolean IsConnected(Orb orb)
	{
		boolean found = false;
		LinkedList<Orb> next_queue = new LinkedList<Orb>();
		
		Visited.add(orb.Name);
		next_queue.offer(orb);
		
		while(!found)
		{
			Orb node = null;
			try
			{
				node = next_queue.remove();
			}
			catch (NoSuchElementException nsee)
			{
				break;  //exit early if we're out of next elements to search...
			}
			
			if (node.Color == SpinBlocks.ORB_CORE)
			{
				found = true;
				break;
			}
			else
			{
				for (String neighbor : node.Neighbors)
				{
					if (!Visited.contains(neighbor))
					{
						Visited.add(neighbor);
						next_queue.offer(Orbs.get(neighbor));
					}
				}
			}
		}
		
		Visited.clear();

		return found;
	}
	
	
}
