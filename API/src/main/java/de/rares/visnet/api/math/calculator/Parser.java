package de.rares.visnet.api.math.calculator;

import de.rares.visnet.api.math.calculator.function.ComplexFunction;
import de.rares.visnet.api.math.calculator.function.ComplexNumber;
import de.rares.visnet.api.math.calculator.function.Function;
import de.rares.visnet.api.math.calculator.function.MultiDimensionalFunction;
import de.rares.visnet.api.math.calculator.util.CalcResult;
import de.rares.visnet.api.math.calculator.util.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




public class Parser {

	public static double SimpleEval(final String function) {

		double result = 0;
		Function f_x = null;

		if ((function != null) && !function.isEmpty()) {
			f_x = new Function(function);
			try {
				result = f_x.getF_xo(0);
			} catch (final IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;

	}


	public static CalcResult eval(final String function) {

		CalcResult result = new CalcResult();
		Function f_x = null;

		if ((function != null) && !function.isEmpty()) {
			try {

				if ((function.toLowerCase().contains("j")) && !function.toLowerCase().contains("x")) {

					result = eval(function, new Point("x", new ComplexNumber(1, 0)));
				} else if (!function.toLowerCase().contains("x")) {
					f_x = new Function(function);
					result.setValue(f_x.getF_xo(0));

				} else {
					throw new IllegalArgumentException("function is not well defined");
				}

			} catch (final IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;

	}


	public static CalcResult eval(final String function, final Point... values) {

		final CalcResult result = new CalcResult();
		Function f_x = null;
		MultiDimensionalFunction f_xs = null;
		ComplexFunction complexFunction = null;

		if ((function != null) && !function.isEmpty()) {

			if (Parser.pointIsComplex(values) || function.toLowerCase().contains("j")) { // ComplexNumber

				complexFunction = new ComplexFunction(function);
				final List<ComplexNumber> valuesList = pointToComplexValue(values);
				final List<String> varsList = pointToVar(values);
				try {
					result.setComplexValue(complexFunction.getValue(valuesList, varsList));
				} catch (final IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {

				try {

					if (values != null) {
						if (values.length == 1) {

							f_x = new Function(function);

							if ((values[0].getStringValue() != null && !values[0].getStringValue().isEmpty())) {
								final CalcResult evaluatedValue = Parser.eval(values[0].getStringValue());
								result.setValue(f_x.getF_xo(evaluatedValue.getValue()));

							} else {
								result.setValue(f_x.getF_xo(values[0].getValue()));
							}

						} else if (values.length > 1) {
							f_xs = new MultiDimensionalFunction(function);
							final List<Double> valuesList = pointToValue(values);
							final List<String> varsList = pointToVar(values);
							result.setValue(f_xs.getValue(valuesList, varsList));
						}

					} else {
						f_x = new Function(function);
						result.setValue(f_x.getF_xo(0));
					}
				}

				catch (final IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}


	public static double eval(final String function, final String[] vars, final Double[] values) {

		double result = 0;
		Function f_x = null;
		MultiDimensionalFunction f_xs = null;
		if ((function != null) && !function.isEmpty()) {
			try {
				if ((((vars == null) || (vars.length < 1)) && (values == null)) || (values.length < 1)) {
					f_x = new Function(function);
					result = f_x.getF_xo(0);
				} else if ((values != null) && (values.length == 1)) {
					f_x = new Function(function);
					result = f_x.getF_xo(values[0]);
				} else if ((vars != null) && (vars.length > 1) && (values != null) && (values.length > 1)) {
					f_xs = new MultiDimensionalFunction(function);
					final List<Double> valuesList = Arrays.asList(values);
					final List<String> varsList = Arrays.asList(vars);
					result = f_xs.getValue(valuesList, varsList);
				}

			} catch (final IllegalArgumentException e) {

				e.printStackTrace();
			}
		}

		return result;

	}


	private static List<Double> pointToValue(final Point... values) {
		final List<Double> result = new ArrayList<Double>();
		for (int i = 0; i < values.length; i++) {
			if ((values[i].getStringValue() != null && !values[i].getStringValue().isEmpty())) {
				final CalcResult evaluatedValue = Parser.eval(values[i].getStringValue());
				result.add(evaluatedValue.getValue());
			} else {
				result.add(values[i].getValue());
			}
		}
		return result;
	}



	private static List<ComplexNumber> pointToComplexValue(final Point... values) {
		final List<ComplexNumber> result = new ArrayList<ComplexNumber>();
		for (int i = 0; i < values.length; i++) {
			if (values[i].isComplex() && (values[i].getStringValue() == null || values[i].getStringValue().isEmpty())) {
				result.add(values[i].getComplexValue());
			} else if ((values[i].getStringValue() != null && !values[i].getStringValue().isEmpty())) {
				final CalcResult evaluatedValue = Parser.eval(values[i].getStringValue());
				if (evaluatedValue.isComplex()) {
					result.add(evaluatedValue.getComplexValue());
				} else {
					result.add(new ComplexNumber(evaluatedValue.getValue(), 0));
				}
			} else {
				result.add(new ComplexNumber(values[i].getValue(), 0));
			}

		}
		return result;
	}


	private static boolean pointIsComplex(final Point... values) {

		boolean result = false;
		for (int i = 0; i < values.length; i++) {
			if (values[i].isComplex() && (values[i].getStringValue() == null || values[i].getStringValue().isEmpty())) {
				result = true;
				break;
			} else {
				if (values[i].getStringValue() != null && !values[i].getStringValue().isEmpty()) {
					final CalcResult evaluatedValue = Parser.eval(values[i].getStringValue());
					if (evaluatedValue.isComplex()) {
						result = true;
						break;

					}

				}
			}

		}
		return result;
	}
private static List<String> pointToVar(final Point... values) {
		final List<String> result = new ArrayList<String>();
		for (int i = 0; i < values.length; i++) {
			result.add(values[i].getVar());
		}
		return result;
	}

	public static CalcResult calc(String term){
		return null;
	}
}
