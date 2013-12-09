package com.cycle7.bookapp;

import java.util.ArrayList;
import java.util.HashMap;

import com.cycle7.bookapp.database.DBTools;
import com.cycle7.bookapp.fragments.AddBookFragment;
import com.cycle7.bookapp.fragments.ViewBookListFragment;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	DBTools dbTools = new DBTools(this);
	TextView bookId;
	Intent intent;
	private Button addBook;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addBook = (Button)findViewById(R.id.addBookButton);
		
		addBook.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View view) {
				addBookActivity(view);
				
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void addBookActivity(View view) {
		Intent intent = new Intent(getApplication(), AddBookFragment.class);
		startActivity(intent);
	}
	
//	@Override
//	public void onActivityResult(int requestCode, int resultCode, Intent data){
//		
//	}
	
	public void viewBookListActivity(View view) {
		Intent intent = new Intent(getApplication(), ViewBookListFragment.class);

		startActivity(intent);
	}
}
