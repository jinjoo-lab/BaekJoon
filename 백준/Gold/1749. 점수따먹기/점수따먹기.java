import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int n = 0;
    static int m = 0;
    static int[][] board;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        dp = new long[n+1][m+1];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=m;j++){
                board[i][j] = Integer.parseInt(st.nextToken());

                dp[i][j] += board[i][j];

                dp[i][j] += dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1];
            }
        }

        long max = Integer.MIN_VALUE;

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){

                for(int it = 1; it <= i ; it ++){
                    for(int jt = 1 ; jt <= j ; jt ++){
                        long tmp = dp[i][j] - dp[i][jt-1] - dp[it-1][j] + dp[it-1][jt-1];

                        max = Math.max(max,tmp);
                    }
                }
            }
        }

        System.out.println(max);
        br.close();
    }
}
class Node{
    int v1;
    int v2;
    int c;

    Node(int v1,int v2,int c){
        this.v1 = v1;
        this.v2 = v2;
        this.c = c;
    }
}

