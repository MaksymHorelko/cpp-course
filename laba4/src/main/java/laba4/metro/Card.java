package laba4.metro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Card implements Serializable {
	private static final long serialVersionUID = 1L;

	private int serialNumber;
	private User user;
	private String colledge;
	private BigDecimal balance;

	public Card(User user, String colledge, BigDecimal balance) {
		this.user = user;
		this.colledge = colledge;
		this.balance = balance;
		serialNumber = user.hashCode();
	}

	public Card() {

	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public int regenerateSerialNumber() {
		this.serialNumber = user.hashCode();
		return serialNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		serialNumber = user.hashCode();
	}

	public String getColledge() {
		return colledge;
	}

	public void setColledge(String colledge) {
		setCollege(colledge);
	}

	public void setCollege(String colledge) {
		this.colledge = colledge;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public BigDecimal addBalance(BigDecimal add) {
		this.balance = balance.add(add);
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Card [serialNumber=" + serialNumber + ", client=" + user + ", colledge=" + colledge + ", balance="
				+ balance + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(balance, user, colledge, serialNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		return Objects.equals(balance, other.balance) && Objects.equals(user, other.user)
				&& Objects.equals(colledge, other.colledge) && serialNumber == other.serialNumber;
	}

}
