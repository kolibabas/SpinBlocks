package com.memeworks.spinblocks;

public class PuzzleList {

	public static final Puzzle[] Puzzles = new Puzzle[] {
new Puzzle(1, "Easy Does It", new PuzzleOrb[] {
																					  new PuzzleOrb(0,-2, SpinBlocks.ORB_BLUE), 
																					  new PuzzleOrb(0,-1, SpinBlocks.ORB_BLUE),
new PuzzleOrb(-2, 0, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1, 0, SpinBlocks.ORB_ORANGE), 											new PuzzleOrb(1, 0, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2, 0, SpinBlocks.ORB_ORANGE),
																					  new PuzzleOrb(0, 1, SpinBlocks.ORB_BLUE),
																					  new PuzzleOrb(0, 2, SpinBlocks.ORB_BLUE),
}, new int[] { SpinBlocks.ORB_ORANGE, SpinBlocks.ORB_BLUE }),

new Puzzle(2, "Path To The Core", new PuzzleOrb[] {
new PuzzleOrb(-2,-2, SpinBlocks.ORB_RED), new PuzzleOrb(-1,-2, SpinBlocks.ORB_BLUE),   new PuzzleOrb(0,-2, SpinBlocks.ORB_BLUE),   new PuzzleOrb(1,-2, SpinBlocks.ORB_BLUE),   new PuzzleOrb(2,-2, SpinBlocks.ORB_RED), 
new PuzzleOrb(-2,-1, SpinBlocks.ORB_RED), new PuzzleOrb(-1,-1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(0,-1, SpinBlocks.ORB_BLUE), new PuzzleOrb(1,-1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2,-1, SpinBlocks.ORB_RED),
new PuzzleOrb(-2, 0, SpinBlocks.ORB_RED), new PuzzleOrb(-1, 0, SpinBlocks.ORB_ORANGE), 											new PuzzleOrb(1, 0, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2, 0, SpinBlocks.ORB_RED),
new PuzzleOrb(-2, 1, SpinBlocks.ORB_RED), new PuzzleOrb(-1, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(0, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(1, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2, 1, SpinBlocks.ORB_RED),
new PuzzleOrb(-2, 2, SpinBlocks.ORB_RED), new PuzzleOrb(-1, 2, SpinBlocks.ORB_RED), new PuzzleOrb(0, 2, SpinBlocks.ORB_RED), new PuzzleOrb(1, 2, SpinBlocks.ORB_RED), new PuzzleOrb(2, 2, SpinBlocks.ORB_RED),
}, new int[] { SpinBlocks.ORB_BLUE, SpinBlocks.ORB_ORANGE, SpinBlocks.ORB_RED }),	

new Puzzle(3, "The Split", new PuzzleOrb[] {
new PuzzleOrb(-2,-2, SpinBlocks.ORB_GREEN),  new PuzzleOrb(-1,-2, SpinBlocks.ORB_GREEN),											new PuzzleOrb(1,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2,-2, SpinBlocks.ORB_ORANGE), 
new PuzzleOrb(-2,-1, SpinBlocks.ORB_PURPLE), new PuzzleOrb(-1,-1, SpinBlocks.ORB_PURPLE), 											new PuzzleOrb(1,-1, SpinBlocks.ORB_PURPLE), new PuzzleOrb(2,-1, SpinBlocks.ORB_PURPLE),
new PuzzleOrb(-2, 0, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1, 0, SpinBlocks.ORB_ORANGE), 											new PuzzleOrb(1, 0, SpinBlocks.ORB_GREEN),  new PuzzleOrb(2, 0, SpinBlocks.ORB_GREEN),
new PuzzleOrb(-2, 1, SpinBlocks.ORB_PURPLE), new PuzzleOrb(-1, 1, SpinBlocks.ORB_PURPLE), 											new PuzzleOrb(1, 1, SpinBlocks.ORB_PURPLE), new PuzzleOrb(2, 1, SpinBlocks.ORB_PURPLE),
new PuzzleOrb(-2, 2, SpinBlocks.ORB_GREEN),  new PuzzleOrb(-1, 2, SpinBlocks.ORB_GREEN),											new PuzzleOrb(1, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2, 2, SpinBlocks.ORB_ORANGE),
}, new int[] { SpinBlocks.ORB_GREEN, SpinBlocks.ORB_ORANGE, SpinBlocks.ORB_PURPLE }),	

new Puzzle(4, "Connections", new PuzzleOrb[] {
new PuzzleOrb(-3,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-2,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1,-2, SpinBlocks.ORB_ORANGE), 										   new PuzzleOrb(1,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(3,-2, SpinBlocks.ORB_ORANGE), 
new PuzzleOrb(-3,-1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-2,-1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1,-1, SpinBlocks.ORB_ORANGE), 										   new PuzzleOrb(1,-1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2,-1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(3,-1, SpinBlocks.ORB_ORANGE),
										   new PuzzleOrb(-2, 0, SpinBlocks.ORB_GREEN),																					   											 new PuzzleOrb(2, 0, SpinBlocks.ORB_GREEN),
new PuzzleOrb(-3, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-2, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(0, 1, SpinBlocks.ORB_GREEN),  new PuzzleOrb(1, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(3, 1, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-3, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-2, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1, 2, SpinBlocks.ORB_ORANGE), 										   new PuzzleOrb(1, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(3, 2, SpinBlocks.ORB_ORANGE),
}, new int[] { SpinBlocks.ORB_ORANGE, SpinBlocks.ORB_ORANGE, SpinBlocks.ORB_GREEN }),	

new Puzzle(5, "Spiral", new PuzzleOrb[] {
new PuzzleOrb(-3,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-2,-2, SpinBlocks.ORB_GREEN), new PuzzleOrb(-1,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(0,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(1,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(3,-2, SpinBlocks.ORB_ORANGE), 
new PuzzleOrb(-3,-1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-2,-1, SpinBlocks.ORB_GREEN), new PuzzleOrb(-1,-1, SpinBlocks.ORB_BLUE), new PuzzleOrb(0,-1, SpinBlocks.ORB_BLUE), new PuzzleOrb(1,-1, SpinBlocks.ORB_BLUE), new PuzzleOrb(2,-1, SpinBlocks.ORB_BLUE), new PuzzleOrb(3,-1, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-3, 0, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-2, 0, SpinBlocks.ORB_GREEN), new PuzzleOrb(-1, 0, SpinBlocks.ORB_BLUE), 										   new PuzzleOrb(1, 0, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2, 0, SpinBlocks.ORB_BLUE), new PuzzleOrb(3, 0, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-3, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-2, 1, SpinBlocks.ORB_GREEN), new PuzzleOrb(-1, 1, SpinBlocks.ORB_BLUE), new PuzzleOrb(0, 1, SpinBlocks.ORB_BLUE), new PuzzleOrb(1, 1, SpinBlocks.ORB_GREEN), new PuzzleOrb(2, 1, SpinBlocks.ORB_BLUE), new PuzzleOrb(3, 1, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-3, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-2, 2, SpinBlocks.ORB_GREEN), new PuzzleOrb(-1, 2, SpinBlocks.ORB_GREEN), new PuzzleOrb(0, 2, SpinBlocks.ORB_GREEN), new PuzzleOrb(1, 2, SpinBlocks.ORB_GREEN), new PuzzleOrb(2, 2, SpinBlocks.ORB_BLUE), new PuzzleOrb(3, 2, SpinBlocks.ORB_ORANGE),
}, new int[] { SpinBlocks.ORB_ORANGE, SpinBlocks.ORB_BLUE, SpinBlocks.ORB_GREEN}),	

new Puzzle(6, "Dig Deep", new PuzzleOrb[] {
new PuzzleOrb(-3,-2, SpinBlocks.ORB_GREEN), new PuzzleOrb(-2,-2, SpinBlocks.ORB_RED),  new PuzzleOrb(-1,-2, SpinBlocks.ORB_RED), new PuzzleOrb(0,-2, SpinBlocks.ORB_GREEN),  new PuzzleOrb(1,-2, SpinBlocks.ORB_RED), new PuzzleOrb(2,-2, SpinBlocks.ORB_RED), new PuzzleOrb(3,-2, SpinBlocks.ORB_ORANGE), 
new PuzzleOrb(-3,-1, SpinBlocks.ORB_GREEN), new PuzzleOrb(-2,-1, SpinBlocks.ORB_BLUE), new PuzzleOrb(-1,-1, SpinBlocks.ORB_BLUE), 										 new PuzzleOrb(1,-1, SpinBlocks.ORB_GREEN), new PuzzleOrb(2,-1, SpinBlocks.ORB_GREEN), new PuzzleOrb(3,-1, SpinBlocks.ORB_ORANGE),
										  										   new PuzzleOrb(-1, 0, SpinBlocks.ORB_GREEN), 										 new PuzzleOrb(1, 0, SpinBlocks.ORB_BLUE), new PuzzleOrb(2, 0, SpinBlocks.ORB_BLUE), new PuzzleOrb(3, 0, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-3, 1, SpinBlocks.ORB_GREEN), new PuzzleOrb(-2, 1, SpinBlocks.ORB_BLUE), new PuzzleOrb(-1, 1, SpinBlocks.ORB_BLUE), 	 									 new PuzzleOrb(1, 1, SpinBlocks.ORB_GREEN), new PuzzleOrb(2, 1, SpinBlocks.ORB_GREEN), new PuzzleOrb(3, 1, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-3, 2, SpinBlocks.ORB_GREEN), new PuzzleOrb(-2, 2, SpinBlocks.ORB_RED),  new PuzzleOrb(-1, 2, SpinBlocks.ORB_RED), new PuzzleOrb(0, 2, SpinBlocks.ORB_GREEN),  new PuzzleOrb(1, 2, SpinBlocks.ORB_RED), new PuzzleOrb(2, 2, SpinBlocks.ORB_RED), new PuzzleOrb(3, 2, SpinBlocks.ORB_ORANGE),
}, new int[] { SpinBlocks.ORB_GREEN, SpinBlocks.ORB_BLUE, SpinBlocks.ORB_GREEN, SpinBlocks.ORB_ORANGE, SpinBlocks.ORB_BLUE, }),	

new Puzzle(7, "Checkerboard", new PuzzleOrb[] {
new PuzzleOrb(-2,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1,-2, SpinBlocks.ORB_PURPLE), new PuzzleOrb(0,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(1,-2, SpinBlocks.ORB_PURPLE), new PuzzleOrb(2,-2, SpinBlocks.ORB_ORANGE), 
new PuzzleOrb(-2,-1, SpinBlocks.ORB_PURPLE), new PuzzleOrb(-1,-1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(0,-1, SpinBlocks.ORB_PURPLE), new PuzzleOrb(1,-1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2,-1, SpinBlocks.ORB_PURPLE),
new PuzzleOrb(-2, 0, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1, 0, SpinBlocks.ORB_PURPLE), 											new PuzzleOrb(1, 0, SpinBlocks.ORB_PURPLE), new PuzzleOrb(2, 0, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-2, 1, SpinBlocks.ORB_PURPLE), new PuzzleOrb(-1, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(0, 1, SpinBlocks.ORB_PURPLE), new PuzzleOrb(1, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2, 1, SpinBlocks.ORB_PURPLE),
new PuzzleOrb(-2, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1, 2, SpinBlocks.ORB_PURPLE), new PuzzleOrb(0, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(1, 2, SpinBlocks.ORB_PURPLE), new PuzzleOrb(2, 2, SpinBlocks.ORB_ORANGE),
}, new int[] { SpinBlocks.ORB_ORANGE, SpinBlocks.ORB_PURPLE }),	

new Puzzle(8, "Closer To The Sun", new PuzzleOrb[] {
new PuzzleOrb(-2,-2, SpinBlocks.ORB_ORANGE), 											  new PuzzleOrb(0,-2, SpinBlocks.ORB_ORANGE),											  new PuzzleOrb(2,-2, SpinBlocks.ORB_ORANGE), 
										   new PuzzleOrb(-1,-1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(0,-1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(1,-1, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-2, 0, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1, 0, SpinBlocks.ORB_ORANGE), 											new PuzzleOrb(1, 0, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2, 0, SpinBlocks.ORB_ORANGE),
										   new PuzzleOrb(-1, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(0, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(1, 1, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-2, 2, SpinBlocks.ORB_ORANGE), 										      new PuzzleOrb(0, 2, SpinBlocks.ORB_ORANGE), 										  new PuzzleOrb(2, 2, SpinBlocks.ORB_ORANGE),
}, new int[] { SpinBlocks.ORB_BOMB }),

new Puzzle(9, "Diagonals", new PuzzleOrb[] {
new PuzzleOrb(-2,-2, SpinBlocks.ORB_GREEN), new PuzzleOrb(-1,-2, SpinBlocks.ORB_BLUE), new PuzzleOrb(0,-2, SpinBlocks.ORB_PURPLE), new PuzzleOrb(1,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2,-2, SpinBlocks.ORB_RED), 
new PuzzleOrb(-2,-1, SpinBlocks.ORB_BLUE), new PuzzleOrb(-1,-1, SpinBlocks.ORB_GREEN), new PuzzleOrb(0,-1, SpinBlocks.ORB_BLUE), new PuzzleOrb(1,-1, SpinBlocks.ORB_PURPLE), new PuzzleOrb(2,-1, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-2, 0, SpinBlocks.ORB_PURPLE), new PuzzleOrb(-1, 0, SpinBlocks.ORB_BLUE), 											new PuzzleOrb(1, 0, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2, 0, SpinBlocks.ORB_PURPLE),
new PuzzleOrb(-2, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1, 1, SpinBlocks.ORB_PURPLE), new PuzzleOrb(0, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(1, 1, SpinBlocks.ORB_RED), new PuzzleOrb(2, 1, SpinBlocks.ORB_BLUE),
new PuzzleOrb(-2, 2, SpinBlocks.ORB_RED), new PuzzleOrb(-1, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(0, 2, SpinBlocks.ORB_PURPLE), new PuzzleOrb(1, 2, SpinBlocks.ORB_BLUE), new PuzzleOrb(2, 2, SpinBlocks.ORB_GREEN),
}, new int[] { SpinBlocks.ORB_GREEN, SpinBlocks.ORB_BLUE, SpinBlocks.ORB_PURPLE, SpinBlocks.ORB_ORANGE, SpinBlocks.ORB_RED, }),	

new Puzzle(10, "Quadrants", new PuzzleOrb[] {
new PuzzleOrb(-2,-2, SpinBlocks.ORB_BLUE), new PuzzleOrb(-1,-2, SpinBlocks.ORB_PURPLE), new PuzzleOrb(0,-2, SpinBlocks.ORB_BLUE), new PuzzleOrb(1,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2,-2, SpinBlocks.ORB_PURPLE), 
new PuzzleOrb(-2,-1, SpinBlocks.ORB_PURPLE), new PuzzleOrb(-1,-1, SpinBlocks.ORB_PURPLE), new PuzzleOrb(0,-1, SpinBlocks.ORB_RED), new PuzzleOrb(1,-1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2,-1, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-2, 0, SpinBlocks.ORB_GREEN), new PuzzleOrb(-1, 0, SpinBlocks.ORB_RED), 											new PuzzleOrb(1, 0, SpinBlocks.ORB_RED), new PuzzleOrb(2, 0, SpinBlocks.ORB_PURPLE),
new PuzzleOrb(-2, 1, SpinBlocks.ORB_BLUE), new PuzzleOrb(-1, 1, SpinBlocks.ORB_BLUE), new PuzzleOrb(0, 1, SpinBlocks.ORB_RED), new PuzzleOrb(1, 1, SpinBlocks.ORB_GREEN), new PuzzleOrb(2, 1, SpinBlocks.ORB_GREEN),
new PuzzleOrb(-2, 2, SpinBlocks.ORB_GREEN), new PuzzleOrb(-1, 2, SpinBlocks.ORB_BLUE), new PuzzleOrb(0, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(1, 2, SpinBlocks.ORB_GREEN), new PuzzleOrb(2, 2, SpinBlocks.ORB_ORANGE),
}, new int[] { SpinBlocks.ORB_GREEN, SpinBlocks.ORB_BLUE, SpinBlocks.ORB_PURPLE, SpinBlocks.ORB_ORANGE, SpinBlocks.ORB_RED, }),

new Puzzle(11, "Symmetry", new PuzzleOrb[] {
new PuzzleOrb(-3,-3, SpinBlocks.ORB_RED), new PuzzleOrb(-2,-3, SpinBlocks.ORB_RED), new PuzzleOrb(-1,-3, SpinBlocks.ORB_RED), new PuzzleOrb(0,-3, SpinBlocks.ORB_PURPLE), new PuzzleOrb(1,-3, SpinBlocks.ORB_RED), new PuzzleOrb(2,-3, SpinBlocks.ORB_RED), new PuzzleOrb(3,-3, SpinBlocks.ORB_RED),
new PuzzleOrb(-3,-2, SpinBlocks.ORB_RED), new PuzzleOrb(-2,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(0,-2, SpinBlocks.ORB_BLUE), new PuzzleOrb(1,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(3,-2, SpinBlocks.ORB_RED), 
new PuzzleOrb(-3,-1, SpinBlocks.ORB_RED), new PuzzleOrb(-2,-1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1,-1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(0,-1, SpinBlocks.ORB_GREEN), new PuzzleOrb(1,-1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2,-1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(3,-1, SpinBlocks.ORB_RED),
new PuzzleOrb(-3, 0, SpinBlocks.ORB_PURPLE), new PuzzleOrb(-2, 0, SpinBlocks.ORB_BLUE), new PuzzleOrb(-1, 0, SpinBlocks.ORB_GREEN), 										   new PuzzleOrb(1, 0, SpinBlocks.ORB_GREEN), new PuzzleOrb(2, 0, SpinBlocks.ORB_BLUE), new PuzzleOrb(3, 0, SpinBlocks.ORB_PURPLE),
new PuzzleOrb(-3, 1, SpinBlocks.ORB_RED), new PuzzleOrb(-2, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(0, 1, SpinBlocks.ORB_GREEN), new PuzzleOrb(1, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(3, 1, SpinBlocks.ORB_RED),
new PuzzleOrb(-3, 2, SpinBlocks.ORB_RED), new PuzzleOrb(-2, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(0, 2, SpinBlocks.ORB_BLUE), new PuzzleOrb(1, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(3, 2, SpinBlocks.ORB_RED),
new PuzzleOrb(-3, 3, SpinBlocks.ORB_RED), new PuzzleOrb(-2, 3, SpinBlocks.ORB_RED), new PuzzleOrb(-1, 3, SpinBlocks.ORB_RED), new PuzzleOrb(0, 3, SpinBlocks.ORB_PURPLE), new PuzzleOrb(1, 3, SpinBlocks.ORB_RED), new PuzzleOrb(2, 3, SpinBlocks.ORB_RED), new PuzzleOrb(3, 3, SpinBlocks.ORB_RED),
}, new int[] { SpinBlocks.ORB_RED, SpinBlocks.ORB_PURPLE, SpinBlocks.ORB_PURPLE, SpinBlocks.ORB_BLUE, SpinBlocks.ORB_BLUE, SpinBlocks.ORB_GREEN, SpinBlocks.ORB_GREEN, }),

new Puzzle(12, "Short Circuit", new PuzzleOrb[] {
new PuzzleOrb(-3,-3, SpinBlocks.ORB_GREEN), new PuzzleOrb(-2,-3, SpinBlocks.ORB_GREEN), new PuzzleOrb(-1,-3, SpinBlocks.ORB_ORANGE), new PuzzleOrb(0,-3, SpinBlocks.ORB_ORANGE), new PuzzleOrb(1,-3, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2,-3, SpinBlocks.ORB_GREEN), new PuzzleOrb(3,-3, SpinBlocks.ORB_GREEN),
new PuzzleOrb(-3,-2, SpinBlocks.ORB_PURPLE), new PuzzleOrb(-2,-2, SpinBlocks.ORB_GREEN), new PuzzleOrb(-1,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(0,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(1,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2,-2, SpinBlocks.ORB_GREEN), new PuzzleOrb(3,-2, SpinBlocks.ORB_ORANGE), 
new PuzzleOrb(-3,-1, SpinBlocks.ORB_PURPLE), new PuzzleOrb(-2,-1, SpinBlocks.ORB_GREEN), new PuzzleOrb(-1,-1, SpinBlocks.ORB_GREEN), new PuzzleOrb(0,-1, SpinBlocks.ORB_PURPLE), new PuzzleOrb(1,-1, SpinBlocks.ORB_GREEN), new PuzzleOrb(2,-1, SpinBlocks.ORB_GREEN), new PuzzleOrb(3,-1, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-3, 0, SpinBlocks.ORB_PURPLE), new PuzzleOrb(-2, 0, SpinBlocks.ORB_PURPLE), new PuzzleOrb(-1, 0, SpinBlocks.ORB_ORANGE), 										   new PuzzleOrb(1, 0, SpinBlocks.ORB_PURPLE), new PuzzleOrb(2, 0, SpinBlocks.ORB_ORANGE), new PuzzleOrb(3, 0, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-3, 1, SpinBlocks.ORB_PURPLE), new PuzzleOrb(-2, 1, SpinBlocks.ORB_GREEN), new PuzzleOrb(-1, 1, SpinBlocks.ORB_GREEN), new PuzzleOrb(0, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(1, 1, SpinBlocks.ORB_GREEN), new PuzzleOrb(2, 1, SpinBlocks.ORB_GREEN), new PuzzleOrb(3, 1, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-3, 2, SpinBlocks.ORB_PURPLE), new PuzzleOrb(-2, 2, SpinBlocks.ORB_GREEN), new PuzzleOrb(-1, 2, SpinBlocks.ORB_PURPLE), new PuzzleOrb(0, 2, SpinBlocks.ORB_PURPLE), new PuzzleOrb(1, 2, SpinBlocks.ORB_PURPLE), new PuzzleOrb(2, 2, SpinBlocks.ORB_GREEN), new PuzzleOrb(3, 2, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-3, 3, SpinBlocks.ORB_GREEN), new PuzzleOrb(-2, 3, SpinBlocks.ORB_GREEN), new PuzzleOrb(-1, 3, SpinBlocks.ORB_PURPLE), new PuzzleOrb(0, 3, SpinBlocks.ORB_PURPLE), new PuzzleOrb(1, 3, SpinBlocks.ORB_PURPLE), new PuzzleOrb(2, 3, SpinBlocks.ORB_GREEN), new PuzzleOrb(3, 3, SpinBlocks.ORB_GREEN),
}, new int[] { SpinBlocks.ORB_GREEN, SpinBlocks.ORB_GREEN, SpinBlocks.ORB_PURPLE, SpinBlocks.ORB_ORANGE}),	 

new Puzzle(13, "Nightmare", new PuzzleOrb[] {
new PuzzleOrb(-3,-3, 3), new PuzzleOrb(-2,-3, 4), new PuzzleOrb(-1,-3, 5), new PuzzleOrb(0,-3, 6), new PuzzleOrb(1,-3, 5), new PuzzleOrb(2,-3, 4), new PuzzleOrb(3,-3, 3),
new PuzzleOrb(-3,-2, 4), new PuzzleOrb(-2,-2, 1), new PuzzleOrb(-1,-2, 2), new PuzzleOrb(0,-2, 1), new PuzzleOrb(1,-2, 2), new PuzzleOrb(2,-2, 1), new PuzzleOrb(3,-2, 4), 
new PuzzleOrb(-3,-1, 5), new PuzzleOrb(-2,-1, 2), new PuzzleOrb(-1,-1, 0), new PuzzleOrb(0,-1, 0), new PuzzleOrb(1,-1, 0), new PuzzleOrb(2,-1, 2), new PuzzleOrb(3,-1, 5),
new PuzzleOrb(-3, 0, 6), new PuzzleOrb(-2, 0, 1), new PuzzleOrb(-1, 0, 0), 						   new PuzzleOrb(1, 0, 0), new PuzzleOrb(2, 0, 1), new PuzzleOrb(3, 0, 6),
new PuzzleOrb(-3, 1, 5), new PuzzleOrb(-2, 1, 2), new PuzzleOrb(-1, 1, 0), new PuzzleOrb(0, 1, 0), new PuzzleOrb(1, 1, 0), new PuzzleOrb(2, 1, 2), new PuzzleOrb(3, 1, 5),
new PuzzleOrb(-3, 2, 4), new PuzzleOrb(-2, 2, 1), new PuzzleOrb(-1, 2, 2), new PuzzleOrb(0, 2, 1), new PuzzleOrb(1, 2, 2), new PuzzleOrb(2, 2, 1), new PuzzleOrb(3, 2, 4),
new PuzzleOrb(-3, 3, 3), new PuzzleOrb(-2, 3, 4), new PuzzleOrb(-1, 3, 5), new PuzzleOrb(0, 3, 6), new PuzzleOrb(1, 3, 5), new PuzzleOrb(2, 3, 4), new PuzzleOrb(3, 3, 3),
}, new int[] { 6,5,5,4,4,3,3,2,2,1,1,0 }),	 

new Puzzle(14, "Patience", new PuzzleOrb[] {
new PuzzleOrb(-2,-2, SpinBlocks.ORB_BLUE), new PuzzleOrb(-1,-2, SpinBlocks.ORB_BLUE), new PuzzleOrb(0,-2, SpinBlocks.ORB_BLUE), new PuzzleOrb(1,-2, SpinBlocks.ORB_BLUE), new PuzzleOrb(2,-2, SpinBlocks.ORB_BLUE), 
new PuzzleOrb(-2,-1, SpinBlocks.ORB_BLUE), new PuzzleOrb(-1,-1, SpinBlocks.ORB_BLUE), 										  new PuzzleOrb(1,-1, SpinBlocks.ORB_BLUE), new PuzzleOrb(2,-1, SpinBlocks.ORB_BLUE),
new PuzzleOrb(-2, 0, SpinBlocks.ORB_BLUE), new PuzzleOrb(-1, 0, SpinBlocks.ORB_BLUE), 																				  new PuzzleOrb(2, 0, SpinBlocks.ORB_BLUE),
new PuzzleOrb(-2, 1, SpinBlocks.ORB_BLUE),																				  new PuzzleOrb(1, 1, SpinBlocks.ORB_BLUE), new PuzzleOrb(2, 1, SpinBlocks.ORB_BLUE),
new PuzzleOrb(-2, 2, SpinBlocks.ORB_BLUE),										  new PuzzleOrb(0, 2, SpinBlocks.ORB_BLUE), new PuzzleOrb(1, 2, SpinBlocks.ORB_BLUE), new PuzzleOrb(2, 2, SpinBlocks.ORB_BLUE),
}, new int[] { SpinBlocks.ORB_RED, SpinBlocks.ORB_RED, SpinBlocks.ORB_RED, SpinBlocks.ORB_RED, SpinBlocks.ORB_RED, 
				SpinBlocks.ORB_RED, SpinBlocks.ORB_RED, SpinBlocks.ORB_RED, SpinBlocks.ORB_RED, SpinBlocks.ORB_RED,
				SpinBlocks.ORB_RED, SpinBlocks.ORB_RED, SpinBlocks.ORB_RED, SpinBlocks.ORB_RED, SpinBlocks.ORB_RED, 
				SpinBlocks.ORB_RED, SpinBlocks.ORB_RED, SpinBlocks.ORB_RED, SpinBlocks.ORB_RED, SpinBlocks.ORB_RED, 
				SpinBlocks.ORB_BOMB, SpinBlocks.ORB_BOMB }),	
				
new Puzzle(15, "Nautilus", new PuzzleOrb[] {
new PuzzleOrb(-3,-2, 6), new PuzzleOrb(-2,-2, 2), new PuzzleOrb(-1,-2, 2), new PuzzleOrb(0,-2, 2), new PuzzleOrb(1,-2, 2), new PuzzleOrb(2,-2, 4), new PuzzleOrb(3,-2, 2), 
new PuzzleOrb(-3,-1, 6), new PuzzleOrb(-2,-1, 3), new PuzzleOrb(-1,-1, 3), new PuzzleOrb(0,-1, 3), new PuzzleOrb(1,-1, 3), new PuzzleOrb(2,-1, 4), new PuzzleOrb(3,-1, 2),
new PuzzleOrb(-3, 0, 6), new PuzzleOrb(-2, 0, 2), new PuzzleOrb(-1, 0, 1), 						   						   new PuzzleOrb(2, 0, 4), new PuzzleOrb(3, 0, 2),
new PuzzleOrb(-3, 1, 6), new PuzzleOrb(-2, 1, 2), new PuzzleOrb(-1, 1, 1), new PuzzleOrb(0, 1, 1), new PuzzleOrb(1, 1, 2), new PuzzleOrb(2, 1, 4), new PuzzleOrb(3, 1, 2),
new PuzzleOrb(-3, 2, 6), new PuzzleOrb(-2, 2, 2), new PuzzleOrb(-1, 2, 1), new PuzzleOrb(0, 2, 2), new PuzzleOrb(1, 2, 2), new PuzzleOrb(2, 2, 4), new PuzzleOrb(3, 2, 2),
new PuzzleOrb(-3, 3, 6), new PuzzleOrb(-2, 3, 5), new PuzzleOrb(-1, 3, 5), new PuzzleOrb(0, 3, 5), new PuzzleOrb(1, 3, 5), new PuzzleOrb(2, 3, 5), new PuzzleOrb(3, 3, 5),
new PuzzleOrb(-3, 4, 2), new PuzzleOrb(-2, 4, 2), new PuzzleOrb(-1, 4, 2), new PuzzleOrb(0, 4, 2), new PuzzleOrb(1, 4, 2), new PuzzleOrb(2, 4, 2), new PuzzleOrb(3, 4, 2),
}, new int[] { 6, 5, 4, 3, 1 }),	 

new Puzzle(16, "Layers", new PuzzleOrb[] {
												  new PuzzleOrb(-1,-3, 3), new PuzzleOrb(0,-3, 3), new PuzzleOrb(1,-3, 4),
						 new PuzzleOrb(-2,-2, 3), new PuzzleOrb(-1,-2, 3), new PuzzleOrb(0,-2, 2), new PuzzleOrb(1,-2, 4), new PuzzleOrb(2,-2, 4),
new PuzzleOrb(-3,-1, 4), new PuzzleOrb(-2,-1, 4), new PuzzleOrb(-1,-1, 1), new PuzzleOrb(0,-1, 2), new PuzzleOrb(1,-1, 1), new PuzzleOrb(2,-1, 4), new PuzzleOrb(3,-1, 0),
new PuzzleOrb(-3, 0, 4), new PuzzleOrb(-2, 0, 2), new PuzzleOrb(-1, 0, 1), 										   new PuzzleOrb(1, 0, 1), new PuzzleOrb(2, 0, 3), new PuzzleOrb(3, 0, 0),
new PuzzleOrb(-3, 1, 4), new PuzzleOrb(-2, 1, 2), new PuzzleOrb(-1, 1, 2), new PuzzleOrb(0, 1, 2), new PuzzleOrb(1, 1, 3), new PuzzleOrb(2, 1, 3), new PuzzleOrb(3, 1, 0),
						 new PuzzleOrb(-2, 2, 5), new PuzzleOrb(-1, 2, 5), new PuzzleOrb(0, 2, 0), new PuzzleOrb(1, 2, 5), new PuzzleOrb(2, 2, 5),
												  new PuzzleOrb(-1, 3, 5), new PuzzleOrb(0, 3, 0), new PuzzleOrb(1, 3, 5),
}, new int[] { 0, 4, 3, 5, 2, 3, 5, 2, 1, 1 }),	 

new Puzzle(17, "Harder Does It", new PuzzleOrb[] {
																		   new PuzzleOrb(-1,-3, 2), new PuzzleOrb(0,-3, 2), new PuzzleOrb(1,-3, 2),
																		   new PuzzleOrb(-1,-2, 1), new PuzzleOrb(0,-2, 1), new PuzzleOrb(1,-2, 1), 
new PuzzleOrb(-3,-1, 5), new PuzzleOrb(-2,-1, 4), new PuzzleOrb(-1,-1, 3), new PuzzleOrb(0,-1, 0), new PuzzleOrb(1,-1, 0), new PuzzleOrb(2,-1, 2), new PuzzleOrb(3,-1, 1),
new PuzzleOrb(-3, 0, 5), new PuzzleOrb(-2, 0, 4), new PuzzleOrb(-1, 0, 3), 										   new PuzzleOrb(1, 0, 3), new PuzzleOrb(2, 0, 2), new PuzzleOrb(3, 0, 1),
new PuzzleOrb(-3, 1, 5), new PuzzleOrb(-2, 1, 4), new PuzzleOrb(-1, 1, 0), new PuzzleOrb(0, 1, 0), new PuzzleOrb(1, 1, 3), new PuzzleOrb(2, 1, 2), new PuzzleOrb(3, 1, 1),
																		   new PuzzleOrb(-1, 2, 5), new PuzzleOrb(0, 2, 5), new PuzzleOrb(1, 2, 5),
																		   new PuzzleOrb(-1, 3, 4), new PuzzleOrb(0, 3, 4), new PuzzleOrb(1, 3, 4),
}, new int[] { 5,4,3,2,1,0 }),	

new Puzzle(18, "Trominoes", new PuzzleOrb[] {
new PuzzleOrb(-3,-2, 3), new PuzzleOrb(-2,-2, 3), new PuzzleOrb(-1,-2, 1), new PuzzleOrb(0,-2, 1), new PuzzleOrb(1,-2, 2), new PuzzleOrb(2,-2, 2), new PuzzleOrb(3,-2, 4), 
new PuzzleOrb(-3,-1, 3), new PuzzleOrb(-2,-1, 2), new PuzzleOrb(-1,-1, 1), new PuzzleOrb(0,-1, 0), new PuzzleOrb(1,-1, 0), new PuzzleOrb(2,-1, 2), new PuzzleOrb(3,-1, 4),
						 new PuzzleOrb(-2, 0, 2), new PuzzleOrb(-1, 0, 2), 						   new PuzzleOrb(1, 0, 0), new PuzzleOrb(2, 0, 3),
new PuzzleOrb(-3, 1, 0), new PuzzleOrb(-2, 1, 5), new PuzzleOrb(-1, 1, 5), new PuzzleOrb(0, 1, 4), new PuzzleOrb(1, 1, 3), new PuzzleOrb(2, 1, 3), new PuzzleOrb(3, 1, 1),
new PuzzleOrb(-3, 2, 0), new PuzzleOrb(-2, 2, 0), new PuzzleOrb(-1, 2, 5), new PuzzleOrb(0, 2, 4), new PuzzleOrb(1, 2, 4), new PuzzleOrb(2, 2, 1), new PuzzleOrb(3, 2, 1),
}, new int[] { 0,1,2,3,4,5 }),	

new Puzzle(19, "Doubles", new PuzzleOrb[] {
new PuzzleOrb(-3,-3, 0), new PuzzleOrb(-2,-3, 0), new PuzzleOrb(-1,-3, 6), new PuzzleOrb(0,-3, 6), new PuzzleOrb(1,-3, 5), new PuzzleOrb(2,-3, 5), new PuzzleOrb(3,-3, 4),
new PuzzleOrb(-3,-2, 3), new PuzzleOrb(-2,-2, 2), new PuzzleOrb(-1,-2, 2), new PuzzleOrb(0,-2, 1), new PuzzleOrb(1,-2, 2), new PuzzleOrb(2,-2, 2), new PuzzleOrb(3,-2, 4), 
new PuzzleOrb(-3,-1, 3), new PuzzleOrb(-2,-1, 4), new PuzzleOrb(-1,-1, 4), new PuzzleOrb(0,-1, 1), new PuzzleOrb(1,-1, 0), new PuzzleOrb(2,-1, 3), new PuzzleOrb(3,-1, 3),
new PuzzleOrb(-3, 0, 6), new PuzzleOrb(-2, 0, 0), new PuzzleOrb(-1, 0, 3), 										   new PuzzleOrb(1, 0, 0), new PuzzleOrb(2, 0, 4), new PuzzleOrb(3, 0, 5),
new PuzzleOrb(-3, 1, 6), new PuzzleOrb(-2, 1, 0), new PuzzleOrb(-1, 1, 3), new PuzzleOrb(0, 1, 4), new PuzzleOrb(1, 1, 6), new PuzzleOrb(2, 1, 4), new PuzzleOrb(3, 1, 5),
new PuzzleOrb(-3, 2, 5), new PuzzleOrb(-2, 2, 3), new PuzzleOrb(-1, 2, 1), new PuzzleOrb(0, 2, 4), new PuzzleOrb(1, 2, 6), new PuzzleOrb(2, 2, 0), new PuzzleOrb(3, 2, 1),
new PuzzleOrb(-3, 3, 5), new PuzzleOrb(-2, 3, 3), new PuzzleOrb(-1, 3, 1), new PuzzleOrb(0, 3, 2), new PuzzleOrb(1, 3, 2), new PuzzleOrb(2, 3, 0), new PuzzleOrb(3, 3, 1),
}, new int[] { 0,0,1,1,2,2,3,3,4,4,5,5,6,6 }),	 

new Puzzle(20, "Singles", new PuzzleOrb[] {
new PuzzleOrb(-2,-2, 1), new PuzzleOrb(-1,-2, 2), new PuzzleOrb(0,-2, 5), new PuzzleOrb(1,-2, 6), new PuzzleOrb(2,-2, 3), 
new PuzzleOrb(-2,-1, 3), new PuzzleOrb(-1,-1, 4), new PuzzleOrb(0,-1, 1), new PuzzleOrb(1,-1, 5), new PuzzleOrb(2,-1, 1),
new PuzzleOrb(-2, 0, 4), new PuzzleOrb(-1, 0, 0), 											new PuzzleOrb(1, 0, 2), new PuzzleOrb(2, 0, 0),
new PuzzleOrb(-2, 1, 0), new PuzzleOrb(-1, 1, 5), new PuzzleOrb(0, 1, 3), new PuzzleOrb(1, 1, 6), new PuzzleOrb(2, 1, 4),
new PuzzleOrb(-2, 2, 2), new PuzzleOrb(-1, 2, 1), new PuzzleOrb(0, 2, 4), new PuzzleOrb(1, 2, 0), new PuzzleOrb(2, 2, 5),
}, new int[] { 2, 5, 6, 3, 1, 4, 0 }),	

new Puzzle(21, "Endurance", new PuzzleOrb[] {
new PuzzleOrb(-2,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(0,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(1,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2,-2, SpinBlocks.ORB_ORANGE), 
new PuzzleOrb(-2,-1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1,-1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(0,-1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(1,-1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2,-1, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-2, 0, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1, 0, SpinBlocks.ORB_ORANGE), 											new PuzzleOrb(1, 0, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2, 0, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-2, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(0, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(1, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2, 1, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-2, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(0, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(1, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2, 2, SpinBlocks.ORB_ORANGE),
}, new int[] { 1, 2, 4, SpinBlocks.ORB_BOMB }),	

new Puzzle(22, "Walls", new PuzzleOrb[] {
new PuzzleOrb(-3,-2, 3), new PuzzleOrb(-2,-2, 5), new PuzzleOrb(-1,-2, 5), new PuzzleOrb(0,-2, 5), new PuzzleOrb(1,-2, 5), new PuzzleOrb(2,-2, 5), new PuzzleOrb(3,-2, 2), 
new PuzzleOrb(-3,-1, 3), new PuzzleOrb(-2,-1, 3), new PuzzleOrb(-1,-1, 1), new PuzzleOrb(0,-1, 0), new PuzzleOrb(1,-1, 0), new PuzzleOrb(2,-1, 2), new PuzzleOrb(3,-1, 2),
new PuzzleOrb(-3, 0, 5), new PuzzleOrb(-2, 0, 5), new PuzzleOrb(-1, 0, 1), 										   new PuzzleOrb(1, 0, 0), new PuzzleOrb(2, 0, 5), new PuzzleOrb(3, 0, 5),
new PuzzleOrb(-3, 1, 2), new PuzzleOrb(-2, 1, 2), new PuzzleOrb(-1, 1, 1), new PuzzleOrb(0, 1, 1), new PuzzleOrb(1, 1, 0), new PuzzleOrb(2, 1, 3), new PuzzleOrb(3, 1, 3),
new PuzzleOrb(-3, 2, 2), new PuzzleOrb(-2, 2, 5), new PuzzleOrb(-1, 2, 5), new PuzzleOrb(0, 2, 5), new PuzzleOrb(1, 2, 5), new PuzzleOrb(2, 2, 5), new PuzzleOrb(3, 2, 3),
}, new int[] { 2,3,2,3,1,0 }),	

new Puzzle(23, "Structure", new PuzzleOrb[] {
new PuzzleOrb(-3,-3, 6), new PuzzleOrb(-2,-3, 5), new PuzzleOrb(-1,-3, 1), new PuzzleOrb(0,-3, 0), new PuzzleOrb(1,-3, 2), new PuzzleOrb(2,-3, 5), new PuzzleOrb(3,-3, 6),
new PuzzleOrb(-3,-2, 5), new PuzzleOrb(0,-2, 4), new PuzzleOrb(3,-2, 5), 
new PuzzleOrb(-3,-1, 4), new PuzzleOrb(0,-1, 3), new PuzzleOrb(3,-1, 1),
new PuzzleOrb(-3, 0, 0), new PuzzleOrb(-2, 0, 2), new PuzzleOrb(-1, 0, 1), 										   new PuzzleOrb(1, 0, 3), new PuzzleOrb(2, 0, 4), new PuzzleOrb(3, 0, 0),
new PuzzleOrb(-3, 1, 3), new PuzzleOrb(0, 1, 1), new PuzzleOrb(3, 1, 2),
new PuzzleOrb(-3, 2, 5), new PuzzleOrb(0, 2, 2), new PuzzleOrb(3, 2, 5),
new PuzzleOrb(-3, 3, 6), new PuzzleOrb(-2, 3, 5), new PuzzleOrb(-1, 3, 4), new PuzzleOrb(0, 3, 0), new PuzzleOrb(1, 3, 3), new PuzzleOrb(2, 3, 5), new PuzzleOrb(3, 3, 6),
}, new int[] { 0,2,4,6,1,3,5 }),	

new Puzzle(24, "Flower",  new PuzzleOrb[] {
new PuzzleOrb(-1,-3, 5), new PuzzleOrb(1,-3, 5),
new PuzzleOrb(-2,-2, 2), new PuzzleOrb(-1,-2, 3), new PuzzleOrb(0,-2, 5), new PuzzleOrb(1,-2, 3), new PuzzleOrb(2,-2, 4),  
new PuzzleOrb(-3,-1, 0), new PuzzleOrb(-2,-1, 1), new PuzzleOrb(-1,-1, 2), new PuzzleOrb(0,-1, 3), new PuzzleOrb(1,-1, 4), new PuzzleOrb(2,-1, 1), new PuzzleOrb(3,-1, 0),
new PuzzleOrb(-2, 0, 0), new PuzzleOrb(-1, 0, 1), 										   new PuzzleOrb(1, 0, 1), new PuzzleOrb(2, 0, 0), 
new PuzzleOrb(-3, 1, 0), new PuzzleOrb(-2, 1, 1), new PuzzleOrb(-1, 1, 4), new PuzzleOrb(0, 1, 3), new PuzzleOrb(1, 1, 2), new PuzzleOrb(2, 1, 1), new PuzzleOrb(3, 1, 0),
new PuzzleOrb(-2, 2, 4), new PuzzleOrb(-1, 2, 3), new PuzzleOrb(0, 2, 5), new PuzzleOrb(1, 2, 3), new PuzzleOrb(2, 2, 2), 
new PuzzleOrb(-1, 3, 5), new PuzzleOrb(1, 3, 5),
}, new int[] { 5,4,3,2,1,0}),	

new Puzzle(25, "Wat", new PuzzleOrb[] {
new PuzzleOrb(-4,-4, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-3,-4, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-2,-4, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1,-4, SpinBlocks.ORB_ORANGE), new PuzzleOrb(0,-4, 2), new PuzzleOrb(1,-4, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2,-4, SpinBlocks.ORB_ORANGE), new PuzzleOrb(3,-4, SpinBlocks.ORB_ORANGE), new PuzzleOrb(4,-4, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-4,-3, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-3,-3, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-2,-3, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1,-3, SpinBlocks.ORB_ORANGE), new PuzzleOrb(0,-3, 2), new PuzzleOrb(1,-3, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2,-3, SpinBlocks.ORB_ORANGE), new PuzzleOrb(3,-3, SpinBlocks.ORB_ORANGE), new PuzzleOrb(4,-3, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-4,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-3,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-2,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(0,-2, 1), new PuzzleOrb(1,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(3,-2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(4,-2, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-4,-1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-3,-1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-2,-1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1,-1, 3), 					new PuzzleOrb(0,-1, 1), 				  new PuzzleOrb(1,-1, 3), new PuzzleOrb(2,-1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(3,-1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(4,-1, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-4, 0, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-3, 0, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-2, 0, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1, 0, 3), 										  					  new PuzzleOrb(1, 0, 3), new PuzzleOrb(2, 0, SpinBlocks.ORB_ORANGE), new PuzzleOrb(3, 0, SpinBlocks.ORB_ORANGE), new PuzzleOrb(4, 0, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-4, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-3, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-2, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1, 1, 3), 					new PuzzleOrb(0, 1, 3), 				  new PuzzleOrb(1, 1, 3), new PuzzleOrb(2, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(3, 1, SpinBlocks.ORB_ORANGE), new PuzzleOrb(4, 1, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-4, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-3, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-2, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(0, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(1, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(3, 2, SpinBlocks.ORB_ORANGE), new PuzzleOrb(4, 2, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-4, 3, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-3, 3, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-2, 3, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1, 3, SpinBlocks.ORB_ORANGE), new PuzzleOrb(0, 3, SpinBlocks.ORB_ORANGE), new PuzzleOrb(1, 3, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2, 3, SpinBlocks.ORB_ORANGE), new PuzzleOrb(3, 3, SpinBlocks.ORB_ORANGE), new PuzzleOrb(4, 3, SpinBlocks.ORB_ORANGE),
new PuzzleOrb(-4, 4, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-3, 4, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-2, 4, SpinBlocks.ORB_ORANGE), new PuzzleOrb(-1, 4, SpinBlocks.ORB_ORANGE), new PuzzleOrb(0, 4, SpinBlocks.ORB_ORANGE), new PuzzleOrb(1, 4, SpinBlocks.ORB_ORANGE), new PuzzleOrb(2, 4, SpinBlocks.ORB_ORANGE), new PuzzleOrb(3, 4, SpinBlocks.ORB_ORANGE), new PuzzleOrb(4, 4, SpinBlocks.ORB_ORANGE),
}, new int[] { 2,1,3 }),	 

/*
new Puzzle(11, new PuzzleOrb[] {
new PuzzleOrb(-3,-3, Spinblox.ORB_ORANGE), new PuzzleOrb(-2,-3, Spinblox.ORB_ORANGE), new PuzzleOrb(-1,-3, Spinblox.ORB_ORANGE), new PuzzleOrb(0,-3, Spinblox.ORB_ORANGE), new PuzzleOrb(1,-3, Spinblox.ORB_ORANGE), new PuzzleOrb(2,-3, Spinblox.ORB_ORANGE), new PuzzleOrb(3,-3, Spinblox.ORB_ORANGE),
new PuzzleOrb(-3,-2, Spinblox.ORB_ORANGE), new PuzzleOrb(-2,-2, Spinblox.ORB_ORANGE), new PuzzleOrb(-1,-2, Spinblox.ORB_ORANGE), new PuzzleOrb(0,-2, Spinblox.ORB_ORANGE), new PuzzleOrb(1,-2, Spinblox.ORB_ORANGE), new PuzzleOrb(2,-2, Spinblox.ORB_ORANGE), new PuzzleOrb(3,-2, Spinblox.ORB_ORANGE), 
new PuzzleOrb(-3,-1, Spinblox.ORB_ORANGE), new PuzzleOrb(-2,-1, Spinblox.ORB_ORANGE), new PuzzleOrb(-1,-1, Spinblox.ORB_ORANGE), new PuzzleOrb(0,-1, Spinblox.ORB_ORANGE), new PuzzleOrb(1,-1, Spinblox.ORB_ORANGE), new PuzzleOrb(2,-1, Spinblox.ORB_ORANGE), new PuzzleOrb(3,-1, Spinblox.ORB_ORANGE),
new PuzzleOrb(-3, 0, Spinblox.ORB_ORANGE), new PuzzleOrb(-2, 0, Spinblox.ORB_ORANGE), new PuzzleOrb(-1, 0, Spinblox.ORB_ORANGE), 										   new PuzzleOrb(1, 0, Spinblox.ORB_ORANGE), new PuzzleOrb(2, 0, Spinblox.ORB_ORANGE), new PuzzleOrb(3, 0, Spinblox.ORB_ORANGE),
new PuzzleOrb(-3, 1, Spinblox.ORB_ORANGE), new PuzzleOrb(-2, 1, Spinblox.ORB_ORANGE), new PuzzleOrb(-1, 1, Spinblox.ORB_ORANGE), new PuzzleOrb(0, 1, Spinblox.ORB_ORANGE), new PuzzleOrb(1, 1, Spinblox.ORB_ORANGE), new PuzzleOrb(2, 1, Spinblox.ORB_ORANGE), new PuzzleOrb(3, 1, Spinblox.ORB_ORANGE),
new PuzzleOrb(-3, 2, Spinblox.ORB_ORANGE), new PuzzleOrb(-2, 2, Spinblox.ORB_ORANGE), new PuzzleOrb(-1, 2, Spinblox.ORB_ORANGE), new PuzzleOrb(0, 2, Spinblox.ORB_ORANGE), new PuzzleOrb(1, 2, Spinblox.ORB_ORANGE), new PuzzleOrb(2, 2, Spinblox.ORB_ORANGE), new PuzzleOrb(3, 2, Spinblox.ORB_ORANGE),
new PuzzleOrb(-3, 3, Spinblox.ORB_ORANGE), new PuzzleOrb(-2, 3, Spinblox.ORB_ORANGE), new PuzzleOrb(-1, 3, Spinblox.ORB_ORANGE), new PuzzleOrb(0, 3, Spinblox.ORB_ORANGE), new PuzzleOrb(1, 3, Spinblox.ORB_ORANGE), new PuzzleOrb(2, 3, Spinblox.ORB_ORANGE), new PuzzleOrb(3, 3, Spinblox.ORB_ORANGE),
}, new int[] { Spinblox.ORB_ORANGE }),	 

new Puzzle(5, new PuzzleOrb[] {
new PuzzleOrb(-3,-2, Spinblox.ORB_ORANGE), new PuzzleOrb(-2,-2, Spinblox.ORB_ORANGE), new PuzzleOrb(-1,-2, Spinblox.ORB_ORANGE), new PuzzleOrb(0,-2, Spinblox.ORB_ORANGE), new PuzzleOrb(1,-2, Spinblox.ORB_ORANGE), new PuzzleOrb(2,-2, Spinblox.ORB_ORANGE), new PuzzleOrb(3,-2, Spinblox.ORB_ORANGE), 
new PuzzleOrb(-3,-1, Spinblox.ORB_ORANGE), new PuzzleOrb(-2,-1, Spinblox.ORB_ORANGE), new PuzzleOrb(-1,-1, Spinblox.ORB_ORANGE), new PuzzleOrb(0,-1, Spinblox.ORB_ORANGE), new PuzzleOrb(1,-1, Spinblox.ORB_ORANGE), new PuzzleOrb(2,-1, Spinblox.ORB_ORANGE), new PuzzleOrb(3,-1, Spinblox.ORB_ORANGE),
new PuzzleOrb(-3, 0, Spinblox.ORB_ORANGE), new PuzzleOrb(-2, 0, Spinblox.ORB_ORANGE), new PuzzleOrb(-1, 0, Spinblox.ORB_ORANGE), 										   new PuzzleOrb(1, 0, Spinblox.ORB_ORANGE), new PuzzleOrb(2, 0, Spinblox.ORB_ORANGE), new PuzzleOrb(3, 0, Spinblox.ORB_ORANGE),
new PuzzleOrb(-3, 1, Spinblox.ORB_ORANGE), new PuzzleOrb(-2, 1, Spinblox.ORB_ORANGE), new PuzzleOrb(-1, 1, Spinblox.ORB_ORANGE), new PuzzleOrb(0, 1, Spinblox.ORB_ORANGE), new PuzzleOrb(1, 1, Spinblox.ORB_ORANGE), new PuzzleOrb(2, 1, Spinblox.ORB_ORANGE), new PuzzleOrb(3, 1, Spinblox.ORB_ORANGE),
new PuzzleOrb(-3, 2, Spinblox.ORB_ORANGE), new PuzzleOrb(-2, 2, Spinblox.ORB_ORANGE), new PuzzleOrb(-1, 2, Spinblox.ORB_ORANGE), new PuzzleOrb(0, 2, Spinblox.ORB_ORANGE), new PuzzleOrb(1, 2, Spinblox.ORB_ORANGE), new PuzzleOrb(2, 2, Spinblox.ORB_ORANGE), new PuzzleOrb(3, 2, Spinblox.ORB_ORANGE),
}, new int[] { Spinblox.ORB_ORANGE }),	
	
new Puzzle(6, new PuzzleOrb[] {
new PuzzleOrb(-2,-2, Spinblox.ORB_ORANGE), new PuzzleOrb(-1,-2, Spinblox.ORB_ORANGE), new PuzzleOrb(0,-2, Spinblox.ORB_ORANGE), new PuzzleOrb(1,-2, Spinblox.ORB_ORANGE), new PuzzleOrb(2,-2, Spinblox.ORB_ORANGE), 
new PuzzleOrb(-2,-1, Spinblox.ORB_ORANGE), new PuzzleOrb(-1,-1, Spinblox.ORB_ORANGE), new PuzzleOrb(0,-1, Spinblox.ORB_ORANGE), new PuzzleOrb(1,-1, Spinblox.ORB_ORANGE), new PuzzleOrb(2,-1, Spinblox.ORB_ORANGE),
new PuzzleOrb(-2, 0, Spinblox.ORB_ORANGE), new PuzzleOrb(-1, 0, Spinblox.ORB_ORANGE), 											new PuzzleOrb(1, 0, Spinblox.ORB_ORANGE), new PuzzleOrb(2, 0, Spinblox.ORB_ORANGE),
new PuzzleOrb(-2, 1, Spinblox.ORB_ORANGE), new PuzzleOrb(-1, 1, Spinblox.ORB_ORANGE), new PuzzleOrb(0, 1, Spinblox.ORB_ORANGE), new PuzzleOrb(1, 1, Spinblox.ORB_ORANGE), new PuzzleOrb(2, 1, Spinblox.ORB_ORANGE),
new PuzzleOrb(-2, 2, Spinblox.ORB_ORANGE), new PuzzleOrb(-1, 2, Spinblox.ORB_ORANGE), new PuzzleOrb(0, 2, Spinblox.ORB_ORANGE), new PuzzleOrb(1, 2, Spinblox.ORB_ORANGE), new PuzzleOrb(2, 2, Spinblox.ORB_ORANGE),
}, new int[] { Spinblox.ORB_ORANGE }),	
*/
	};
}
