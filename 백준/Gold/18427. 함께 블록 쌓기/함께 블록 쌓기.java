import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int m = 0;
    static int h = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());


        int[][] dp = new int[n + 1][h + 1];
        ArrayList<Integer>[] board = new ArrayList[n+1];

        dp[0][0] = 1;

        for(int i=1;i<=n;i++) {
            board[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine(), " ");

            while(st.hasMoreTokens())
            {
                board[i].add(Integer.parseInt(st.nextToken()));
            }

            dp[i][0] = 1;
        }

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=h;j++)
            {
                for(int k=0;k<board[i].size();k++)
                {
                    if(board[i].get(k) > j)
                        continue;

                    else{
                        dp[i][j] += dp[i-1][j- board[i].get(k)];
                        dp[i][j] %= 10007;
                    }
                }

                dp[i][j] += dp[i-1][j];
                dp[i][j] %= 10007;
            }
        }


        System.out.println(dp[n][h]);
        br.close();
    }
    
}