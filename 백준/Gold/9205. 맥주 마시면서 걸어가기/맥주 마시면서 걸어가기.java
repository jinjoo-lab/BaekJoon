import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int t = 0;
    static int n = 0;

    static int[][] dis;

    static boolean[][] dp;

    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());

        for(int a = 1; a <= t ; a++) {
            st = new StringTokenizer(br.readLine()," ");
            n = Integer.parseInt(st.nextToken());

            n = n + 2;

            board = new int[n+1][3];

            for(int i = 1 ; i <= n ; i++){
                st = new StringTokenizer(br.readLine()," ");

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                board[i][0] = x;
                board[i][1] = y;
            }

            dp = new boolean[n+1][n+1];

            for(int i = 1 ; i <= n ; i++){
                for(int j = 1 ; j <= n ; j++){

                    if(i == j)
                        continue;

                    dp[i][j] = getDis(board[i][0],board[i][1],board[j][0],board[j][1]);
                }
            }


            for(int k = 1; k <= n ; k++){
                for(int i = 1 ; i <= n ; i++){
                    for(int j = 1 ; j <= n ; j++){
                        dp[i][j] = dp[i][j] || (dp[i][k] && dp[k][j]);
                    }
                }
            }

            if(dp[1][n]){
                sb.append("happy\n");
            }else{
                sb.append("sad\n");
            }

        }

        System.out.print(sb);
        br.close();
    }

    static boolean getDis(int x,int y,int nx,int ny){
        return (Math.abs(x - nx) + Math.abs(y - ny)) <= 1000;
    }

}
