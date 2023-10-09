import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        board = new int[n+1];
        int[][] dp = new int[n+1][2];

        for(int i=1;i<=n;i++){
            board[i] = Integer.parseInt(st.nextToken());
        }
        dp[1][0] = board[1];
        dp[1][1] = 0;

        int tmp = dp[1][0];

        for(int i=2;i<=n;i++){

            dp[i][0] = Math.max(dp[i-1][0] + board[i],board[i]); // 삭제 x
            dp[i][1] = Math.max(dp[i-1][0],dp[i-1][1] + board[i]); // 삭제 o

            tmp = Math.max(tmp,Math.max(dp[i][0],dp[i][1]));
        }
        System.out.println(tmp);

        br.close();
    }
}
