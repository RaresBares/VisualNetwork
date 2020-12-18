package de.rares.visnet.api.math.derivative.derivative.elements;

import de.rares.visnet.api.math.derivative.derivative.Parser;


public class Quotient extends Element {


    Element top;
    Element bottom;

    public Quotient(Element top, Element bottom)
    {
        this.bottom = bottom;
        this.top = top;
    }
    public Quotient(String bottoms,String tops,String target) {

        super.term = "(" + "bottoms "+ ")/(" + tops + ")";
        Element top = Parser.match(tops,target);
        Element bottom = Parser.match(bottoms,target);
        this.top = top;
        this.bottom = bottom;
    }


    @Override
    public String getDerivative() {
        String der = "(" + top.getDerivative()+ " * (" + bottom + ") - " + bottom.getDerivative() + " * " + top + ")/((" + bottom + ") * (" + bottom + "))";
        return  der;
    }
}
