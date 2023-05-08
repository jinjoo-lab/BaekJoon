import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] light = new boolean[101][101];
    static boolean[][] visit = new boolean[101][101];
    static int n = 0;
    static int m = 0;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<point>[][] board = new ArrayList[101][101];
    static ArrayList<point> turn = new ArrayList<>();
    static int result = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                board[i][j] = new ArrayList<>();
            }
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int dx = Integer.parseInt(st.nextToken());
            int dy = Integer.parseInt(st.nextToken());

            board[sx][sy].add(new point(dx,dy));
        }
        lighton();
        System.out.println(result);
        br.close();
    }
    static void lighton() {
        Queue<point> q = new LinkedList<>();
        light[1][1] = true;
        visit[1][1] = true;
        q.add(new point(1, 1));
        while (!q.isEmpty()) {
            point cur = q.poll();
            for (point next : board[cur.x][cur.y]) {
                if (!light[next.x][next.y]) {
                    light[next.x][next.y] = true;
                    result = result + 1;

                    if(can(next.x, next.y))
                    {
                        if(!visit[next.x][next.y]) {
                            visit[next.x][next.y] = true;
                            q.add(new point(next.x, next.y));
                        }
                    }
                }
            }

            for(int i=0;i<4;i++)
            {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx>=1&&nx<=n&&ny>=1&&ny<=n)
                {
                    if(!visit[nx][ny]&&light[nx][ny])
                    {
                        q.add(new point(nx,ny));
                        visit[nx][ny] = true;
                    }
                }
            }

        }
    }

    static boolean can(int x,int y)
    {
        boolean re = false;
        for(int i=0;i<4;i++)
        {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx>=1&&nx<=n&&ny>=1&&ny<=n)
            {
                if(visit[nx][ny])
                    re = true;
            }
        }

        return re;
    }
}
class point
{
    int x;
    int y;
    point(int sx,int sy)
    {
        this.x = sx;
        this.y = sy;
    }
}