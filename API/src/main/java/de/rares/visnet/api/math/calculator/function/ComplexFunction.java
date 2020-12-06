package de.rares.visnet.api.math.calculator.function;

import de.rares.visnet.api.math.calculator.ParserManager;

import java.util.ArrayList;
import java.util.List;


public class ComplexFunction {

	
	public boolean degree = false;


	private String f;

	public ComplexFunction(final String f) {
		this.f = f.trim().replaceAll(" ", "").toLowerCase();
		degree = ParserManager.getInstance().isDeegre();

	}


	public String getF() {
		return f;
	}


	public void setF(final String f) {
		this.f = f;
	}


	private ComplexNumber eval(final String f, final List<ComplexNumber> values, final List<String> variables)
			throws IllegalArgumentException {
		ComplexNumber value = new ComplexNumber(0, 0);
		String number = "";
		String function = "";

		boolean hasNumber = false;
		boolean hasFunction = false;
		boolean isImaginary = false;

		for (int i = 0; i < f.length(); i++) {
			final char character = f.charAt(i);

			if (character >= '0' && character <= '9') {

				hasNumber = true;
				number = number + character;
				if (i == (f.length() - 1)) {
					value = new ComplexNumber(new Double(number), 0);
					number = "";
					hasNumber = false;
				}

			} else if (character == '+') {

				if (hasNumber && !isImaginary) {
					final Double numb = new Double(number);
					final String new_f = f.substring(i + 1, f.length());
					value = ComplexNumber.add(new ComplexNumber(numb, 0), eval(new_f, values, variables));
					i = i + new_f.length();
					hasNumber = false;
					number = "";
				} else if (hasNumber && isImaginary) {
					final Double numb = new Double(number);
					final String new_f = f.substring(i + 1, f.length());
					value = ComplexNumber.add(new ComplexNumber(0, numb), eval(new_f, values, variables));
					i = i + new_f.length();
					hasNumber = false;
					isImaginary = false;
					number = "";
				} else if (hasFunction) {
					final String new_f = f.substring(i + 1, f.length());
					value = ComplexNumber.add(eval(function, values, variables), eval(new_f, values, variables));
					i = i + new_f.length();
					hasFunction = false;
					function = "";

				} else {
					final String new_f = f.substring(i + 1, f.length());
					value = ComplexNumber.add(value, eval(new_f, values, variables));
					i = i + new_f.length();
				}

			} else if (character == '*') {

				if (hasNumber && !isImaginary) {
					final Double numb = new Double(number);
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = ComplexNumber.mul(new ComplexNumber(numb, 0), eval(new_f, values, variables));
					i = i + new_f.length();
					hasNumber = false;
					number = "";

				} else if (hasNumber && isImaginary) {
					final Double numb = new Double(number);
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = ComplexNumber.mul(new ComplexNumber(0, numb), eval(new_f, values, variables));
					i = i + new_f.length();
					hasNumber = false;
					isImaginary = false;
					number = "";
				} else if (hasFunction) {
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = ComplexNumber.mul(eval(function, values, variables), eval(new_f, values, variables));
					i = i + new_f.length();
					hasFunction = false;
					function = "";
				} else {
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = ComplexNumber.mul(value, eval(new_f, values, variables));
					i = i + new_f.length();
				}

			} else if (character == '-') {

				if (hasNumber && !isImaginary) {
					final Double numb = new Double(number);
					final String new_f = nextMinusFunction(f.substring(i + 1, f.length()));
					value = ComplexNumber.sub(new ComplexNumber(numb, 0), eval(new_f, values, variables));
					i = i + new_f.length();
					hasNumber = false;
					number = "";
				} else if (hasNumber && isImaginary) {
					final Double numb = new Double(number);
					final String new_f = nextMinusFunction(f.substring(i + 1, f.length()));
					value = ComplexNumber.sub(new ComplexNumber(0, numb), eval(new_f, values, variables));
					i = i + new_f.length();
					hasNumber = false;
					isImaginary = false;
					number = "";

				} else if (hasFunction) {
					final String new_f = nextMinusFunction(f.substring(i + 1, f.length()));
					value = ComplexNumber.sub(eval(function, values, variables), eval(new_f, values, variables));
					i = i + new_f.length();
					hasFunction = false;
					function = "";

				} else {
					final String new_f = nextMinusFunction(f.substring(i + 1, f.length()));
					value = ComplexNumber.sub(value, eval(new_f, values, variables));
					i = i + new_f.length();
				}

			} else if (character == '/') {

				if (hasNumber && !isImaginary) {
					final Double numb = new Double(number);
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = ComplexNumber.div(new ComplexNumber(numb, 0), eval(new_f, values, variables));
					i = i + new_f.length();
					hasNumber = false;
					number = "";
				} else if (hasNumber && isImaginary) {
					final Double numb = new Double(number);
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = ComplexNumber.div(new ComplexNumber(0, numb), eval(new_f, values, variables));
					i = i + new_f.length();
					hasNumber = false;
					isImaginary = false;
					number = "";

				} else if (hasFunction) {
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = ComplexNumber.div(eval(function, values, variables), eval(new_f, values, variables));
					i = i + new_f.length();
					hasFunction = false;
					function = "";

				} else {
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = ComplexNumber.div(value, eval(new_f, values, variables));
					i = i + new_f.length();
				}

			} else if (character == '^') {

				if (hasNumber && !isImaginary) {
					final Double numb = new Double(number);
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = ComplexNumber.pow(eval(new_f, values, variables), numb);
					i = i + new_f.length();
					hasNumber = false;
					number = "";
				} else if (hasNumber && isImaginary) {
					final Double numb = new Double(number);
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = ComplexNumber.pow(eval(new_f, values, variables), new ComplexNumber(0, numb));
					i = i + new_f.length();
					hasNumber = false;
					isImaginary = false;
					number = "";

				} else if (hasFunction) {
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = ComplexNumber.pow(eval(function, values, variables), eval(new_f, values, variables));
					i = i + new_f.length();
					hasFunction = false;
					function = "";

				} else {
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = ComplexNumber.pow(value, eval(new_f, values, variables));
					i = i + new_f.length();
				}

			} else if (character == '.') {
				if (i == (f.length() - 1)) {
					throw new IllegalArgumentException("The function is not well-formed");
				}
				if (hasNumber && (number.length() > 0)) {
					number = number + character;
				}

			} else if (character == '(') {
				if (i == (f.length() - 1)) {
					throw new IllegalArgumentException("The function is not well-formed");
				}

				final String new_f = f.substring(i + 1, nextBracket(f));
				if (hasFunction) {
					if (function.equals(Constants.SIN)) {
						value = eval(new_f, values, variables).sin();

					} else if (function.equals(Constants.COS)) {
						value = eval(new_f, values, variables).cos();

					} else if (function.equals(Constants.TAN)) {
						value = eval(new_f, values, variables).tan();

					} else if (function.equals(Constants.SINH)) {
						value = eval(new_f, values, variables).sinh();

					} else if (function.equals(Constants.COSH)) {
						value = eval(new_f, values, variables).cosh();

					} else if (function.equals(Constants.TANH)) {
						value = eval(new_f, values, variables).tanh();

					} else if (function.equals(Constants.ASIN)) {
						value = eval(new_f, values, variables).asin();

					} else if (function.equals(Constants.ACOS)) {
						value = eval(new_f, values, variables).acos();

					} else if (function.equals(Constants.ATAN)) {
						value = eval(new_f, values, variables).atan();
					} else if (function.equals(Constants.LN)) {
						value = eval(new_f, values, variables).log();
					} else if (function.equals(Constants.LOG)) {
						value = eval(new_f, values, variables).log10();
					} else if (function.equals(Constants.SQRT)) {
						value = eval(new_f, values, variables).sqrt();
					} else if (function.equals(Constants.CBRT)) {
						value = ComplexNumber.cbrt(eval(new_f, values, variables));
					} else {
						throw new IllegalArgumentException("The function is not well-formed");
					}

					hasFunction = false;
					function = "";

				} else {
					value = eval(new_f, values, variables);
				}
				i = i + new_f.length() + 1;

			} else if (character == ')') {
				throw new IllegalArgumentException(" '(' is not finished ");

			} else if (character == ' ') {

			} else if (character == 'i') {
				if (!hasFunction) {
					if (hasNumber) {

						value = new ComplexNumber(0, new Double(number));
						number = "";
						isImaginary = true;
					} else {
						value = new ComplexNumber(0, 1);
						isImaginary = true;
					}
				} else {

					function = function + character;
					hasFunction = true;

					if (i == (f.length() - 1)) {

						if (function.equals(Constants.E)) {
							value = new ComplexNumber(Math.E, 0);
						} else if (function.equals(Constants.PI)) {
							value = new ComplexNumber(Math.PI, 0);
						} else {
							if (function.length() == 1) {
								final int n = variables.indexOf(function);
								if (n >= 0) {
									value = values.get(n);
								} else {
									throw new IllegalArgumentException("function is not well defined");
								}

							} else {
								throw new IllegalArgumentException("function is not well defined");
							}
						}
					}
				}

			} else if (character == 'j') {
				if (!hasFunction) {
					if (hasNumber) {
						value = new ComplexNumber(0, new Double(number));
						isImaginary = true;
					} else {
						value = new ComplexNumber(0, 1);
						isImaginary = true;
					}
				} else {
					function = function + character;
					hasFunction = true;

					if (i == (f.length() - 1)) {

						if (function.equals(Constants.E)) {
							value = new ComplexNumber(Math.E, 0);
						} else if (function.equals(Constants.PI)) {
							value = new ComplexNumber(Math.PI, 0);
						} else {
							if (function.length() == 1) {
								final int n = variables.indexOf(function);
								if (n >= 0) {
									value = values.get(n);
								} else {
									throw new IllegalArgumentException("function is not well defined");
								}

							} else {
								throw new IllegalArgumentException("function is not well defined");
							}
						}

					}
				}

			} else if (isValidCharacter(character)) {
				function = function + character;
				hasFunction = true;

				if (i == (f.length() - 1)) {

					if (function.equals(Constants.E)) {
						value = new ComplexNumber(Math.E, 0);

					} else if (function.equals(Constants.PI)) {
						value = new ComplexNumber(Math.PI, 0);
					} else {
						if (function.length() == 1) {
							final int n = variables.indexOf(function);
							if (n >= 0) {
								value = values.get(n);
							} else {
								throw new IllegalArgumentException("function is not well defined");
							}

						} else {
							throw new IllegalArgumentException("function is not well defined");
						}
					}
				}
			} else {
				throw new IllegalArgumentException("Invalid character:" + character);
			}
		}
		return value;

	}


	private String nextFunction(final String f) throws IllegalArgumentException {
		String result = "";

		for (int i = 0; i < f.length(); i++) {
			final char character = f.charAt(i);

			if (character == '+' || character == '*' || character == '-' || character == '/') {
				i = f.length();
			} else if (character == '^') {
				result = result + character;
			} else if (character == '.') {
				result = result + character;
			} else if (character == '(') {
				final String new_f = f.substring(i, nextBracket(f) + 1);
				result = result + new_f;
				i = (i + new_f.length()) - 1;
			} else if (character == ')') {
				throw new IllegalArgumentException(" '(' is not finished ");
			} else if (character == ' ') {
				result = result + character;
			} else if (isValidNumericAndCharacter(character)) {
				result = result + character;
			} else {
				throw new IllegalArgumentException("Invalid character:" + character);
			}
		}
		return result;

	}


	private String nextMinusFunction(final String f) throws IllegalArgumentException {
		String result = "";

		for (int i = 0; i < f.length(); i++) {
			final char character = f.charAt(i);

			if (character == '+') {
				i = f.length();
			} else if (character == '*') {
				result = result + character;
			} else if (character == '-') {
				i = f.length();
			} else if (character == '/') {
				result = result + character;
			} else if (character == '^') {
				result = result + character;
			} else if (character == '.') {
				result = result + character;
			} else if (character == '(') {
				final String new_f = f.substring(i, nextBracket(f) + 1);
				result = result + new_f;
				i = (i + new_f.length()) - 1;
			} else if (character == ')') {
				throw new IllegalArgumentException(" '(' is not finished ");
			} else if (character == ' ') {
				result = result + character;
			} else if (isValidNumericAndCharacter(character)) {
				result = result + character;
			} else {
				throw new IllegalArgumentException("Invalid character:" + character);
			}

		}
		return result;
	}


	private boolean isValidCharacter(final char character) {
		boolean result = false;
		if ((character >= 'a' && character <= 'z')) {
			result = true;
		}
		return result;
	}


	private boolean isValidNumericAndCharacter(final char character) {
		boolean result = false;
		if ((character >= 'a' && character <= 'z') || (character >= '0' && character <= '9')) {
			result = true;
		}
		return result;
	}

	private int nextBracket(final String f) throws IllegalArgumentException {
		int result = 0;
		int count = 0;
		for (int i = 0; i < f.length(); i++) {
			final char character = f.charAt(i);
			if (character == '(') {
				result = i;
				count++;
			} else if (character == ')') {
				result = i;
				count--;
				if (count == 0) {
					return i;
				}
			} else {
				result = i;
			}
		}

		if (count != 0) {
			throw new IllegalArgumentException("( is not finished");
		}
		return result;
	}

	public ComplexNumber getValue(final List<ComplexNumber> values, final List<String> variables) throws IllegalArgumentException {
		final List<String> vars = new ArrayList<String>();
		for (final String string : variables) {
			vars.add(string.trim().replaceAll(" ", "").toLowerCase());
		}
		return eval(f, values, vars);
	}
}
