package der.elements;

import der.Parser;

public class Sum extends Element {

    Element[] cont;

    public Sum(Element[] cont) {
        this.cont = cont;
    }
    public Sum(String[] cont)
    {
        super.term = String.join("+",cont);
        Element[] contr = new Element[cont.length];
        for (int i = 0; i < cont.length; i++) {
            contr[i] = Parser.match(Parser.trimClauses(cont[i]));
        }
        this.cont = contr;
    }
    @Override
    public String getDerivative() {

        String res = "";
        for (int i = 0; i < cont.length; i++) {
            if(i != cont.length-1){

                res += cont[i].getDerivative() + "+";

            }else {
                res += cont[i].getDerivative();

            }
        }
        return res;
    }
}
