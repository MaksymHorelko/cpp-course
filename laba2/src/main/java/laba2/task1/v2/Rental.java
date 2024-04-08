package laba2.task1.v2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Rental {
	private Reader reader;
	private Book book;
	private int durationDays;

	public Rental(Reader reader, Book book, int durationDays) {
		this.reader = reader;
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

	public void writeObject(ObjectOutputStream out) throws IOException {
		reader.writeObject(out);
		
		book.writeObject(out);

		out.writeInt(durationDays);
	}

	public static Rental readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		return new Rental(Reader.readObject(in), Book.readObject(in), in.readInt());
	}

	@Override
	public String toString() {
		return "Rental{" + "reader=" + reader + ", book=" + book + ", duration=" + durationDays + '}';
	}
}
