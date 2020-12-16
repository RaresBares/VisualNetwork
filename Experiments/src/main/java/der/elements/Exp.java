package der.elements;

public class Exp extends Element {

    public String term = "x";
    Element top;
    Element bottom;
    public Exp(Element bottom,Element top) {
        this.top = top;
        this.bottom = bottom;
    }

    @Override
    public String getDerivative() {
        String res = "(ln(" + bottom + "))*(" + bottom  +")^(" + top + ")" ;
        if(!top.equals("x")){
            res += "*"  + top.getDerivative();
        }
        return res;
    }


}
