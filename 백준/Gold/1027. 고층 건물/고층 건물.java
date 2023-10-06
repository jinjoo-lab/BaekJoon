import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static double[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());

        dp = new double[n+1];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=n;i++){
            dp[i] = Double.parseDouble(st.nextToken());
        }

        int[] count = new int[n+1];


        for(int i=1;i<=n;i++) {
            int c = 0;
            double tmp = 0;

            for (int j = i - 1; j >= 1; j--) {
                double per = (dp[i] - dp[j]) / (i - j);

                if(j == i-1 || tmp > per){
                    c = c + 1;
                    tmp = per;
                }
            }

            tmp = 0;

            for(int j=i+1;j<=n;j++){
                double per = (dp[i] - dp[j]) / (i - j);

                if(j == i+1 || tmp < per){
                    c = c + 1;
                    tmp = per;
                }
            }

            count[i] = c;
        }



        int max = 0;
        for(int i=1;i<=n;i++){
            max = Math.max(max,count[i]);
        }
        System.out.println(max);
        br.close();
    }

    static void print(int[] count){
        for(int i=1;i<=n;i++){
            System.out.print(count[i]+" ");
        }
        System.out.println();
    }
}
