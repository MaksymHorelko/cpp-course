package laba1.task3;

import java.lang.reflect.*;

public class MethodInvoker {

	public static Object invokeMethod(Object obj, String methodName, Object... args) throws FunctionNotFoundException {
		try {
			Class<?>[] parameterTypes = new Class<?>[args.length];
			for (int i = 0; i < args.length; i++) {
				parameterTypes[i] = args[i].getClass();
			}
			Method method = obj.getClass().getMethod(methodName, parameterTypes);
			return method.invoke(obj, args);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			throw new FunctionNotFoundException(
					"Method '" + methodName + "' not found or cannot be invoked on this object", e);
		}
	}

	public static void main(String[] args) {
		MyClass obj = new MyClass();
		try {
			Object result = invokeMethod(obj, "sum", 5, 3);
			System.out.println("Result: " + result);
		} catch (FunctionNotFoundException e) {
			e.printStackTrace();
		}
	}

	static class MyClass {
		public int sum(Integer a, Integer b) {
			return a + b;
		}
	}

	static class FunctionNotFoundException extends RuntimeException {
		public FunctionNotFoundException(String message, Throwable cause) {
			super(message, cause);
		}
	}
}
