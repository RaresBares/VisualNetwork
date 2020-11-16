package de.rares.visnet.api.math.structures.matrix;

import de.rares.visnet.api.math.structures.NumberType;
import de.rares.visnet.api.math.structures.Structur;
import de.rares.visnet.api.math.structures.vector.Vector;
import de.rares.visnet.api.math.structures.vector.VectorType;


public class Matrix<T extends Number> implements Structur {

    public int columns; // |
    public int rows;    // -
    public T[][] content;
    public NumberType numberType; // 3.14 // 3


    public Matrix(int columns, int rows, NumberType numberType, T[][] content) {
        this.columns = columns;
        this.rows = rows;
        this.numberType = numberType;
        this.content = content;
        content = (T[][]) new Number[columns][rows];
    }

    public Vector<T> getVector(VectorType vectorType, int position) {


        if (numberType == NumberType.FULL) {
            if (vectorType == VectorType.ROW) {

                Long[] vcont = new Long[columns];
                if (position >= columns) {
                    throw new IndexOutOfBoundsException("There are not so many columns");
                } else {

                    for (int i = 0; i < columns; i++) {
                        vcont[i] = content[i][position].longValue();
                    }
                    Vector result = new Vector<Long>(vcont.length, NumberType.FULL, vectorType, vcont);
                    return result;
                }
            } else if (vectorType == VectorType.COLUMN) {
                Long[] vcont = new Long[rows];
                if (position >= rows) {
                    throw new IndexOutOfBoundsException("There are not so many columns");
                } else {

                    for (int i = 0; i < rows; i++) {
                        vcont[i] = content[position][rows].longValue();
                    }
                }
                Vector result = new Vector<Long>(vcont.length, NumberType.FULL, vectorType, vcont);
                return result;
            } else if (vectorType == VectorType.NUMBER) {
                return null;
            } else {
                return null;
            }
        } else if (numberType == NumberType.REAL) {
            if (vectorType == VectorType.ROW) {
                Double[] vcont = new Double[columns];
                if (position >= columns) {
                    throw new IndexOutOfBoundsException("There are not so many columns");
                } else {

                    for (int i = 0; i < columns; i++) {

                        vcont[i] = content[i][position].doubleValue();
                    }
                }
                Vector result = new Vector<Double>(vcont.length, NumberType.REAL, vectorType, vcont);
                return result;
            } else if (vectorType == VectorType.COLUMN) {
                Double[] vcont = new Double[rows];
                if (position >= rows) {
                    throw new IndexOutOfBoundsException("There are not so many columns");
                } else {

                    for (int i = 0; i < rows; i++) {
                        vcont[i] = content[position][rows].doubleValue();
                    }
                }
                Vector result = new Vector<Double>(vcont.length, NumberType.REAL, vectorType, vcont);
                return result;
            } else if (vectorType == VectorType.NUMBER) {
                return null;
            } else {
                return null;
            }
        } else {

            throw new IndexOutOfBoundsException("");
        }


    }
/*
    public Matrix multiply(Matrix target) {
        if (this.rows == target.columns) {
            if (numberType == NumberType.FULL) {

            } else if (numberType == NumberType.REAL) {

            } else {

            }
        } else {
            throw new ArrayIndexOutOfBoundsException("Matrixmultiplication-conditions are not holden");

        }
    }*/


}
