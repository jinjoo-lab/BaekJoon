import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int k = 0;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int[][] board = new int[n+1][m+1];

        for(int i=1;i<=n;i++)
        {
            String line = br.readLine();
            for(int j=1;j<=m;j++)
            {
                if(line.charAt(j-1)=='1')
                    board[i][j] = 1;
            }
        }

        bfs(board);

        if(result == Integer.MAX_VALUE)
            System.out.println(-1);

        else
            System.out.println(result);


        br.close();
    }

    static void bfs(int[][] board)
    {
        Queue<point> q = new ArrayDeque<>();
        q.add(new point(1,1,k,0));
        int[][][][] visit = new int[n+1][m+1][k+1][2];
        visit[1][1][k][0] = 1;

        while(!q.isEmpty())
        {
            point cur = q.poll();

            if(cur.x==n&&cur.y==m)
            {
                result = Math.min(result , visit[cur.x][cur.y][cur.c][cur.day]);
                return;
            }

            if(visit[cur.x][cur.y][cur.c][change(cur.day)]==0)
            {
                visit[cur.x][cur.y][cur.c][change(cur.day)] = visit[cur.x][cur.y][cur.c][cur.day] + 1;
                q.add(new point(cur.x,cur.y,cur.c,change(cur.day)));
            }

            for(int i=0;i<4;i++)
            {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nday = change(cur.day);

                if(nx<1||nx>n||ny<1||ny>m)
                    continue;

                if(board[nx][ny]==1)
                {
                    if(cur.day==0&&cur.c>=1)
                    {
                        if(visit[nx][ny][cur.c-1][nday] == 0) {
                            visit[nx][ny][cur.c - 1][nday] = visit[cur.x][cur.y][cur.c][cur.day] + 1;
                            q.add(new point(nx, ny, cur.c - 1, nday));
                        }
                    }
                }
                else{
                    if(visit[nx][ny][cur.c][nday]==0)
                    {
                        visit[nx][ny][cur.c][nday] = visit[cur.x][cur.y][cur.c][cur.day] + 1;
                        q.add(new point(nx, ny, cur.c , nday));
                    }
                }
            }

        }
    }

    static int change(int day)
    {
        if(day==1)
            return 0;
        else
            return 1;
    }
}
class point
{
    int x;
    int y;
    int c;
    int day;

    point(int x,int y,int c,int day)
    {
        this.x = x;
        this.y = y;
        this.c = c;
        this.day = day;
    }
}