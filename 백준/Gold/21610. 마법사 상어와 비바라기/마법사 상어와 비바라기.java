import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[] dx = {0,0,-1,-1,-1,0,1,1,1};
    static int[] dy = {0,-1,-1,0,1,1,1,0,-1};

    static int[] cx = {-1,-1,1,1};
    static int[] cy = {-1,1,1,-1};
    static int[][] board = new int[51][51];
    static Queue<point> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            for(int j =1 ;j<=n;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        q.add(new point(n,1));
        q.add(new point(n,2));
        q.add(new point(n-1,1));
        q.add(new point(n-1,2));

        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            move(d,s);
        }
        int result = sum();

        System.out.println(result);
        br.close();
    }
    static int over(int x)
    {
        if(x>n)
            return x-n;
        else if(x<1)
        {
            return n + x;
        }

        else
            return x;
    }

    static int[][] copy()
    {
        int[][] tmp = new int[51][51];

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                tmp[i][j] = board[i][j];
            }
        }

        return tmp;
    }
    static void recopy(int[][] tmp)
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                board[i][j] = tmp[i][j];
            }
        }
    }
    static void print()
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    static int sum()
    {
        int result = 0;
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                result+=board[i][j];
            }
        }
        return result;
    }

    static void move(int d, int s)
    {
        Queue<point> copyq = new LinkedList<>();
        boolean[][] visit = new boolean[51][51];
        while(!q.isEmpty())
        {
            point cur = q.poll();
            int nx = cur.x + (s%n)*dx[d];
            int ny = cur.y + (s%n)*dy[d];

            nx = over(nx);
            ny = over(ny);
            copyq.add(new point(nx,ny));
            board[nx][ny] = board[nx][ny] + 1;
            visit[nx][ny] = true;
        }
        int[][] tmp = copy();

        while(!copyq.isEmpty())
        {
            point cur = copyq.poll();
            int num = 0;

            for(int i=0;i<4;i++)
            {
                int nx = cur.x+cx[i];
                int ny = cur.y+cy[i];

                if(nx>=1&&nx<=n&&ny>=1&&ny<=n)
                {
                    if(board[nx][ny]>0) {
                        num++;
                    }
                }
            }
            tmp[cur.x][cur.y] = tmp[cur.x][cur.y] + num;
        }
        recopy(tmp);

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                if(board[i][j]>=2&&!visit[i][j])
                {
                    board[i][j] = board[i][j] -2;
                    q.add(new point(i,j));
                }
            }
        }

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