package com.cycle7.bookapp;

import java.util.ArrayList;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cycle7.bookapp.database.DBTools;

public class CustomDialog extends DialogFragment{
	
	private Button mButton;
	private EditText mEditText;
	private String text = "";
	private onSubmitListener mListener;
	private DBTools dbTools;
	private EditText listName;
	private Button saveButton;
	private Button cancelButton;
	interface onSubmitListener {  
		  void setOnSubmitListener(String arg);  
		 }  
		  
		 @Override  
		 public Dialog onCreateDialog(Bundle savedInstanceState) {  
		  final Dialog dialog = new Dialog(getActivity());  
		  dbTools = new DBTools(getActivity());
		  dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);  
		  dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  
		    WindowManager.LayoutParams.FLAG_FULLSCREEN);  
		  dialog.setContentView(R.layout.activity_create_booklist);  
		  dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);
		  dialog.show();  
		  saveButton = (Button)dialog.findViewById(R.id.save_button);
		  cancelButton = (Button)dialog.findViewById(R.id.list_cancel_button);
		  listName = (EditText)dialog.findViewById(R.id.listNameInput); 
		  saveButton.setOnClickListener(new OnClickListener() {  
		  
				@Override
				public void onClick(View v) {
					String name = listName.getText().toString();
					if(!name.equals("") && checkForDuplicate(name)){
					try{
					dbTools.createBookList(listName.getText().toString());
					dialog.dismiss();
					}catch(Exception e){
						e.printStackTrace();
					}
					}else{
						Toast.makeText(getActivity(), "Blank or non-unique list names are not allowed", Toast.LENGTH_SHORT).show();
					}
				}  
		  });  
		  cancelButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		  return dialog;  
		 }  
		 
		 
		 public boolean checkForDuplicate(String nameToCheck){
			 boolean isUnique = true;
			 ArrayList<BookList> listOfNames= dbTools.getAllLists();
			 for(int i = 0; i < listOfNames.size(); i++){
				 if(listOfNames.get(i).getBookListName().toLowerCase().equals(nameToCheck.toLowerCase())){
					 isUnique = false;
				 }
			 }
			 
			 return isUnique;
		 }
		}  

