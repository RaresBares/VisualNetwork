package de.rares.visnet.api.math.derivative.derivative.elements;

import de.rares.visnet.api.math.derivative.derivative.Parser;


public class Exp extends Element {


    Element top;
    Element bottom;
    private String target;
    public Exp(Element bottom,Element top) {
        this.top = top;
        this.bottom = bottom;

    }


    public Exp(String bottoms, String tops,String target){
        super.term = "(" + bottoms + ")^(" + tops + ")";
        this.target = target;
        Element top = Parser.match(tops,target);
        Element bottom = Parser.match(bottoms,target);
        this.top = top;
        this.bottom = bottom;

    }

    @Override
    public String getDerivative() {
        String res = "(ln(" + bottom.term+ "))*((" + bottom.term  +")^(" + top.term + "))" ;
        if(!top.equals(target)){
            res += "*("  + top.getDerivative()+")";
        }
        return res.replace("(1)*","");
    }


}
