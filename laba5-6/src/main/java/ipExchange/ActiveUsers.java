package ipExchange;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActiveUsers implements Serializable {

	private static final long serialVersionUID = -1L;

	private final List<User> users;

	public ActiveUsers() {
		users = new ArrayList<>();
	}

	public void addUser(User user) {
		users.add(user);
	}

	public int getUsersSize() {
		return users.size();
	}

	public boolean isContains(User user) {
		return users.contains(user);
	}

	public User getUser(int index) {
		return users.get(index);
	}

	@Override
	public String toString() {
		return users.toString();
	}
}
