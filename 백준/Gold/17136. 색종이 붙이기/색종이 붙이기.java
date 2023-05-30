import java.io.*;
import java.util.*;
public class Main {
    static int n = 0;
    static int[][] board = new int[11][11];
    static boolean[][] visit = new boolean[11][11];
    static int result = 26;
    static int[] count = new int[6];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=1;i<=10;i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j=1;j<=10;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1;i<=5;i++)
            count[i] = 5;

        travel(1,1);
        if(result==26)
            result = -1;
        System.out.println(result);
        br.close();
    }
    static boolean isIt(int x,int y,int cur)
    {
        for(int i=0;i<=cur;i++)
        {
            for(int j=0;j<=cur;j++)
            {
                int nx = x + i;
                int ny = y + j;

                if(nx<1||nx>10||ny<1||ny>10)
                {
                    return false;
                }

                if(board[nx][ny]==0)
                    return false;

                if(board[nx][ny]==1&&visit[nx][ny])
                    return false;
            }
        }

        return true;
    }
    static void go(int x,int y,int cur)
    {
        for(int i=0;i<=cur;i++) {
            for (int j = 0; j <= cur; j++) {
                int nx = x + i;
                int ny = y + j;

                visit[nx][ny] = true;
            }
        }
    }

    static void back(int x,int y,int cur)
    {
        for(int i=0;i<=cur;i++) {
            for (int j = 0; j <= cur; j++) {
                int nx = x + i;
                int ny = y + j;

                visit[nx][ny] = false;
            }
        }
    }
    static void travel(int x,int y)
    {
        if(y>10)
        {
            x = x + 1;
            y = 1;
        }

        if(x>10)
        {
            int tmp =0;
            for(int i=1;i<=5;i++)
            {
                tmp += (5-count[i]);
            }
            result = Math.min(result,tmp);
            return;
        }

        if(!visit[x][y]&&board[x][y]==1)
        {
            for(int i=0;i<=4;i++)
            {
                boolean can = isIt(x,y,i);
                if(can&&count[i+1]>=1)
                {
                    go(x,y,i);
                    count[i+1] = count[i+1] -1;
                    travel(x,y+1);
                    count[i+1] = count[i+1] +1;
                    back(x,y,i);
                }
            }
        }
        else{
            travel(x,y+1);
        }
    }
}
