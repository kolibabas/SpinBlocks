package com.memeworks.spinblocks;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class OrbLauncher extends MovableObject {
	
	/** Direction this launcher launches orbs */
	public int Direction = SpinBlocks.DIRECTION_NONE;

	public float Launch_Timer_Init = 2.0f;
	public float Launch_Timer = 2.0f;
	private Paint launch_timer_paint = new Paint();
	private Paint target_paint = new Paint();
	private Paint next_orb_paint = new Paint();
	
	public String Orb_Name = "";
	public boolean Orb_Loaded = false;
	public boolean Orb_Loading = false;
	
	public int Next_Orb_Color;
	private Bitmap next_orb_bitmap;
	
	private Bitmap arrow_left;
	private Bitmap arrow_right;
	
	public int Target_Y_Pos;
	
	public OrbLauncher(int direction, int x, int y, Bitmap d) {
		super(x, y, d);
		
		this.Direction = direction;	
		launch_timer_paint.setARGB(255, 160, 160, 160);
		
		target_paint.setARGB(75, 255, 255, 255);
		
		arrow_left = SpinBlocks.BITMAP_ARROW_LEFT;
		arrow_right = SpinBlocks.BITMAP_ARROW_RIGHT;
		
		Random rand = new Random();

		Target_Y_Pos = (int)SpinBlocks.CENTER_CORE_Y;
		
		if (this.Direction == SpinBlocks.DIRECTION_UP && SpinBlocks.Gametype == SpinBlocks.GAMETYPE_PUZZLE)
		{
			SetNextOrb(SpinBlocks.Current_Puzzle.GetNextOrbColor());
			Launch_Timer = 6;
			Launch_Timer_Init = 6;
		}
		else
		{
			SetNextOrb(rand.nextInt(SpinBlocks.Difficulty));
		}
		next_orb_paint.setTextAlign(Paint.Align.CENTER);
		
		this.Snap_To_Grid(true, false);
	}
	
	public void Frame_Started(float frame_time)
	{
		if (Orb_Loaded)
		{
			Launch_Timer -= frame_time;
			
			//Don't launch while rotating
			if (Launch_Timer <= 0)
			{
				Launch();
			}
		}
	}
	
	@Override
	public void Draw(Canvas c)
	{
		if (this.Direction == SpinBlocks.DIRECTION_DOWN)
		{
			//Draw left timer
			c.drawRect(Position_World_X - Image.getWidth() / 2,
					0, 
					Position_World_X - Image.getWidth() / 2 + 20, 
					40 * Launch_Timer / Launch_Timer_Init, 
					launch_timer_paint);
			
			//Draw Right timer
			c.drawRect(Position_World_X + Image.getWidth() / 2 - 20,
					0, 
					Position_World_X + Image.getWidth() / 2, 
					40 * Launch_Timer / Launch_Timer_Init, 
					launch_timer_paint);
		}
		else if (this.Direction == SpinBlocks.DIRECTION_UP)
		{
			//Draw left timer
			c.drawRect(Position_World_X - Image.getWidth() / 2, 
					SpinBlocks.SCREEN_HEIGHT - (40 * Launch_Timer / Launch_Timer_Init), 
					Position_World_X - Image.getWidth() / 2 + 20, 
					SpinBlocks.SCREEN_HEIGHT, 
					launch_timer_paint);
			
			//Draw Right timer
			c.drawRect(Position_World_X + Image.getWidth() / 2 - 20, 
					SpinBlocks.SCREEN_HEIGHT - (40 * Launch_Timer / Launch_Timer_Init), 
					Position_World_X + Image.getWidth() / 2, 
					SpinBlocks.SCREEN_HEIGHT, launch_timer_paint);
		}
		
		//Draw targeting guide
		if (this.Orb_Loaded)
		{
			if (this.Direction == SpinBlocks.DIRECTION_DOWN)
			{
				c.drawRect(this.Position_World_X - (SpinBlocks.BITMAP_ORB_CORE.getWidth() / 2), 
						0, 
						this.Position_World_X + (SpinBlocks.BITMAP_ORB_CORE.getWidth() / 2), 
						Target_Y_Pos,
						target_paint);
			}
			else if (this.Direction == SpinBlocks.DIRECTION_UP)
			{
				c.drawRect(this.Position_World_X - (SpinBlocks.BITMAP_ORB_CORE.getWidth() / 2), 
						Target_Y_Pos,
						this.Position_World_X + (SpinBlocks.BITMAP_ORB_CORE.getWidth() / 2), 
						SpinBlocks.SCREEN_HEIGHT, 
						target_paint);				
			}
		}
		
		//Draw next orb bitmap
		if (this.Direction == SpinBlocks.DIRECTION_DOWN)
		{
			c.drawBitmap(next_orb_bitmap, this.Position_World_X - next_orb_bitmap.getWidth() / 2, this.Position_World_Y - next_orb_bitmap.getHeight(), null);
			
			if (SpinBlocks.COLORBLIND_MODE && Next_Orb_Color < SpinBlocks.ORB_POWERUP_MARKER)
			{
				String text = "" + Next_Orb_Color;
				c.drawText(text, this.Position_World_X, (this.Position_World_Y - next_orb_bitmap.getHeight() / 2) + 5, next_orb_paint);
			}
		}
		else if (this.Direction == SpinBlocks.DIRECTION_UP)
		{
			c.drawBitmap(next_orb_bitmap, this.Position_World_X - next_orb_bitmap.getWidth() / 2, this.Position_World_Y, null);
			
			if (SpinBlocks.COLORBLIND_MODE && Next_Orb_Color < SpinBlocks.ORB_POWERUP_MARKER)
			{
				String text = "" + Next_Orb_Color;
				c.drawText(text, this.Position_World_X, (this.Position_World_Y + next_orb_bitmap.getHeight() / 2) + 5, next_orb_paint);
			}
		}
		
		//Draw self over timers
		c.drawBitmap(Image, Position_World_X - Image.getWidth() / 2, Position_World_Y - Image.getHeight() / 2, null);
		
		
		//Draw arrows
		if (this.Position_Grid_X > SpinBlocks.ORB_GRID.Grid_Bound_Left)
		{
			c.drawBitmap(arrow_left, (Position_World_X - Image.getWidth() / 2) - arrow_left.getWidth(), Position_World_Y - Image.getHeight() / 2, null);
		}
		
		if (this.Position_Grid_X < SpinBlocks.ORB_GRID.Grid_Bound_Right)
		{
			c.drawBitmap(arrow_right, Position_World_X + Image.getWidth() / 2, Position_World_Y - Image.getHeight() / 2, null);
		}
	}
	
	public void LoadOrb(String name, int color, Bitmap image)
	{
		Orb_Loaded = true;
		Orb_Name = name;
	}
	
	public void SetNextOrb(int color)
	{
		Next_Orb_Color = color;
		
		switch(color)
		{
		case SpinBlocks.ORB_RED:
			next_orb_bitmap = SpinBlocks.BITMAP_ORB_RED;
			break;
		case SpinBlocks.ORB_GREEN:
			next_orb_bitmap = SpinBlocks.BITMAP_ORB_GREEN;
			break;
		case SpinBlocks.ORB_BLUE:
			next_orb_bitmap = SpinBlocks.BITMAP_ORB_BLUE;
			break;
		case SpinBlocks.ORB_ORANGE:
			next_orb_bitmap = SpinBlocks.BITMAP_ORB_ORANGE;
			break;
		case SpinBlocks.ORB_PURPLE:
			next_orb_bitmap = SpinBlocks.BITMAP_ORB_PURPLE;
			break;	
		case SpinBlocks.ORB_LIME:
			next_orb_bitmap = SpinBlocks.BITMAP_ORB_LIME;
			break;	
		case SpinBlocks.ORB_GRAY:
			next_orb_bitmap = SpinBlocks.BITMAP_ORB_GRAY;
			break;	
			
		case SpinBlocks.ORB_BOMB:
			next_orb_bitmap = SpinBlocks.BITMAP_ORB_BOMB;
			break;
		case SpinBlocks.ORB_WILDCARD:
			next_orb_bitmap = SpinBlocks.BITMAP_ORB_WILDCARD;
			break;			
		}
		
		if (color == SpinBlocks.ORB_BLUE || color == SpinBlocks.ORB_LIME)
		{
			next_orb_paint.setARGB(255, 0, 0, 0);
		}
		else
		{
			next_orb_paint.setARGB(255, 255, 255, 255);
		}
	}
	
	public void Launch()
	{
		//Check if Orb is loaded, called on timeout or if player launches manually
		if (Orb_Loaded && !SpinBlocks.Orb_In_Air && SpinBlocks.ORB_GRID.Rotate_Direction == SpinBlocks.ROTATE_NONE)
		{
			Limit_To_Grid_Boundary();
			
			SpinBlocks.Orb_In_Air = true;
			SpinBlocks.Can_Rotate = false;
			Launch_Timer = Launch_Timer_Init;
			SpinBlocks.ORB_GRID.LaunchOrb(Orb_Name, Direction, SpinBlocks.World_To_Grid_X((int)this.Position_World_X));
			Orb_Loaded = false;
			Orb_Name = "";
		}
	}
	
	public void Move(MotionEvent evt)
	{
		if (SpinBlocks.ORB_GRID.Rotate_Direction == SpinBlocks.ROTATE_NONE)
		{
			int original_grid_x = this.Position_Grid_X;
			this.Position_World_X = evt.getX();
			this.Snap_To_Grid(true, false);
			
			if (this.Position_Grid_X != original_grid_x)
			{
				SpinBlocks.SOUND_MANAGER.playSound(SpinBlocks.SOUND_LAUNCHER_MOVE);
				
				if (this.Position_Grid_X < SpinBlocks.ORB_GRID.Grid_Bound_Left ||
						this.Position_Grid_X > SpinBlocks.ORB_GRID.Grid_Bound_Right)
				{
					Limit_To_Grid_Boundary();
				}
				
				if (Orb_Loaded)
				{
					SpinBlocks.ORB_GRID.Orbs.get(Orb_Name).Position_World_X = this.Position_World_X;
				}
				
				SpinBlocks.ORB_GRID.Compute_Target_Y(this.Direction);
			}
		}
	}
	
	public void Move(int direction)
	{
		if (SpinBlocks.ORB_GRID.Rotate_Direction == SpinBlocks.ROTATE_NONE)
		{
			if (direction == SpinBlocks.DIRECTION_LEFT && this.Position_Grid_X > SpinBlocks.ORB_GRID.Grid_Bound_Left)
			{
				SpinBlocks.SOUND_MANAGER.playSound(SpinBlocks.SOUND_LAUNCHER_MOVE);
				this.Position_Grid_X--;
				Snap_To_Grid_X(this.Position_Grid_X);
				
				if (Orb_Loaded)
				{
					SpinBlocks.ORB_GRID.Orbs.get(Orb_Name).Position_World_X = this.Position_World_X;
				}
			}
			else if (direction == SpinBlocks.DIRECTION_RIGHT && this.Position_Grid_X < SpinBlocks.ORB_GRID.Grid_Bound_Right)
			{
				SpinBlocks.SOUND_MANAGER.playSound(SpinBlocks.SOUND_LAUNCHER_MOVE);
				this.Position_Grid_X++;
				Snap_To_Grid_X(this.Position_Grid_X);
				
				if (Orb_Loaded)
				{
					SpinBlocks.ORB_GRID.Orbs.get(Orb_Name).Position_World_X = this.Position_World_X;
				}
			}
			
			SpinBlocks.ORB_GRID.Compute_Target_Y(this.Direction);
		}
	}
	
	public void Limit_To_Grid_Boundary()
	{
		if (this.Position_Grid_X < SpinBlocks.ORB_GRID.Grid_Bound_Left)
		{
			this.Position_Grid_X = SpinBlocks.ORB_GRID.Grid_Bound_Left;
			Snap_To_Grid_X(this.Position_Grid_X);
		}
		else if (this.Position_Grid_X > SpinBlocks.ORB_GRID.Grid_Bound_Right)
		{
			this.Position_Grid_X = SpinBlocks.ORB_GRID.Grid_Bound_Right;
			Snap_To_Grid_X(this.Position_Grid_X);
		}
		
		if (Orb_Loaded)
		{
			SpinBlocks.ORB_GRID.Orbs.get(Orb_Name).Position_World_X = this.Position_World_X;
		}
	}

}
