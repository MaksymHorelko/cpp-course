package executor;

import java.io.Serializable;

public interface Executable extends Serializable{
	public Result execute();
}
