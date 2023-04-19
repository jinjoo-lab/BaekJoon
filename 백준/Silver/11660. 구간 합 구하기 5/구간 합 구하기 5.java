import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[][] board = new int[1025][1025];
    static int[][] dp = new int[1025][1025];

    static int[] re = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1;j<=n;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = dp[i][j-1]  + board[i][j];
            }
        }

        for(int i=1;i<=n;i++) {
            for (int j = 1; j <= n; j++){
                dp[i][j] += dp[i-1][j];
            }
        }

        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            {
                int sx = Integer.parseInt(st.nextToken());
                int sy = Integer.parseInt(st.nextToken());
                int lx = Integer.parseInt(st.nextToken());
                int ly = Integer.parseInt(st.nextToken());

                int result = dp[lx][ly] - dp[sx-1][ly] - dp[lx][sy-1] + dp[sx-1][sy-1];
                bw.write(result+"\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
    static void print()
    {
        for(int i=1;i<=n;i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
    }
}