package de.rares.visnet.api.math.structures.matrix;

public class Matrix<T> {

    public int columns; // |
    public int rows;    // -
    public T[][] content;
    public Matrix(int columns, int rows){
        this.columns = columns;
        this.rows = rows;
        content = (T[][]) new Object[columns][rows];
    }

  /*  public Matrix multiply(Matrix m){
            if(this.rows == m.columns ){

            }else{

                throw new ArrayIndexOutOfBoundsException("Matrixmultiplication-conditions are not holden");

            }
    }*/


}
