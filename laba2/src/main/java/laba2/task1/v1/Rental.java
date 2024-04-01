package laba2.task1.v1;

import java.io.Serializable;

public class Rental implements Serializable {
	private static final long serialVersionUID = -3947484781301526592L;
	private Reader reader;
	private Book book;
	private int durationDays;

	public Rental(Reader reader, Book book, int durationDays) {
		this.reader = reader;
		book.changeRental();
		this.book = book;
		this.durationDays = durationDays;
	}

	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getDuration() {
		return durationDays;
	}

	public void setDuration(int durationDays) {
		this.durationDays = durationDays;
	}

	@Override
	public String toString() {
		return "Rental{" + "reader=" + reader + ", book=" + book + ", duration=" + durationDays + '}';
	}
}
