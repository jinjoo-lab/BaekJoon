import java.io.*;
import java.util.*;

public class Main {
    static int n  = 0;
    static int m = 0 ;
    static char[][] board = new char[51][51];
    static int[][] visit = new int[51][51];

    static boolean[][] water = new boolean[51][51];

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static int result = -1;
    static int sx = 0;
    static int sy = 0;

    static Queue<point> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=1;i<=n;i++)
        {
            String line = br.readLine();
            for(int j=1;j<=m;j++) {
                board[i][j] = line.charAt(j - 1);

                if (board[i][j] == 'S')
                {
                    sx = i;
                    sy = j;
                }

                if(board[i][j]=='*')
                {
                    q.add(new point(i,j,true));
                    water[i][j]= true;
                }
            }
        }

        bfs();

        if(result==-1)
            System.out.println("KAKTUS");
        else{
            System.out.println(result);
        }

        br.close();
    }

    static void bfs()
    {
        q.add(new point(sx,sy,false));
        visit[sx][sy] = 1;

        while(!q.isEmpty())
        {
            point cur = q.poll();

            if(cur.w==false)
            {
                if(board[cur.x][cur.y]=='D')
                    result = visit[cur.x][cur.y]-1;
            }

            for(int i=0;i<4;i++)
            {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<1||nx>n||ny<1||ny>m)
                    continue;

                if(cur.w)
                {
                    if(!water[nx][ny]&&board[nx][ny]=='.')
                    {
                        water[nx][ny] = true;
                        q.add(new point(nx,ny,true));
                    }
                }

                else{
                    if(visit[nx][ny]==0&&water[nx][ny]==false)
                    {
                        if(board[nx][ny]=='.'||board[nx][ny]=='D')
                        {
                            visit[nx][ny] = visit[cur.x][cur.y] + 1;
                            q.add(new point(nx,ny,false));
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

    boolean w;
    point(int x,int y,boolean w)
    {
        this.x = x;
        this.y = y;
        this.w = w;
    }
}