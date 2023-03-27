
import java.io.*;
import java.util.*;

public class Main {
    static int[][] board = new int[16][16];
    static int[][] tmp = new int[16][16];
    static int[] arrow = new int[4];
    static int n = 0;
    static int m = 0;
    static int d = 0;
    static int result = 0;
    static Queue<point> dead = new LinkedList<>();
    static int[] dx = {-1,0,0};
    static int[] dy = {0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            for(int j=1;j<=m;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        travel(1,1);
        System.out.println(result);
        br.close();
    }
    static void copy()
    {
        for(int i=1;i<=n;i++) {
            for (int j = 1; j <= m; j++) {
                tmp[i][j] = board[i][j];
            }
        }
    }
    static void down()
    {
        for(int i=n-1;i>=1;i--)
        {
            for(int j=1;j<=m;j++)
            {
                tmp[i+1][j] = tmp[i][j];
            }
        }

        for(int j=1;j<=m;j++)
            tmp[1][j] = 0;

    }
    static void travel(int k,int cur)
    {
        if(k==4)
        {
            copy();
            int tmp = game();
            if(tmp>result)
               result = tmp;
            return;
        }

        for(int i=cur;i<=m;i++)
        {
            arrow[k] = i;
            travel(k+1,i+1);
            arrow[k] = 0;
        }
    }
    static boolean check()
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                if(tmp[i][j]==1)
                    return true;
            }
        }

        return false;
    }
    static int game()
    {
        int result = 0;

        while(check()) {
            result = result + attack();
            down();
        }
        return result;
    }
    static int attack()
    {
        int num = 0;
        for(int i=1;i<=3;i++)
        {
            if(tmp[n][arrow[i]]==1)
            {
                dead.add(new point(n,arrow[i]));
            }

            else{
                Queue<point> q = new LinkedList<>();
                boolean[][] visit = new boolean[16][16];
                q.add(new point(n,arrow[i]));
                visit[n][arrow[i]] = true;
                int tx = -1;
                int ty = -1;
                int td = -1;
                while(!q.isEmpty())
                {
                    point cur =q.poll();
                    for(int j=0;j<3;j++)
                    {
                        int nx = cur.x + dx[j];
                        int ny = cur.y + dy[j];
                        if(nx>=1&&nx<=n&&ny>=1&&ny<=m) {
                            if (!visit[nx][ny]){
                                visit[nx][ny] = true;
                                int dis = Math.abs(nx-(n+1)) + Math.abs(ny - arrow[i]);

                                if(dis<=d)
                                {
                                    if(tmp[nx][ny]==1) {
                                        if (td == -1 || td > dis) {
                                            td = dis;
                                            tx = nx;
                                            ty = ny;
                                        } else if (td == dis && ty > ny) {
                                            td = dis;
                                            tx = nx;
                                            ty = ny;
                                        }
                                    }
                                    q.add(new point(nx,ny));
                                }
                            }
                        }
                    }
                }
                if(tx!=-1&&ty!=-1)
                    dead.add(new point(tx,ty));
            }
        }

        while(!dead.isEmpty())
        {
            point body = dead.poll();
            if(tmp[body.x][body.y]==1)
            {
                tmp[body.x][body.y] = 0;
                num = num + 1;
            }
        }
        return num;
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