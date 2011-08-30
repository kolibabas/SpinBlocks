package com.memeworks.spinblocks;

import com.memeworks.spinblocks.R;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class MainView extends SurfaceView implements SurfaceHolder.Callback {

	class MainThread extends Thread {
		
        /** State-tracking constants */
		public int State = SpinBlocks.STATE_GAMEOVER;
		public boolean Finishing = false;

		//** Pointer to the Game Activity */
		private Game game_pointer;

        /** Handle to the surface manager object we interact with */
        private SurfaceHolder surface_holder;
        
        /** Indicate whether the surface has been created & is ready to draw */
        private boolean running = false;
        
        // Game Variables
        private long last_frame_time = 0;
        private float time_since_last_frame = 0;
        private Point Drag_Start = null;
        private Point Drag_End = null;
        private int Down_Grid_X = 0;
        @SuppressWarnings("unused")
		private int Down_Grid_Y = 0;
        @SuppressWarnings("unused")
		private int launcher_selected = SpinBlocks.DIRECTION_NONE;
        private FloatText overlay_text;
        private FloatText overlay_reason_text;
        private float game_over_timer = 2.5f;
 
		public MainThread(SurfaceHolder holder, Context context, Handler handler) {
            surface_holder = holder;

            // Load Resources here
            Resources res = context.getResources();
            
            SpinBlocks.BITMAP_ORB_CORE = BitmapFactory.decodeResource(res, R.drawable.orbcore);
            SpinBlocks.BITMAP_ORB_RED = BitmapFactory.decodeResource(res, R.drawable.orbred);
            SpinBlocks.BITMAP_ORB_GREEN = BitmapFactory.decodeResource(res, R.drawable.orbgreen);
            SpinBlocks.BITMAP_ORB_BLUE = BitmapFactory.decodeResource(res, R.drawable.orbblue);
            SpinBlocks.BITMAP_ORB_ORANGE = BitmapFactory.decodeResource(res, R.drawable.orborange);
            SpinBlocks.BITMAP_ORB_PURPLE = BitmapFactory.decodeResource(res, R.drawable.orbpurple);      
            SpinBlocks.BITMAP_ORB_LIME = BitmapFactory.decodeResource(res, R.drawable.orblime);      
            SpinBlocks.BITMAP_ORB_GRAY = BitmapFactory.decodeResource(res, R.drawable.orbgray);   
            
            SpinBlocks.BITMAP_ORB_BOMB = BitmapFactory.decodeResource(res, R.drawable.orbbomb);
            SpinBlocks.BITMAP_ORB_WILDCARD = BitmapFactory.decodeResource(res, R.drawable.orbwildcard);   
            
            SpinBlocks.BITMAP_LAUNCHER_TOP = BitmapFactory.decodeResource(res, R.drawable.launchertop);
            SpinBlocks.BITMAP_LAUNCHER_BOTTOM = BitmapFactory.decodeResource(res, R.drawable.launcherbottom);
            
            SpinBlocks.BITMAP_ARROW_LEFT = BitmapFactory.decodeResource(res, R.drawable.arrowleft);
            SpinBlocks.BITMAP_ARROW_RIGHT = BitmapFactory.decodeResource(res, R.drawable.arrowright);
            
            SpinBlocks.BITMAP_FRAME_LEFT = BitmapFactory.decodeResource(res, R.drawable.frameleft);
            SpinBlocks.BITMAP_FRAME_RIGHT = BitmapFactory.decodeResource(res, R.drawable.frameright);
            
            SpinBlocks.BITMAP_BUTTON_SOUND_OFF = BitmapFactory.decodeResource(res, R.drawable.gamesoundoff);
            SpinBlocks.BITMAP_BUTTON_SOUND_ON = BitmapFactory.decodeResource(res, R.drawable.gamesoundon);
            SpinBlocks.BITMAP_BUTTON_PAUSE = BitmapFactory.decodeResource(res, R.drawable.gamepause);
            SpinBlocks.BITMAP_BUTTON_MENU = BitmapFactory.decodeResource(res, R.drawable.gamebackmenu);
            SpinBlocks.BITMAP_BUTTON_RESTART = BitmapFactory.decodeResource(res, R.drawable.gamerestart);
            
            SpinBlocks.GRID_CELL_SIZE = SpinBlocks.BITMAP_ORB_CORE.getWidth();

            SpinBlocks.SOUND_MANAGER = new SoundManager(context);
            
            SpinBlocks.SOUND_MANAGER.addSound(SpinBlocks.SOUND_BLOCK_POP, R.raw.blockpop);
            SpinBlocks.SOUND_MANAGER.addSound(SpinBlocks.SOUND_BLOCK_ATTACH, R.raw.blockattach);
            SpinBlocks.SOUND_MANAGER.addSound(SpinBlocks.SOUND_ORB_GRID_ROTATE, R.raw.orbgridrotate);
            SpinBlocks.SOUND_MANAGER.addSound(SpinBlocks.SOUND_LAUNCHER_MOVE, R.raw.launchermove);
            SpinBlocks.SOUND_MANAGER.addSound(SpinBlocks.SOUND_LAUNCHER_FIRE, R.raw.launcherfire);
            SpinBlocks.SOUND_MANAGER.addSound(SpinBlocks.SOUND_BOMB_EXPLODE, R.raw.blockexplode);
            SpinBlocks.SOUND_MANAGER.addSound(SpinBlocks.SOUND_LEVEL_UP, R.raw.levelup);
            SpinBlocks.SOUND_MANAGER.addSound(SpinBlocks.SOUND_GAME_OVER, R.raw.gameover);
		}
		

		public void setRunning(boolean b) {
			running = b;
		}
		
		public void pause() {
			synchronized (surface_holder) {
                if (State == SpinBlocks.STATE_RUNNING) {
                	State = SpinBlocks.STATE_PAUSE;
                }
            }
		}
		
		public void unPause() {
			synchronized (surface_holder) {
				if (State == SpinBlocks.STATE_PAUSE) {
                	State = SpinBlocks.STATE_RUNNING;
                	last_frame_time = System.currentTimeMillis();
                }
            }
		}
        
        public void setGamePointer(Game game) {
        	game_pointer = game;
        }
        
        @Override
        public void run() {
            while (running) {
            	
                Canvas c = null;
                try {
                	synchronized(TouchEventMutex) {
                		TouchEventMutex.notify();
                	}
                	Thread.yield();
                	
                    c = surface_holder.lockCanvas();
                    synchronized (surface_holder) {
                        if (State == SpinBlocks.STATE_RUNNING) {
                        	FrameStarted();
                        	Draw(c);
                        }
                        else if (State == SpinBlocks.STATE_PAUSE){
                        	drawPaused(c);
                        }
                        else if (State == SpinBlocks.STATE_GAMEOVER){
                        	FrameStarted();
                        	drawGameOver(c);
                        }
                        updateState();                        
                    }
                }
                catch (Exception e) {
                	StackTraceElement[] stack = e.getStackTrace();
                	
                	for (int i = 0; i < stack.length; i++)
                	{
                		Log.e("Exception", stack[i].toString());
                	}
                }  
                finally {
                    if (c != null) {
                        surface_holder.unlockCanvasAndPost(c);
                    }
                }
            }
        }
        
        private void updateState() {
			if (State == SpinBlocks.STATE_GAMEOVER && game_over_timer <= 0 && !Finishing){
				Finishing = true;
				Intent gameIntent;
				
				if (SpinBlocks.Gametype != SpinBlocks.GAMETYPE_PUZZLE)
				{
					gameIntent = new Intent(game_pointer, MainMenu.class);
				}
				else
				{
					gameIntent = new Intent(game_pointer, PuzzleSelector.class);
				}
	        	game_pointer.startActivity(gameIntent);
	        	game_pointer.finish();
			}
		}
        
        private void drawPaused(Canvas c) {
        	overlay_text = new FloatText();
        	overlay_text.Show(SpinBlocks.SCREEN_CENTER_X, SpinBlocks.SCREEN_CENTER_Y, "PAUSED", 40, 0);
        	overlay_text.Draw(c);        	
        }
        
        private void drawGameOver(Canvas c) {
        	String game_over_string = "GAME OVER";
        		
        	if (SpinBlocks.Gametype == SpinBlocks.GAMETYPE_PUZZLE && SpinBlocks.Game_Over_Reason.contains("Move"))
        	{
        		game_over_string = "LEVEL COMPLETE";
        	}
        	else if (SpinBlocks.Gametype == SpinBlocks.GAMETYPE_PUZZLE)
        	{
        		game_over_string = "LEVEL FAILED";
        	}
        	
        	overlay_text = new FloatText();
        	overlay_text.Show(SpinBlocks.SCREEN_CENTER_X, SpinBlocks.SCREEN_CENTER_Y, game_over_string, 40, 0);
        	
        	overlay_reason_text = new FloatText();
        	overlay_reason_text.Show(SpinBlocks.SCREEN_CENTER_X, SpinBlocks.SCREEN_CENTER_Y + 40, SpinBlocks.Game_Over_Reason, 30, 0);
        	
        	overlay_text.Draw(c);   
        	overlay_reason_text.Draw(c);
        }

		private void Draw(Canvas c) {
			c.drawColor(0, PorterDuff.Mode.CLEAR);
			
			SpinBlocks.ORB_GRID.Draw(c);
			SpinBlocks.UI_MANAGER.Draw(c);
			SpinBlocks.GAMETYPE.Draw(c);
		}

		/**
         * Starts the game, sets parameters for the current game type.
         */
        public void doStart() {
        	
        	SpinBlocks.ORB_GRID = new OrbGrid();
        	SpinBlocks.UI_MANAGER = new UIManager();
        	SpinBlocks.GAMETYPE = new Gametype();
        	
        	game_over_timer = 2.5f;
        	SpinBlocks.LAST_SCORE = -1;
        	SpinBlocks.LAST_PUZZLE_RESULT = SpinBlocks.PUZZLE_INCOMPLETE;
        	Finishing = false;
        	last_frame_time = System.currentTimeMillis() + 100;
        	State = SpinBlocks.STATE_RUNNING;
        	
			SpinBlocks.Can_Launch = true;
			SpinBlocks.Can_Rotate = true;
			SpinBlocks.Orb_In_Air = false;
        }
        
        /**
         * Handles pretty much all of the game play 
         */
        private void FrameStarted() {
        	synchronized (surface_holder) {
	        	//Calculate frame time
	            long now = System.currentTimeMillis();
	            if (last_frame_time > now) return;
	            time_since_last_frame = (now - last_frame_time) / 1000.0f;
	            last_frame_time = now;
	
	            if (State != SpinBlocks.STATE_GAMEOVER)
	            {
	            	SpinBlocks.GAMETYPE.Frame_Started(time_since_last_frame);
	            	SpinBlocks.ORB_GRID.Frame_Started(time_since_last_frame);
	            	SpinBlocks.UI_MANAGER.Frame_Started(time_since_last_frame);
	            }
	            else
	            {
	            	game_over_timer -= time_since_last_frame;
	            }
        	}
        }

		//Input Handlers	
		public boolean doKeyDown(int keyCode, KeyEvent msg) {
			switch (keyCode) {
			case KeyEvent.KEYCODE_MENU:
				if (State == SpinBlocks.STATE_RUNNING) {
					this.pause();
				}
				else if (State == SpinBlocks.STATE_PAUSE) {
					this.unPause();
				}
				break;
				case KeyEvent.KEYCODE_VOLUME_UP :
		            SpinBlocks.SOUND_MANAGER.mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
		                                             AudioManager.ADJUST_RAISE, 
		                                             AudioManager.FLAG_SHOW_UI);
		            break;
		        case KeyEvent.KEYCODE_VOLUME_DOWN:
		        	SpinBlocks.SOUND_MANAGER.mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, 
		                                             AudioManager.ADJUST_LOWER, 
		                                             AudioManager.FLAG_SHOW_UI);
		            break;
		            
		        //Debug keys
		            /*
		        case KeyEvent.KEYCODE_L:
		        	SpinBlocks.ORB_GRID.CreateRandomLauncherOrb(SpinBlocks.DIRECTION_UP);
		        	break;
		        	
		        case KeyEvent.KEYCODE_P:
		        	SpinBlocks.ORB_GRID.CreateRandomLauncherOrb(SpinBlocks.DIRECTION_DOWN);
		        	break;
		        	
		        case KeyEvent.KEYCODE_Q:
		        	SpinBlocks.ORB_GRID.Rotate(SpinBlocks.ROTATE_LEFT);
		        	break;
		        	
		        case KeyEvent.KEYCODE_W:
		        	SpinBlocks.ORB_GRID.Rotate(SpinBlocks.ROTATE_RIGHT);
		        	break;
		        		*/
		            
				default: 
					break;
			}
			return false;
		}

		public boolean doKeyUp(int keyCode, KeyEvent msg) {
			switch (keyCode) {
			case KeyEvent.KEYCODE_VOLUME_UP :
				SpinBlocks.SOUND_MANAGER.mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, 
	                                             AudioManager.ADJUST_SAME, 
	                                             AudioManager.FLAG_SHOW_UI);
	            break;
	        case KeyEvent.KEYCODE_VOLUME_DOWN:
	        	SpinBlocks.SOUND_MANAGER.mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, 
	                                             AudioManager.ADJUST_SAME, 
	                                             AudioManager.FLAG_SHOW_UI);
	            break;
			}
				
			return true;
		}

		public boolean doTouchEvent(MotionEvent evt) {

			switch(evt.getAction())
			{
			case MotionEvent.ACTION_MOVE:
				if (evt.getY() < SpinBlocks.BITMAP_LAUNCHER_TOP.getHeight() * 1.3 && Down_Grid_X != SpinBlocks.World_To_Grid_X((int)evt.getX()))
				{
					//Top Launcher
					SpinBlocks.ORB_GRID.Launcher_Top.Move(evt);
				}
				else if (evt.getY() > SpinBlocks.SCREEN_HEIGHT - SpinBlocks.BITMAP_LAUNCHER_TOP.getHeight() * 1.3 && 
						Down_Grid_X != SpinBlocks.World_To_Grid_X((int)evt.getX()) &&
						evt.getX() > SpinBlocks.UI_MANAGER.Menu_Button_Rect.right)
				{
					//Bottom Launcher
					SpinBlocks.ORB_GRID.Launcher_Bottom.Move(evt);
				}
				break;
			case MotionEvent.ACTION_DOWN:
				Down_Grid_X = SpinBlocks.World_To_Grid_X((int)evt.getX());
				Down_Grid_Y = SpinBlocks.World_To_Grid_Y((int)evt.getY());
				
				if (evt.getY() > SpinBlocks.BITMAP_LAUNCHER_TOP.getHeight() * 1.3 &&
						evt.getY() < SpinBlocks.SCREEN_HEIGHT - SpinBlocks.BITMAP_LAUNCHER_TOP.getHeight() * 1.3)
				{
					Drag_Start = new Point((int)evt.getX(), (int)evt.getY()); 
				}
				break;
				
			case MotionEvent.ACTION_UP:
				//Grid Rotation
				if (Drag_Start != null)
				{
					Drag_End = new Point((int)evt.getX(), (int)evt.getY()); 
					
					int direction = SpinBlocks.DIRECTION_NONE;
					
					int dx = Drag_End.x - Drag_Start.x;
					int dy = Drag_End.y - Drag_Start.y;
					int dx_abs = Math.abs(dx);
					int dy_abs = Math.abs(dy);
					
					//Determine major direction of swipe
					if (dx > 0 && dx_abs > dy_abs)
					{
						direction = SpinBlocks.DIRECTION_RIGHT;
					}
					else if (dx < 0 && dx_abs > dy_abs)
					{
						direction = SpinBlocks.DIRECTION_LEFT;
					}
					else if (dy > 0 && dy_abs > dx_abs)
					{
						direction = SpinBlocks.DIRECTION_DOWN;
					}
					else if (dy < 0 && dy_abs > dx_abs)
					{
						direction = SpinBlocks.DIRECTION_UP;
					}
					
					//Do rotation based on origin and direction
					if  (
							(Drag_Start.x > SpinBlocks.CENTER_CORE_X && direction == SpinBlocks.DIRECTION_DOWN) ||
							(Drag_Start.x < SpinBlocks.CENTER_CORE_X && direction == SpinBlocks.DIRECTION_UP) ||
							(Drag_Start.y > SpinBlocks.CENTER_CORE_Y && direction == SpinBlocks.DIRECTION_LEFT) ||
							(Drag_Start.y < SpinBlocks.CENTER_CORE_Y && direction == SpinBlocks.DIRECTION_RIGHT)
						)
					{
						SpinBlocks.ORB_GRID.Rotate(SpinBlocks.ROTATE_RIGHT);
					}
					else
					{
						SpinBlocks.ORB_GRID.Rotate(SpinBlocks.ROTATE_LEFT);
					}
					
				}
				
				Rect touch_rect = new Rect((int)evt.getX() - 12, (int)evt.getY() - 12, (int)evt.getX() + 12, (int)evt.getY() + 12);
				
				if (SpinBlocks.Gametype == SpinBlocks.GAMETYPE_PUZZLE && Rect.intersects(SpinBlocks.UI_MANAGER.Restart_Button_Rect, touch_rect))
				{
					SpinBlocks.Current_Puzzle = PuzzleList.Puzzles[SpinBlocks.Puzzle_Level];
	        		SpinBlocks.Current_Puzzle.Reset();
					SpinBlocks.ORB_GRID = new OrbGrid();
					SpinBlocks.UI_MANAGER.Current_Game_Time = SpinBlocks.UI_MANAGER.Max_Game_Time;
					SpinBlocks.UI_MANAGER.Score_Keeper.Current_Score = 0;
					SpinBlocks.GAMETYPE = new Gametype();
					
					SpinBlocks.Can_Launch = true;
					SpinBlocks.Can_Rotate = true;
					SpinBlocks.Orb_In_Air = false;
				}
				else if (Rect.intersects(SpinBlocks.UI_MANAGER.Menu_Button_Rect, touch_rect))
				{
		        	this.pause();
		        	Intent gameIntent = new Intent(game_pointer, MainMenu.class);
		        	game_pointer.startActivity(gameIntent);
		        	game_pointer.finish();
				}
				else if (Rect.intersects(SpinBlocks.UI_MANAGER.Pause_Toggle_Rect, touch_rect))
				{
					if(this.State == SpinBlocks.STATE_PAUSE)
					{
						this.unPause();
					}
					else if (this.State == SpinBlocks.STATE_RUNNING)
					{
						this.pause();
					}
				}
				else if (Rect.intersects(SpinBlocks.UI_MANAGER.Sound_Toggle_Rect, touch_rect))
				{
					SpinBlocks.SOUND = !SpinBlocks.SOUND;
				}
				else if (evt.getY() < SpinBlocks.BITMAP_LAUNCHER_TOP.getHeight() * 1.3 && Down_Grid_X == SpinBlocks.World_To_Grid_X((int)evt.getX()))
				{
					//Top Launcher
					if (evt.getX() > SpinBlocks.ORB_GRID.Launcher_Top.Position_World_X - (SpinBlocks.BITMAP_LAUNCHER_TOP.getWidth() / 2) && evt.getX() < SpinBlocks.ORB_GRID.Launcher_Top.Position_World_X + (SpinBlocks.BITMAP_LAUNCHER_TOP.getWidth() / 2))
					{
						SpinBlocks.ORB_GRID.Launcher_Top.Launch();
					}
					else if (evt.getX() < SpinBlocks.ORB_GRID.Launcher_Top.Position_World_X)
					{
						SpinBlocks.ORB_GRID.Launcher_Top.Move(SpinBlocks.DIRECTION_LEFT);
					}
					else
					{
						SpinBlocks.ORB_GRID.Launcher_Top.Move(SpinBlocks.DIRECTION_RIGHT);
					}
				}
				else if (evt.getY() > SpinBlocks.SCREEN_HEIGHT - SpinBlocks.BITMAP_LAUNCHER_TOP.getHeight() * 1.3 && Down_Grid_X == SpinBlocks.World_To_Grid_X((int)evt.getX()))
				{
					//Bottom Launcher
					if (evt.getX() > SpinBlocks.ORB_GRID.Launcher_Bottom.Position_World_X - (SpinBlocks.BITMAP_LAUNCHER_BOTTOM.getWidth() / 2) && evt.getX() < SpinBlocks.ORB_GRID.Launcher_Bottom.Position_World_X + (SpinBlocks.BITMAP_LAUNCHER_BOTTOM.getWidth() / 2))
					{
						SpinBlocks.ORB_GRID.Launcher_Bottom.Launch();
					}
					else if (evt.getX() < SpinBlocks.ORB_GRID.Launcher_Bottom.Position_World_X)
					{
						SpinBlocks.ORB_GRID.Launcher_Bottom.Move(SpinBlocks.DIRECTION_LEFT);
					}
					else
					{
						SpinBlocks.ORB_GRID.Launcher_Bottom.Move(SpinBlocks.DIRECTION_RIGHT);
					}
				}
				Drag_End = null;
				Drag_Start = null;
				launcher_selected = SpinBlocks.DIRECTION_NONE;
				break;
			}
			return true;
		}
		
		public boolean doTrackballEvent(MotionEvent evt) {

			return true;
		} 

		/* Callback invoked when the surface dimensions change. */
		public void setSurfaceSize(int width, int height) {
            synchronized (surface_holder) {
       
                SpinBlocks.SCREEN_WIDTH = width;
                SpinBlocks.SCREEN_HEIGHT = height;
                SpinBlocks.SCREEN_CENTER_X = width / 2;
                SpinBlocks.SCREEN_CENTER_Y = height / 2;
                
                //Scale Background Image
                /*if (mBackgroundImage.getWidth() < (width * 1.5) || mBackgroundImage.getHeight() < (height * 1.5)) {
                	int bWidth = mBackgroundImage.getWidth();
                	int bHeight = mBackgroundImage.getHeight();
                	int newWidth = (int) (width * 1.5);
                	int newHeight = (int) (height * 1.5);
                	
                	float scaleWidth = ((float) newWidth) / bWidth;
                    float scaleHeight = ((float) newHeight) / bHeight; 
                    
                    Matrix matrix = new Matrix();
                    matrix.postScale(scaleWidth, scaleHeight);
                    mBackgroundImage = Bitmap.createBitmap(mBackgroundImage, 0, 0, bWidth, bHeight, matrix, true);
                } */

                State = SpinBlocks.STATE_READY;
                doStart();
            }
		}

    };
    
    /** The thread that actually draws the animation */
    private MainThread thread;
    
    /** Mutex object for touch thread */
    private Object TouchEventMutex = new Object();
    
    public MainView(Context context, AttributeSet attrs) {
		super(context, attrs);

        // register our interest in hearing about changes to our surface
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        // create thread only; it's started in surfaceCreated()
        thread = new MainThread(holder, context, new Handler() {
        	@Override
            public void handleMessage(Message m) {
        		
            }
        });

        // make sure we get key events
        setFocusableInTouchMode(true);
        setFocusable(true); 
	}
 
    
    /**
     * Fetches the animation thread corresponding to this MainView.
     * 
     * @return the animation thread
     */
    public MainThread getThread() {
        return thread;
    }
    
    /**
     * Standard override to get key-press events.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent msg) {
        return thread.doKeyDown(keyCode, msg);
    }

    /**
     * Standard override for key-up.
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent msg) {
        return thread.doKeyUp(keyCode, msg);
    }
    
    /** Standard override for trackball events
     * 
     */
    @Override
    public boolean onTrackballEvent(MotionEvent evt) {
    	return thread.doTrackballEvent(evt);
    } 
    
    /**
     * Standard override for touch events
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent evt) {
    	thread.doTouchEvent(evt);
    	
    	synchronized(TouchEventMutex) {
    		try {
    			TouchEventMutex.wait(1000L);
    		}
    		catch (InterruptedException e) {}
    	}
    	return true;
    }

    
    /**
     * Standard window-focus override. Notice focus lost so we can pause on
     * focus lost. e.g. user switches to take a call.
     */
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (!hasWindowFocus) {
        	thread.pause();
        	
        	if (thread.State != SpinBlocks.STATE_GAMEOVER && !thread.Finishing) {
        		thread.Finishing = true;
	        	Intent gameIntent = new Intent(thread.game_pointer, MainMenu.class);
	        	thread.game_pointer.startActivity(gameIntent);
	        	thread.game_pointer.finish();
        	}
        }
    }

    
    /* Callback invoked when the surface dimensions change. */
	public void surfaceChanged(SurfaceHolder arg0, int format, int width, int height) {
		thread.setSurfaceSize(width, height);
	}

	
	/*
     * Callback invoked when the Surface has been created and is ready to be
     * used.
     */
	public void surfaceCreated(SurfaceHolder holder) {
		// Start the thread here so that we don't busy-wait in run()
        // Waiting for the surface to be created
		if (thread.State != SpinBlocks.STATE_PAUSE) {
			thread.setRunning(true);
    		thread.start();
		}
	}

	
    /*
     * Callback invoked when the Surface has been destroyed and must no longer
     * be touched. WARNING: after this method returns, the Surface/Canvas must
     * never be touched again!
     */
	public void surfaceDestroyed(SurfaceHolder holder) {
		// Tell thread to shut down & wait for it to finish
        boolean retry = true;
        thread.setRunning(false);
        
        while (retry) {
            try {
                thread.join();
                retry = false;
            } 
            catch (InterruptedException e) {
            }
        }
	}
}

