/**
 * 
 */
package com.cycle7.bookapp.database;

import java.util.ArrayList;
import java.util.HashMap;

import com.cycle7.bookapp.Book;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Hyoung
 *
 */
public class DBTools extends SQLiteOpenHelper{

	public DBTools(Context context){
	
		super(context, "book.sqlite", null, 1);

	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		String query = "CREATE TABLE book( bookId INTEGER PRIMARY KEY, bookTitle TEXT, bookAuthor TEXT, bookPages INTEGER, bookRating REAL, bookReview TEXT, bookRead INTEGER)";
		database.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int arg1, int arg2) {
		
		String query = "DROP TABLE IF EXISTS book";
		database.execSQL(query);
		onCreate(database);
	}

	
	public void insertBook(Book book){
		int bookRead;
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("bookTitle", book.getBookTitle());
		values.put("bookAuthor", book.getBookAuthor());
		values.put("bookPages", book.getBookPages());
		values.put("bookRating", book.getBookRating());
		values.put("bookReview",book.getBookReview());
		if(book.isBookRead()){
			bookRead = 1;
		}else {
			bookRead = 0;
		}
		values.put("bookRead", bookRead);
		database.insert("book", null, values);
		database.close();
		
	}
	
	
	public ArrayList<Book> getBooks(){
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		String query = "SELECT * FROM book";
		SQLiteDatabase database = getWritableDatabase();
		
		Cursor cursor =  database.rawQuery(query, null);
		int bookIdIndex = cursor.getColumnIndex("bookId");
		int bookTitleIndex = cursor.getColumnIndex("bookTitle");
		int bookAuthorIndex = cursor.getColumnIndex("bookAuthor");
		int bookPagesIndex = cursor.getColumnIndex("bookPages");
		int bookRatingIndex = cursor.getColumnIndex("bookRating");
		
		if(cursor.moveToFirst()){
			do{
				Book book = new Book();
				try {
				book.setBookId(cursor.getInt(bookIdIndex));
				book.setBookTitle(cursor.getString(bookTitleIndex));
				book.setBookAuthor(cursor.getString(bookAuthorIndex));
				book.setBookPages(cursor.getString(bookPagesIndex));
				book.setBookRating(cursor.getFloat(bookRatingIndex));
				bookList.add(book);
				}catch(Exception e){
					e.printStackTrace();
				}

				
			}while(cursor.moveToNext());
		}
		database.close();
		return bookList;
	}

	public Book getBookInfo(String id){
		Book book = new Book();
		//HashMap<String, String>bookMap = new HashMap<String, String>();
		SQLiteDatabase database = this.getReadableDatabase();
		String query = "SELECT * FROM book WHERE bookId = '"+id +"'";
		Cursor cursor =  database.rawQuery(query, null);
		
		int bookIdIndex = cursor.getColumnIndex("bookId");
		int bookTitleIndex = cursor.getColumnIndex("bookTitle");
		int bookAuthorIndex = cursor.getColumnIndex("bookAuthor");
		int bookPagesIndex = cursor.getColumnIndex("bookPages");
		int bookRatingIndex = cursor.getColumnIndex("bookRating");
		int bookReviewIndex = cursor.getColumnIndex("bookReview");
		int bookReadIndex = cursor.getColumnIndex("bookRead");
		
		if(cursor.moveToFirst()){
			do{
				book.setBookId(cursor.getLong(bookIdIndex));//TODO fix cursor
				book.setBookTitle(cursor.getString(bookTitleIndex));
				book.setBookAuthor(cursor.getString(bookAuthorIndex));
				book.setBookPages(cursor.getString(bookPagesIndex));
				book.setBookRating(cursor.getFloat((bookRatingIndex)));
				book.setBookReview(cursor.getString(bookReviewIndex));
				int bookRead = (cursor.getInt(bookReadIndex));
				if(bookRead == 1){
					book.setBookRead(true);
				}else{
					book.setBookRead(false);
				}
				
				
			}while(cursor.moveToNext());
		}
	return book;
	}
	
	public void updateBook(Book book){
		SQLiteDatabase database = this.getWritableDatabase();
		int bookRead = 0;

		ContentValues values = new ContentValues();
		values.put("bookId", book.getBookId());
		values.put("bookTitle", book.getBookTitle());
		values.put("bookAuthor", book.getBookAuthor());
		values.put("bookPages", book.getBookPages());
		values.put("bookRating", book.getBookRating());
		values.put("bookReview", book.getBookReview());
		if(book.isBookRead()){
			bookRead = 1;
		}else{
			bookRead = 0;
		}
		values.put("bookRead", bookRead);
		try{
			database.replace("book", null, values);
		}catch(Exception e){
			e.printStackTrace();
		}
		database.close();
	}
	
	public void deleteBook(String id){
		SQLiteDatabase database = this.getWritableDatabase();
		String query = "DELETE FROM book where bookId ='"+id+"'";
		database.execSQL(query);
	}

}



