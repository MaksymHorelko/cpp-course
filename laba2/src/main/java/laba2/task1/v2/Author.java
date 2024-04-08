package laba2.task1.v2;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Author extends Person {

	public Author(String firstname, String lastname, Long authorId) {
		super(firstname, lastname, authorId);
	}

	public static Author readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		return new Author((String) in.readObject(), (String) in.readObject(), (Long) in.readObject());
	}

	@Override
	public String toString() {
		return "Author{" + "id=" + getId() + ", firstname=" + getFirstname() + ", lastname=" + getLastname() + "}";
	}
}