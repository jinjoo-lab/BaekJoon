import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[][] board = new int[21][21];
    static boolean[][] visit = new boolean[21][21];
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int score = 0;
    static int rainbow = 0;
    static boolean[] force = new boolean[21];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1;j<=n;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean keep = true;

        while(keep)
        {
            keep = search();

            force();

            turn();

            force();

        }
        System.out.println(score);

        br.close();
    }

    static void force()
    {
        force = new boolean[21];

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                if(board[i][j]==-2)
                    force[j] = true;
            }
        }

        for(int i=1;i<=n;i++)
        {
            if(force[i])
            {
                for(int j=n;j>=1;j--)
                {
                    if(board[j][i]!=-2&&board[j][i]!=-1)
                    {
                        for(int k=j+1;k<=n;k++)
                        {
                            if(board[k][i]==-2)
                            {
                                board[k][i] = board[k-1][i];
                                board[k-1][i] = -2;
                            }
                            else{
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    static void turn()
    {
        int[][] tmp = new int[21][21];
        int nx = 1;
        for(int i= n;i>=1;i--)
        {
            int ny = 1;
            for(int j=1;j<=n;j++)
            {
                tmp[nx][ny] = board[j][i];
                ny++;
            }
            nx++;
        }

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                board[i][j] = tmp[i][j];
            }
        }
    }


    static boolean search()
    {
        int rb = -1;
        visit = new boolean[21][21];
        int result= 1;
        int mx = -1;
        int my = -1;
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                if(!visit[i][j]&&board[i][j]>0)
                {
                    int tmp = block(i,j);

                    if(tmp==1)
                        continue;
                    if(tmp>result)
                    {
                        rb = rainbow;
                        result = tmp;
                        mx = i;
                        my = j;
                    }
                    else if(tmp==result) {
                        if (rainbow > rb) {
                            rb = rainbow;
                            result = tmp;
                            mx = i;
                            my = j;
                        }
                        else if (rainbow == rb) {
                            if (i > mx) {
                                result = tmp;
                                mx = i;
                                my = j;
                            }
                            else if (i == mx) {
                                if (j > my) {
                                    result = tmp;
                                    mx = i;
                                    my = j;
                                }
                            }
                        }
                    }
                }
            }
        }

        if(mx==-1&&my==-1)
        {
            return false;
        }
        else{
            score +=result*result;
            remove(mx,my);
        }

        return true;
    }
    static void remove(int x,int y)
    {
        boolean[][] r = new boolean[21][21];
        Queue<point> q = new LinkedList<>();
        int goal = board[x][y];
        q.add(new point(x,y,goal));
        r[x][y]= true;
        board[x][y] = -2;

        while(!q.isEmpty())
        {
            point cur = q.poll();

            for(int i=0;i<4;i++)
            {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx>=1&&nx<=n&&ny>=1&&ny<=n) {
                    if(board[nx][ny]==0||board[nx][ny]==goal)
                    {
                        if(!r[nx][ny])
                        {
                            r[nx][ny]= true;
                            board[nx][ny] = -2;
                            q.add(new point(nx,ny,goal));
                        }
                    }
                }
            }
        }
    }
    static int block(int x,int y)
    {
        boolean[][] zero = new boolean[21][21];
        int score = 1;
        Queue<point> q = new LinkedList<>();
        int goal = board[x][y];
        q.add(new point(x,y,board[x][y]));
        visit[x][y] = true;
        rainbow = 0;
        while(!q.isEmpty())
        {
            point cur = q.poll();
            for(int i=0;i<4;i++)
            {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx>=1&&nx<=n&&ny>=1&&ny<=n)
                {
                    if(board[nx][ny]==goal&&!visit[nx][ny])
                    {
                        visit[nx][ny] = true;
                        score = score + 1;
                        q.add(new point(nx,ny,goal));
                    }

                    else if(board[nx][ny]==0&&!zero[nx][ny])
                    {
                        zero[nx][ny] = true;
                        score = score + 1;
                        rainbow = rainbow + 1;
                        q.add(new point(nx,ny,goal));
                    }
                }
            }
        }

        return score;
    }
}
class point{
    int x;
    int y;
    int c;

    point(int x,int y,int c)
    {
        this.x =x;
        this.y = y;
        this.c =c;
    }
}
