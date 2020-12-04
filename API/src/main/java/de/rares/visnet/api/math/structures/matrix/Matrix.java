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
                        vcont[i] = content[position][i].longValue();
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

                           /* System.out.println("i: " + i + " rows: " + rows + "  result: " + content[i][position].longValue()  +" column: " + position + " vcont length: " + vcont.length + " cont: " +  content[2][position] );
                        System.out.println(Arrays.deepToString(content));
                        System.out.println(this.toString());*/
                        vcont[i] = content[i][position].longValue();
                     //   System.out.println("done");
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

                        vcont[i] = content[position][i].doubleValue();
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


    public Matrix multiply(Structur t) {
        if (t instanceof Matrix) {
            Matrix target = (Matrix) t;
            System.out.println(this.toString());
            System.out.println(target.toString());
            if (this.columns == target.rows) {

                if (numberType == NumberType.FULL) {
                    Long[][] rescont = new Long[this.rows][target.columns];

                    for (int x = 0; x < this.rows; x++) {
                        Vector rowFromThis = this.getVector(VectorType.ROW, x);
                        for (int y = 0; y < target.columns; y++) {
                            Vector columnFromTarget = target.getVector(VectorType.COLUMN, y);


                            System.out.println("multiply: ");
                            System.out.println(rowFromThis.toString());
                            System.out.println("and ");
                            System.out.println(columnFromTarget.toString());

                            Vector prod = (Vector) rowFromThis.multiply(columnFromTarget);
                            if (prod.vectorType == VectorType.NUMBER) {

                                rescont[x][y] = prod.content[0].longValue();
                            }
                        }
                    }

                    Matrix<Long> result = new Matrix<Long>(target.columns, this.rows, NumberType.FULL, rescont);
                    return result;
                } else if (numberType == NumberType.REAL) {
                    Double[][] rescont = new Double[this.rows][target.columns];

                    for (int x = 0; x < this.rows; x++) {
                        for (int y = 0; y < target.columns; y++) {
                            Vector rowFromThis = this.getVector(VectorType.ROW, x);

                            Vector columnFromTarget = target.getVector(VectorType.ROW, y);

                            Vector prod = (Vector) rowFromThis.multiply(columnFromTarget);

                            if (prod.vectorType == VectorType.NUMBER) {
                                rescont[x][y] = prod.content[0].doubleValue();
                            }
                        }
                    }

                    Matrix<Double> result = new Matrix<Double>(target.columns, this.rows, NumberType.REAL, rescont);
                    return result;
                } else {
                    return null;
                }
            } else {
                System.out.println(this.toString());
            System.out.println(target.toString());
                throw new ArrayIndexOutOfBoundsException("Matrixmultiplication-conditions are not holden");

            }
        } else if (t instanceof Vector) {
            Matrix result;
            Vector target = (Vector) t;
            T[][] recont;
            if (target.numberType == NumberType.FULL) {
                if (target.vectorType == VectorType.ROW) {
                    Long[][] precont = new Long[1][target.dimension];
                    for (int i = 0; i < target.content.length; i++) {
                        precont[0][i] = target.content[i].longValue();
                    }
                    recont = (T[][]) precont;
                    result = new Matrix(target.dimension,1, target.numberType, recont);
                    return this.multiply(result);
                } else if (target.vectorType == VectorType.COLUMN) {
                    Long[][] precont = new Long[target.dimension][1];
                    for (int i = 0; i < target.content.length; i++) {
                        precont[i][0] = target.content[i].longValue();
                    }
                    recont = (T[][]) precont;
                    result = new Matrix(1, target.dimension, target.numberType, recont);
                    return this.multiply(result);
                } else {
                    return null;
                }


            } else if (target.numberType == NumberType.REAL) {
                if (target.vectorType == VectorType.ROW) {
                    Double[][] precont = new Double[1][target.dimension];
                    for (int i = 0; i < target.content.length; i++) {
                        precont[0][i] = target.content[i].doubleValue();
                    }
                    recont = (T[][]) precont;
                    result = new Matrix( target.dimension,1, target.numberType, recont);
                    return this.multiply(result);
                } else if (target.vectorType == VectorType.COLUMN) {
                    Double[][] precont = new Double[target.dimension][1];
                    for (int i = 0; i < target.content.length; i++) {
                        precont[i][0] = target.content[i].doubleValue();
                    }
                    recont = (T[][]) precont;
                    result = new Matrix(1, target.dimension, target.numberType, recont);
                    return this.multiply(result);
                } else {
                    return null;
                }

            } else {
                return null;
            }


        } else {
            return null;
        }
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public NumberType getNumberType() {
        return numberType;
    }


    public T getElement(int row, int column) {
        return content[row][column];
    }

    public void setElement(int row, int column, T value) {
        content[row][column] = value;
    }


    public Matrix add(Matrix target) {


        if (this.columns == target.columns && this.rows == target.rows) {
            if (this.numberType == target.numberType) {
                if (this.numberType == NumberType.FULL) {
                    Long[][] recont = new Long[this.rows][this.columns];
                    for (int x = 0; x < this.rows; x++) {
                        for (int y = 0; y < this.columns; y++) {
                            recont[x][y] = this.content[x][y].longValue() + target.content[x][y].longValue();
                        }
                    }
                    Matrix result = new Matrix(this.columns, this.columns, NumberType.FULL, recont);
                    return result;
                } else if (this.numberType == NumberType.REAL) {
                    Double[][] recont = new Double[this.rows][this.columns];
                    for (int x = 0; x < this.rows; x++) {
                        for (int y = 0; y < this.columns; y++) {
                            recont[x][y] = this.content[x][y].doubleValue() + target.content[x][y].doubleValue();
                        }
                    }
                    Matrix result = new Matrix(this.columns, this.columns, NumberType.REAL, recont);
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

    public Matrix transform() {


        if (this.numberType == NumberType.FULL) {

            for (int x = 0; x < this.rows; x++) {
                for (int y = 0; y < this.columns; y++) {
                    if (x < y) {
                        Long xy = this.content[x][y].longValue();
                        Long yx = this.content[y][x].longValue();
                        System.out.println("switch: " + xy + " and " + yx);
                        this.content[x][y] = (T) yx;
                        this.content[y][x] = (T) xy;
                    }
                }
            }

            return this;


        } else if (this.numberType == NumberType.REAL) {

            for (int x = 0; x < this.rows; x++) {
                for (int y = 0; y < this.columns; y++) {
                    if (x < y) {
                        Double xy = this.content[x][y].doubleValue();
                        Double yx = this.content[y][x].doubleValue();

                        this.content[x][y] = (T) yx;
                        this.content[y][x] = (T) xy;
                    }
                }
            }

            return this;

        } else {
            throw new IllegalArgumentException("xy");

        }

    }

    public Matrix subtract(Matrix target) {


        if (this.columns == target.columns && this.rows == target.rows) {
            if (this.numberType == target.numberType) {
                if (this.numberType == NumberType.FULL) {
                    Long[][] recont = new Long[this.rows][this.columns];
                    for (int x = 0; x < this.rows; x++) {
                        for (int y = 0; y < this.columns; y++) {
                            recont[x][y] = this.content[x][y].longValue() - target.content[x][y].longValue();
                        }
                    }
                    Matrix result = new Matrix(this.columns, this.columns, NumberType.FULL, recont);
                    return result;
                } else if (this.numberType == NumberType.REAL) {
                    Double[][] recont = new Double[this.rows][this.columns];
                    for (int x = 0; x < this.rows; x++) {
                        for (int y = 0; y < this.columns; y++) {
                            recont[x][y] = this.content[x][y].doubleValue() - target.content[x][y].doubleValue();
                        }
                    }
                    Matrix result = new Matrix(this.columns, this.columns, NumberType.REAL, recont);
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

    public String toString() {
        String result = "";
        for (int i = 0; i < this.rows; i++) {
            result += "(|";
            for (int j = 0; j < this.columns; j++) {

                if ((j + 1) < this.columns) {
                    result += " " + this.content[i][j] + ", ";
                } else {
                    result += " " + this.content[i][j] + " ";
                }
            }
            result += "|)";
            result += "\n";
        }
        return result;

    }

}
