package der.elements;

public class Const extends Element {



    public Const(String s) {

        super.term = s;
    }

    @Override
    public String getDerivative() {
        return   "0";
    }
}
