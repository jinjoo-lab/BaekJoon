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
    static void lighton()
    {
        Queue<point> q = new LinkedList<>();
        light[1][1] = true;
        visit[1][1] = true;
        q.add(new point(1,1));
        while(!q.isEmpty())
        {
            point cur = q.poll();
            for(point next : board[cur.x][cur.y])
            {
                if(!light[next.x][next.y])
                {
                    light[next.x][next.y] = true;
                    result = result + 1;
                }
            }

            Queue<point> q2 = new LinkedList<>();
            boolean[][] visit2 = new boolean[101][101];
            q2.add(cur);
            visit2[cur.x][cur.y] = true;

            while(!q2.isEmpty())
            {
                point cur2 = q2.poll();
                for(int i=0;i<4;i++)
                {
                    int nx = cur2.x + dx[i];
                    int ny = cur2.y + dy[i];

                    if(nx>=1&&nx<=n&&ny>=1&&ny<=n)
                    {
                        if(light[nx][ny])
                        {
                            if(!visit[nx][ny])
                            {
                                visit[nx][ny]= true;
                                q.add(new point(nx,ny));
                            }

                            if(!visit2[nx][ny])
                            {
                                visit2[nx][ny] = true;
                                q2.add(new point(nx,ny));
                            }
                        }
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
    point(int sx,int sy)
    {
        this.x = sx;
        this.y = sy;
    }
}