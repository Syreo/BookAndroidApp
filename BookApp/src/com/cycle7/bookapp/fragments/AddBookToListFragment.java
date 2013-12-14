package com.cycle7.bookapp.fragments;

import java.util.ArrayList;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cycle7.bookapp.Book;
import com.cycle7.bookapp.BookList;
import com.cycle7.bookapp.R;
import com.cycle7.bookapp.database.DBTools;

public class AddBookToListFragment extends ListFragment{
	private View mHeader;
	private ArrayList<Book>bookList;
	private DBTools dbTools;
	private ArrayList<BookList>lists;
	private ArrayList<String>listNames; 
	private Spinner listSpinner;
	private CheckBox checkbox;
	private ArrayList<String>checkedBook;
	private Button addButton;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		dbTools = new DBTools(getActivity());
	}
	
	public class ListAdapter extends ArrayAdapter<Book>{
		public ListAdapter(ArrayList<Book>bookList){
			super(getActivity(), 0, bookList);
		}
	
		@Override
		public View getView(int position,  View convertView, ViewGroup parent){
			if(convertView == null){
				convertView = getActivity().getLayoutInflater().inflate(R.layout.activity_add_book_to_list, null);
			}
			Book book = getItem(position);
			TextView bookTitle = (TextView)convertView.findViewById(R.id.addBookTitle);
			TextView bookAuthor = (TextView)convertView.findViewById(R.id.addBookAuthor);
			
			checkbox = (CheckBox)convertView.findViewById(R.id.selectToAddCheckBox);
			bookTitle.setText(book.getBookTitle());
			bookAuthor.setText(book.getBookAuthor());
			
//			checkbox.setOnClickListener(new View.OnClickListener() {
//				 
//				@Override
//				public void onClick(View v) {
//					CheckBox cb = (CheckBox) v.findViewById(R.id.selectToAddCheckBox);
//					 TextView name = (TextView)v.findViewById(R.id.addBookTitle);
//					if(cb.isChecked()){
//						checkedBook.add(name.getText().toString());
//						Toast.makeText(getActivity(), name.getText().toString(), Toast.LENGTH_LONG).show();
//					}
//					
//				}
//			});
			
			 
			 
			 
			return convertView;
		}
	}
	
	public void addBookToList(){
		if(checkbox.isChecked()){
			
		}
	}
	
	@Override
	public void onResume(){
		super.onResume();
		bookList = dbTools.getBooks();
		ListAdapter listAdapter = new ListAdapter(bookList);
		mHeader = getActivity().getLayoutInflater().inflate(R.layout.add_book_to_list_header, null);
		setUpListSpinner();
		if(getListView().getHeaderViewsCount() < 1){
		getListView().addHeaderView(mHeader);
		}
		addButton = (Button)mHeader.findViewById(R.id.addBooksButton);
addButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addBookToList();
				
			}
		});
		setListAdapter(listAdapter);
	}
	
	public void setUpListSpinner(){
		lists = new ArrayList<BookList>();
		listNames = new ArrayList<String>();
		lists = dbTools.getAllLists();
		for(int i = 0; i < lists.size(); i++){
			String name = lists.get(i).getBookListName();
			listNames.add(name);
		}
		
		 
		listSpinner = (Spinner)mHeader.findViewById(R.id.listSpinner);
		ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listNames);
		listSpinner.setAdapter(adapter);
	}
}
