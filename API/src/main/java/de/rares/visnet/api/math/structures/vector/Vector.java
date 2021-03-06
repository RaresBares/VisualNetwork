package de.rares.visnet.api.math.structures.vector;


import de.rares.visnet.api.math.structures.NumberType;
import de.rares.visnet.api.math.structures.Structur;
import de.rares.visnet.api.math.structures.matrix.Matrix;

public class Vector<T extends Number> implements Structur {

    public final VectorType vectorType;
    public final int dimension;
    public T[] content;
    public NumberType numberType;

    public Vector(int dimension, VectorType vectorType, NumberType numberType) {
        this.dimension = dimension;
        this.numberType = numberType;
        this.vectorType = vectorType;
    }

    public Vector(int dimension, NumberType numberType, VectorType vectorType, T[] content) {
        this.numberType = numberType;
        this.dimension = dimension;
        this.content = content;

        this.vectorType = vectorType;
    }

    public void setElement(int position, Number value) {

        content[position] = (T) value;
    }

    public <T extends Number> T getElement(int position) {
        return (T) content[position];
    }

    public Structur multiply(Vector target) {


        if (this.vectorType != target.vectorType && this.dimension == target.dimension && this.vectorType == VectorType.ROW) {

            if (numberType == NumberType.REAL && target.numberType == NumberType.REAL) {
                double sum = 0;
                for (int i = 0; i < target.content.length; i++) {
                    sum += this.content[i].doubleValue() * target.content[i].doubleValue();
                }

                Vector result = new Vector(this.dimension, NumberType.REAL, VectorType.NUMBER, new Double[]{sum});

                return result;
            } else if (numberType == NumberType.FULL && target.numberType == NumberType.FULL) {

                long sum = 0;
                for (int i = 0; i < target.content.length; i++) {
                    sum += this.content[i].longValue() * target.content[i].longValue();
                }

                Vector result = new Vector(this.dimension, NumberType.REAL, VectorType.NUMBER, new Long[]{sum});

                return result;


            } else {
                return null;
            }
        } else if (this.vectorType != target.vectorType && this.dimension == target.dimension && this.vectorType == VectorType.COLUMN) {


            if (this.numberType == NumberType.FULL && target.numberType == NumberType.FULL) {

                Long[][] rescont = new Long[this.dimension][this.dimension];
                for (int x = 0; x < this.dimension; x++) {
                    for (int y = 0; y < this.dimension; y++) {


                        rescont[x][y] = (this.content[x].longValue()) * (target.content[y].longValue());

                    }
                }
                Matrix<Long> result = new Matrix<Long>(this.dimension, this.dimension, NumberType.FULL, rescont);
                return result;
            } else {
                Double[][] rescont = new Double[this.dimension][this.dimension];


                for (int x = 0; x < this.dimension; x++) {
                    for (int y = 0; y < this.dimension; y++) {


                        rescont[x][y] = (this.content[x].doubleValue()) * (target.content[y].doubleValue());

                    }
                }
                Matrix<Double> result = new Matrix<Double>(this.dimension, this.dimension, NumberType.REAL, rescont);
                return result;
            }


        } else {
            throw new ArrayIndexOutOfBoundsException("Vectormultiplication-conditions are not holden");
        }


    }


    public Vector add(Vector target) {
        if (this.dimension == target.dimension && this.vectorType == target.vectorType) {
            if (this.numberType == target.numberType) {
                if (this.numberType == NumberType.FULL) {
                    Long[] recont = new Long[this.dimension];
                    for (int x = 0; x < this.dimension; x++) {

                            recont[x] = this.content[x].longValue() + target.content[x].longValue();

                    }
                     Vector<T> result = new Vector<T>(dimension, numberType, vectorType, (T[]) recont);
                    return result;
                } else if (this.numberType == NumberType.REAL) {
                    Double[] recont = new Double[this.dimension];
                    for (int x = 0; x < this.dimension; x++) {

                        recont[x] = this.content[x].doubleValue() + target.content[x].doubleValue();

                    }
                    Vector<T> result = new Vector<T>(dimension, numberType, vectorType, (T[]) recont);
                    return result;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            throw new IllegalArgumentException("xy");

        }
    }

    public Vector subtract(Vector target) {
        if (this.dimension == target.dimension && this.vectorType == target.vectorType) {
            if (this.numberType == target.numberType) {
                if (this.numberType == NumberType.FULL) {
                    Long[] recont = new Long[this.dimension];
                    for (int x = 0; x < this.dimension; x++) {

                        recont[x] = this.content[x].longValue() - target.content[x].longValue();

                    }
                    Vector<T> result = new Vector<T>(dimension, numberType, vectorType, (T[]) recont);
                    return result;
                } else if (this.numberType == NumberType.REAL) {
                    Double[] recont = new Double[this.dimension];
                    for (int x = 0; x < this.dimension; x++) {

                        recont[x] = this.content[x].doubleValue() - target.content[x].doubleValue();

                    }
                    Vector<T> result = new Vector<T>(dimension, numberType, vectorType, (T[]) recont);
                    return result;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            throw new IllegalArgumentException("xy");

        }
    }

    public Vector transform() {


        if(this.vectorType == VectorType.COLUMN){
            Vector result = new Vector(dimension, numberType, VectorType.ROW,content);
            return result;
        }else {
            Vector result = new Vector(dimension, numberType, VectorType.COLUMN,content);
            return result;
        }


    }

    public Vector copy(){
        Vector result = new Vector(dimension, numberType, vectorType,content);
        return result;
    }

    public String toString() {
        String result = "";
        if (vectorType == VectorType.ROW) {
            result += "(";
            for (T t : this.content) {
                if (t != this.content[this.content.length - 1]) {
                    result += t + ", ";
                } else {
                    result += t;
                }
            }
            result += ")";
            return result;
        } else {
            result += "(";
            for (T t : this.content) {
                if (t != this.content[this.content.length - 1]) {
                    result += t + "\n ";
                } else {
                    result += t;
                }
            }
            result += ")";
            return result;
        }


    }

}
