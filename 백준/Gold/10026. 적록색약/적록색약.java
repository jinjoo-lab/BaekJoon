import java.io.*;
import java.util.*;

public class Main {

    static int n = 0;
    static char[][] board;
    static boolean[][] visit;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        board = new char[n+1][n+1];
        visit = new boolean[n+1][n+1];

        for(int i=1;i<=n;i++)
        {
            String line = br.readLine();
            for(int j=1;j<=n;j++)
            {
                board[i][j] = line.charAt(j-1);
            }
        }

        int first = 0;
        int second = 0;

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                if(!visit[i][j])
                {
                    bfs(i,j);
                    first = first + 1;
                }
            }
        }
        for(int i=1;i<=n;i++) {
            for (int j = 1; j <= n; j++)
            {
                if(board[i][j]=='G')
                    board[i][j] = 'R';
            }
        }

        visit = new boolean[n+1][n+1];
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                if(!visit[i][j])
                {
                    bfs(i,j);
                    second = second + 1;
                }
            }
        }

        System.out.println(first+" "+second);
        br.close();
    }

    static void bfs(int x,int y)
    {
        Queue<point> q = new LinkedList<>();
        q.add(new point(x,y));
        visit[x][y] = true;

        while(!q.isEmpty())
        {
            point cur = q.poll();

            for(int i=0;i<4;i++)
            {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx>=1&&nx<=n&&ny>=1&&ny<=n)
                {
                    if(!visit[nx][ny]&&board[cur.x][cur.y]==board[nx][ny])
                    {
                        visit[nx][ny] = true;
                        q.add(new point(nx,ny));
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

    point(int x,int y)
    {
        this.x = x;
        this.y = y;
    }
}
