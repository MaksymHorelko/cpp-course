package laba2.task1.v3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Rental implements Externalizable {
	private Reader reader;
	private Book book;
	private int durationDays;

	public Rental(Reader reader, Book book, int durationDays) {
		this.reader = reader;
		this.book = book;
		this.durationDays = durationDays;
	}

	public Rental() {}
	
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

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		reader.writeExternal(out);

		book.writeExternal(out);

		out.writeInt(durationDays);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		reader = new Reader();
		book = new Book();

		reader.readExternal(in);
		book.readExternal(in);
		durationDays = in.readInt();
	}
}
