package de.rares.visnet.api.math.diff.derivative.patterns;

public class Pow extends Pattern {


    public Pow(String partfirst, String secondfirst) {
        super(partfirst, secondfirst);
    }


    public static Pow of(String f) {
        f = f.replace(" ", "");
        if (f.contains("^")) {
            String first = "";
            String second = "";

            if (f.startsWith("(") && f.endsWith(")")) {
                String partfirst = "";
                String partsecond = "";
                int waitingClauses = 0;
                int lastpos = 0;
                for (int i = 0; i < f.toCharArray().length; i++) {


                    char c = f.charAt(i);
                    if (c != '+' && c != '(' && c != ')') {

                    } else if (c == '+') {

                    } else if (c == '(') {


                        waitingClauses += 1;

                    } else if (c == ')') {


                        waitingClauses -= 1;

                        if (waitingClauses == 0) {

                            if (partfirst == "") {

                                partfirst = f.substring(lastpos + 1, i);

                            } else if (partsecond == "") {

                                partsecond = f.substring(lastpos+ 1, i);
                            }
                            i = i + 1;
                            lastpos = i + 1;

                        }

                    }

                }
                return new Pow(partfirst, partsecond);
            }
        }

        return null;

    }

    @Override
    public String getDerivate() {
        return null;
    }


}
