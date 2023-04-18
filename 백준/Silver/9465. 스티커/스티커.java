import java.io.*;
import java.util.*;

public class Main {

    static int t = 0;
    static int n = 0;
    static int[][] board= new int[3][100001];
    static int[][] dp = new int[3][100001];
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        t = Integer.parseInt(st.nextToken());
        for(int k=1;k<=t;k++)
        {
            st = new StringTokenizer(br.readLine()," ");
            n = Integer.parseInt(st.nextToken());
            board= new int[3][100001];
            dp = new int[3][100001];

            for(int i=1;i<=2;i++)
            {
                st = new StringTokenizer(br.readLine()," ");
                for(int j=1;j<=n;j++)
                {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp[1][1] = board[1][1];
            dp[2][1] = board[2][1];

            for(int i=2;i<=n;i++)
            {
                dp[1][i] = Math.max(dp[2][i-1],Math.max(dp[1][i-2],dp[2][i-2])) + board[1][i];
                dp[2][i] = Math.max(dp[1][i-1],Math.max(dp[1][i-2],dp[2][i-2])) + board[2][i];
            }

            int result = Math.max(dp[1][n],dp[2][n]);
            list.add(result);
        }

        list.forEach(
                x-> System.out.println(x)
        );
        br.close();
    }
}