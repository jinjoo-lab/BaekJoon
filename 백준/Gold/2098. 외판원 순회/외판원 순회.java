
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
     static int n = 0;
     static int[][] board;

     static int[][] dp;

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        board = new int[n][n];

        for(int i = 0 ; i < n ; i++){

            st = new StringTokenizer(br.readLine(), " ");

            for(int j = 0 ; j < n ; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[n+1][1 << 16];

        System.out.println(go(0,1));
        br.close();
    }

    static int INF = 16_000_001;
    static int go(int cur,int v){

        if(v == (1 << n) - 1){
            if(board[cur][0] != 0){
                return board[cur][0];
            }else{
                return INF;
            }
        }

        if(dp[cur][v] != 0){
            return dp[cur][v];
        }

        dp[cur][v] = INF;

        for(int i = 0 ; i < n ; i ++){

            if(board[cur][i] == 0 || (v & (1 <<i)) != 0)
                continue;

            dp[cur][v] = Math.min(dp[cur][v], go(i,(v | (1 << i))) + board[cur][i]);

        }

        return dp[cur][v];
    }
}
