package com.cycle7.bookapp.fragments;

import com.cycle7.bookapp.R;
import com.cycle7.bookapp.R.id;
import com.cycle7.bookapp.R.layout;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;




	public abstract class SingleFragmentActivity extends Activity {
		protected abstract Fragment createFragment();
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_fragment);
	        
	        FragmentManager fm = getFragmentManager();
	        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
	        if(fragment == null){
	        	fragment = createFragment();
	        	fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
	        }
	    }
}
