package laba1.task1;

import java.lang.reflect.*;

public class ClassAnalyzer {

	public static String getClassDescription(String className) {
		try {
			Class<?> clazz = Class.forName(className);
			return getClassDescription(clazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "Class not found: " + className;
		}
	}

	public static String getClassDescription(Class<?> clazz) {
		StringBuilder description = new StringBuilder();

		// Package name
		description.append("Package: ").append(clazz.getPackage().getName()).append("\n");

		// Modifiers and class name
		int modifiers = clazz.getModifiers();
		description.append("Modifiers: ").append(Modifier.toString(modifiers)).append("\n");
		description.append("Class Name: ").append(clazz.getSimpleName()).append("\n");

		// Superclass
		Class<?> superClass = clazz.getSuperclass();
		if (superClass != null) {
			description.append("Superclass: ").append(superClass.getName()).append("\n");
		}

		// Implemented interfaces
		Class<?>[] interfaces = clazz.getInterfaces();
		if (interfaces.length > 0) {
			description.append("Implemented Interfaces: ");
			for (Class<?> intf : interfaces) {
				description.append(intf.getName()).append(", ");
			}
			description.delete(description.length() - 2, description.length());
			description.append("\n");
		}

		// Fields
		Field[] fields = clazz.getDeclaredFields();
		if (fields.length > 0) {
			description.append("Fields:\n");
			for (Field field : fields) {
				description.append("\t").append(Modifier.toString(field.getModifiers())).append(" ")
						.append(field.getType().getSimpleName()).append(" ").append(field.getName()).append("\n");
			}
		}

		// Constructors
		Constructor<?>[] constructors = clazz.getDeclaredConstructors();
		if (constructors.length > 0) {
			description.append("Constructors:\n");
			for (Constructor<?> constructor : constructors) {
				description.append("\t").append(Modifier.toString(constructor.getModifiers())).append(" ")
						.append(clazz.getSimpleName()).append("(");
				Class<?>[] parameterTypes = constructor.getParameterTypes();
				for (int i = 0; i < parameterTypes.length; i++) {
					description.append(parameterTypes[i].getSimpleName());
					if (i < parameterTypes.length - 1) {
						description.append(", ");
					}
				}
				description.append(")\n");
			}
		}

		// Methods
		Method[] methods = clazz.getDeclaredMethods();
		if (methods.length > 0) {
			description.append("Methods:\n");
			for (Method method : methods) {
				description.append("\t").append(Modifier.toString(method.getModifiers())).append(" ")
						.append(method.getReturnType().getSimpleName()).append(" ").append(method.getName())
						.append("(");
				Class<?>[] parameterTypes = method.getParameterTypes();
				for (int i = 0; i < parameterTypes.length; i++) {
					description.append(parameterTypes[i].getSimpleName());
					if (i < parameterTypes.length - 1) {
						description.append(", ");
					}
				}
				description.append(")\n");
			}
		}

		return description.toString();
	}
}