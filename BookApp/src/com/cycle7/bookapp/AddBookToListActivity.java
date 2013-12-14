package com.cycle7.bookapp;

import android.app.Fragment;

import com.cycle7.bookapp.fragments.AddBookToListFragment;
import com.cycle7.bookapp.fragments.SingleFragmentActivity;

public class AddBookToListActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new AddBookToListFragment();
	}

}
