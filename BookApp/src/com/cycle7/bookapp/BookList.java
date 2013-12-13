package com.cycle7.bookapp;

public class BookList {

	private long bookListId;
	private String bookId;
	private String bookListName;
	
	
	
	public BookList(){
		
	}
	
	public BookList(long bookListId, String bookId, String bookListName){
		this.bookListId = bookListId;
		this.bookId = bookId;
		this.bookListName = bookListName;
	}

	public long getBookListId() {
		return bookListId;
	}
	public void setBookListId(long bookListId) {
		this.bookListId = bookListId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookListName() {
		return bookListName;
	}
	public void setBookListName(String bookListName) {
		this.bookListName = bookListName;
	}
	

}
