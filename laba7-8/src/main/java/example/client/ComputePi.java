package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.math.BigDecimal;
import compute.Task;
import compute.Compute;

public class ComputePi {
    public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            Registry registry = LocateRegistry.getRegistry(args[0]);
            String name = "Compute";
            Compute comp = (Compute) registry.lookup(name);
            Pi task = new Pi(Integer.parseInt(args[1]));
            E task2 = new E(Integer.parseInt(args[1]));
            BigDecimal e = comp.executeTask(task2);
            BigDecimal pi = comp.executeTask(task);
            System.out.println("pi = " + pi + "e = " + e);
        } catch (Exception e) {
            System.err.println("ComputePi exception:");
            e.printStackTrace(); }
    }
}
