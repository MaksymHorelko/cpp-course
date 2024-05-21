package laba4.metro;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MetroSystem {
	private Map<Integer, Card> store;

	public MetroSystem() {
		this.store = new HashMap<>();
	}

	public boolean addMoney(int serialNumber, BigDecimal add) {
		try {
			store.get(serialNumber).addBalance(add);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public BigDecimal getMoney(int serialNumber) {
		return store.get(serialNumber).getBalance();
	}

	public boolean payByCard(int serialNumber, BigDecimal value) {
		try {
			store.get(serialNumber).addBalance(value.negate());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void addCard(Card card) {
		if (store.containsValue(card)) {
			throw new CardPresentException("The card: " + card + " is already present in the system.");
		}

		store.putIfAbsent(card.getSerialNumber(), card);
	}

	public Optional<Card> findCard(int serialNumber) {
		return Optional.ofNullable(store.get(serialNumber));
	}

	public boolean removeCard(int serialNumber) {
		try {
			store.remove(serialNumber);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public int numCards() {
		return store.size();
	}
}
