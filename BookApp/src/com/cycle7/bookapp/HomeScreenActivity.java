package com.cycle7.bookapp;

import com.cycle7.bookapp.fragments.HomeScreenFragment;
import com.cycle7.bookapp.fragments.SingleFragmentActivity;

import android.app.Fragment;



public class HomeScreenActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new HomeScreenFragment();
	}

}
