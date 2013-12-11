package com.cycle7.bookapp;

import android.app.Fragment;

import com.cycle7.bookapp.fragments.ReadingTimerFragment;
import com.cycle7.bookapp.fragments.SingleFragmentActivity;



public class ReadingTimerActivity extends SingleFragmentActivity{

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new ReadingTimerFragment();
	}
	
	
}
