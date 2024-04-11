package laba2.task1.v3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Book implements Externalizable {

	private String title;
	private Author author;
	private Boolean isRental = false;

	public Book(String title, Author author) {
		this.title = title;
		this.author = author;
	}
	public Book() {}

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

	@Override
	public String toString() {
		return "Book{" + "title='" + title + '\'' + ", author=" + author + '}';
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(title);

		author.writeExternal(out);

		out.writeBoolean(isRental);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		title = (String) in.readObject();
		author = new Author((String) in.readObject(), (String) in.readObject(), (Long) in.readObject());
		isRental = in.readBoolean();

	}

}
