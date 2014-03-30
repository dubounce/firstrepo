package com.example.mlbandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

public class SurfaceViewTest extends Activity {
	
	FastRenderView renderView;
    
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        renderView = new FastRenderView(this);
        setContentView(renderView);
    }
	
    protected void onResume() {
        super.onResume();
        renderView.resume();
    }
    
    protected void onPause() {
        super.onPause();
        renderView.pause();
    }
    
	class FastRenderView extends SurfaceView implements Runnable {
        Thread renderThread = null;
        SurfaceHolder holder;
        volatile boolean running = false;
        private float xCoord;
        private float yCoord;
        private int xDiff;
        private int yDiff;        
        private Direction currDirection;
        private boolean firstPass;
                
        public FastRenderView(Context context) {
            super(context);
            holder = getHolder();
            xDiff = 20;
            yDiff = 20;
            currDirection = Direction.SOUTHEAST;
            firstPass = true;
        }

        public void resume() {
            running = true;
            renderThread = new Thread(this);
            renderThread.start();
        }
        
        public void run() {
            while (running) {
                if (!holder.getSurface().isValid())
                    continue;
                Canvas canvas = holder.lockCanvas();
                canvas.drawColor(Color.BLACK);
                
                Paint paint = new Paint();
                paint.setARGB(255, 255, 255, 255);
                calculateCoords(canvas);
                canvas.drawCircle(xCoord, yCoord, 10, paint);
                
                holder.unlockCanvasAndPost(canvas);                
            }
        }
        
        
        
        public void calculateCoords(Canvas canvas) {
        	int width = canvas.getWidth();
            int height = canvas.getHeight();
            
            if (firstPass) {
        		double xBlah = Math.floor(width/2);
                double yBlah = Math.floor(height/2);
                xCoord = (float) xBlah;
                yCoord = (float) yBlah;
                firstPass = false;        		
        	} else {
        		if (currDirection.equals(Direction.NORTHEAST)) {
        			xCoord = xCoord + xDiff;
        			yCoord = yCoord - yDiff;
        		} else if (currDirection.equals(Direction.NORTHWEST)) {
        			xCoord = xCoord - xDiff;
        			yCoord = yCoord - yDiff;
        		} else if (currDirection.equals(Direction.SOUTHWEST)) {
        			xCoord = xCoord - xDiff;
        			yCoord = yCoord + yDiff;
        		} else {
        			xCoord = xCoord + xDiff;
        			yCoord = yCoord + yDiff;
        		}
        		
        		if (yCoord < 0) {        			
        			if (currDirection.equals(Direction.NORTHEAST))
        				currDirection = Direction.SOUTHEAST;
        			else if (currDirection.equals(Direction.NORTHWEST)) 
        				currDirection = Direction.SOUTHWEST;
        			yCoord = 0;
        		} else if (xCoord > width) {
        			if (currDirection.equals(Direction.NORTHEAST))
        				currDirection = Direction.NORTHWEST;
        			else if (currDirection.equals(Direction.SOUTHEAST)) 
        				currDirection = Direction.SOUTHWEST;
        			xCoord = width;
        		} else if (yCoord > height) {
        			if (currDirection.equals(Direction.SOUTHEAST))
        				currDirection = Direction.NORTHEAST;
        			else if (currDirection.equals(Direction.SOUTHWEST)) 
        				currDirection = Direction.NORTHWEST;
        			yCoord = height;
        		} else if (xCoord < 0) {
        			if (currDirection.equals(Direction.NORTHWEST))
        				currDirection = Direction.NORTHEAST;
        			else if (currDirection.equals(Direction.SOUTHWEST))
        				currDirection = Direction.SOUTHEAST;
        			xCoord = 0;
        		}
        	}
        }
        
		public void pause() {
            running = false;
            while (true) {
                try {
                    renderThread.join();
                    return;
                } catch (InterruptedException e) {
                    // retry
                }
            }
        }
    }
}
