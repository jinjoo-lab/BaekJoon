import java.util.*;
import java.io.*;
public class Main {
    static int n = 0;
    static int m = 0;

    static int[] dp;

    static int[][] bag;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dp = new int[m+1];
        bag = new int[n][2];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(bf.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            bag[i][0] = x;
            bag[i][1] = y;
        }

        int max = 0;

        for(int i=0;i<n;i++){
            int curT = bag[i][0];
            int curP = bag[i][1];

            for(int j = m; j >=0 ; j--){
                if(j >= curT) {
                    dp[j] = Math.max(dp[j], dp[j - curT] + curP);
                }

                max = Math.max(max,dp[j]);
            }

        }

        System.out.println(max);
        bf.close();
    }

}

