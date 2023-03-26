
import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int k = 0;
    static int[][] board = new int[101][101];
    static boolean[][] visit = new boolean[101][101];
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i=1;i<=k;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x][y] = 1;
        }

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                if(!visit[i][j]&&board[i][j]==1)
                {
                    int size = bfs(i,j);
                    if(size>result)
                        result = size;
                }
            }
        }

        System.out.println(result);
        br.close();
    }

    static int bfs(int x,int y){
        int size = 1;
        Queue<point> q = new LinkedList<>();
        visit[x][y] = true;
        q.add(new point(x,y));

        while(!q.isEmpty())
        {
            point cur = q.poll();

            for(int i=0;i<4;i++)
            {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx>=1&&nx<=n&&ny>=1&&ny<=m)
                {
                    if(!visit[nx][ny]&&board[nx][ny]==1)
                    {
                        visit[nx][ny] = true;
                        q.add(new point(nx,ny));
                        size = size + 1;
                    }
                }
            }
        }

        return size;
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