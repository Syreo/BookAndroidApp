package com.cycle7.bookapp.fragments;

import com.cycle7.bookapp.Book;
import com.cycle7.bookapp.EditBookActivity;
import com.cycle7.bookapp.R;
import com.cycle7.bookapp.database.DBTools;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;
import android.content.Intent;


public class ViewBookFragment extends Fragment {
	public static String BOOK_ID = "bookId";
	private TextView bookTitle;
	private TextView bookAuthor;
	private TextView bookPages;
	private RatingBar bookRating;
	private TextView bookReview;
	private CheckBox bookRead;
	private String bookId;
	private Book book;
	private DBTools dbTools;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbTools = new DBTools(getActivity());
		

	
	
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.activity_view_book, null);
		bookTitle = (TextView) v.findViewById(R.id.bookTitle);
		bookAuthor = (TextView) v.findViewById(R.id.bookAuthor);
		bookPages = (TextView) v.findViewById(R.id.bookPages);
		bookRating = (RatingBar) v.findViewById(R.id.bookRating);
		bookReview = (TextView)v.findViewById(R.id.bookReview);
		bookRead = (CheckBox)v.findViewById(R.id.bookReadCheckbox);
		Button deleteButton = (Button) v.findViewById(R.id.deleteButton);
		Button editButton = (Button)v.findViewById(R.id.editButton);
		deleteButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
			deleteBook();

			}
		});
		editButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				editBook(v);
				
			}
		});
		
		
		return v;
	}
	

	public void deleteBook() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Confirm");
		builder.setPositiveButton("Yes", null);
		builder.setNegativeButton("No", null);
		builder.setMessage("Yes");
		AlertDialog theAlertDialog = builder.create();
		theAlertDialog.show();
		dbTools.deleteBook(bookId);
		Intent theIntent = new Intent(getActivity(),
				ViewBookListFragment.class);
		startActivity(theIntent);
	}
	


	public void editBook(View view) {
		
		Intent intent = new Intent(getActivity(), EditBookActivity.class);
		intent.putExtra("bookId", bookId);
		startActivity(intent);
	}
	
	public void onResume(){
		super.onResume();
		bookId = getActivity().getIntent().getStringExtra("bookId");
		book = dbTools.getBookInfo(bookId);
		bookTitle.setText(book.getBookTitle());
		bookAuthor.setText(book.getBookAuthor());
		bookPages.setText(book.getBookPages());
		bookRating.setRating(book.getBookRating());
		bookReview.setText(book.getBookReview());
		bookRead.setChecked(book.isBookRead());
	}
	
	public static ViewBookFragment newInstance(long bookId){
		Bundle args = new Bundle();
		args.putLong(BOOK_ID, bookId);
		ViewBookFragment fragment = new ViewBookFragment();
		fragment.setArguments(args);
		return fragment;
	}

}
