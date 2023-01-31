import java.io.*;
import java.util.*;

public class Main {
    static int k = 0;
    static int n = 0;
    static int m = 0;
    static int[][] board = new int[201][201];
    static int[][][] visit = new int[201][201][31];
    static Queue<point> q= new LinkedList<>();
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int[] mx = {-1,1,-2,2,-2,2,-1,1};
    static int[] my = {-2,-2,-1,-1,1,1,2,2};
    static int result = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine()," ");
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            for(int j=1;j<=m;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        q.add(new point(1,1,k));
        visit[1][1][k] = 1;
        bfs();
        if(result== -1)
        {
            System.out.println(result);
        }
        else{
            System.out.println(result-1);
        }
    }
    static void bfs()
    {
        while(!q.isEmpty()) {
            point cur = q.poll();

            if(cur.x==n&&cur.y==m)
            {
                if(result==-1||result>visit[cur.x][cur.y][cur.k])
                {
                    result = visit[cur.x][cur.y][cur.k];
                }
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 1 && ny >= 1 && nx <= n && ny <= m) {
                    if(board[nx][ny]==0) {
                        if (visit[nx][ny][cur.k] == 0 || visit[nx][ny][cur.k] > visit[cur.x][cur.y][cur.k] + 1) {
                            visit[nx][ny][cur.k] = visit[cur.x][cur.y][cur.k] + 1;
                            q.add(new point(nx, ny, cur.k));
                        }
                    }
                }
            }
            if (cur.k >= 1) {
                for (int i = 0; i < 8; i++) {
                    int nx = cur.x + mx[i];
                    int ny = cur.y + my[i];

                    if (nx >= 1 && ny >= 1 && nx <= n && ny <= m) {
                        if (board[nx][ny] == 0) {
                            if (visit[nx][ny][cur.k - 1] == 0 || visit[nx][ny][cur.k - 1] > visit[cur.x][cur.y][cur.k] + 1) {
                                visit[nx][ny][cur.k - 1] = visit[cur.x][cur.y][cur.k] + 1;
                                q.add(new point(nx, ny, cur.k - 1));
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
    int k;

    point(int x,int y,int k)
    {
        this.x = x;
        this.y = y;
        this.k = k;
    }
}