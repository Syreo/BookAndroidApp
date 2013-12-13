package com.cycle7.bookapp.fragments;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cycle7.bookapp.AddBookActivity;
import com.cycle7.bookapp.BookList;
import com.cycle7.bookapp.CustomDialog;
import com.cycle7.bookapp.R;
import com.cycle7.bookapp.ReadingLogActivity;
import com.cycle7.bookapp.ReadingTimerActivity;
import com.cycle7.bookapp.ViewBookListActivity;
import com.cycle7.bookapp.database.DBTools;


public class HomeScreenFragment extends Fragment{
	private Button addBook;
	private Button viewBookList;
	private Button readingTimer;
	private Button readingLog;
	private Button createList;
	private EditText listName;
	private Button saveButton;
	private Button cancelButton;

	private DBTools dbTools;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		dbTools = new DBTools(getActivity());
		
	}
	
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.activity_main, parent, false);
		addBook = (Button)v.findViewById(R.id.addBookButton);
		viewBookList = (Button)v.findViewById(R.id.viewBookList);
		readingTimer = (Button)v.findViewById(R.id.readingTimerButton);
		readingLog = (Button)v.findViewById(R.id.logReadingButton);
		createList = (Button)v.findViewById(R.id.createBookList);
		
		createList.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			final CustomDialog dialog = new CustomDialog();
			
			dialog.show(getFragmentManager(), "fragmentDialog");
			saveButton = (Button)v.findViewById(R.id.save_button);
			cancelButton = (Button)v.findViewById(R.id.list_cancel_button);
			listName = (EditText)v.findViewById(R.id.listNameInput);
//			cancelButton.setOnClickListener(new View.OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					dialog.dismiss();
//					
//				}
//			});
//		saveButton.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				if(listName.getText() != null){
//				try{
//					
//				dbTools.createBookList(listName.getText().toString());
//				dialog.dismiss();
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//				}else {
//					Toast.makeText(getActivity(), "Please enter a list name!", Toast.LENGTH_SHORT).show();
//				}
//			}
//		});
			}
		});
		
		
		addBook.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				addBookActivity(v);	
			}

		});
		
		readingLog.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				readingLogActivity(v);
				
			}
		});
		
		viewBookList.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				viewBookListActivity(v);	
			}
		});
		
		readingTimer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), ReadingTimerActivity.class);
				startActivity(intent);
			}
		});
		
		Toast.makeText(getActivity(), "here", Toast.LENGTH_SHORT).show();
		return v;
	}
	
	public void readingLogActivity(View view){
		Intent intent = new Intent(getActivity(), ReadingLogActivity.class);
		startActivity(intent);
	}
	
	public void addBookActivity(View view) {
		Intent intent = new Intent(getActivity(), AddBookActivity.class);
		startActivity(intent);
	}
	
//	@Override
//	public void onActivityResult(int requestCode, int resultCode, Intent data){
//		
//	}
	
	public void viewBookListActivity(View view) {
		Intent intent = new Intent(getActivity(), ViewBookListActivity.class);

		startActivity(intent);
	}
	
	public void onResume(){
		super.onResume();
		ArrayList<BookList>test = new ArrayList<BookList>();
		test = dbTools.getAllLists();
		for(int i = 0; i < test.size(); i++){
			Log.d("book", String.valueOf(test.size()));
		}
	}
	
}
