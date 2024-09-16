import java.util.*;
import java.io.*;

public class Main {

    static int tt,n;
    static int[] card;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        tt = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= tt ; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());

            card = new int[n + 1];
            dp = new int[n+1][n+1];

            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 1; i <= n ; i++) {
                card[i] = Integer.parseInt(st.nextToken());
            }


            go(1,n,true);
            sb.append(dp[1][n]+"\n");
        }

        System.out.print(sb);
        br.close();
    }

    static int go(int l,int r,boolean turn) {

        if(l > r)
            return 0;

        if(dp[l][r] != 0)
            return dp[l][r];


        if(turn)
            dp[l][r] = Math.max(card[l] + go(l + 1 , r, false) , card[r] + go(l, r - 1, false));

        else
            dp[l][r] = Math.min(go(l+1,r,true), go(l,r - 1, true));

        return dp[l][r];
    }
}
