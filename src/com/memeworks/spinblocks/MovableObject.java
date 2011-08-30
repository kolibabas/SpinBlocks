package com.memeworks.spinblocks;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Base class for all movables in the game, should be extended for specific purposes
 * @author glitch
 *
 */
public class MovableObject {
	
	//Public Properties
	public int State = SpinBlocks.MOVABLE_STATE_ALIVE;;
	
	//Track Position in the Game World
	public float Position_World_X;
	public float Position_World_Y;
	public int Position_Grid_X = 0;
	public int Position_Grid_Y = 0;
	
	public Rect bounds;
	public boolean Visible = true;
	public Bitmap Image;

	
	public MovableObject(int x, int y, Bitmap d)
	{
		Image = d;

		Position_World_X = x;
		Position_World_Y = y;
		
		bounds = new Rect();
	}
	
	public void Draw(Canvas c) {
		if (Visible)
		{
			if (State == SpinBlocks.MOVABLE_STATE_ALIVE || State == SpinBlocks.MOVABLE_STATE_DEATH_SCHEDULED) {
				c.drawBitmap(Image, Position_World_X - Image.getWidth() / 2, Position_World_Y - Image.getHeight() / 2, null);
			}
			else if (State == SpinBlocks.MOVABLE_STATE_DYING) {
	
			}
		}
	}
	
	public void Snap_To_Grid()
	{
		Snap_To_Grid(true, true);
	}
	
	public void Snap_To_Grid_X(int grid_x)
	{
		this.Position_World_X = SpinBlocks.CENTER_CORE_X + (grid_x * SpinBlocks.GRID_CELL_SIZE);
	}
	
	public void Snap_To_Grid_Y(int grid_y)
	{
		this.Position_World_Y = SpinBlocks.CENTER_CORE_Y + (grid_y * SpinBlocks.GRID_CELL_SIZE);
	}
	
	public void Snap_To_Grid(boolean x, boolean y)
	{		
		if(x)
		{
			int cell_x = (int)this.Position_World_X % SpinBlocks.GRID_CELL_SIZE;
			if (cell_x < SpinBlocks.GRID_CELL_SIZE / 2)
			{
				this.Position_World_X = this.Position_World_X - cell_x;
			}
			else 
			{
				this.Position_World_X = this.Position_World_X + (SpinBlocks.GRID_CELL_SIZE - cell_x);
			}
			
			this.Position_Grid_X = SpinBlocks.World_To_Grid_X((int)this.Position_World_X);
		}

		if (y)
		{
			int cell_y = (int)this.Position_World_Y % SpinBlocks.GRID_CELL_SIZE;
			if (cell_y < SpinBlocks.GRID_CELL_SIZE / 2) 
			{
				this.Position_World_Y = this.Position_World_Y - cell_y;
			}
			else
			{
				this.Position_World_Y = this.Position_World_Y + (SpinBlocks.GRID_CELL_SIZE - cell_y);
			}
			
			this.Position_Grid_Y = SpinBlocks.World_To_Grid_Y((int)this.Position_World_Y);
		}
	}
}
