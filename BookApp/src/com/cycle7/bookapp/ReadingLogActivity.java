package com.cycle7.bookapp;

import android.app.Fragment;

import com.cycle7.bookapp.fragments.ReadingLogFragment;
import com.cycle7.bookapp.fragments.SingleFragmentActivity;

public class ReadingLogActivity extends SingleFragmentActivity{
	
	@Override
	public Fragment createFragment(){
		return new ReadingLogFragment();
	}

}
