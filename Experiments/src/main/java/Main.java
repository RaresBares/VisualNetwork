import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main() {
        Scanner sc = new Scanner(System.in);
        int cases = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < cases; i++) {
            String[] r = sc.nextLine().split(" ");
            int n = Integer.parseInt(r[0]);
            int p = Integer.parseInt(r[1]);


            for (int j = 0; j < n; j++) {
                String[] skill = sc.nextLine().split(" ");
                System.out.println("Case #" + (j+1) + " " + processCase(p,StringArrToIntArr(skill)));
            }
        }
    }

    public static int processCase( int p, int[] skill){
        Arrays.sort(skill);
        int totalhours = 0;
        int med = skill[p-1];
        for (int i = 0; i < p-1; i++) {
            totalhours += med - skill[p-1];
        }


        return totalhours;


    }
    public static int[] StringArrToIntArr(String[] s) {
        int[] result = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            result[i] = Integer.parseInt(s[i]);
        }
        return result;
    }
}
