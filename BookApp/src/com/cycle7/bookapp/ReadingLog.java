package com.cycle7.bookapp;

public class ReadingLog {

	private long readingTimeId;
	private long readingTime;
	private long readingDate;
	
	
	public ReadingLog(){
		
	}
	public ReadingLog(long readingTime, long readingDate){
		this.readingTime = readingTime;
		this.readingDate = readingDate;
	}
	
	public long getReadingTimeId() {
		return readingTimeId;
	}
	public void setReadingTimeId(long readingTimeId) {
		this.readingTimeId = readingTimeId;
	}
	public long getReadingTime() {
		return readingTime;
	}
	public void setReadingTime(long readingTime) {
		this.readingTime = readingTime;
	}
	public long getReadingDate() {
		return readingDate;
	}
	public void setReadingDate(long readingDate) {
		this.readingDate = readingDate;
	}
	
	
	
	
	
	
	
}
