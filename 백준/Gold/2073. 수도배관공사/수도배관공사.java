import java.util.*;
import java.io.*;

public class Main {

    static int d,p;
    static int[][] input;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        d = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        input = new int[p+1][2];

        for(int i = 1 ; i <= p ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            input[i][0] = v1;
            input[i][1] = v2;
        }

        int[] dp = new int[d+1];

        dp[0] = Integer.MAX_VALUE;

        for(int i = 1 ; i <= p ; i++) {

            int curD = input[i][0];
            int curL = input[i][1];

            for(int j = d ; j >= curD ; j--) {

                if(dp[j - curD] != 0) {
                    dp[j] = Math.max(dp[j], Math.min(dp[j - curD], curL));
                }

            }
        }

        System.out.println(dp[d]);

        br.close();
    }
}
