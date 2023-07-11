import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int m = 0;

    static int dx[] = {0,0,-1,1};
    static int dy[] = {1,-1,0,0};

    static int result;

    static boolean[][] visit;

    static int[][] dis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        int[][] board = new int[n+1][m+1];
        visit = new boolean[n+1][m+1];
        dis = new int[n+1][m+1];
        for(int i=1;i<=n;i++)
        {
            String line = br.readLine();
            for(int j=1;j<=m;j++)
            {
                board[i][j] = Integer.parseInt(line.charAt(j-1)+"");
                dis[i][j] = Integer.MAX_VALUE;
            }
        }



        result = (n) * (m);

        bfs(board);
        System.out.println(dis[n][m]);

        br.close();
    }

    static void bfs(int[][] board)
    {
       PriorityQueue<point> pq= new PriorityQueue<>(
               (x,y) -> x.count - y.count
       );

       pq.add(new point(1,1,0));
       dis[1][1] = 0;


       while(!pq.isEmpty())
       {
           point cur = pq.poll();

           if(visit[cur.x][cur.y])
               continue;

           visit[cur.x][cur.y] = true;

           for(int i=0;i<4;i++)
           {
               int nx = cur.x + dx[i];
               int ny = cur.y + dy[i];

               if(nx < 1 || nx > n || ny < 1 || ny > m)
                   continue;

               if(dis[nx][ny] > dis[cur.x][cur.y] + board[nx][ny])
               {
                   dis[nx][ny] = dis[cur.x][cur.y] + board[nx][ny];
                   pq.add(new point(nx,ny,dis[nx][ny]));
               }
           }
       }
    }
}
class point
{
    int x;
    int y;

    int count;

    point(int x,int y,int count)
    {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}
