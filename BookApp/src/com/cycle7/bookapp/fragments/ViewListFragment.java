package com.cycle7.bookapp.fragments;



import java.util.ArrayList;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cycle7.bookapp.BookList;
import com.cycle7.bookapp.R;
import com.cycle7.bookapp.database.DBTools;

public class ViewListFragment extends ListFragment{

	private ArrayList<BookList>list;
	private DBTools dbTools;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		dbTools = new DBTools(getActivity());
	}
	
//	@Override
//	public void onListItemClick(ListView l, View v, int position, long id){
//		BookList bList = ((ListAdapter)getListAdapter()).getItem(position);
//		Intent intent = new Intent(getActivity(), BookPagerActivity.class); 
//		intent.putExtra("bookId", bList.getBookId());
//		startActivity(intent);
//	}
	public class ListAdapter extends ArrayAdapter<BookList>{
		public ListAdapter(ArrayList<BookList>lists){
			super(getActivity(), 0, lists);
		}
	
		
		@Override
		public View getView(int position,  View convertView, ViewGroup parent){
			if(convertView == null){
				convertView = getActivity().getLayoutInflater().inflate(R.layout.activity_view_list, null);
				
			}
			BookList bList = getItem(position);
			TextView listName = (TextView)convertView.findViewById(R.id.bookListName);
			listName.setText(bList.getBookListName());
			TextView bookCount = (TextView)convertView.findViewById(R.id.bookCount);
			bookCount.setText(String.valueOf(list.size()));
			
			return convertView;
		}
	}
	
	
	@Override
	public void onResume(){
		super.onResume();
		list = dbTools.getAllLists();
		ListAdapter listAdapter = new ListAdapter(list);
		setListAdapter(listAdapter);
	}
	
	}

