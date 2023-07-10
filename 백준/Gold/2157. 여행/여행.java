import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int k = 0;
    static int[][] board = new int[302][302];
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i=1;i<=k;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board[a][b] = Math.max(board[a][b] , c);
        }

        dp = new int[n+1][m+1];

        search();

        br.close();
    }

    static void search()
    {
        Queue<point> q = new LinkedList<>();
        q.add(new point(1,1));

        while(!q.isEmpty())
        {
            point cur = q.poll();

            if(cur.x == n)
            {
                continue;
            }

            if(cur.count == m)
                continue;

            for(int i=cur.x+1;i<=n;i++)
            {
                if(board[cur.x][i]!=0)
                {
                    if(dp[i][cur.count+1] < dp[cur.x][cur.count] + board[cur.x][i])
                    {
                        dp[i][cur.count+1] = dp[cur.x][cur.count] + board[cur.x][i];
                        q.add(new point(i,cur.count + 1));
                    }
                }
            }
        }

        int result = 0;

        for(int i=1;i<=m;i++)
        {
            result = Math.max(result , dp[n][i]);
        }

        System.out.println(result);
    }

}
class point
{
    int x;

    int count;


    point(int x,int count)
    {
        this.x = x;
        this.count = count;
    }
}