package com.cycle7.bookapp;

import android.app.Fragment;

import com.cycle7.bookapp.fragments.CreateBookListFragment;
import com.cycle7.bookapp.fragments.SingleFragmentActivity;

public class CreateBookListActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		
		return new CreateBookListFragment();
	}



}
