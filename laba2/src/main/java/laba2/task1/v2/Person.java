package laba2.task1.v2;

import java.io.IOException;
import java.io.ObjectOutputStream;

public abstract class Person {

	private String firstname;
	private String lastname;
	private Long id;

	public Person(String firstname, String lastname, Long id) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public Long getId() {
		return id;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setId(Long newId) {
		this.id = newId;
	}

	public void writeObject(ObjectOutputStream out) throws IOException {
		out.writeObject(firstname);
		
		out.writeObject(lastname);
		
		out.writeObject(id);
	}

	@Override
	public String toString() {
		return "Person{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + "}";
	}
}
