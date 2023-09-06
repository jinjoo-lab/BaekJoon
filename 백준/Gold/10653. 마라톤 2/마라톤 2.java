import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int m = 0;

    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][2];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            board[i][0] = x;
            board[i][1] = y;
        }

        int[][] dp = new int[n+1][m+1];

        for(int i=1;i<=n;i++){
            for(int j=0;j<=m;j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[1][0] = 0; // i 지점까지 j개를 건너뛴 최소 값

        for(int i=2;i<=n;i++){

            dp[i][0] = dp[i-1][0] + dis(board[i],board[i-1]);

            for(int j=1;j<=m;j++){

                int idx = j;

                for(int k=i-1;k>=1;k--){

                    if(dp[k][idx] != Integer.MAX_VALUE){
                        dp[i][j] = Math.min(dp[i][j], dp[k][idx] + dis(board[i], board[k]));
                    }

                    idx = idx - 1;

                    if(idx < 0)
                        break;
                }

            }
        }
        
        int result = Integer.MAX_VALUE;
        for(int i=0;i<=m;i++){
            result = Math.min(result , dp[n][i]);
        }
        System.out.println(result);
        br.close();
    }

    static void print(int[][] dp){
        for(int i=1;i<=n;i++){
            for(int j=0;j<=m;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int dis(int[] x1,int[] x2){
        return Math.abs(x1[0] - x2[0]) + Math.abs(x1[1] - x2[1]);
    }
}
