package laba4.metro;

import java.math.BigDecimal;

public class AddMoneyOperation extends CardOperation {
	private static final long serialVersionUID = 1L;
	
	private int serNum = 0;
	private BigDecimal money = new BigDecimal(0);

	public AddMoneyOperation(int serNum, BigDecimal money) {
		this.serNum = serNum;
		this.money = money;
	}

	public AddMoneyOperation() {
		this(0, new BigDecimal(0));
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public int getSerNum() {
		return serNum;
	}

	public void setSerNum(int serNum) {
		this.serNum = serNum;
	}
}
