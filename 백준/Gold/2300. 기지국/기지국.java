import java.util.*;
import java.io.*;

public class Main {

    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][2];

        for(int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[i][0] = x;
            arr[i][1] = y;
        }

        Arrays.sort(arr, (x,y) -> {
            if(x[0] == y[0])
                return x[1] - y[1];

            return x[0] - y[0];
        });

        int[] dp = new int[n+1];

        for(int i = 1 ; i <= n ; i++) {
            dp[i] = Integer.MAX_VALUE;

            int dis = 0;

            for(int j = i ; j >= 1 ; j--) {
                dis = Math.max(dis, Math.abs(arr[j - 1][1]));

                dp[i] = Math.min(dp[i], dp[j - 1] + Math.max(2 * dis, arr[i - 1][0] - arr[j - 1][0]));
            }

        }


        System.out.println(dp[n]);
        br.close();
    }
}
