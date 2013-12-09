package com.cycle7.bookapp.fragments;

import com.cycle7.bookapp.AddBookActivity;
import com.cycle7.bookapp.R;
import com.cycle7.bookapp.ViewBookListActivity;
import com.cycle7.bookapp.R.id;
import com.cycle7.bookapp.R.layout;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class HomeScreenFragment extends Fragment {
	private Button addBook;
	private Button viewBookList;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.activity_main, parent, false);
		addBook = (Button)v.findViewById(R.id.addBookButton);
		viewBookList = (Button)v.findViewById(R.id.viewBookList);
		addBook.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				addBookActivity(v);	
			}

		});
		
		viewBookList.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				viewBookListActivity(v);	
			}
		});
		
		Toast.makeText(getActivity(), "here", Toast.LENGTH_SHORT).show();
		return v;
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
	
}
