package der.elements;

public class X extends Element {

    public X(){
        super.term = "x";
    }
    @Override
    public String getDerivative() {
        return  "1";
    }
}
