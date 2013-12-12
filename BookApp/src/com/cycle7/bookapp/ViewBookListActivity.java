package com.cycle7.bookapp;

import com.cycle7.bookapp.fragments.SingleFragmentActivity;
import com.cycle7.bookapp.fragments.ViewBookListFragment;

import android.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.PopupMenu;

public class ViewBookListActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new ViewBookListFragment();
	}

}
