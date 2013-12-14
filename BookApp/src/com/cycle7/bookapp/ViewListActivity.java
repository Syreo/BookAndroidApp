package com.cycle7.bookapp;

import android.app.Fragment;

import com.cycle7.bookapp.fragments.SingleFragmentActivity;
import com.cycle7.bookapp.fragments.ViewListFragment;

public class ViewListActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		
		return new ViewListFragment();
	}

}
