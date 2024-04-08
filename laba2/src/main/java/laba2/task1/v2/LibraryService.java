package laba2.task1.v2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LibraryService implements Serializable {
	private static final long serialVersionUID = 3838500973495419830L;
	private List<Reader> readers;
	private List<Rental> rentals;
	private transient Bookshelf bookshelf;

	public LibraryService() {
		this.readers = new ArrayList<>();
		this.bookshelf = new Bookshelf();
		this.rentals = new ArrayList<>();
	}

	public void addBook(Book book) {
		bookshelf.addBook(book);
	}

	public void addReader(Reader reader) {
		readers.add(reader);
	}

	public void addRentals(Rental rentals) {
		this.rentals.add(rentals);
	}

	public List<Reader> getReaders() {
		return readers;
	}

	public Bookshelf getBookshelf() {
		return bookshelf;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	@Override
	public String toString() {
		return "LibrarySystem{" + "readers=" + readers + ", bookshelf=" + bookshelf + ", rentals=" + rentals + '}';
	}

	public void saveState(String fileName) {
		try {

			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			bookshelf.writeObject(out);

			out.writeInt(readers.size());
			for (Reader read : readers) {
				read.writeObject(out);
			}

			out.writeInt(rentals.size());
			for (Rental rent : rentals) {
				rent.writeObject(out);
			}

			out.close();
			fileOut.close();

			System.out.println("Serialized data is saved in " + fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static LibraryService restoreState(String fileName) {
		LibraryService system = null;
		try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);

			system = new LibraryService();

			system.bookshelf = Bookshelf.readObject(in);

			system.readers = new ArrayList<Reader>();
			int readersSize = in.readInt();
			for (int i = 0; i < readersSize; i++) {
				system.readers.add(Reader.readObject(in));
			}

			int rentalsSize = in.readInt();
			system.rentals = new ArrayList<Rental>();
			for (int i = 0; i < rentalsSize; i++) {
				system.rentals.add(Rental.readObject(in));
			}

			in.close();
			fileIn.close();
			System.out.println("Serialized data is restored from " + fileName);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return system;
	}
}