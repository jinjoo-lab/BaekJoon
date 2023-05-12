
import java.io.*;
import java.util.*;

public class Main {
    static int tc = 0;
    static int[][] board;
    static int[][] visit;
    static boolean[][] fvisit;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static Queue<point> fire;
    static point start;
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
         tc = Integer.parseInt(st.nextToken());

        for(int k=1;k<=tc;k++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            fire = new LinkedList<>();
            int[][] board = new int[n+1][m+1];
            visit = new int[n+1][m+1];
            fvisit = new boolean[n+1][m+1];
            Queue<point> person = new LinkedList<>();

            for(int i=1;i<=n;i++)
            {
                String[] line = br.readLine().split("");
                for(int j=1;j<=m;j++)
                {
                    if(line[j-1].equals("#"))
                        board[i][j] = 1;
                    else if(line[j-1].equals("@")) {
                        start = new point(i, j);
                        person.add(start);
                        visit[i][j] = 1;
                    }

                    else if(line[j-1].equals("*")) {
                        board[i][j] = 2;
                        fire.add(new point(i, j));
                        fvisit[i][j] = true;
                    }
                }
            }
            result = 0;

            while(!person.isEmpty())
            {
               boolean re =  bfs(board,person,n,m);
               if(re)
                   break;
            }

            if(result==0)
                System.out.println("IMPOSSIBLE");
            else{
                System.out.println(result);
            }

        }

        br.close();
    }

    static boolean bfs(int[][] board,Queue<point> person,int n,int m)
    {
        int fsize = fire.size();
        while(fsize != 0)
        {
            point f = fire.poll();
            fsize--;

            for(int i=0;i<4;i++)
            {
                int nx = f.x + dx[i];
                int ny = f.y + dy[i];

                if(nx>=1&&nx<=n&&ny>=1&&ny<=m)
                {
                    if(board[nx][ny]==0&&!fvisit[nx][ny])
                    {
                        fvisit[nx][ny] = true;
                        board[nx][ny] = 2;
                        fire.add(new point(nx,ny));
                    }
                }
            }
        }

        int psize = person.size();
        while(psize!=0)
        {
            point cur = person.poll();
            psize--;

            for(int i=0;i<4;i++)
            {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<1||nx>n||ny<1||ny>m)
                {
                    result = visit[cur.x][cur.y];
                    return true;
                }

                if(nx>=1&&nx<=n&&ny>=1&&ny<=m)
                {
                    if(board[nx][ny]==0)
                    {
                        if(visit[nx][ny]==0)
                        {
                            visit[nx][ny] = visit[cur.x][cur.y]+1;
                            person.add(new point(nx,ny));
                        }
                    }
                }
            }
        }

        return false;
    }

    static void print(int n,int m)
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                System.out.print(visit[i][j]+" ");
            }
            System.out.println();
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