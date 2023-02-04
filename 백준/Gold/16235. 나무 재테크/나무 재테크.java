import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int k = 0;
    static int[][] board = new int[11][11];
    static int[][] repeal = new int[11][11];
    static Queue<point> death = new LinkedList<>();
    static PriorityQueue<point> q = new PriorityQueue<>();
    static int[] dx = {-1,-1,-1,0,0,1,1,1};
    static int[] dy = {-1,0,1,-1,1,-1,0,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            for(int j=1;j<=n;j++)
            {
                repeal[i][j] = Integer.parseInt(st.nextToken());
                board[i][j] = 5;
            }
        }

        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            point p = new point(x,y,e);
            q.add(p);
        }

        for(int i =1;i<=k;i++)
        {
            spring();
            summer();
            fall();
            winter();
        }
        System.out.println(q.size());
        br.close();
    }

    static void spring()
    {
        PriorityQueue<point> tmp = new PriorityQueue<>();
        while(!q.isEmpty())
        {
            point cur = q.poll();
            if(board[cur.x][cur.y]>=cur.e)
            {
                board[cur.x][cur.y] = board[cur.x][cur.y]-cur.e;
                tmp.add(new point(cur.x,cur.y,cur.e+1));
            }
            else{
                death.add(cur);
            }
        }
        q = new PriorityQueue<>(tmp);
    }

    static void summer()
    {
        while(!death.isEmpty())
        {
            point cur = death.poll();

            board[cur.x][cur.y] = board[cur.x][cur.y]+(int)(cur.e/2);
        }


    }

    static void fall()
    {
        PriorityQueue<point> tmp = new PriorityQueue<>();
        while(!q.isEmpty())
        {
            point cur = q.poll();

            if(cur.e%5==0) {
                for (int i = 0; i < 8; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if (nx >= 1 && nx <= n && ny >= 1 && ny <= n) {
                        tmp.add(new point(nx,ny,1));
                    }
                }
            }

            tmp.add(cur);
        }

        q = new PriorityQueue<>(tmp);
    }

    static void winter()
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                board[i][j] = board[i][j] + repeal[i][j];
            }
        }
    }
}
class point implements Comparable<point>
{
    int x;
    int y;
    int e;

    point(int x,int y,int e)
    {
        this.x =x;
        this.y =y;
        this.e = e;
    }

    @Override
    public int compareTo(point o) {
        if(this.e>o.e)
            return 1;
        else if(this.e<o.e)
            return -1;
        return 0;
    }
}
