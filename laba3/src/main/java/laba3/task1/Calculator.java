package laba3.task1;

import java.util.List;

public class Calculator {
	public static double[] calculateLinearRegression(List<DataPoint> dataPoints) {
		double sumX = 0.0;
		double sumY = 0.0;
		double sumXY = 0.0;
		double sumXX = 0.0;
		int n = dataPoints.size();

		for (DataPoint point : dataPoints) {
			sumX += point.x;
			sumY += point.y;
			sumXY += point.x * point.y;
			sumXX += point.x * point.x;
		}

		double meanX = sumX / n;
		double meanY = sumY / n;

		if (sumX == 0)
			return new double[1];

		double b = (sumXY - sumX * meanY) / (sumXX - sumX * meanX);

		double k = meanY - b * meanX;

		return new double[] { k, b };
	}
}
