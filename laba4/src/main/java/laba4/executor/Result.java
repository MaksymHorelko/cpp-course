package laba4.executor;

import java.io.Serializable;

public interface Result extends Serializable {
	public Object output();

	public double scoreTime();
}
