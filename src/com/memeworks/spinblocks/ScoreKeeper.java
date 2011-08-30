package com.memeworks.spinblocks;

import android.graphics.Canvas;
import android.graphics.Paint;

public class ScoreKeeper {

	public final int FLOATER_SCORE = 50;
	
	public FloatText[] Texts = new FloatText[125];
	public int Current_Text_Index = 0;
	
	public StringBuilder Score_Text = new StringBuilder();
	private final char score_chars[] = new char[100];
	private Paint score_paint = new Paint();
	private Paint score_outline_paint = new Paint();
	private float score_text_size = 22;
	
	public StringBuilder Multiplier_Text = new StringBuilder();
	private final char multiplier_chars[] = new char[100];
	private Paint multiplier_paint = new Paint();
	private Paint multiplier_outline_paint = new Paint();
	private float multiplier_text_size = 15;
	
	public float Current_Score = 0;
	private int target_score = 0;
	private float score_to_add = 0;
	
	public int Current_Multiplier = 1;
	public float Multiplier_Level = 0;
	
	public int Current_Level = 1;
	
	public ScoreKeeper() {
		
		if (SpinBlocks.Gametype == SpinBlocks.GAMETYPE_PUZZLE)
		{
			score_text_size = 17;
		}
		
		score_paint.setARGB(255, 255, 255, 255);
		score_paint.setTypeface(SpinBlocks.FONT);
		score_paint.setTextSize(score_text_size);
		score_paint.setTextAlign(Paint.Align.RIGHT);
		score_paint.setAntiAlias(true);
		
		score_outline_paint.setARGB(255, 0, 0, 0);
		score_outline_paint.setTextAlign(Paint.Align.RIGHT);
		score_outline_paint.setTextSize(score_text_size);
		score_outline_paint.setTypeface(SpinBlocks.FONT);
		score_outline_paint.setStyle(Paint.Style.STROKE);
		score_outline_paint.setStrokeWidth(3);
		score_outline_paint.setAntiAlias(true);
		
		multiplier_paint.setARGB(255, 255, 255, 255);
		multiplier_paint.setTypeface(SpinBlocks.FONT);
		multiplier_paint.setTextSize(multiplier_text_size);
		multiplier_paint.setTextAlign(Paint.Align.RIGHT);
		multiplier_paint.setAntiAlias(true);
		
		multiplier_outline_paint.setARGB(255, 0, 0, 0);
		multiplier_outline_paint.setTextAlign(Paint.Align.RIGHT);
		multiplier_outline_paint.setTextSize(multiplier_text_size);
		multiplier_outline_paint.setTypeface(SpinBlocks.FONT);
		multiplier_outline_paint.setStyle(Paint.Style.STROKE);
		multiplier_outline_paint.setStrokeWidth(2);
		multiplier_outline_paint.setAntiAlias(true);

		for (int i = 0; i < Texts.length; i++)
		{
			Texts[i] = new FloatText();
		}
	}

	public void Draw(Canvas c)
	{
		//Draw scoreboard / combo
		Score_Text.getChars(0, Score_Text.length(), score_chars, 0);
		c.drawText(score_chars, 0, Score_Text.length(), SpinBlocks.SCREEN_WIDTH - 3, 32, score_outline_paint);
		c.drawText(score_chars, 0, Score_Text.length(), SpinBlocks.SCREEN_WIDTH - 3, 32, score_paint);
		
		Multiplier_Text.getChars(0, Multiplier_Text.length(), multiplier_chars, 0);
		c.drawText(multiplier_chars, 0, Multiplier_Text.length(), SpinBlocks.SCREEN_WIDTH - 3, SpinBlocks.SCREEN_HEIGHT - 5, multiplier_outline_paint);
		c.drawText(multiplier_chars, 0, Multiplier_Text.length(), SpinBlocks.SCREEN_WIDTH - 3, SpinBlocks.SCREEN_HEIGHT - 5, multiplier_paint);
		
		//Draw floating texts
		for (int i = 0; i < Texts.length; i++)
		{
			if (Texts[i].Visible)
			{
				Texts[i].Draw(c);
			}
		}
	}
	
	public void Frame_Started(float frame_time)
	{
		if (SpinBlocks.Gametype != SpinBlocks.GAMETYPE_PUZZLE)
		{
			//Deal with scores and multipliers
			if (Multiplier_Level > 0)
			{
				Multiplier_Level -= 10 * frame_time * Current_Multiplier;
				Current_Multiplier = (int)(Multiplier_Level / 200) + 1;
			}
			else
			{
				Multiplier_Level = 0;
			}
			
			if (score_to_add > 0)
			{
				float add_amount = score_to_add / 4;
				
				if (add_amount < 2)
				{
					add_amount = score_to_add;
				}
				Current_Score += add_amount;
				score_to_add -= add_amount;
				
				Multiplier_Level += add_amount / Current_Multiplier;
				if (Multiplier_Level > 2000)
				{
					Multiplier_Level = 2000;
				}
				
				SpinBlocks.UI_MANAGER.Current_Game_Time += add_amount;
			}
			else
			{
				Current_Score = target_score;
			}
			
			for (int i = 0; i < Texts.length; i++)
			{
				if (Texts[i].Visible)
				{
					Texts[i].Frame_Started(frame_time);
				}
			}
		}
		
		//Deal with Text
		Score_Text.setLength(0);
		Multiplier_Text.setLength(0);
		if (SpinBlocks.Gametype != SpinBlocks.GAMETYPE_PUZZLE)
		{
			Score_Text.append((int)Current_Score);
			Multiplier_Text.append("x " + Current_Multiplier);
		}
		else
		{
			Score_Text.append("Moves: " + (int)Current_Score);
		}
		
	}
	
	public void NotifyPointsOrbPopped(int x, int y, int points)
	{
		if (SpinBlocks.Gametype != SpinBlocks.GAMETYPE_PUZZLE)
		{
			score_to_add += points * Current_Multiplier;
			target_score = (int) (Current_Score + score_to_add);
			
			Current_Text_Index++;
			
			if (Current_Text_Index >= Texts.length)
			{
				Current_Text_Index = 0;
			}
			
			Texts[Current_Text_Index].Show(x, y, "+" + (points * Current_Multiplier), 12, 225);
		}
	}
	
	public void NotifyNextLevel()
	{
		Current_Level++;
		
		Current_Text_Index++;
		
		if (Current_Text_Index >= Texts.length)
		{
			Current_Text_Index = 0;
		}
		
		Texts[Current_Text_Index].Show(SpinBlocks.SCREEN_CENTER_X, SpinBlocks.SCREEN_CENTER_Y, "LEVEL UP!", 40, 100);
	}

	public int GetComboScore(int num_orbs)
	{
		return (int) (5 * Math.pow(2, num_orbs));
	}
}
