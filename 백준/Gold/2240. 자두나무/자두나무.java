import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] board= new int[n];
        for(int i=0;i<n;i++){
            board[i] = Integer.parseInt(br.readLine());
        }
        // n번째 자두에 대해 m번 움직여 k번째 자두 나무 밑에서 먹은 자두의 최대 개수
        int[][][] dp = new int[n][m+1][3];
        if(board[0] == 1)
            dp[0][0][1] = 1;

        else
            dp[0][1][2] = 1;

        for(int i=1;i<n;i++){

            dp[i][0][board[i]] = dp[i-1][0][board[i]] + 1;
            dp[i][0][change(board[i])] = dp[i-1][0][change(board[i])];

            for(int j=1;j<=m;j++){
                dp[i][j][board[i]] = Math.max(dp[i-1][j][board[i]] , dp[i-1][j-1][change(board[i])]) + 1;
                dp[i][j][change(board[i])] = Math.max(dp[i-1][j][change(board[i])] , dp[i-1][j-1][board[i]]);
            }
        }

        int max = 0;
        for(int i =0 ;i<=m;i++){
            max = Math.max(max,Math.max(dp[n-1][i][1],dp[n-1][i][2]));
        }

        System.out.println(max);
        br.close();
    }

    static int change(int cur){
        if(cur == 1)
            return 2;

        return 1;
    }
}
