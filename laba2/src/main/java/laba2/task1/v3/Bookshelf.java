package laba2.task1.v3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

public class Bookshelf implements Externalizable {

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

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(books.size());
		for (Book b : books) {
			b.writeExternal(out);
		}
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		int size = in.readInt();
		for (int i = 0; i < size; i++) {
			Book b = new Book();
			b.readExternal(in);
			books.add(b);
		}
	}

}
