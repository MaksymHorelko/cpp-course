package laba3.task1;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<DataPoint> dataPoints = XMLParser.parseXML("data.xml");

		if (dataPoints != null && dataPoints.size() > 1) {
			double[] coefficients = Calculator.calculateLinearRegression(dataPoints);
			System.out.println("Коефіцієнти прямої y = k * x + b:");
			System.out.println("k = " + coefficients[0]);
			System.out.println("b = " + coefficients[1]);
		} else {
			System.out.println("Недостатньо даних для обчислення регресії.");
		}
	}
}
