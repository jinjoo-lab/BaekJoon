import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[] board;
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        board = new int[n+1];
        dp = new boolean[n+1][n+1];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=n;i++){
            board[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());

        for(int i=1;i<=n;i++){
            dp[i][i] = true;
        }

        for(int i=1;i<=n-1;i++){
            if(board[i] == board[i+1]){
                dp[i][i+1] = true;
            }
        }

        for(int i=n-1;i>=1;i--){
            for(int j=i+1;j<=n;j++){
                if(i == j || i +1 == j)
                    continue;

                if(board[i] == board[j]){
                    dp[i][j] = dp[i+1][j-1];
                }
            }
        }


        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(dp[x][y]){
                sb.append("1\n");
            }else{
                sb.append("0\n");
            }
        }

        System.out.print(sb);
        br.close();
    }
}
