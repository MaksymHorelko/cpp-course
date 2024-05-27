package conference.rmi;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Participant implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String lastName;
	private String email;
	private String report;
	private String affiliation;

	public Participant() {
	}

	public Participant(String name, String lastName, String email, String report, String affiliation) {
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.report = report;
		this.affiliation = affiliation;
	}

	public static boolean validateParticipant(String name, String lastName, String email, String report,
			String affiliation) {
		return Arrays.asList(name, lastName, email, report, affiliation).stream()
				.allMatch(s -> !s.isEmpty() && !s.trim().isEmpty());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(affiliation, email, lastName, name, report);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participant other = (Participant) obj;
		return Objects.equals(affiliation, other.affiliation) && Objects.equals(email, other.email)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(name, other.name)
				&& Objects.equals(report, other.report);
	}

	@Override
	public String toString() {
		return "Participant [name=" + name + ", lastName=" + lastName + ", email=" + email + ", report=" + report
				+ ", affiliation=" + affiliation + "]";
	}

}
