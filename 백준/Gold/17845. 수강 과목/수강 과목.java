import java.util.*;
import java.io.*;
public class Main {
    static int n = 0;
    static int m = 0;

    static int[][] bag;

    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        bag = new int[m][2];

        for(int i=0;i<m;i++){
            st = new StringTokenizer(bf.readLine(), " ");

            bag[i][0] = Integer.parseInt(st.nextToken());
            bag[i][1] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n+1];
        int result = 0;

        for(int i=0;i<m;i++){
            int curP = bag[i][0];
            int curT = bag[i][1];

            if(curT > n)
                continue;

            for(int j=n;j>=curT;j--){

                dp[j] = Math.max(dp[j],dp[j-curT] + curP);

                result = Math.max(result,dp[j]);
            }
        }

        System.out.println(result);
        bf.close();
    }
}
