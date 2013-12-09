package com.cycle7.bookapp;

import android.app.Fragment;

import com.cycle7.bookapp.fragments.EditBookFragment;
import com.cycle7.bookapp.fragments.SingleFragmentActivity;

public class EditBookActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new EditBookFragment();
	}

}
