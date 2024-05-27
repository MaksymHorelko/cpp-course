package metro;

import java.math.BigDecimal;
import java.util.Date;

//dell
public class Main {
	public static void main(String[] args) throws Exception {

		Client cl = new Client("localhost", 7891);
		AddMetroCardOperation op = new AddMetroCardOperation();
		op.getCrd().setUser(new User("Petr", "Petrov", "Serhiyovich", Sex.Male, new Date(1968, 12, 25)));
		op.getCrd().setColledge("KhNU");
		op.getCrd().setBalance(new BigDecimal(25));
		cl.applyOperation(op);
		
		
		
		int ser = op.getCrd().getSerialNumber();
		System.out.println(ser);
		
//		cl.finish();

//		cl = new Client("localhost", 7891);
		cl.applyOperation(new ShowBalanceOperation(-48483161));
		
		
		
		
		cl.finish();

	}
}
