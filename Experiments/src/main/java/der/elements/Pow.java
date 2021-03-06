package der.elements;

import der.Parser;

public class Pow extends Element {

    private String target;
    Element top;
    Element bottom;

    public Pow(Element bottom,Element top) {
        this.top = top;
        this.bottom = bottom;

    }


    public Pow(String bottoms,String tops,String target) {

        super.term = "(" + bottoms + ")^(" + tops + ")";
        Element top = Parser.match(tops,target);
        Element bottom = Parser.match(bottoms,target);
        this.target = target;
        this.top = top;
        this.bottom = bottom;
    }


    @Override
    public String getDerivative() {
        String res = "(" + top.term + ")*(" + bottom.term  +")^(" + top.term + "-1)";

        if(!bottom.term.equals(target) && bottom.term.contains(target)){
            res +="*(" + Parser.match(bottom.term,target ).getDerivative() + ")";
        }
        return res;
    }
}
