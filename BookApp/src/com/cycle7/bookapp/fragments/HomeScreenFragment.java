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
import android.widget.ImageButton;
import com.cycle7.bookapp.AddBookActivity;
import com.cycle7.bookapp.BookList;
import com.cycle7.bookapp.CustomDialog;
import com.cycle7.bookapp.R;
import com.cycle7.bookapp.ReadingLogActivity;
import com.cycle7.bookapp.ReadingTimerActivity;
import com.cycle7.bookapp.TabClass;
import com.cycle7.bookapp.ViewBookListActivity;
import com.cycle7.bookapp.ViewListActivity;
import com.cycle7.bookapp.database.DBTools;


public class HomeScreenFragment extends Fragment{
	private ImageButton addBook;
	private ImageButton viewAllBooks;
	private ImageButton readingTimer;
	private ImageButton readingLog;
	private ImageButton createList;
	private ImageButton viewListButton;
	private DBTools dbTools;
	private ImageButton addToListButton;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		dbTools = new DBTools(getActivity());
		
	}
	
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.activity_main, parent, false);
		addBook = (ImageButton)v.findViewById(R.id.addBookButton);
		viewAllBooks = (ImageButton)v.findViewById(R.id.viewAllBooks);
		readingTimer = (ImageButton)v.findViewById(R.id.readingTimerButton);
		readingLog = (ImageButton)v.findViewById(R.id.logReadingButton);
		createList = (ImageButton)v.findViewById(R.id.createBookList);
		viewListButton = (ImageButton)v.findViewById(R.id.viewAllLists);
		addToListButton = (ImageButton)v.findViewById(R.id.addBookToListButton);
		createList.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			final CustomDialog dialog = new CustomDialog();
			
			dialog.show(getFragmentManager(), "fragmentDialog");
			}
		});
		
		viewListButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), ViewListActivity.class);
				startActivity(intent);
				
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
		
		viewAllBooks.setOnClickListener(new View.OnClickListener() {
			
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
		addToListButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), TabClass.class);
				startActivity(intent);
				
			}
		});
		
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
