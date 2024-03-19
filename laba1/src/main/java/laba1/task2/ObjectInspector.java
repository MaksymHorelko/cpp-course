package laba1.task2;

import java.lang.reflect.*;
import java.util.Scanner;

public class ObjectInspector {

	public static void inspect(Object obj) {
		Class<?> clazz = obj.getClass();
		System.out.println("Real Type: " + clazz.getName());

 		System.out.println("Fields:");
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				Object value = field.get(obj);
				System.out.println("\t" + field.getName() + ": " + value);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Public Methods:");
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			if (Modifier.isPublic(method.getModifiers()) && method.getParameterCount() == 0) {
				System.out.println("\t" + method.getName());
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Object obj = new MyClass();
		inspect(obj);

		System.out.println("\nChoose a method to invoke (enter method name):");
		String methodName = scanner.nextLine();

		try {
			Method method = obj.getClass().getMethod(methodName);
			Object result = method.invoke(obj);

			System.out.println("Result of method " + methodName + ": " + result);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		scanner.close();
	}

	static class MyClass {
		private int value = 10;

		public void printHello() {
			System.out.println("Hello");
		}

		public int getValue() {
			return value;
		}
	}
}
