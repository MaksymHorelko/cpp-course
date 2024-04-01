package laba2.task1.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bookshelf implements Serializable {

	private static final long serialVersionUID = 1986907824380663434L;
	private List<Book> books;

	public Bookshelf() {
		books = new ArrayList<>();
	}

	public Bookshelf(List<Book> books) {
		this.books = books;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void addBook(Book book) {
		books.add(book);
	}

	public void deleteBook(int i) {
		books.remove(i);
	}

	public void clearBookshelf() {
		books.clear();
	}

	@Override
	public String toString() {
		return "Bookshelf{" + "books=" + books.toString() + "}";
	}

}
