/**
 * 
 */
package com.cycle7.bookapp.fragments;

import java.util.HashMap;

import com.cycle7.bookapp.Book;
import com.cycle7.bookapp.R;
import com.cycle7.bookapp.R.id;
import com.cycle7.bookapp.R.layout;
import com.cycle7.bookapp.database.DBTools;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

/**
 * @author Hyoung
 *
 */
public class AddBookFragment extends Fragment {
	
	private EditText bookTitle;
	private EditText bookAuthor;
	private EditText bookPages;
	private RatingBar bookRating;
	private DBTools dbTools;
	private Context mContext;
	private EditText bookReview;
	private CheckBox bookRead;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		dbTools = new DBTools(getActivity());
		
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.activity_add_book, null);
		
		bookTitle = (EditText)v.findViewById(R.id.bookTitle);
		bookAuthor = (EditText)v.findViewById(R.id.bookAuthor);
		bookPages = (EditText)v.findViewById(R.id.bookPages);
		bookRating = (RatingBar)v.findViewById(R.id.bookRating);
		bookReview = (EditText)v.findViewById(R.id.bookReview);
		bookRead = (CheckBox)v.findViewById(R.id.readCheckbox);
		Button addButton = (Button)v.findViewById(R.id.addBook);
		
		addButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addBook(v);
				
			}
		});
		Toast.makeText(getActivity(), "test", Toast.LENGTH_LONG).show();
		
		return v;
		
	}
	
	public void addBook(View view){
		Book book = new Book();
		book.setBookTitle(bookTitle.getText().toString());
		book.setBookAuthor(bookAuthor.getText().toString());
		book.setBookPages(bookPages.getText().toString());
		book.setBookRating(bookRating.getRating());
		book.setBookReview(bookReview.getText().toString());
		book.setBookRead(bookRead.isChecked());
		try{
		dbTools.insertBook(book);
		getActivity().finish();
		Toast.makeText(mContext, "Success!", Toast.LENGTH_SHORT).show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
