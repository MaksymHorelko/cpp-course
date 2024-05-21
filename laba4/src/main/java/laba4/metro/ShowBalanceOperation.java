package laba4.metro;

public class ShowBalanceOperation extends CardOperation {
	private static final long serialVersionUID = 1L;

	private int serNum = 0;
	
	public ShowBalanceOperation(int serNum) {
		this.serNum = serNum;
	}

	public ShowBalanceOperation() {
		this(0);
	}
	
	public int getSerNum() {
		return serNum;
	}
}
