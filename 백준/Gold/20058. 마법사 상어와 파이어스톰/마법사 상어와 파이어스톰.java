import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int q = 0;

    static int[][] board = new int[65][65];

    static int[] dx ={-1,1,0,0};
    static int[] dy ={0,0,-1,1};
    static boolean[][] visit = new boolean[65][65];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        int num = IntPow(n);

        for(int i=1;i<=num;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1;j<=num;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=q;i++)
        {
            int f = Integer.parseInt(st.nextToken());

            turn(f,num);
            melt(num);
        }

        int result = score(num);
        int result2 = 0;
        for(int i=1;i<=num;i++)
        {
            for(int j=1;j<=num;j++)
            {
                if(!visit[i][j]&&board[i][j]>0)
                {
                   int tmp =  bfs(i,j,num);

                   if(tmp>result2)
                   {
                       result2 = tmp;
                   }
                }
            }
        }

        System.out.println(result);
        System.out.println(result2);
        br.close();

    }
    static int score(int num)
    {
        int result = 0;
        for(int i=1;i<=num;i++)
        {
            for(int j=1;j<=num;j++)
            {
                result +=board[i][j];
            }
        }

        return result;
    }

    static int bfs(int x,int y,int num)
    {
        int result = 1;
        Queue<point> q = new LinkedList<>();
        visit[x][y] = true;
        q.add(new point(x,y));

        while(!q.isEmpty())
        {
            point cur = q.poll();

            for(int i=0;i<4;i++)
            {
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];

                if(nx>=1&&ny>=1&&nx<=num&&ny<=num)
                {
                    if(board[nx][ny]!=0&&!visit[nx][ny])
                    {
                        visit[nx][ny]= true;
                        result = result+1;
                        q.add(new point(nx,ny));
                    }
                }
            }
        }

        return result;
    }

    static boolean isMelt(int x,int y,int num)
    {
        int count = 0;

        for(int i=0;i<4;i++)
        {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx>=1&&ny>=1&&nx<=num&&ny<=num)
            {
                if(board[nx][ny]>0)
                {
                    count++;
                }
            }
        }

        if(count<3)
            return true;
        else
            return false;
    }

    static void melt(int num)
    {
        int[][] tmp = new int[65][65];

        for(int i=1;i<=num;i++)
        {
            for(int j=1;j<=num;j++)
            {
                if(board[i][j]>0&&isMelt(i,j,num))
                {
                   tmp[i][j] = board[i][j] - 1;
                }
                else{
                    tmp[i][j] = board[i][j];
                }
            }
        }

        for(int i=1;i<=num;i++)
        {
            for(int j=1;j<=num;j++)
            {
                board[i][j] = tmp[i][j];
            }
        }
    }

    static void turn(int l,int num) {
        int[][] tmp = new int[65][65];
        int lnum = IntPow(l);
        int c = 1;
        int tx = 1;
        int ty = lnum;
        int limit = lnum;
        int ylimit = lnum;
        for (int i = 1; i <= num; i++)
        {
            for(int j=1;j<=num;j++)
            {
                tmp[tx][ty] = board[i][j];
                tx++;
                if(tx>limit)
                {
                    tx=c;
                    ty = ty+lnum;
                }
            }
            if(i%lnum==0)
            {
                c = c+lnum;
                tx = c;
                ty = lnum;
                limit = limit + lnum;
                ylimit = lnum;
            }
            else {
                tx = c;
                ylimit = ylimit - 1;
                ty = ylimit;
            }
        }

        for(int i=1;i<=num;i++)
        {
            for(int j=1;j<=num;j++)
            {
                board[i][j] = tmp[i][j];
            }
        }
    }

    static int IntPow(int num)
    {
        int re = (int)Math.pow(2.0,num);

        return re;
    }
}
class point
{
    int x;
    int y;

    point(int x,int y)
    {
        this.x = x;
        this.y = y;
    }
}