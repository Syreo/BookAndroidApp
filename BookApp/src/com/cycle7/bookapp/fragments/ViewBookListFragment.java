package com.cycle7.bookapp.fragments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import com.cycle7.bookapp.Book;
import com.cycle7.bookapp.BookPagerActivity;
import com.cycle7.bookapp.R;
import com.cycle7.bookapp.database.DBTools;

public class ViewBookListFragment extends ListFragment {
	
	private DBTools dbTools;
	private ArrayList<Book> bookList;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbTools = new DBTools(getActivity());
		setHasOptionsMenu(true);

	}
	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id){
		Book b = ((BookAdapter)getListAdapter()).getItem(position);
		Intent intent = new Intent(getActivity(), BookPagerActivity.class); 
		intent.putExtra("bookId", b.getBookId());
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
		CheckBox bookRead = (CheckBox)convertView.findViewById(R.id.isReadCheckbox);
		bookRead.setChecked(b.isBookRead());
	return convertView;
	}
	}
	
	@Override
	public void onResume(){
		super.onResume();
		bookList = dbTools.getBooks();
		BookAdapter bookAdapter = new BookAdapter(bookList);
		setListAdapter(bookAdapter);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.options_menu, menu);
		MenuItem item = menu.findItem(R.id.alpha_author);
		MenuItem item2 = menu.findItem(R.id.alpha_title);
		MenuItem item3 = menu.findItem(R.id.sort_read);
		item.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				sortAlphaByAuthor();
				return false;
			}
		});
		item2.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				sortAlphaByTitle();
				return false;
			}
		});
		
		item3.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}
	
	public void sortAlphaByAuthor(){
		Collections.sort(bookList, new Comparator<Book>(){

			@Override
			public int compare(Book book1, Book book2) {
				
				return book1.getBookAuthor().compareTo(book2.getBookAuthor());
			}
			
		});
		BookAdapter bookAdapter = new BookAdapter(bookList);
		setListAdapter(bookAdapter);
	}
	
	public void sortAlphaByTitle(){
		Collections.sort(bookList, new Comparator<Book>(){

			@Override
			public int compare(Book book1, Book book2) {
				
				return book1.getBookTitle().compareTo(book2.getBookTitle());
			}
			
		});
		BookAdapter bookAdapter = new BookAdapter(bookList);
		setListAdapter(bookAdapter);
	}
	
}
