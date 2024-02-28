
import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int [][] board;

    static int[] dx = {-1,-1,0};
    static int[] dy = {0,-1,-1};
    static long [][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];

        for(int i = 1 ; i <= n ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1 ; j <= n ; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new long[n+1][n+1][3];

        // U C L

        for(int i = 2 ; i <= n ; i++){

            if(board[1][i] == 1)
                break;

            dp[1][i][2] = 1;
        }

        for(int i = 2 ; i <= n ; i++){
            for(int j = 3 ; j <= n ; j++){

                if(board[i][j] == 1)
                    continue;

                if(board[i-1][j] != 1){
                    dp[i][j][0] += dp[i-1][j][0];
                    dp[i][j][0] += dp[i-1][j][1];
                }

                if(board[i][j-1] != 1){
                    dp[i][j][2] += dp[i][j-1][2];
                    dp[i][j][2] += dp[i][j-1][1];
                }

                boolean isPossible = true;
                for(int k = 0; k < 3 ; k++){

                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(nx < 1 || nx > n || ny < 1 || ny > n){
                        isPossible = false;
                        break;
                    }

                    if(board[nx][ny] == 1){
                        isPossible = false;
                        break;
                    }
                }

                if(isPossible){
                    dp[i][j][1] += dp[i-1][j-1][0];
                    dp[i][j][1] += dp[i-1][j-1][2];
                    dp[i][j][1] += dp[i-1][j-1][1];
                }
            }
        }

        long result = 0;
        result += dp[n][n][0];
        result += dp[n][n][1];
        result += dp[n][n][2];

        System.out.println(result);

        br.close();
    }

    static void print(){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                System.out.print(dp[i][j][0]+" "+dp[i][j][1]+" "+dp[i][j][2]+" , ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
