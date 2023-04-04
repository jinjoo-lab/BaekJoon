import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int k = 0;
    static int[][] board = new int[101][101];
    static int[][] tmp = new int[101][101];

    static int height = 0;
    static int start =  0;

    static int dx[] = {1,-1,0,0};
    static int dy[] = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=n;i++)
        {
            board[n][i] = Integer.parseInt(st.nextToken());
        }

        int count = 1;
        while(true) {
            first();

            second();

            copy();
            thrid();

            four();

            five();
            boolean re = cal();
            if(re)
                break;
            count = count + 1;
        }
        System.out.println(count);
        br.close();
    }
    static boolean cal()
    {
        int max = -1;
        int min = -1;

        for(int i=1;i<=n;i++)
        {
            if(board[n][i]>=max)
                max = board[n][i];

            if(min==-1||board[n][i]<min)
                min = board[n][i];
        }

        if(max - min <=k)
            return true;

        return false;
    }
    static void first()
    {
        int min = board[n][1];
        for(int i=2;i<=n;i++)
        {
            if(min>board[n][i])
                min = board[n][i];
        }

        for(int i=1;i<=n;i++)
        {
            if(min==board[n][i])
                board[n][i] = board[n][i] + 1;
        }


        tmp = new int[101][101];
    }

    static void second()
    {
       int pre = 1;
       int w = 1;
       int h = 1;
       int turn = 1;

       while(pre -1 + w + h <=n)
       {
           int row = n - w;
           for(int j=pre;j<pre + w;j++) {
               int idx = 0;
               for(int i=n;i>n - h;i--){
                   board[row][pre + w +idx] = board[i][j];
                   board[i][j] = 0;
                   idx = idx + 1;
               }
               row = row +1;
           }

           pre = pre + w;
           turn = turn + 1;
           if(turn%2==0)
               h = h+1;
           else{
               w = w+1;
           }
       }
        start = pre;
        height = h;
    }
    static void copy()
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                tmp[i][j] = board[i][j];
            }
        }
    }
    static void thrid()
    {
        boolean[][] visit = new boolean[101][101];
        for(int i = n; i> n - height; i--)
        {
            for(int j= start;j<=n;j++)
            {
                if(board[i][j]==0)
                    continue;

                for(int k=0;k<4;k++)
                {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(nx>=1&&nx<=n&&ny>=1&&ny<=n)
                    {
                        if(board[nx][ny]==0 || visit[nx][ny])
                            continue;

                        int d = Math.abs(board[i][j] - board[nx][ny]) / 5;
                        if(d > 0)
                        {
                            if(board[nx][ny] > board[i][j])
                            {
                                tmp[nx][ny] = tmp[nx][ny] - d;
                                tmp[i][j] = tmp[i][j] + d;
                            }

                            else{
                                tmp[nx][ny] = tmp[nx][ny] + d;
                                tmp[i][j] = tmp[i][j] - d;
                            }
                        }
                    }
                }
                visit[i][j] = true;
            }
        }
    }

    static void four() {
        board = new int[101][101];
        int idx = 1;
        for (int i = start; i <= n; i++) {
            for (int j = n; j > n - height; j--) {
                if (tmp[j][i] == 0)
                    continue;

                board[n][idx] = tmp[j][i];
                idx = idx + 1;
            }
        }
    }

    static void five()
    {
        tmp = new int[101][101];
        boolean[][] visit = new boolean[101][101];
        int half = n/2;
        int idx = 1;
        for(int i=half;i>=1;i--)
        {
            board[n-1][half + idx] = board[n][i];
            board[n][i] = 0;
            idx = idx + 1;
        }
        int sx = n-2;
        for(int i= n-1;i<=n;i++)
        {
            int sy = half + n/4 + 1;
            for(int j= half + n/4;j> half;j--)
            {
                board[sx][sy] = board[i][j];
                board[i][j] = 0;
                sy = sy + 1;
            }
            sx = sx -1;
        }
        copy();
        for(int i= half + n/4 + 1;i<=n;i++)
        {
            for(int j= n;j>n-4;j--)
            {
                if(board[j][i]==0)
                    continue;

                for(int k=0;k<4;k++)
                {
                    int nx = j + dx[k];
                    int ny = i + dy[k];

                    if(nx>=1&&nx<=n&&ny>=1&&ny<=n)
                    {
                        if(board[nx][ny]==0 || visit[nx][ny])
                            continue;

                        int d = Math.abs(board[j][i] - board[nx][ny]) / 5;
                        if(d > 0)
                        {
                            if(board[nx][ny] > board[j][i])
                            {
                                tmp[nx][ny] = tmp[nx][ny] - d;
                                tmp[j][i] = tmp[j][i] + d;
                            }

                            else{
                                tmp[nx][ny] = tmp[nx][ny] + d;
                                tmp[j][i] = tmp[j][i] - d;
                            }
                        }
                    }
                }
                visit[j][i] = true;
            }
        }
        board = new int[101][101];
        idx = 1;
        for(int i= half + n/4 + 1;i<=n;i++)
        {
            for(int j= n;j>n-4;j--)
            {
                board[n][idx] = tmp[j][i];
                idx = idx + 1;
            }
        }
        tmp = new int[101][101];
    }
  
}