package com.cycle7.bookapp.fragments;

import java.util.ArrayList;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import com.cycle7.bookapp.Book;
import com.cycle7.bookapp.BookList;
import com.cycle7.bookapp.R;
import com.cycle7.bookapp.TabClass.TabsAdapter;
import com.cycle7.bookapp.database.DBTools;

public class AddBookToListFragment extends ListFragment{
	private View mHeader;
	private ArrayList<Book>bookListToBeDisplayed;
	private DBTools dbTools;
	private ArrayList<BookList>lists;
	private ArrayList<String>listNames; 
	private Spinner listSpinner;
	private CheckBox cBox;
	private ArrayList<Long>booksToBeAdded;
	private Button addButton;
	private TableRow tLayout;
	private TextView bookTitle;
	private TextView bookAuthor;
	private ArrayList<BookList>listOfBookIdsInSelectedList;
	private ArrayList<Book>booksInSelectedList;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		dbTools = new DBTools(getActivity());
		
	}
    static AddBookToListFragment newInstance(int num) {
    	AddBookToListFragment f = new AddBookToListFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);

        return f;
    }
	public class ListAdapter extends ArrayAdapter<Book>{
		public ListAdapter(ArrayList<Book>bookList){
			super(getActivity(), 0, bookList);
		}
	
		@Override
		public View getView(int position,  View convertView, ViewGroup parent){
			if(convertView == null){
				
				convertView = getActivity().getLayoutInflater().inflate(R.layout.activity_add_book_to_list, null);
				tLayout = (TableRow)convertView.findViewById(R.id.tableRow2);
			}
			Book book = getItem(position);
			bookTitle = (TextView)convertView.findViewById(R.id.addBookTitle);
			bookAuthor = (TextView)convertView.findViewById(R.id.addBookAuthor);
			cBox = new CheckBox(getActivity());	
			cBox.setId(position);
			cBox.setOnClickListener(getOnClickDoSomething(cBox, position));
			bookTitle.setText(book.getBookTitle());
			bookAuthor.setText(book.getBookAuthor());
			tLayout.addView(cBox);
			 
			return convertView;
		}
	}
	

	
	View.OnClickListener getOnClickDoSomething(final CheckBox checkbox, final int position)  {
	    return new View.OnClickListener() {
	        public void onClick(View v) {
	        	if(checkbox.isChecked()){
	        		checkbox.setText("test");
	        		long bookId = bookListToBeDisplayed.get(position).getBookId();
	        		Log.d("book", String.valueOf(bookId));
	        		booksToBeAdded.add(bookId);
	        	}else{
	        		booksToBeAdded.remove(bookListToBeDisplayed.get(position));
	        	}
	        }
	    };
	}
	
	@Override
	public void onResume(){
		super.onResume();
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
				getActivity().recreate(); //temporary fix
			}
		});
		updateAllLists();
	}

/**
 * updates the lists on resume and on spinner change. 
 */
	public void updateAllLists(){
		String listName = "";
		booksToBeAdded = new ArrayList<Long>();
		bookListToBeDisplayed = new ArrayList<Book>();
		bookListToBeDisplayed = dbTools.getBooks();
		booksInSelectedList = new ArrayList<Book>();
		BookList booksInList = new BookList();
		if(listSpinner.getSelectedItem() != null){
		listName = listSpinner.getSelectedItem().toString();
		}else {
			listName = "None";
		}
		booksInList = dbTools.getListIdByName(listName);
		listOfBookIdsInSelectedList = dbTools.getAllBooksInList(booksInList.getBookListId());
		booksInSelectedList = dbTools.getBooksFromList(listOfBookIdsInSelectedList);
		/*
		 * removes books that are already present in the selected book list
		 */
		for(int i = 0; i<booksInSelectedList.size(); i++){
			for(int j = 0; j<bookListToBeDisplayed.size(); j++){
				if (bookListToBeDisplayed.get(j).getBookTitle().toLowerCase()
						.equals(booksInSelectedList.get(i).getBookTitle().toLowerCase())
						&& bookListToBeDisplayed.get(j).getBookAuthor().toLowerCase()
								.equals(booksInSelectedList.get(i).getBookAuthor().toLowerCase())){
					bookListToBeDisplayed.remove(j);
				}
			}
		}
		//resets the list adapter which updates the list that is displayed
		ListAdapter listAdapter = new ListAdapter(bookListToBeDisplayed);
		
		setListAdapter(listAdapter);
		listAdapter.notifyDataSetChanged();
		
	}
	
	public void addBookToList(){
		String listName = listSpinner.getSelectedItem().toString();
		long bookListId = 0;
		ArrayList<BookList>lists = dbTools.getAllLists();
		for(int i = 0; i<lists.size(); i++){
			if(lists.get(i).getBookListName().equals(listName)){
			bookListId = lists.get(i).getBookListId();
			break;
			}
		}
		dbTools.addManyBooksToList(bookListId, booksToBeAdded);
		updateAllLists();
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
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listNames);
		listSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
			updateAllLists();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				updateAllLists();
				
			}
			
		});
		
		listSpinner.setAdapter(adapter);
	}
}
