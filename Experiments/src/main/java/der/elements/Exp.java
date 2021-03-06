package der.elements;

import der.Parser;

public class Exp extends Element {


    private String target;
    Element top;
    Element bottom;
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
        String res = "(ln(" + bottom.term+ "))*(" + bottom.term  +")^(" + top.term + ")" ;
        if(!top.equals(target)){
            res += "*("  + top.getDerivative()+")";
        }
        return res;
    }


}
