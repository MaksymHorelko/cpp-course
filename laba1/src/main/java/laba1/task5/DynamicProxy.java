package laba1.task5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface MyInterface {
	void myMethod();
}

class MyImpl implements MyInterface {
	public void myMethod() {
		System.out.println("Executing myMethod()...");
	}
}

class ProfilingInvocationHandler implements InvocationHandler {
	private final Object target;

	public ProfilingInvocationHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		long startTime = System.nanoTime();
		Object result = method.invoke(target, args);
		long endTime = System.nanoTime();
		System.out.println("Method execution time: " + (endTime - startTime) + " nanoseconds");
		return result;
	}
}

class TracingInvocationHandler implements InvocationHandler {
	private final Object target;

	public TracingInvocationHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("Method name: " + method.getName());
		System.out.printf("Method parameters: ");
		if (args != null) {
			for (Object arg : args) {
				System.out.println(arg);
			}
		} else {
			System.out.println("null");
		}
		Object result = method.invoke(target, args);
		System.out.println("Method result: " + result);
		return result;
	}
}

public class DynamicProxy {
	public static void main(String[] args) {
		MyInterface profilingProxy = (MyInterface) Proxy.newProxyInstance(MyInterface.class.getClassLoader(),
				new Class[] { MyInterface.class }, new ProfilingInvocationHandler(new MyImpl()));

		profilingProxy.myMethod();

		MyInterface tracingProxy = (MyInterface) Proxy.newProxyInstance(MyInterface.class.getClassLoader(),
				new Class[] { MyInterface.class }, new TracingInvocationHandler(new MyImpl()));

		tracingProxy.myMethod();
	}
}
