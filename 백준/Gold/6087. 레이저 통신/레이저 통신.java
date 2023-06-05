import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static char[][] board = new char[101][101];
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static int sx = -1;
    static int sy = -1;
    static int lx = -1;
    static int ly = -1;

    static int result = 10001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        for(int i=1;i<=n;i++)
        {
            String line = br.readLine();
            for(int j=1;j<=m;j++)
            {
                board[i][j] = line.charAt(j-1);

                if(board[i][j]=='C') {
                    if (sx == -1) {
                        sx = i;
                        sy = j;
                    } else if (lx == -1) {
                        lx = i;
                        ly = j;
                    }
                }
            }
        }

        bfs();
        System.out.println(result);
        br.close();
    }

    static void bfs()
    {
        int[][][] visit = new int[n+1][m+1][4];
        Queue<point> q = new LinkedList<>();

        for(int i=0;i<4;i++)
        {
            q.add(new point(sx,sy,i));
            visit[sx][sy][i] = 1;
        }

        while(!q.isEmpty())
        {
            point cur = q.poll();

            if(cur.x==lx&&cur.y==ly){
                result = Math.min(result , visit[lx][ly][cur.dir] -1);
            }

            for(int i=0;i<4;i++)
            {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<1||nx>n||ny<1||ny>m)
                    continue;

                if(board[nx][ny]=='*')
                    continue;


                if(i==cur.dir)
                {
                    if(visit[nx][ny][cur.dir]==0||visit[nx][ny][cur.dir] > visit[cur.x][cur.y][cur.dir])
                    {
                        visit[nx][ny][cur.dir] = visit[cur.x][cur.y][cur.dir];
                        q.add(new point(nx,ny,cur.dir));
                    }
                }

                else{
                    if(visit[nx][ny][i]==0||visit[nx][ny][i] > visit[cur.x][cur.y][cur.dir] + 1)
                    {
                        visit[nx][ny][i] = visit[cur.x][cur.y][cur.dir] + 1;
                        q.add(new point(nx,ny,i));
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

    int dir;

    point(int x,int y,int dir)
    {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}
