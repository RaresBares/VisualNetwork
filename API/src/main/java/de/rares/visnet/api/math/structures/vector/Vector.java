package de.rares.visnet.api.math.structures.vector;

import de.rares.visnet.api.math.structures.Structur;

import java.math.BigDecimal;

public class Vector<T extends Number> implements Structur {

    private final VectorType vectorType;
    private final int dimension;
    public T[] content;

    public Vector(int dimension, VectorType vectorType) {
        this.dimension = dimension;
        this.vectorType = vectorType;
    }

    public Vector(int dimension, VectorType vectorType, T[] content) {

        this.dimension = dimension;
        this.content = content;

        this.vectorType = vectorType;
    }

    public void setElement(int position, Number value) {

        content[position] = (T) value;
    }

    public T getElement(int position) {
        return content[position];
    }

    public Structur multiply(Structur target) {
        Structur result = null;
        if (target instanceof Vector) {
            Vector v = (Vector) target;
            if (this.vectorType != v.vectorType && this.dimension == v.dimension && this.vectorType == VectorType.ROW) {
                 result = new Vector(this.dimension, VectorType.NUMBER);
                double sum = 0;
                for (Number t : this.content) {
                    for (Number o : v.content) {
                        System.out.println(t + "  |  " + o);
                        sum += t.doubleValue() + o.doubleValue();
                    }
                }
                System.out.println(sum);
               result.setElement(0,sum);

            } else {
                throw new ArrayIndexOutOfBoundsException("Vectormultiplication-conditions are not holden");
            }
        }
return result;

    }


    public Structur add(Structur target) {
        return null;
    }

    public Structur subtract(Structur target) {
        return null;
    }

    public Structur divide(Structur target) {
        return null;
    }

    public Structur pow(Structur target) {
        return null;
    }
}
