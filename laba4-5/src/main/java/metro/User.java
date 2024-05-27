package metro;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String firstName;
	private String lastName;
	private String patronymic;
	private Sex sex;
	private Date birthday;

	public User(String firstName, String lastName, String patronymic, Sex sex, Date birthday) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.patronymic = patronymic;
		this.sex = sex;
		this.birthday = birthday;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Client [firstName=" + firstName + ", lastName=" + lastName + ", patronymic=" + patronymic + ", gender="
				+ sex + ", birthday=" + birthday + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthday, firstName, lastName, patronymic, sex);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(birthday, other.birthday) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(patronymic, other.patronymic)
				&& sex == other.sex;
	}
}
