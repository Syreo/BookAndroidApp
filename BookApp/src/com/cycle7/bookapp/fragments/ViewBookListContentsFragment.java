package com.cycle7.bookapp.fragments;

import java.util.ArrayList;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cycle7.bookapp.Book;
import com.cycle7.bookapp.BookList;
import com.cycle7.bookapp.BookPagerActivity;
import com.cycle7.bookapp.R;
import com.cycle7.bookapp.database.DBTools;

public class ViewBookListContentsFragment extends ListFragment{
	private DBTools dbTools;
	private Long bookListId = 0L;
	private TextView bookTitle;
	private TextView bookAuthor;
	private ArrayList<BookList>lists;
	private ArrayList<Book>books;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		dbTools = new DBTools(getActivity());
		
		
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id){
		Book b = ((ListAdapter)getListAdapter()).getItem(position);
		Intent intent = new Intent(getActivity(), BookPagerActivity.class); 
		intent.putExtra("bookId", b.getBookId());
		startActivity(intent);
	}
	
	public class ListAdapter extends ArrayAdapter<Book>{
		public ListAdapter(ArrayList<Book>books){
			super(getActivity(), 0, books);
		}
	
	@Override
	public View getView(int position,  View convertView, ViewGroup parent){
		if(convertView == null){
			convertView = getActivity().getLayoutInflater().inflate(R.layout.activity_view_book_list_contents, null);
			
		}
		Book book = getItem(position);
		bookTitle = (TextView)convertView.findViewById(R.id.contentsBookTitle);
		bookAuthor = (TextView)convertView.findViewById(R.id.contentBookAuthor);
		
		bookTitle.setText(book.getBookTitle());
		bookAuthor.setText(book.getBookAuthor());
		
		return convertView;
	}
	}

	@Override
	public void onResume(){
		super.onResume();
		books = new ArrayList<Book>();
		lists = new ArrayList<BookList>();
		bookListId = getActivity().getIntent().getExtras().getLong("bookListId");
		Log.d("bookDebug", String.valueOf(bookListId)+" id");
		lists = dbTools.getBooksInList(bookListId);
		Log.d("bookDebug", String.valueOf(lists.size())+ "size");
		for(int i = 0; i< lists.size(); i++){
			Book book = new Book();
			book = dbTools.getBookInfo(lists.get(i).getBookId());
			books.add(book);
		}
		ListAdapter listAdapter = new ListAdapter(books);
		setListAdapter(listAdapter);
	}
}
