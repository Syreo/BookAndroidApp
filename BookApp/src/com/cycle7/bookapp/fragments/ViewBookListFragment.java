package com.cycle7.bookapp.fragments;

import java.util.ArrayList;
import java.util.HashMap;

import com.cycle7.bookapp.Book;
import com.cycle7.bookapp.R;
import com.cycle7.bookapp.ViewBookActivity;
import com.cycle7.bookapp.R.id;
import com.cycle7.bookapp.R.layout;
import com.cycle7.bookapp.database.DBTools;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ViewBookListFragment extends ListFragment {
	

	private TextView bookId;
	private Intent intent;
	private String bookIdValue;
	private Context mContext;
	private ArrayList<Book>mBook;
	private DBTools dbTools;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbTools = new DBTools(getActivity());
	
	}
	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id){
		Book b = ((BookAdapter)getListAdapter()).getItem(position);
		bookId = (TextView)v.findViewById(R.id.bookId);
		bookIdValue = bookId.getText().toString();
		Intent intent = new Intent(getActivity(), ViewBookActivity.class); //change view book to fragment
		intent.putExtra("bookId", bookIdValue);
		startActivity(intent);
	}
	public class BookAdapter extends ArrayAdapter<Book>{
		public BookAdapter(ArrayList<Book>books){
			super(getActivity(), 0, books);
		}
	
	
	@Override
	public View getView(int position,  View convertView, ViewGroup parent){
		if(convertView == null){
			convertView = getActivity().getLayoutInflater().inflate(R.layout.view_book_list_layout, null);
			
		}
		Book b = getItem(position);
		TextView bookTitle = (TextView)convertView.findViewById(R.id.bookTitle);
		bookTitle.setText(b.getBookTitle());
		TextView bookAuthor = (TextView)convertView.findViewById(R.id.bookAuthor);
		bookAuthor.setText(b.getBookAuthor());
		TextView bookId = (TextView)convertView.findViewById(R.id.bookId);
		bookId.setText(String.valueOf(b.getBookId()));
	return convertView;
	}
	}
	
	@Override
	public void onResume(){
		super.onResume();
		ArrayList<Book> bookList = dbTools.getBooks();
		BookAdapter bookAdapter = new BookAdapter(bookList);
		setListAdapter(bookAdapter);
	}
}
