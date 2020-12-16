package de.rares.visnet.api.math.derivative.derivative;

public class StructParser {



        public static void main(String[] args) {
            String func = "(((((x)))))";
            int i = 4;

            System.out.println(trimClauses(func));
        }



        public static int getClosingClause(String t, int pos){
            if(t.charAt(pos) == '('){

                int waiting = 1 ;
                for (int i = pos + 1; i < t.length(); i++) {

                    if(t.charAt(i) == '('){

                        waiting++;
                    }else if(t.charAt(i) == ')'){
                        waiting--;

                        if(waiting==0){
                            return i;
                        }
                    }
                }


            }

            return -1;
        }


        public static String trimClauses(String function){
            String res = function;
            if(function.charAt(0) == '('){
                if(getClosingClause(function,0) == function.length() - 1){
                    res = function.substring(1, function.length()-1);
                    res = trimClauses(res);
                }else {

                }

            }
            return res;
        }
    }


