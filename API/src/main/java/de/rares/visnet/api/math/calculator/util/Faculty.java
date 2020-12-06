package de.rares.visnet.api.math.calculator.util;




public class Faculty {


	public static int cal(final int m, final boolean valueZero) throws IllegalArgumentException {
		if (m < 0) {
			throw new IllegalArgumentException("the number must be greater than 0");
		}

		int result = 1;
		if (m == 0) {
			if (valueZero) {
				result = 0;
			} else {
				result = 1;
			}
		} else {
			for (int i = m; i > 0; i--) {
				result *= i;
			}
		}
		return result;
	}

}
