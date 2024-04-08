package laba2.task1.v2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

	public void writeObject(ObjectOutputStream out) throws IOException {
		out.writeInt(books.size());
		for (Book b : books) {
			b.writeObject(out);
		}
	}

	public static Bookshelf readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		List<Book> books = new ArrayList<Book>();

		int size = in.readInt();
		for (int i = 0; i < size; i++) {
			books.add(Book.readObject(in));
		}
		
		return new Bookshelf(books);
	}

	@Override
	public String toString() {
		return "Bookshelf{" + "books=" + books.toString() + "}";
	}

}
