import java.io.*;
import java.util.*;
public class Main {
    static int n = 0;
    static int[] board = new int[1001];
    static int[] dp = new int[1001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i=1;i<=n;i++)
        {
            board[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = 0;
        if(n>=2)
        {
            dp[2] = Math.abs(board[2] - board[1]);
        }
        for(int i=3;i<=n;i++)
        {
            int max = board[i];
            int min = board[i];
            for(int j=i-1;j>=1;j--)
            {
                max = Math.max(max,board[j]);
                min = Math.min(min,board[j]);

                dp[i] = Math.max(dp[i],(max - min) + dp[j-1]);
            }
        }
        System.out.println(dp[n]);
        br.close();

    }
}