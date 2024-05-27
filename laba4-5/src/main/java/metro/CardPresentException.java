package metro;

public class CardPresentException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CardPresentException(String message, Throwable cause) {
		super(message, cause);
	}

	public CardPresentException(String message) {
		super(message);
	}
	
	public CardPresentException(Throwable cause) {
		super(cause);
	}
}
