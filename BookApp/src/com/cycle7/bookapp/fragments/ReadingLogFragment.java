package com.cycle7.bookapp.fragments;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cycle7.bookapp.R;
import com.cycle7.bookapp.ReadingLog;
import com.cycle7.bookapp.database.DBTools;

public class ReadingLogFragment extends ListFragment{
private DBTools dbTools;
private View mHeader;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		dbTools = new DBTools(getActivity());
		
	}
	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id){
		
	}
	
	public class ReadingAdapter extends ArrayAdapter<ReadingLog>{
		public ReadingAdapter(ArrayList<ReadingLog>logList){
			super(getActivity(), 0, logList);
		}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		
		
		if(convertView == null){
			convertView = getActivity().getLayoutInflater().inflate(R.layout.activity_reading_log, null);
			
		}
		
		ReadingLog log = getItem(position);
		TextView date = (TextView) convertView.findViewById(R.id.reading_date);
		TextView readingTime = (TextView) convertView.findViewById(R.id.reading_time);
	
		String readingDate = convertDate(log.getReadingDate());
		String time = convertTime(log.getReadingTime());
		date.setText(readingDate);
		readingTime.setText(time);
		
		return convertView;
	}
	}
	
	public String convertTime(long toBeConverted){
		long second = (toBeConverted / 1000) % 60;
		long minute = (toBeConverted / (1000 * 60)) % 60;
		long hour = (toBeConverted / (1000 * 60 * 60)) % 24;

		String time = String.format("%02d:%02d:%02d", hour, minute, second);
		return time;
	}
	
	public String convertDate(long toBeConverted){
		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		String dateFormatted = formatter.format(toBeConverted); 
		
		return dateFormatted;
	}
	
	
	@Override
	public void onResume(){
		super.onResume();
		ArrayList<ReadingLog>log = dbTools.getReadingLogs();	
		ReadingAdapter readingAdapter = new ReadingAdapter(log);
		mHeader = getActivity().getLayoutInflater().inflate(R.layout.log_header, null);
		if(getListView().getHeaderViewsCount() < 1){
		getListView().addHeaderView(mHeader);
		}
		setListAdapter(readingAdapter);
	}
}
