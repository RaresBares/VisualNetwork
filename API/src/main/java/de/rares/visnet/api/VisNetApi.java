package de.rares.visnet.api;


import de.rares.visnet.api.math.structures.NumberType;
import de.rares.visnet.api.math.structures.matrix.Matrix;
import de.rares.visnet.api.math.structures.vector.Vector;
import de.rares.visnet.api.math.structures.vector.VectorType;

public class VisNetApi {


    public VisNetApi() {


    }

    public static void main(String[] args) {
        Vector v = new Vector<Integer>(3, NumberType.FULL, VectorType.COLUMN, new Integer[]{4, 6, 3});
        Vector v2 = new Vector<Integer>(3, NumberType.FULL, VectorType.ROW, new Integer[]{1, 3, 8});
        Matrix m2 = ((Matrix) v.multiply(v2)).multiply(v);


       System.out.println(m2.toString());


        System.out.println();
    }


}
