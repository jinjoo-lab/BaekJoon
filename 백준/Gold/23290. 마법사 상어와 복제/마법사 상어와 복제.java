import java.io.*;
import java.util.*;

public class Main {
    static int[][][] board = new int[5][5][9];
    static int[][][] tmp = new int[5][5][9];
    static int[][] smell = new int[5][5];

    static boolean[][] visit = new boolean[5][5];

    static int[] sdir = new int[5];
    static int[] sresult = new int[5];
    static int m = 0;
    static int s = 0;
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};

    static int[] sdx = {0, -1, 0, 1, 0};
    static int[] sdy = {0, 0, -1, 0, 1};
    static int sx = 0;
    static int sy = 0;

    static int max = 0;
    static int rest = 0;
    static Queue<point> naruto = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            board[x][y][d] = board[x][y][d] + 1;
        }
        st = new StringTokenizer(br.readLine(), " ");
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());

        for(int i=1;i<=s;i++) {
            sresult = new int[5];
            sdir = new int[5];
            bockje();
            move();
            visit = new boolean[5][5];
            max = 0;
            smove(1, sx, sy, 0);
            eat();
            smellOut();
            complete();
        }
        result();
        System.out.println(rest);
        br.close();
    }
    static void result()
    {
        for(int i=1;i<=4;i++) {
            for (int j = 1; j <= 4; j++) {
                for (int k = 1; k <= 8; k++) {
                    rest = rest + board[i][j][k];
                }
            }
        }
    }
    static void bockje()
    {
        for(int i=1;i<=4;i++)
        {
            for(int j=1;j<=4;j++)
            {
               for(int k=1;k<=8;k++)
               {
                   if(board[i][j][k]>=1)
                   {
                       naruto.add(new point(i,j,k,board[i][j][k]));
                   }
               }
            }
        }
    }
    static void move()
    {
        tmp = new int[5][5][9];

        while(!naruto.isEmpty())
        {
            point cur = naruto.poll();
            boolean go = false;
            int nd = cur.dir;

            for(int i=1;i<=8;i++)
            {
                int nx = cur.x + dx[nd];
                int ny = cur.y + dy[nd];

                if(nx<1||nx>4||ny<1||ny>4)
                {
                    nd = changeDir(nd);
                    continue;
                }

                if(smell[nx][ny]>=1||(nx==sx&&ny==sy))
                {
                    nd = changeDir(nd);
                    continue;
                }
                go = true;
                tmp[nx][ny][nd] = tmp[nx][ny][nd] + cur.num;
                break;
            }
            if(!go)
            {
                tmp[cur.x][cur.y][cur.dir] = tmp[cur.x][cur.y][cur.dir] + cur.num;
            }
        }

    }
    static void print(int[][][] tmp)
    {
        for(int i=1;i<=4;i++)
        {
            for(int j=1;j<=4;j++)
            {
                int num = 0;
                for(int k=1;k<=8;k++)
                {
                    num = num + tmp[i][j][k];
                }
                System.out.print(num+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    static void copy()
    {
        for(int i=1;i<=3;i++)
            sresult[i] = sdir[i];
    }
    static boolean compare()
    {
        for(int i=1;i<=3;i++)
        {
            if(sresult[i]!=0)
                return false;
        }
        return true;
    }
    static void smove(int k,int x,int y,int fish)
    {
        if(k==4)
        {
            if((max==0&&compare())||max<fish)
            {
                max = fish;
                copy();
                
            }
            return;
        }

        for(int i=1;i<=4;i++)
        {
            int nx = x + sdx[i];
            int ny = y + sdy[i];

            if(nx<1||nx>4||ny<1||ny>4)
            {
                continue;
            }
            sdir[k] = i;
            if(!visit[nx][ny])
            {
                visit[nx][ny] = true;
                int plus = 0;
                for(int j=1;j<=8;j++)
                {
                    plus +=tmp[nx][ny][j];
                }
                smove(k+1,nx,ny,fish+plus);
                visit[nx][ny] = false;
            }

            else{
                smove(k+1,nx,ny,fish);
            }

        }
    }
    static void eat()
    {
        int nx = sx;
        int ny = sy;

        for(int i=1;i<=3;i++)
        {
            nx = nx + sdx[sresult[i]];
            ny = ny + sdy[sresult[i]];
            for(int j=1;j<=8;j++)
            {
                if(tmp[nx][ny][j]>=1) {
                    tmp[nx][ny][j] = 0;
                    smell[nx][ny] = 3;
                }
            }
        }

        sx = nx;
        sy = ny;
    }

    static int changeDir(int dir) {
        if (dir == 1)
            return 8;
        else
            return dir - 1;
    }

    static void smellOut()
    {
        for(int i=1;i<=4;i++)
        {
            for(int j=1;j<=4;j++)
            {
                if(smell[i][j]>=1)
                    smell[i][j] = smell[i][j] - 1;
            }
        }
    }

    static void complete()
    {
        for(int i=1;i<=4;i++)
        {
            for(int j=1;j<=4;j++)
            {
                for(int k=1;k<=8;k++)
                {
                    board[i][j][k] +=tmp[i][j][k];
                }
            }
        }
    }
}
class point
{
    int x;
    int y;
    int dir;
    int num;

    point(int x,int y,int dir,int num)
    {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.num = num;
    }
}