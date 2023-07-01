import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int k = 0;
    static int[][] board;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1;j<=m;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();
    }

    static void bfs()
    {
        int result = Integer.MAX_VALUE;

        Queue<point> q = new LinkedList<>();
        int[][][] visit = new int[n+1][m+1][2];

        if(board[1][1] == 2)
        {
            q.add(new point(1,1,1));
            visit[1][1][1] = 1;
        }

        else{
            q.add(new point(1,1,0));
            visit[1][1][0] = 1;
        }

        while(!q.isEmpty()){
            point cur = q.poll();

            if(cur.x == n && cur.y ==m)
            {
                result = visit[cur.x][cur.y][cur.gram] - 1;
                break;
            }

            if(visit[cur.x][cur.y][cur.gram] -1  >= k)
                continue;

            for(int i=0;i<4;i++)
            {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx <1 || nx> n|| ny<1 || ny>m)
                    continue;

                if(cur.gram== 1)
                {
                    if(visit[nx][ny][1]==0)
                    {
                        visit[nx][ny][1] = visit[cur.x][cur.y][1] + 1;
                        q.add(new point(nx,ny,1));
                    }
                }

                else{
                    if(board[nx][ny]==1)
                        continue;

                    else if(board[nx][ny]==2 && visit[nx][ny][1]==0)
                    {
                        visit[nx][ny][1] = visit[cur.x][cur.y][0] + 1;
                        q.add(new point(nx,ny,1));
                    }

                    else if(board[nx][ny]==0 && visit[nx][ny][0]==0){
                        visit[nx][ny][0] = visit[cur.x][cur.y][0] + 1;
                        q.add(new point(nx,ny,0));
                    }
                }
            }
        }

        if(result== Integer.MAX_VALUE)
            System.out.println("Fail");
        else
            System.out.println(result);
    }
}
class point{
    int x;
    int y;
    int gram;

    point(int x,int y,int gram)
    {
        this.x = x;
        this.y = y;
        this.gram = gram;
    }
}