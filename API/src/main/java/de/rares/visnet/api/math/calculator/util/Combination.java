package de.rares.visnet.api.math.calculator.util;



public class Combination {


	public static double calc(final int m, final int n) throws IllegalArgumentException {
		if (n < 0) {
			throw new IllegalArgumentException("n cannot be <0");
		}

		double result = 0.0;
		if (m == 0) {
			result = 0.0;
		} else {
			result = (double) Faculty.cal(m, false)
					/ (double) (Faculty.cal(m - n, false) * Faculty.cal(n, false));
		}

		return result;

	}

}
