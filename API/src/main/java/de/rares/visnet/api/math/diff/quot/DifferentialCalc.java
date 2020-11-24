package de.rares.visnet.api.math.diff.quot;

public class DifferentialCalc {


    private final String function;
    private final String[] param;

    public DifferentialCalc(String s, String[] param){
        this.function = s;
        this.param = param;
    }

    public String getQuot(String param){

        String[] parts = function.split("\\+");
        String quot = "";
        for (String part : parts) {
            if(!part.contains("x")){
                break;
            }else {
                String patpart = part.replaceAll("[0-9]", "v");


                for (Patterns value : Patterns.values()) {
                    if(patpart.equals(value.pattern)){
                        quot += value.result;
                    }
                }

            }
        }
    return quot;
    }

}
