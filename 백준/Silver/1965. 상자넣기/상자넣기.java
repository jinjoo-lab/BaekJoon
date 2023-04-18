import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] board = new int[1001];
    static int[] dp = new int[1001];
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=n;i++)
        {
            board[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = 1;
        for(int i=2;i<=n;i++)
        {
            for(int j=i-1;j>=1;j--)
            {
                if(board[i]>board[j])
                    dp[i] = Math.max(dp[i],dp[j]+1);
            }

            if(dp[i]==0)
                dp[i]=1;
        }

        for(int i=1;i<=n;i++)
        {
            result = Math.max(result,dp[i]);
        }
        System.out.println(result);
        br.close();
    }
}