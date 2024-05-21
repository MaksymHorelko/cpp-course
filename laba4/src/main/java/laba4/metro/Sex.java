package laba4.metro;

public enum Sex {
	Male("Чоловік"), Female("Жінка");

	private final String sex;

	Sex(String sex) {
		this.sex = sex;
	}
	
	@Override
	public String toString() {
		return sex;
	}
}
