package com.cycle7.bookapp;

public class Book {

	
	private long bookId;
	private String bookTitle;
	private String bookAuthor;
	private String bookReview;
	private String bookPages;
	private float bookRating;
	private boolean bookRead;
	
	public Book(){
		
	}
	
	
	public Book(String bookTitle, String bookAuthor, String bookPages, float bookRating, String bookReview, boolean bookRead){
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookReview = bookReview;
		this.bookPages = bookPages;
		this.bookRating = bookRating;
		this.bookRead = bookRead;
		
	}
	
	public Book(String bookTitle, String bookAuthor, String bookPages, float bookRating){
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookPages = bookPages;
		this.bookRating = bookRating;
	}
	
	public Book(long bookId, String bookTitle, String bookAuthor, String bookReview, String bookPages, float bookRating){
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookReview = bookReview;
		this.bookPages = bookPages;
		this.bookRating = bookRating;
	}
	
	
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getBookReview() {
		return bookReview;
	}
	public void setBookReview(String bookReview) {
		this.bookReview = bookReview;
	}
	public String getBookPages() {
		return bookPages;
	}
	public void setBookPages(String bookPages) {
		this.bookPages = bookPages;
	}
	public float getBookRating() {
		return bookRating;
	}
	public void setBookRating(float bookRating) {
		this.bookRating = bookRating;
	}


	public boolean isBookRead() {
		return bookRead;
	}


	public void setBookRead(boolean bookRead) {
		this.bookRead = bookRead;
	}

	
	
	
	
}
