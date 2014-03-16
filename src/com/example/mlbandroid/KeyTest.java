package com.example.mlbandroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.TextView;

public class KeyTest extends Activity implements OnKeyListener {
	private StringBuilder builder = new StringBuilder();
	private TextView textView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		textView = new TextView(this);
		textView.setText("Press keys (if you have some)!");
		textView.setOnKeyListener(this);
		textView.setFocusableInTouchMode(true);
		textView.requestFocus();
		setContentView(textView);
		Log.d("stupid", "1");
	}

    public boolean onKey(View view, int keyCode, KeyEvent event) {
    	Log.d("stupid", "2");
    	builder.setLength(0);
    	switch (event.getAction()) {
    		case KeyEvent.ACTION_DOWN:
    			builder.append("down, ");
    			break ;
    		case KeyEvent.ACTION_UP:
    			builder.append("up, ");
    			break ;
    	}
    	builder.append(event.getKeyCode());
    	builder.append(", ");
    	builder.append((char) event.getUnicodeChar());
    	String text = builder.toString();
    	Log.d("KeyTest", text);
    	textView.setText(text);
	
    	return event.getKeyCode() != KeyEvent.KEYCODE_BACK;    	
    }
}