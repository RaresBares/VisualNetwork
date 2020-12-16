package der.elements;


import der.Parser;

public class Product extends Element {

    Element[] cont;

    public Product(Element[] cont) {
        this.cont = cont;
    }

    public Product(String[] cont)
    {
        super.term = String.join("+",cont);
        System.out.println("initlength " + cont.length);
       Element[] contr = new Element[cont.length];
        for (int i = 0; i < cont.length; i++) {

            contr[i] = Parser.match(Parser.trimClauses(cont[i]));
            System.out.println(cont[i] + " is a " + contr[i]);
        }
        this.cont = contr;
    }


    @Override
    public String getDerivative() {

        String res = "";


        for (Element target : cont) {
            if(res.equals("")){

                res += "(" + target.getDerivative() + ")*";
                System.out.println("der += " + res);
            }else {
                String der = target.getDerivative();
                res += "+(" + der + ")*";
                System.out.println(der + "der2 += " + res);
            }
            for (Element other : cont) {
                if(target != other) {

                    if(cont[cont.length-1] == other) {
                        System.out.println(other+ " is " + other.getDerivative() + " in " + other.term);
                        res += "(" + other.term + ")";
                    }else {
                        System.out.println(other+ " is " + other.getDerivative() + " in " + other.term);
                        res += "(" + other.term + ")*";
                    }
                }
            }

        }

        return res;
    }
}
