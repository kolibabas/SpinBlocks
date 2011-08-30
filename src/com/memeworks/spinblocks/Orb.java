package com.memeworks.spinblocks;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.FloatMath;

public class Orb extends MovableObject {

	public int Color;
	public float Current_Angle = 0;
	public float Distance_From_Core = 0;
	public String Name = "";
	
	public boolean Launching = false;
	public boolean Attached = false;
	public int Position_Destination_X = 0;
	public int Position_Destination_Y = 0;
	public int Direction = SpinBlocks.DIRECTION_NONE;
	public float Move_Speed = 200.0f;
	
	public int Points = 0;
	
	public ArrayList<String> Neighbors = new ArrayList<String>();
	
	private float pop_timer = 0.0f;
	private float death_timer = 1.0f;
	private Paint text_paint = new Paint();
	private float attach_timer = 2;

	/** Launcher Orb Constructor  */	
	public Orb(String name, int color, int initial_x, int initial_y, int radius, Bitmap d) {
		super(initial_x, initial_y, d);
		
		this.Color = color;
		this.Name = name;
		this.Distance_From_Core = radius;
		
		if (color == SpinBlocks.ORB_BLUE || color == SpinBlocks.ORB_LIME)
		{
			text_paint.setARGB(255, 0, 0, 0);
		}
		else
		{
			text_paint.setARGB(255, 255, 255, 255);
		}

		text_paint.setTextAlign(Paint.Align.CENTER);
		
		this.Snap_To_Grid(true, false);
	}
	
	/** Puzzle Orb Constructor  */	
	public Orb(String name, int color, int initial_x, int initial_y, Bitmap d, int grid_x, int grid_y) {
		super(initial_x, initial_y, d);
		
		this.Color = color;
		this.Name = name;
		
		this.Position_Grid_X = grid_x;
		this.Position_Grid_Y = grid_y;
		
		this.Snap_To_Grid();
		this.ComputeDistanceAngle();
		
		if (color != SpinBlocks.ORB_CORE)
		{
			this.Attached = true;
		}
		else
		{
			SpinBlocks.CENTER_CORE_X = this.Position_World_X;
			SpinBlocks.CENTER_CORE_Y = this.Position_World_Y;
		}
		
		if (color == SpinBlocks.ORB_BLUE || color == SpinBlocks.ORB_LIME)
		{
			text_paint.setARGB(255, 0, 0, 0);
		}
		else
		{
			text_paint.setARGB(255, 255, 255, 255);
		}
		
		text_paint.setTextAlign(Paint.Align.CENTER);
	}
	
	@Override
	public void Draw(Canvas c)
	{
		super.Draw(c);
		
		if (SpinBlocks.COLORBLIND_MODE && (this.State == SpinBlocks.MOVABLE_STATE_ALIVE || State == SpinBlocks.MOVABLE_STATE_DEATH_SCHEDULED)
				&& this.Color < SpinBlocks.ORB_POWERUP_MARKER)
		{
			String color_string = "" + this.Color;
			c.drawText(color_string, (int)this.Position_World_X, (int)this.Position_World_Y + 2, text_paint);
		}
	}
	
	public void Launch(int dest_pos_x, int dest_pos_y, int direction)
	{
		Launching = true;
		Position_Destination_X = dest_pos_x;
		Position_Destination_Y = dest_pos_y;
		Direction = direction;
	}
	
	public void Frame_Started(float frame_time)
	{
		if (this.Launching)
		{
			Move_Toward_Destination(frame_time);
			attach_timer -= frame_time;
			
			if (attach_timer <= 0)
			{
				Attach();
			}
		}
		else if (this.State == SpinBlocks.MOVABLE_STATE_DEATH_SCHEDULED && pop_timer > 0)
		{
			pop_timer -= frame_time;			
		}
		else if (this.State == SpinBlocks.MOVABLE_STATE_DEATH_SCHEDULED && pop_timer <= 0)
		{
			this.State = SpinBlocks.MOVABLE_STATE_DYING;
			this.Visible = false;
			if (this.Points > 0)
			{
				SpinBlocks.UI_MANAGER.Score_Keeper.NotifyPointsOrbPopped((int)Position_World_X, (int)Position_World_Y, Points);
			}
		}
		else if (this.State == SpinBlocks.MOVABLE_STATE_DYING && death_timer > 0)
		{
			DoDeathAnimation(frame_time);
		}
	}
	
	public void Move_Toward_Destination(float frame_time)
	{
		float offset = this.Move_Speed * frame_time;
		
		//If arrived
		if ((this.Direction == SpinBlocks.DIRECTION_UP && Position_World_Y < Position_Destination_Y) ||
				(this.Direction == SpinBlocks.DIRECTION_DOWN && Position_World_Y > Position_Destination_Y))
		{
			Attach();
		}
		else //Move
		{
			switch(Direction)
			{
			case SpinBlocks.DIRECTION_UP:
				Position_World_Y -= offset;
				break;
				
			case SpinBlocks.DIRECTION_DOWN:
				Position_World_Y += offset;
				break;

			case SpinBlocks.DIRECTION_NONE:
			default:
				break;
			}
		}
	}
	
	public void Pop(float delay)
	{
		if (this.Color == SpinBlocks.ORB_BOMB)
		{
			SpinBlocks.SOUND_MANAGER.playSound(SpinBlocks.SOUND_BOMB_EXPLODE);
		}
		{
			SpinBlocks.SOUND_MANAGER.playSound(SpinBlocks.SOUND_BLOCK_POP);
		}
		this.pop_timer = delay;
		this.State = SpinBlocks.MOVABLE_STATE_DEATH_SCHEDULED;
		this.Attached = false;
		this.Neighbors.clear();
	}

	public void DoDeathAnimation(float frame_time) {
		this.death_timer -= frame_time;
		
		if (this.death_timer <= 0) {
			this.State = SpinBlocks.MOVABLE_STATE_DEAD;
			SpinBlocks.ORB_GRID.NotifyOrbPopped(this);
		}
		else {
			
		}
	}

	private void Attach()
	{
		SpinBlocks.ORB_GRID.NotifyOrbAttached(this, this.Direction);
		Direction = SpinBlocks.DIRECTION_NONE;
	}
	
	public void ComputeDistanceAngle()
	{
		float x = Position_World_X - SpinBlocks.CENTER_CORE_X;
		float y = Position_World_Y - SpinBlocks.CENTER_CORE_Y;
		
		Distance_From_Core = FloatMath.sqrt(x * x + y * y);
		
		Current_Angle = (float)Math.atan2(y,x);
	}
	
	public boolean IsAdjacentTo(Orb orb)
	{
		return (this.Position_Grid_X == orb.Position_Grid_X - 1 && this.Position_Grid_Y == orb.Position_Grid_Y) ||
				(this.Position_Grid_X == orb.Position_Grid_X + 1 && this.Position_Grid_Y == orb.Position_Grid_Y) ||
				(this.Position_Grid_Y == orb.Position_Grid_Y - 1 && this.Position_Grid_X == orb.Position_Grid_X) ||
				(this.Position_Grid_Y == orb.Position_Grid_Y + 1 && this.Position_Grid_X == orb.Position_Grid_X);
	}

}
