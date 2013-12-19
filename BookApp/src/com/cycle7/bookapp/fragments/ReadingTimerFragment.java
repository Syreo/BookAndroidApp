package com.cycle7.bookapp.fragments;

import java.util.Date;

import com.cycle7.bookapp.R;
import com.cycle7.bookapp.ReadingLog;
import com.cycle7.bookapp.database.DBTools;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ReadingTimerFragment extends Fragment{
	
	private Button timerButton;
	private TextView timerTextView;
	private Button clearButton;
	private Button saveButton;
	private DBTools dbTools;
	    long startTime = 0;

	    //runs without a timer by reposting this handler at the end of the runnable
	    Handler timerHandler = new Handler();
	    Runnable timerRunnable = new Runnable() {

	        @Override
	        public void run() {
	            long millis = System.currentTimeMillis() - startTime;
	            int seconds = (int) (millis / 1000);
	            int minutes = seconds / 60;
	            int hours = minutes / 60;
	            seconds = seconds % 60;

	            timerTextView.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));

	            timerHandler.postDelayed(this, 500);
	        }
	    };

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        dbTools = new DBTools(getActivity());

	    }
	    
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
	    	View v = inflater.inflate(R.layout.activity_reading_timer, null);
	        timerTextView = (TextView)v.findViewById(R.id.timerScreen);
	        clearButton = (Button)v.findViewById(R.id.clearButton);
	        timerButton = (Button)v.findViewById(R.id.timerButton);
	        saveButton = (Button)v.findViewById(R.id.timer_save_button);
	        timerButton.setText("Start");
	        timerButton.setOnClickListener(new View.OnClickListener() {

	            @Override
	            public void onClick(View v) {
	               
	                if (timerButton.getText().equals("Stop")) {
	                	
	                    startTime = System.currentTimeMillis() - startTime;
	                    timerHandler.removeCallbacks(timerRunnable);
	                    timerButton.setText("Start");
	                } else {
	                	startTime = System.currentTimeMillis() - startTime;
	                    timerHandler.postDelayed(timerRunnable, 0);
	                    timerButton.setText("Stop");
	                }
	            }
	        });
	        
	        clearButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					clearTimer();
				
				}
			});
	        
	        saveButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					saveTime();
					
				}
			});
	        
			return v;
	    }

	  @Override
	    public void onPause() {
	        super.onPause();
//	        timerHandler.removeCallbacks(timerRunnable);
//	        timerButton.setText("start");
	    }
	  
	  
	  public void clearTimer(){
			startTime = 0;
			timerHandler.removeCallbacks(timerRunnable);
			if(timerButton.getText().equals("Stop")){
				timerButton.setText("Start");
			}
			timerTextView.setText("00:00:00");
	  }
	  
	  public void saveTime(){
		  ReadingLog log = new ReadingLog();
		  
		  log.setReadingTime(startTime);
		  Date date = new Date();
		  log.setReadingDate(date.getTime());
		  
		  try{
			  dbTools.insertTime(log);
			  Toast.makeText(getActivity(), "Time saved successfully!", Toast.LENGTH_SHORT).show();
		  }catch(Exception e){
			  Toast.makeText(getActivity(), "Could not save time!", Toast.LENGTH_SHORT).show();
		  }
		  
	  }
}
