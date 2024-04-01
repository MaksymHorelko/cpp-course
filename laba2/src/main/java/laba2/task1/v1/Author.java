package laba2.task1.v1;

import java.io.Serializable;

public class Author extends Person implements Serializable {
	private static final long serialVersionUID = -807561752420962979L;

	public Author(String firstname, String lastname, Long authorId) {
		super(firstname, lastname, authorId);
	}

	@Override
	public String toString() {
		return "Author{" + "id=" + getId() + ", firstname=" + getFirstname() + ", lastname=" + getLastname() + "}";
	}
}