package de.rares.visnet.api.math.derivative.derivative.elements;

public class X extends Element {

    public X(String target){
        super.term = target;
    }
    @Override
    public String getDerivative() {
        return  "1";
    }
}
