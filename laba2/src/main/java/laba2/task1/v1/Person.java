package laba2.task1.v1;

import java.io.Serializable;

public abstract class Person implements Serializable {
	private static final long serialVersionUID = 6149546184043685627L;
	
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

	@Override
	public String toString() {
		return "Person{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + "}";
	}
}
