package metro;

public class AddMetroCardOperation extends CardOperation {
	private static final long serialVersionUID = 1L;
	
	private Card crd = null;

	public AddMetroCardOperation() {
		crd = new Card();
	}

	public Card getCrd() {
		return crd;
	}
}