package der.elements;

import der.Parser;

public class Pow extends Element {

    Element top;
    Element bottom;

    public Pow(Element bottom,Element top) {
        this.top = top;
        this.bottom = bottom;

    }


    public Pow(String bottoms,String tops) {

        super.term = "(" + "bottoms "+ ")^(" + tops + ")";
        Element top = Parser.match(tops);
        Element bottom = Parser.match(bottoms);
        this.top = top;
        this.bottom = bottom;
    }


    @Override
    public String getDerivative() {
        String res = "(" + top.term + ")*(" + bottom.term  +")^(" + top.term + "-1)";
        return res;
    }
}
