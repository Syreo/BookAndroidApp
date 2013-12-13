package com.cycle7.bookapp;

import com.cycle7.bookapp.database.DBTools;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
					if(!listName.getText().toString().equals("")){
					try{
						
					dbTools.createBookList(listName.getText().toString());
					dialog.dismiss();
					}catch(Exception e){
						e.printStackTrace();
					}
					}else {
						Toast.makeText(getActivity(), "Please enter a list name!", Toast.LENGTH_SHORT).show();
					}
				}  
		  });  
		  cancelButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "Canceled!", Toast.LENGTH_SHORT).show();
				dialog.dismiss();
			}
		});
		  return dialog;  
		 }  
		}  

