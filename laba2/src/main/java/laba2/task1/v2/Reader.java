package laba2.task1.v2;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Reader extends Person {

	public Reader(String firstname, String lastname, Long readerID) {
		super(firstname, lastname, readerID);
	}

	public static Reader readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		return new Reader((String) in.readObject(), (String) in.readObject(), (Long) in.readObject());
	}

	@Override
	public String toString() {
		return "Reader{" + "id=" + getId() + ", firstname=" + getFirstname() + ", lastname=" + getLastname() + "}";
	}
}
