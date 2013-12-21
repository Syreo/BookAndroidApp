package com.cycle7.bookapp.fragments;



import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import com.cycle7.bookapp.BookList;
import com.cycle7.bookapp.R;
import com.cycle7.bookapp.ViewBookListContentsActivity;
import com.cycle7.bookapp.database.DBTools;

public class ViewListFragment extends ListFragment{
	private View mHeader;
	private ArrayList<BookList>list;
	private DBTools dbTools;
	private CheckBox cBox;
	private TableRow tLayout;
	private ArrayList<Long>listsToDelete;
	private Button deleteButton;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		dbTools = new DBTools(getActivity());
		listsToDelete = new ArrayList<Long>();
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id){
		BookList bList = ((ListAdapter)getListAdapter()).getItem(position);
		Intent intent = new Intent(getActivity(), ViewBookListContentsActivity.class); 
		intent.putExtra("bookListId", bList.getBookListId());
		startActivity(intent);
	}
	public class ListAdapter extends ArrayAdapter<BookList>{
		public ListAdapter(ArrayList<BookList>lists){
			super(getActivity(), 0, lists);
		}
	
		
		@Override
		public View getView(int position,  View convertView, ViewGroup parent){
			if(convertView == null){
				convertView = getActivity().getLayoutInflater().inflate(R.layout.activity_view_list, null);
				tLayout = (TableRow)convertView.findViewById(R.id.listTableRow);
				
			}
			BookList bList = getItem(position);
			TextView listName = (TextView)convertView.findViewById(R.id.bookListName);
			listName.setText(bList.getBookListName());
			listName.setId(position + 1); //this is so that checkboxes ids will always be one number higher than the textviews ids
			cBox = new CheckBox(getActivity());	
			cBox.setId(position + 2);
			cBox.setOnClickListener(getOnClickDoSomething(cBox, position));
			tLayout.addView(cBox);
			return convertView;
		}
	}
	View.OnClickListener getOnClickDoSomething(final CheckBox checkbox, final int position)  {
	    return new View.OnClickListener() {
	        public void onClick(View v) {
	        	if(checkbox.isChecked()){
	        		
	        		long id = list.get(position).getBookListId();
	        		listsToDelete.add(id);
	        		
	        	}else{
	        		listsToDelete.remove(list.get(position));
	        	}
	        }
	    };
	}
	
	@Override
	public void onResume(){
		super.onResume();
		list = dbTools.getAllLists();
		ListAdapter listAdapter = new ListAdapter(list);
		mHeader = getActivity().getLayoutInflater().inflate(R.layout.list_header, null);
		if(getListView().getHeaderViewsCount() < 1){
			getListView().addHeaderView(mHeader);
			}
		deleteButton = (Button)mHeader.findViewById(R.id.deleteListButton);
		deleteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				createDialog();
				
			}
		});
		setListAdapter(listAdapter);
	}
	
	
	public void createDialog(){
		Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage("Are you sure you want to delete these lists?");
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				try{
				dbTools.deleteBookLists(listsToDelete);
				getActivity().recreate();//temporary fix
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		});
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override 
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				
			} 
		});
		
		builder.show();
	}
	}

