import java.util.*;
import java.io.*;
public class Main {

    static double a;
    static double b;
    static double[][][] dp = new double[19][19][19];
    static HashSet<Integer> set = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        double tmpA = Double.parseDouble(br.readLine());
        double tmpB = Double.parseDouble(br.readLine());

        a = tmpA / 100;
        b = tmpB / 100;

        //0 , 1 , 4 , 6 , 8 , 9 , 10 , 12 , 14 , 15 , 16 , 18
        set.add(0);
        set.add(1);
        set.add(4);
        set.add(6);
        set.add(8);
        set.add(9);
        set.add(10);
        set.add(12);
        set.add(14);
        set.add(15);
        set.add(16);
        set.add(18);

        dp[0][0][0] = 1;

        double result = 0;

        for(int i = 1 ; i <= 18 ; i++) {
            for(int j = 0 ; j <= i - 1 ; j++) {
                for(int k = 0 ; k <= i - 1; k++) {
                    dp[i][j][k] += dp[i-1][j][k] * ((1 - a) * (1 - b));
                    dp[i][j+1][k] += dp[i-1][j][k] * ((a) * (1 - b));
                    dp[i][j][k+1] += dp[i-1][j][k] * ((1 - a) * (b));
                    dp[i][j+1][k+1] += dp[i-1][j][k] * (a * b);
                }
            }
        }


        for(int i = 0 ; i <= 18 ; i++) {
            for(int j = 0 ; j <= 18 ; j++) {
                if(set.contains(i) && set.contains(j)) {
                    result += dp[18][i][j];
                }
            }
        }

        System.out.println(1 - result);
        br.close();
    }
}
