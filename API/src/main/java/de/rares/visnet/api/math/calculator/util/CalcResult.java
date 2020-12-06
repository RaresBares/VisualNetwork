package de.rares.visnet.api.math.calculator.util;

import de.rares.visnet.api.math.calculator.function.ComplexNumber;


public class CalcResult {


	private Double value;


	private ComplexNumber complexValue;


	private boolean complex;


	public Double getValue() {
		return value;
	}


	public void setValue(final Double value) {
		this.value = value;
		complex = false;
	}


	public ComplexNumber getComplexValue() {
		return complexValue;
	}


	public void setComplexValue(final ComplexNumber complexValue) {
		this.complexValue = complexValue;
		complex = true;
	}


	public boolean isComplex() {
		return complex;
	}

	public void setComplex(final boolean complex) {
		this.complex = complex;
	}

}