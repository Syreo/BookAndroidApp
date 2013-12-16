package com.cycle7.bookapp;

public class BookList {

	private long bookListId;
	private long bookId;
	private String bookListName;
	public BookList(){
		
	}
	
	public BookList(long bookListId, long bookId){
		this.bookListId = bookListId;
		this.bookId = bookId;
	}
	public BookList(long bookListId){
		this.bookListId = bookListId;
	}
	public BookList(long bookListId, String bookName){
		this.bookListId = bookListId;
		this.bookListName = bookName;
	}

	public long getBookListId() {
		return bookListId;
	}
	public void setBookListId(long bookListId) {
		this.bookListId = bookListId;
	}
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public String getBookListName() {
		return bookListName;
	}

	public void setBookName(String bookListName) {
		this.bookListName = bookListName;
	}
	

}
