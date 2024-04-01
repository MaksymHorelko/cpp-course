package laba2.task1.v1;

import java.io.Serializable;

public class Book implements Serializable {

	private static final long serialVersionUID = 3070107661999670907L;
	private String title;
	private Author author;
	private Boolean isRental = false;

	public Book(String title, Author author) {
		this.title = title;
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public Author getAuthor() {
		return author;
	}

	public Boolean isRental() {
		return isRental;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public void changeRental() {
		isRental = !isRental;
	}

	@Override
	public String toString() {
		return "Book{" + "title=" + title + ", author=" + author + ", isRental=" + isRental + "}";
	}

}
