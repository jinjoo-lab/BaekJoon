import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[][] board;
    static int[][] dis;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    static int MAX = 210000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        int count = 1;
        while(true) {
            st = new StringTokenizer(br.readLine()," ");
            n = Integer.parseInt(st.nextToken());
            if(n==0)
                break;

            board = new int[126][126];
            dis = new int[126][126];

            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 1; j <= n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    dis[i][j] = MAX;
                }
            }

            PriorityQueue<point> q = new PriorityQueue<>(
                    (o1, o2) -> o1.score - o2.score
            );

            bfs(q);

            System.out.println("Problem "+count+": "+dis[n][n]);
            count = count + 1;
        }
        br.close();
    }

    static void bfs(PriorityQueue<point> q)
    {
        q.add(new point(1,1,board[1][1]));
        dis[1][1] = board[1][1];

        while(!q.isEmpty())
        {
            point cur = q.poll();

            for(int i=0;i<4;i++)
            {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx>=1&&nx<=n&&ny>=1&&ny<=n)
                {
                    if(dis[nx][ny]>dis[cur.x][cur.y] + board[nx][ny]) {
                        dis[nx][ny] = Math.min(dis[nx][ny], dis[cur.x][cur.y] + board[nx][ny]);
                        q.add(new point(nx,ny,dis[nx][ny]));
                    }
                }
            }
        }
    }
}
class point
{
    int x;
    int y;
    int score;

    point(int x,int y,int score)
    {
        this.x = x;
        this.y = y;
        this.score = score;
    }
}