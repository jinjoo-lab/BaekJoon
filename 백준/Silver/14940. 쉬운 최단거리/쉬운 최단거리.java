import java.io.*;
import java.util.*;

public class Main {

    static int n = 0;
    static int m = 0;


    static int[] dx  = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] board = new int[n+1][m+1];
        int sx = 0;
        int sy = 0;
        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1;j<=m;j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (board[i][j] == 2)
                {
                    sx = i;
                    sy = j;
                }
            }
        }

        int[][] result = bfs(board,sx,sy);

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                bw.write(result[i][j]+" ");
            }bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();

    }

    static int[][] bfs(int[][] board , int sx,int sy)
    {
        int[][] visit = new int[n+1][m+1];
        Queue<point> q = new LinkedList<>();

        q.add(new point(sx,sy));

        while(!q.isEmpty())
        {
            point cur = q.poll();

            for(int i=0;i<4;i++)
            {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<1||nx>n||ny<1||ny>m)
                    continue;

                if(visit[nx][ny]>=1||(board[nx][ny]==2))
                    continue;

                if(board[nx][ny]==1&&visit[nx][ny]==0)
                {
                    visit[nx][ny] = visit[cur.x][cur.y] + 1;
                    q.add(new point(nx,ny));
                }
            }

        }

        for(int i=1;i<=n;i++) {
            for (int j = 1; j <= m; j++) {
                if(visit[i][j]==0&&board[i][j]==1)
                    visit[i][j]= -1;
            }
        }

        return visit;

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