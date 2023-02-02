import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[][] board = new int[51][51];
    static boolean[][] visit = new boolean[51][51];
    static Queue<point> q= new LinkedList<>();
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int result  = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());
        point start = new point(x,y,dir);

        for(int i=0;i<n;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            for(int j=0;j<m;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        q.add(start);
        visit[start.x][start.y] = true;
        result++;

        bfs();
        System.out.println(result);

        br.close();
    }
    static int change(int now)
    {
        if(now==0)
            return 3;
        else
            return now-1;
    }
    static void bfs()
    {
        boolean keep = true;
        while(!q.isEmpty()&&keep)
        {
            point cur = q.poll();
            int dir = cur.dir;
            boolean isAll = false;

            for(int i=0;i<4;i++)
            {
                dir = change(dir);
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                if(nx>=0&&nx<n&&ny>=0&&ny<m)
                {
                    if(board[nx][ny]==0&&!visit[nx][ny])
                    {
                        visit[nx][ny]=true;
                        result++;
                        q.add(new point(nx,ny,dir));
                        isAll = true;
                        break;
                    }
                }
            }
            if(!isAll)
            {
                int nx = cur.x - dx[cur.dir];
                int ny = cur.y - dy[cur.dir];

                if(nx>=0&&nx<n&&ny>=0&&ny<m)
                {
                    if(board[nx][ny]==0)
                    {
                        q.add(new point(nx,ny,cur.dir));
                    }

                    else{
                        keep = false;
                    }
                }
                else{
                    keep = false;
                }
            }

        }
    }
}
class point{
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
