/**
 * 
 */
package com.cycle7.bookapp.database;

import java.util.ArrayList;
import java.util.HashMap;

import com.cycle7.bookapp.Book;
import com.cycle7.bookapp.ReadingLog;

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

	private static final String BOOK_TABLE = "CREATE TABLE book( bookId INTEGER PRIMARY KEY, bookTitle TEXT, bookAuthor TEXT, bookPages INTEGER, bookRating REAL, bookReview TEXT, bookRead INTEGER)";
	private static final String READING_TIME_TABLE = "CREATE TABLE readingTime( readingTimeId INTEGER PRIMARY KEY, readingTime INTEGER, readingDate INTEGER)";
	//TODO make column names constants
	
	public DBTools(Context context){
	
		super(context, "book.sqlite", null, 1);

	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		//String query = "CREATE TABLE book( bookId INTEGER PRIMARY KEY, bookTitle TEXT, bookAuthor TEXT, bookPages INTEGER, bookRating REAL, bookReview TEXT, bookRead INTEGER)";
		try{
		database.execSQL(BOOK_TABLE);
		database.execSQL(READING_TIME_TABLE);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int arg1, int arg2) {
		
		String query = "DROP TABLE IF EXISTS book";
		database.execSQL(query);
		onCreate(database);
	}
	
	public void insertTime(ReadingLog log){
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("readingTime", log.getReadingTime());
		values.put("readingDate", log.getReadingDate());
		database.insert("readingTime", null, values);
		database.close();
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

	public Book getBookInfo(long id){
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
	
	public void deleteBook(long id){
		SQLiteDatabase database = this.getWritableDatabase();
		String query = "DELETE FROM book where bookId ='"+id+"'";
		database.execSQL(query);
	}

	public ArrayList<ReadingLog> getReadingLogs() {
		
		ArrayList<ReadingLog>logList = new ArrayList<ReadingLog>();
		SQLiteDatabase database = getWritableDatabase();
		String query = "SELECT * FROM readingTime";
		Cursor cursor =  database.rawQuery(query, null);
		int timerId = cursor.getColumnIndex("readingTimeId");
		int readingTime = cursor.getColumnIndex("readingTime");
		int readingDate = cursor.getColumnIndex("readingDate");
		if(cursor.moveToFirst()){
			do{
				ReadingLog log = new ReadingLog();
				try {
					log.setReadingTimeId(cursor.getLong(timerId));
					log.setReadingTime(cursor.getLong(readingTime));
					log.setReadingDate(cursor.getLong(readingDate));
					logList.add(log);
				}catch(Exception e){
					
				}
			}while(cursor.moveToNext());
	}
	database.close();
		return logList;
	}
	
	

}



