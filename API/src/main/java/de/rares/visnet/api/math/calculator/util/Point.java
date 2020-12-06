package de.rares.visnet.api.math.calculator.util;

import de.rares.visnet.api.math.calculator.function.ComplexNumber;

public class Point {


	private String var;


	private Double value;

	private ComplexNumber complexValue;

	private String stringValue;

	private boolean complex;

	public Point() {

	}


	public Point(final String var, final Double value) {
		this.var = var;
		this.value = value;
		complex = false;
	}


	public Point(final String var, final ComplexNumber value) {
		this.var = var;
		complexValue = value;
		complex = true;
	}


	public Point(final String var, final String value) {
		this.var = var;
		stringValue = value;

	}


	public String getVar() {
		return var;
	}


	public void setVar(final String var) {
		this.var = var;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(final Double value) {
		this.value = value;
	}


	public ComplexNumber getComplexValue() {
		return complexValue;
	}


	public void setComplexValue(final ComplexNumber complexValue) {
		this.complexValue = complexValue;
	}


	public boolean isComplex() {
		return complex;
	}

	public void setComplex(final boolean complex) {
		this.complex = complex;
	}



	public String getStringValue() {
		return stringValue;
	}


	public void setStringValue(final String stringValue) {
		this.stringValue = stringValue;
	}

}