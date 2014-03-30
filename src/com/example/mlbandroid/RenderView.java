package com.example.mlbandroid;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

public class RenderView extends View {

	private int xDiff = 1;
	private int yDiff = 1;
	private Float cx = 0f;
	private Float cy = 0f;
	private boolean start = true;
	
	public RenderView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	protected void onDraw(Canvas canvas) {
        Random rand = new Random();
		//canvas.drawRGB(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        double x = Math.floor(width/2);
        double y = Math.floor(height/2);
        
        Paint paint = new Paint();
        paint.setARGB(255, 255, 255, 255);
        Float x1 = new Float(x);
        Float y1 = new Float(y);
        
		canvas.drawPoint(x1, y1, paint);
		Float x2 = x1 + 20;
		Float y2 = y1 + 100;
		canvas.drawRect(x1, y1, x2, y2, paint);
		
		if (start) {
			cx = x1 - xDiff;
			cy = y1 - yDiff;
			start = false;
		} else {
			cx = cx - xDiff;
			cy = cy - yDiff;
		}
		if (cx < 0) {
			cx = 1f;
			xDiff = -1;
			xDiff--;
		} else {
			xDiff++;
		}
		if (cy < 0) {
			cy = 1f;
			yDiff = -1;
			yDiff--;
		} else
			yDiff++;
		
		Log.d("asfd", "cx = " + cx);
        Log.d("asfd", "cy = " + cy);
        
		canvas.drawCircle(cx, cy, 10, paint);
		//Log.d("asdfds", "inside onDraw()");
		
		
		invalidate();
    }

}
