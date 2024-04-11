package laba2.task1.v3;

public class Author extends Person {

	public Author(String firstname, String lastname, Long authorId) {
		super(firstname, lastname, authorId);
	}

	@Override
	public String toString() {
		return "Author{" + "id=" + getId() + ", firstname=" + getFirstname() + ", lastname=" + getLastname() + "}";
	}
}