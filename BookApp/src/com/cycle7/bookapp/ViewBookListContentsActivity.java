package com.cycle7.bookapp;

import android.app.Fragment;

import com.cycle7.bookapp.fragments.SingleFragmentActivity;
import com.cycle7.bookapp.fragments.ViewBookListContentsFragment;

public class ViewBookListContentsActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new ViewBookListContentsFragment();
	}

}
