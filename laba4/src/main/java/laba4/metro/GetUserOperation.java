package laba4.metro;

public class GetUserOperation extends CardOperation {
	private static final long serialVersionUID = 1L;

	private int serNum = 0;

	public GetUserOperation(int serNum) {
		this.serNum = serNum;
	}

	public GetUserOperation() {
		this(0);
	}

	public int getSerNum() {
		return serNum;
	}
}