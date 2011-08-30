package com.memeworks.spinblocks;

import java.util.ArrayList;
import java.util.Arrays;


public class Puzzle {

	public ArrayList<PuzzleOrb> Initial_Orbs; 
	public int[] Launcher_Orbs; 
	public int ID;
	public String Name;
	
	private int current_launcher_index = -1;
	
	public Puzzle(int id, String name, PuzzleOrb[] initial_orbs, int[] launcher_orbs) {
		ID = id;
		Name = name;
		Initial_Orbs = new ArrayList<PuzzleOrb>(Arrays.asList(initial_orbs));
		Launcher_Orbs = launcher_orbs;
	}
	
	public int GetNextOrbColor()
	{
		current_launcher_index++;
		
		if (current_launcher_index >= Launcher_Orbs.length)
		{
			current_launcher_index = 0;
		}
		
		return Launcher_Orbs[current_launcher_index];
	}
	
	public void Reset()
	{
		current_launcher_index = -1;
	}
	
	@Override
	public String toString()
	{
		return "Puzzle " + ID + " - " + Name;
	}
}
