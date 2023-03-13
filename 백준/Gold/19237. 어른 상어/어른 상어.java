
import java.io.*;
import java.util.*;

public class Main {
    static int sec = 0;
    static int n = 0;
    static int m = 0;
    static int k = 0;
    static int sharkNum = 0;
    static int[][] board = new int[21][21];
    static int[][] smell = new int[21][21];
    static int[][] owner = new int[21][21];
    static int[] curdir = new int[401];
    static int[][][] dir = new int[401][5][5];

    static int[] dx = {0,-1,1,0,0};
    static int[] dy = {0,0,-0,-1,1};
    static Queue<shark> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        sharkNum = m;
        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1;j<=n;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j]>=1) {
                    smell[i][j] = k;
                    owner[i][j] = board[i][j];
                }
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=m;i++)
        {
            curdir[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<=m;i++)
        {
            for(int j=1;j<=4;j++)
            {
                st = new StringTokenizer(br.readLine(), " ");

                for(int l=1;l<=4;l++)
                {
                    dir[i][j][l] = Integer.parseInt(st.nextToken());
                }
            }
        }
        while(true) {
           boolean cur =  init();
           if(cur==false)
               break;
           move();
           if(sec>1000)
               break;
        }

        if(sec>1000)
            System.out.println(-1);
        else{
            System.out.println(sec);
        }
        br.close();
    }
    static void print3()
    {
        for(int i=1;i<=m;i++)
        {
            for(int j=1;j<=4;j++)
            {
                for(int l=1;l<=4;l++)
                {
                    System.out.print((dir[i][j][l]+" "));
                } System.out.println();
            } System.out.println();
        }
    }
    
    static boolean init()
    {
        int num = 0;
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                if(board[i][j]>=1)
                {
                    q.add(new shark(i,j,board[i][j],curdir[board[i][j]]));
                    num++;
                }
            }
        }

        if(num==1)
            return false;
        else
            return true;
    }
    static void perfum()
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                if(smell[i][j]>=1)
                {
                    smell[i][j]= smell[i][j] -1;
                    if(smell[i][j]==0)
                    {
                        owner[i][j] = 0;
                    }
                }

            }
        }

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                if(board[i][j]>=1)
                {
                    smell[i][j]= k;
                    owner[i][j] = board[i][j];
                }
            }
        }

        sec++;
    }
    static void move()
    {
        int[][] tmp = new int[21][21];

        while(!q.isEmpty())
        {
            boolean get = false;
            shark cur = q.poll();
            int nx = cur.x;
            int ny = cur.y;
            int cdir = cur.dir;
            for(int i=1;i<=4;i++)
            {
                nx = cur.x + dx[dir[cur.num][cur.dir][i]];
                ny = cur.y + dy[dir[cur.num][cur.dir][i]];
                cdir = dir[cur.num][cur.dir][i];
                if(nx>=1&&nx<=n&&ny>=1&&ny<=n)
                {
                    if(smell[nx][ny]==0)
                    {
                        get = true;
                        break;
                    }
                }
            }
            if(get)
            {
                if(tmp[nx][ny]==0||tmp[nx][ny]>cur.num) {
                    if(tmp[nx][ny]>=1)
                        sharkNum--;
                    tmp[nx][ny] = cur.num;
                    curdir[cur.num] = cdir;
                }
            }

            else{
                get = false;
                for(int i=1;i<=4;i++)
                {
                    nx = cur.x + dx[dir[cur.num][cur.dir][i]];
                    ny = cur.y + dy[dir[cur.num][cur.dir][i]];
                    cdir = dir[cur.num][cur.dir][i];
                    if(nx>=1&&nx<=n&&ny>=1&&ny<=n)
                    {
                        if(owner[nx][ny]==cur.num)
                        {
                            get = true;
                            break;
                        }
                    }
                }

                if(get)
                {
                    tmp[nx][ny] = cur.num;
                    curdir[cur.num] = cdir;
                }
                else{
                    tmp[cur.x][cur.y] = cur.num;
                    curdir[cur.num] = cur.dir;
                }
            }
        }
        copy(tmp);
        perfum();
    }

    static void copy(int[][] tmp)
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                board[i][j] = tmp[i][j];
            }
        }
    }
}
class shark{
    int x;
    int y;

    int num;
    int dir;

    shark(int x,int y,int num,int dir)
    {
        this.x = x;
        this.y = y;
        this.num = num;
        this.dir =dir;
    }
}
