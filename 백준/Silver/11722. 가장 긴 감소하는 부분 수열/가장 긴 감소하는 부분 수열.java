import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] dp;

    static int[] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        dp = new int[n + 1];
        board = new int[n+1];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=n;i++){
            board[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = 1;
        int max = 1;
        for(int i=2;i<=n;i++){
            dp[i] = 1;

            for(int j=i-1;j>=1;j--){
                if(board[i] < board[j]){
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }

            max = Math.max(max,dp[i]);
        }
        System.out.println(max);
        br.close();
    }

    static void print(){
        for(int i=1;i<=n;i++){
            System.out.print(dp[i]+" ");
        }
        System.out.println();
    }
}
