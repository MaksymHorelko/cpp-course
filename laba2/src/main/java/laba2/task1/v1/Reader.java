package laba2.task1.v1;

import java.io.Serializable;

public class Reader extends Person implements Serializable {
	private static final long serialVersionUID = -4507195945999952518L;

	public Reader(String firstname, String lastname, Long readerID) {
		super(firstname, lastname, readerID);
	}

	@Override
	public String toString() {
		return "Reader{" + "id=" + getId() + ", firstname=" + getFirstname() + ", lastname=" + getLastname() + "}";
	}
}
