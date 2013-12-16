/**
 * 
 */
package com.cycle7.bookapp.database;

import java.util.ArrayList;
import java.util.HashMap;

import com.cycle7.bookapp.Book;
import com.cycle7.bookapp.BookList;
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
	public static final String BOOK_LIST_TABLE = "CREATE TABLE bookList( id INTEGER PRIMARY KEY, bookListId INTEGER, bookId INTEGER)";
	public static final String BOOK_LIST_LOOKUP = "CREATE TABLE bookName(bookListId INTEGER PRIMARY KEY, bookListName TEXT)";
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
		database.execSQL(BOOK_LIST_TABLE);
		database.execSQL(BOOK_LIST_LOOKUP);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int arg1, int arg2) {
		
		String query = "DROP TABLE IF EXISTS book";
		String query2 = "DROP TABLE IF EXISTS readingTime";
		String query3 = "DROP TABLE IF EXISTS bookList";
		database.execSQL(query);
		database.execSQL(query2);
		database.execSQL(query3);
		onCreate(database);
	}
	public ArrayList<BookList> getBooksInList(long bookListId){
		ArrayList<BookList>books = new ArrayList<BookList>();
		SQLiteDatabase database = this.getReadableDatabase();
		String query = "SELECT * FROM bookList WHERE bookListId = '"+bookListId +"'";
		Cursor cursor =  database.rawQuery(query, null);
		int bookIdIndex = cursor.getColumnIndex("bookId");
		if(cursor.moveToFirst()){
			do{
				BookList bookList = new BookList();
				try {
				bookList.setBookId(cursor.getInt(bookIdIndex));
		
				books.add(bookList);
				}catch(Exception e){
					e.printStackTrace();
				}

				
			}while(cursor.moveToNext());
		}
		database.close();
		
		return books;
		
	}
	
	public void removeManyBooksFromList(long bookListId, ArrayList<Long>bookIds){
		SQLiteDatabase database = this.getWritableDatabase();
		for(int i = 0; i<bookIds.size(); i++){
			String query = "DELETE FROM bookList WHERE bookId ='"+bookIds.get(i)+"'";
			database.execSQL(query);
			
		}
		database.close();
		
	}
	
	public void addManyBooksToList(long bookListId, ArrayList<Long>bookIds){
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		for(int i = 0; i< bookIds.size(); i++){
		values.put("bookId", bookIds.get(i));
		values.put("bookListId", bookListId);
		database.insert("bookList", null, values);
		}
		database.close();
		
	}
	
	public void addSingleBookToList(long bookListId, long bookId){
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("bookId", bookId);
		values.put("bookListId", bookListId);
		database.insert("bookList", null, values);
		database.close();
		
	}
	
	public void createBookList(String bookListName){
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("bookListName", bookListName);
		database.insert("bookName", null, values);		
		database.close();
		long listId = getLastRow();
		createList(listId);
	}
	
	public void createList(long listId){
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("bookListId", listId);
		database.insert("bookList", null, values);
		database.close();
		
		
	}
	
	public BookList getListIdByName(String listName){
		SQLiteDatabase database = this.getReadableDatabase();
		String query = "SELECT * FROM bookName WHERE bookListName = '"+listName+"'";
		Cursor cursor = database.rawQuery(query, null);
		int listNameIndex = cursor.getColumnIndex("bookListName");
		int listIdIndex = cursor.getColumnIndex("bookListId");
		
		BookList bookList = new BookList();
		if(cursor.moveToFirst()){
			do{
				
				try {
				bookList.setBookName(cursor.getString(listNameIndex));
				bookList.setBookListId(cursor.getLong(listIdIndex));
				}catch(Exception e){
					e.printStackTrace();
				}

				
			}while(cursor.moveToNext());
		}
		database.close();
		return bookList;
	}
	
	public long getLastRow(){
		SQLiteDatabase database = this.getReadableDatabase();
		long id = 0;
		String query = "SELECT bookListId from bookName order by bookListId DESC limit 1";
		Cursor c = database.rawQuery(query, null);
		int idColumnIndex = c.getColumnIndex("bookListId");
		if(c != null && c.moveToFirst()){
			id = c.getLong(idColumnIndex);
		}
		database.close();
		return id;
	}
	
	public ArrayList<BookList> getAllLists(){
		ArrayList<BookList> bookList = new ArrayList<BookList>();
		SQLiteDatabase database = this.getReadableDatabase();
		String query = "SELECT * FROM bookName";
		Cursor cursor =  database.rawQuery(query, null);
		int bookListIdIndex = cursor.getColumnIndex("bookListId");
		int bookNameIndex = cursor.getColumnIndex("bookListName");
		if(cursor.moveToFirst()){
			do{
				BookList list = new BookList();
				try {
					list.setBookListId(cursor.getLong(bookListIdIndex));
					list.setBookName(cursor.getString(bookNameIndex));
					bookList.add(list);
				}catch(Exception e){
					
				}
			}while(cursor.moveToNext());
		}
		database.close();
		return bookList;
	}
	
	public ArrayList<BookList>getAllBooksInList(long bookListId){
		ArrayList<BookList> bookList = new ArrayList<BookList>();
		SQLiteDatabase database = this.getReadableDatabase();
		String query = "SELECT * FROM bookList WHERE bookListId = '"+bookListId +"'";
		Cursor cursor =  database.rawQuery(query, null);
		int bookIdIndex = cursor.getColumnIndex("bookId");
		if(cursor.moveToFirst()){
			do{
				BookList list = new BookList();
				try {
				list.setBookId(cursor.getLong(bookIdIndex));
				bookList.add(list);
				}catch(Exception e){
					
				}
			}while(cursor.moveToNext());
		}
		return bookList;
	}
	
	public ArrayList<BookList>getBooksInListById(long bookListId){
		ArrayList<BookList> bookList = new ArrayList<BookList>();
		SQLiteDatabase database = this.getReadableDatabase();
		String query = "SELECT * FROM bookList WHERE bookListId = '"+bookListId +"'";
		Cursor cursor =  database.rawQuery(query, null);
		int bookIdIndex = cursor.getColumnIndex("bookId");
		if(cursor.moveToFirst()){
			do{
				BookList list = new BookList();
				try {
				list.setBookId(cursor.getLong(bookIdIndex));
				bookList.add(list);
				}catch(Exception e){
					
				}
			}while(cursor.moveToNext());
		}
		return bookList;
	}
	
	public ArrayList<Book> getBooksFromList(ArrayList<BookList> bookIds){
		ArrayList<Book> bookList = new ArrayList<Book>();
		SQLiteDatabase database = this.getReadableDatabase();
		for(int i = 0; i < bookIds.size(); i++){
			String query = "SELECT * FROM book WHERE bookId = '"+ bookIds.get(i).getBookId()+"'";
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
					Book book = new Book();
					book.setBookId(cursor.getLong(bookIdIndex));
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
					
					bookList.add(book);
				}while(cursor.moveToNext());
			}
		}
		
		return bookList;
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
		SQLiteDatabase database = this.getReadableDatabase();
		
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
				Book book = new Book();
				try {
				book.setBookId(cursor.getInt(bookIdIndex));
				book.setBookTitle(cursor.getString(bookTitleIndex));
				book.setBookAuthor(cursor.getString(bookAuthorIndex));
				book.setBookPages(cursor.getString(bookPagesIndex));
				book.setBookRating(cursor.getFloat(bookRatingIndex));
				book.setBookReview(cursor.getString(bookReviewIndex));
				int bookRead = (cursor.getInt(bookReadIndex));
				if(bookRead == 1){
					book.setBookRead(true);
				}else{
					book.setBookRead(false);
				}
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



