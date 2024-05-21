package laba4.executor;

import java.io.Serializable;

public class Factorial implements Executable, Serializable {
	private static final long serialVersionUID = 1L;

	private int number;

	public Factorial(int number) {
		this.number = number;
	}

	@Override
	public Result execute() {
		long time = System.currentTimeMillis();
		int result = factorial(number);
		long timeRes = System.currentTimeMillis() - time;
		
		return new Result() {
			private static final long serialVersionUID = -8850353130859676238L;

			@Override
			public Object output() {
				return result;
			}

			@Override
			public double scoreTime() {
				return timeRes;
			}

		};
	}

	private int factorial(int n) {
		if (n == 0 || n == 1) {
			return 1;
		}
		return n * factorial(n - 1);
	}
}