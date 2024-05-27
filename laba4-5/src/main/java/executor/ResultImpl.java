package executor;

import java.io.Serializable;

public class ResultImpl implements Result, Serializable {
	private static final long serialVersionUID = 1392113210336990016L;

	Object output;
	double scoreTime;

	public ResultImpl(Object o, double c) {
		output = o;
		scoreTime = c;
	}

	public Object output() {
		return output;
	}

	public double scoreTime() {
		return scoreTime;
	}
}