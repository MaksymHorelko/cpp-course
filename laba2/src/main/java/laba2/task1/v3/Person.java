package laba2.task1.v3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public abstract class Person implements Externalizable {

	private String firstname;
	private String lastname;
	private Long id;

	public Person(String firstname, String lastname, Long id) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.id = id;
	}
	
	public Person() {}

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
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(firstname);

		out.writeObject(lastname);
		
		out.writeObject(id);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		firstname = (String) in.readObject();
		lastname = (String) in.readObject();
		id = (Long) in.readObject();
	}

	@Override
	public String toString() {
		return "Person{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + "}";
	}
}
