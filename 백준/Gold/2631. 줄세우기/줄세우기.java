import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] board = new int[201];
    static int[] dp = new int[201];

    static int longest = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        for(int i=1;i<=n;i++)
        {
            board[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = 1;

        for(int i=2;i<=n;i++)
        {
            int result = -1;
            for(int j=i-1;j>=1;j--)
            {
                if(board[j] < board[i])
                {
                    if(result<dp[j]+1)
                    {
                        longest = Math.max(longest,dp[j]+1);
                        result = dp[j]+1;
                    }
                }
            }

            if(result==-1)
                dp[i] = 1;

            else
                dp[i] = result;
        }
        
        System.out.println(n - longest);


        br.close();
    }
}