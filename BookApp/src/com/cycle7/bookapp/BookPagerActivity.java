package com.cycle7.bookapp;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cycle7.bookapp.database.DBTools;
import com.cycle7.bookapp.fragments.ViewBookFragment;

public class BookPagerActivity extends Activity{
	private ViewPager mViewPager;
	private ArrayList<Book>books;
	private DBTools dbTools;
	private TextView bookTitle;
	private TextView bookAuthor;
	private TextView bookPages;
	private RatingBar bookRating;
	private TextView bookReview;
	private CheckBox bookRead;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		dbTools = new DBTools(this);
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.viewPager);
		setContentView(mViewPager);
		books = dbTools.getBooks();
		
			
	FragmentManager fm = getFragmentManager();
		mViewPager.setAdapter(new FragmentStatePagerAdapter(fm){
			@Override
			public int getCount(){
				return books.size();
			}
			@Override
			public Fragment getItem(int pos){
				Book book = books.get(pos);
				return ViewBookFragment.newInstance(book.getBookId());
			}
		});

		
		long bookId = getIntent().getLongExtra("bookId", 0);
		for(int i = 0; i < books.size(); i++){
			if(books.get(i).getBookId() == bookId){
				mViewPager.setCurrentItem(i);
				
				break;
			}else {
				Toast.makeText(this, "not found", Toast.LENGTH_LONG).show();
			}
		}
		
		
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int pos) {
				Book book = books.get(pos);
				if(book.getBookTitle() != null){
					setTitle(book.getBookTitle());
					
				}
			}
			
			@Override
			public void onPageScrolled(int pos, float posOffset, int posOffsetPixels) {
			
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
				
			}
		});
	}
@Override
public void onResume(){
	super.onResume();

	
}
}
