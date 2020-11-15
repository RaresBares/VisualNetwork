package de.rares.visnet.api;


import de.rares.visnet.api.math.structures.vector.Vector;
import de.rares.visnet.api.math.structures.vector.VectorType;

public class VisNetApi {

    public static void main(String[] args) {
        Vector v = new Vector<Integer>(3, VectorType.ROW, new Integer[]{1, 2, 3});
        Vector v2 = new Vector<Integer>(3, VectorType.COLUMN, new Integer[]{1, 2, 3});
        System.out.println(((Vector)v.multiply(v2)).getElement(0));
    }
    public VisNetApi(){



    }


}
