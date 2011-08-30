package com.memeworks.spinblocks;

import com.memeworks.spinblocks.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class PuzzleListAdapter extends ArrayAdapter<Puzzle> {
	private Activity context;
	private Puzzle[] puzzles;

	PuzzleListAdapter(Activity context, Puzzle[] items) {
		super(context, R.layout.puzzlelistitem, items);
		
		puzzles = items;
		this.context = context;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View row = inflater.inflate(R.layout.puzzlelistitem, null);
		
		ImageView icon=(ImageView)row.findViewById(R.id.puzzlelistitemicon);
		TextView label=(TextView) row.findViewById(R.id.puzzlelistitemtext);
	
		String[] puzzle_values = SpinBlocks.PUZZLE_RECORDS[position].split(" ");
		int puzzle_status = Integer.parseInt(puzzle_values[0]);
		int puzzle_moves = Integer.parseInt(puzzle_values[1]);
		
		if (puzzle_status == SpinBlocks.PUZZLE_COMPLETE)
		{
			label.setText(puzzles[position].toString() + " - Status: COMPLETE - Least Moves:" + puzzle_moves);
			icon.setImageResource(R.drawable.puzzleiconcomplete);
		}
		else if (puzzle_status == SpinBlocks.PUZZLE_FAILED)
		{
			label.setText(puzzles[position].toString() + " - Status: FAILED - Least Moves:" + puzzle_moves);
			icon.setImageResource(R.drawable.puzzleiconfailed);
		}
		else
		{
			label.setText(puzzles[position].toString() + " - Status: INCOMPLETE");
			icon.setImageResource(R.drawable.puzzleiconincomplete);
		}

		return(row);
	}
}