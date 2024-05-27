package metro;

public class RemoveCardOperation extends CardOperation {
	private static final long serialVersionUID = 1L;

	private int serNum = 0;

	public RemoveCardOperation(int serNum) {
		this.serNum = serNum;
	}

	public RemoveCardOperation() {
	}

	public int getSerNum() {
		return serNum;
	}

	public void setSerNum(int serNum) {
		this.serNum = serNum;
	}
}