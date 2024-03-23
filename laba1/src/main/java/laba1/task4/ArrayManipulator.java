package laba1.task4;

import java.lang.reflect.Array;

public class ArrayManipulator {
	public static void main(String[] args) {

		// array
		Object array = createArray("java.lang.String", 10);

		System.out.println("String array: " + arrayToString(array));

		array = resizeArray(array, 15);
		System.out.println("New size for string array: " + arrayToString(array));

		// matrix
		Object matrix = createMatrix("java.lang.Double", 10, 5);
		System.out.println("matrix array: " + matrixToString(matrix));

		matrix = resizeMatrix(matrix, 5, 5);
		System.out.println("New size for string array: " + matrixToString(matrix));
	}

	public static <T> T[] createArray(String className, int size) {
		try {
			Class<?> clazz = Class.forName(className);

			@SuppressWarnings("unchecked")
			T[] array = (T[]) Array.newInstance(clazz, size);
			return array;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("There is no class with this name: " + className, e);
		}
	}

	public static Object resizeArray(Object array, int newSize) {
		Class<?> componentType = array.getClass().getComponentType();
		Object newArray = Array.newInstance(componentType, newSize);
		int length = Math.min(Array.getLength(array), newSize);
		System.arraycopy(array, 0, newArray, 0, length);
		return newArray;
	}

	@SuppressWarnings("unchecked")
	public static <T> StringBuilder arrayToString(Object array) {
		StringBuilder str = new StringBuilder("[ ");
		for (T item : (T[]) array) {
			str.append(item + " ");
		}
		str.append("]");

		return str;
	}

	public static <T> T[][] createMatrix(String className, int rows, int cols) {
		try {
			Class<?> clazz = Class.forName(className);

			@SuppressWarnings("unchecked")
			T[][] array = (T[][]) Array.newInstance(clazz, rows, cols);
			return array;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("There is no class with this name: " + className, e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T[][] resizeMatrix(Object matrix, int newRows, int newCols) {
		Class<?> componentType = ((T[][]) matrix).getClass().getComponentType().getComponentType();
		T[][] newMatrix = (T[][]) Array.newInstance(componentType, newRows, newCols);
		for (int i = 0; i < Math.min(((T[][]) matrix).length, newRows); i++) {
			System.arraycopy(((T[][]) matrix)[i], 0, newMatrix[i], 0, Math.min(((T[][]) matrix)[i].length, newCols));
		}
		return newMatrix;
	}

	@SuppressWarnings("unchecked")
	public static <T> String matrixToString(Object matrix) {
		StringBuilder sb = new StringBuilder();
		for (T[] row : (T[][]) matrix) {
			sb.append("[ ");
			for (T item : row) {
				sb.append(item).append(" ");
			}
			sb.append("]\n");
		}
		return sb.toString();
	}
}
