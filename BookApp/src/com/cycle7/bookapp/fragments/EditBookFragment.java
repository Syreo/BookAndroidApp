package com.cycle7.bookapp.fragments;

import com.cycle7.bookapp.Book;
import com.cycle7.bookapp.MainActivity;
import com.cycle7.bookapp.R;
import com.cycle7.bookapp.R.id;
import com.cycle7.bookapp.R.layout;
import com.cycle7.bookapp.database.DBTools;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class EditBookFragment extends Fragment {

	private EditText bookTitle;
	private EditText bookAuthor;
	private EditText bookPages;
	private RatingBar bookRating;
	private String bookId;
	private DBTools dbTools;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbTools = new DBTools(getActivity());
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.activity_edit_book, null);
		
		bookTitle = (EditText) v.findViewById(R.id.bookTitle);
		bookAuthor = (EditText) v.findViewById(R.id.bookAuthor);
		bookPages = (EditText) v.findViewById(R.id.bookPages);
		bookRating = (RatingBar) v.findViewById(R.id.bookRating);
		Button saveButton = (Button)v.findViewById(R.id.saveButton);
		Intent theIntent = getActivity().getIntent();
		bookId = theIntent.getStringExtra("bookId");
		saveButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				saveBook();
				
			}
		});
	
		return v;
		
	}

//	public void editBook(View view) {
//		Book book = new Book();
//
//		book.setBookId(Long.parseLong((bookId)));
//		bookTitle = (EditText) findViewById(R.id.bookTitle);
//		bookAuthor = (EditText) findViewById(R.id.bookAuthor);
//		bookPages = (EditText) findViewById(R.id.bookPages);
//		bookRating = (RatingBar) findViewById(R.id.bookRating);
//
//		
//		book.setBookTitle(bookTitle.getText().toString());
//		book.setBookAuthor(bookAuthor.getText().toString());
//		book.setBookPages(bookPages.getText().toString());
//		book.setBookRating(bookRating.getRating());
//		
//
//		this.goHome(view);
//
//	}
	
	public void saveBook(){
		Book book = new Book();
		book.setBookId(Long.parseLong(bookId));
		book.setBookTitle(bookTitle.getText().toString());
		book.setBookAuthor(bookAuthor.getText().toString());
		book.setBookPages(bookPages.getText().toString());
		book.setBookRating(bookRating.getRating());
		Log.d("book", bookId);
		Log.d("book", bookTitle.getText().toString());
		Log.d("book", bookAuthor.getText().toString());
		try{
		dbTools.updateBook(book);
		getActivity().finish();
		}catch(Exception e){
			Toast.makeText(getActivity(), "Could not save entry", Toast.LENGTH_LONG).show();
		}
	}

	public void goHome(View view) {
		Intent intent = new Intent(getActivity(), MainActivity.class);
		startActivity(intent);
	}
	
	@Override
	public void onResume(){
		super.onResume();
		Book book = dbTools.getBookInfo(bookId);

		bookTitle.setText(book.getBookTitle());
		bookAuthor.setText(book.getBookAuthor());
		bookPages.setText(book.getBookPages());
		bookRating.setRating(book.getBookRating());
		
	}

}
