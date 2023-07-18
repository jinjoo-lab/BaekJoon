import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int g = 0;
    static int r = 0;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int[][] board;

    static boolean[] keep;

    static ArrayList<Point> seed;

    static int result = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        seed = new ArrayList<>();

        Point[] ga = new Point[g+1];
        Point[] ra = new Point[r+1];


        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            for(int j=1;j<=m;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());

                if(board[i][j] == 2)
                {
                    seed.add(new Point(i,j));
                }
            }
        }

        int len = seed.size();
        keep = new boolean[len+1];
        go(0,0,0,ga,ra,len);

        if(result == Integer.MIN_VALUE)
            System.out.println(0);

        else
            System.out.println(result);
        br.close();
    }

    static void print(Point[] arr,int num)
    {
        for(int i = 0 ; i< num ; i++)
        {
            System.out.print(arr[i].x+" , "+arr[i].y+" : ");
        }
        System.out.println();
    }

    static void go(int start,int gn , int rn,Point[] ga,Point[] ra,int len)
    {
        if(gn > g || rn > r)
            return;

        if(gn == g && rn == r)
        {
            bfs(ga,ra);
            return;
        }

        for(int i=start;i<len;i++)
        {
            if(!keep[i])
            {
                keep[i] = true;
                ga[gn] = seed.get(i);
                go(i+1,gn+1,rn,ga,ra,len);
                ra[rn] = seed.get(i);
                go(i+1,gn,rn+1,ga,ra,len);
                keep[i] = false;
            }
        }
    }

    static void bfs(Point[] ga , Point[] ra)
    {
        int[][][] v = new int[n+1][m+1][2];
        boolean[][] f = new boolean[n+1][m+1];

        Queue<Data> q = new LinkedList<>();

        for(int i=0;i<g;i++) {
            q.add(new Data(ga[i].x,ga[i].y,0));
            v[ga[i].x][ga[i].y][0] = 1;
        }
        for(int i=0;i<r;i++)
        {
            q.add(new Data(ra[i].x,ra[i].y,1));
            v[ra[i].x][ra[i].y][1] = 1;
        }

        int count = 0;

        while(!q.isEmpty())
        {
            Data cur = q.poll();

            if(f[cur.x][cur.y])
                continue;

            for(int i=0;i<4;i++)
            {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > m)
                    continue;

                if(board[nx][ny] == 0)
                    continue;

                if(v[nx][ny][cur.c] == 0)
                {
                    if(cur.c == 0 && v[nx][ny][1] == 0)
                    {
                        v[nx][ny][cur.c] = v[cur.x][cur.y][cur.c] + 1;
                        q.add(new Data(nx,ny,cur.c));
                    }

                    else if(cur.c == 1)
                    {
                        if(v[cur.x][cur.y][1] + 1 == v[nx][ny][0])
                        {
                            f[nx][ny] = true;
                            count = count + 1;
                            v[nx][ny][1] = v[cur.x][cur.y][1] + 1;
                        }

                        else if(v[nx][ny][0] == 0)
                        {
                            v[nx][ny][1] =  v[cur.x][cur.y][1] + 1;
                            q.add(new Data(nx,ny,cur.c));
                        }
                    }
                }
            }
        }

        result = Math.max(result , count);
    }

    static void print2(boolean[][] a)
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1 ; j<=m;j++)
            {
                if(a[i][j])
                    System.out.print(1+" ");
                else
                    System.out.print(0+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
class Point
{
    int x;
    int y;

    Point(int x,int y)
    {
        this.x = x;
        this.y = y;
    }
}

class Data
{
    int x;
    int y;
    int c;

    Data(int x,int y,int c)
    {
        this.x = x;
        this.y = y;
        this.c = c;
    }
}