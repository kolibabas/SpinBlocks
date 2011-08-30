package com.memeworks.spinblocks;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class SoundManager {
	private SoundPool mSoundPool;
	private HashMap<Integer, Integer> mSoundPoolMap;
	public AudioManager  mAudioManager;
	public MediaPlayer mMediaPlayer;
	private Context mContext;
	
	public SoundManager(Context context) {
	    mContext = context;
	    mSoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
	    mSoundPoolMap = new HashMap<Integer, Integer>();
	    mAudioManager = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE);
	    mMediaPlayer = new MediaPlayer();
	}
	
	public void addSound(int index, int SoundID)
	{
	    mSoundPoolMap.put(index, mSoundPool.load(mContext, SoundID, 1));
	}
	
	public void playSound(int index)
	{
		if (mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC) > 0 && SpinBlocks.SOUND) {
			mSoundPool.play((Integer)mSoundPoolMap.get(index), 1, 1, 1, 0, 1f);
		}
	}
	
	public void playMusic(int index)
	{
		if (mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC) > 0) {
			
		}
	}

}
