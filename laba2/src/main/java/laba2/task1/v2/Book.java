package laba2.task1.v2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Book {

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

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public void changeRental() {
		isRental = !isRental;
	}

	public void setRental(boolean isRental) {
		this.isRental = isRental;
	}

	public void writeObject(ObjectOutputStream out) throws IOException {
		out.writeObject(title);

		author.writeObject(out);

		out.writeBoolean(isRental);
	}

	public static Book readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		Book b = new Book((String) in.readObject(),
				new Author((String) in.readObject(), (String) in.readObject(), (Long)in.readObject()));
		b.setRental(in.readBoolean());
		return b;
	}

	@Override
	public String toString() {
		return "Book{" + "title='" + title + '\'' + ", author=" + author + '}';
	}

}
