import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] board = new int[1001];
    static int[] dp = new int[1001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=n;i++)
        {
            board[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = board[1];

        for(int i=2;i<=n;i++)
        {
            int idx = i;
            for(int j=i-1;j>=1;j--)
            {
                if(board[j]<board[i]) {
                    if(idx==i||dp[idx]<dp[j])
                        idx = j;
                }
            }

            if(idx!=i)
            {
                dp[i] = dp[idx] + board[i];
            }

            else{
                dp[i] = board[i];
            }


        }
        int result = 0;
        for(int i=1;i<=n;i++)
        {
            if(result<dp[i])
                result = dp[i];
        }
        System.out.println(result);
        br.close();
    }
}