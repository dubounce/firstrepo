package com.example.mlbandroid;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

public class ExternalStorageTest extends Activity {
	
	private static int times = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);
		setContentView(textView);

		String state = Environment.getExternalStorageState();
		if (!state.equals(Environment.MEDIA_MOUNTED)) {
			textView.setText("No external storage mounted");
		} else {
			File externalDir = Environment.getExternalStorageDirectory();
			String path = externalDir.getAbsolutePath() + File.separator + "text.txt";
			Log.d("stupid", path);
			File textFile = new File(path);
			try {
				writeTextFile(textFile, "wtf " + times + "\n");
				times++;
				String text = readTextFile(textFile);
				textView.setText(text);
				/*if (!textFile.delete()) {
					textView.setText("Couldn't remove temporary file");
				}*/
			} catch (IOException e) {
				textView.setText("Something went wrong! " + e.getMessage());
			}
		}
	}

	private void writeTextFile(File file, String text) throws IOException {
		String currentText = readTextFile(file);
		currentText = currentText + text;
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(currentText);
		writer.close();
	}

	private String readTextFile(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		StringBuilder text = new StringBuilder();
		String line;
		int i = 0;
		while ((line = reader.readLine()) != null ) {
			Log.d("stupid", "------- line " + i);
			i++;
			text.append(line);
			text.append("\n");
		}
		reader.close();
		return text.toString();
	}
}
