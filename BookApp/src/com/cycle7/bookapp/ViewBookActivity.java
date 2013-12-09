package com.cycle7.bookapp;

import com.cycle7.bookapp.fragments.SingleFragmentActivity;
import com.cycle7.bookapp.fragments.ViewBookFragment;

import android.app.Fragment;

public class ViewBookActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new ViewBookFragment();
	}

}
