import java.io.*;
import java.util.*;

public class Main {
    static boolean keep = true;
    static int n = 0;
    static int m = 0;
    static int sx;
    static int sy;

    static int tx;
    static int ty;
    static int[][] board = new int[51][51];

    static int idx = 0;

    static int[] dx = {0,-1,1,0,0};
    static int[] dy = {0,0,0,-1,1};

    static int[] movex = {0,-1,0,1};
    static int[] movey = {1,0,-1,0};

    static int result = 0;
    static int[] tmp = new int[6000];
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

        sx = (n+1)/2;
        sy = (n+1)/2;

        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            blizard(d,s);
            all_boom();
            baby();
        }

        System.out.println(result);
        br.close();
    }
    static void all_boom()
    {
        boolean re = boom();

        while(re)
        {
            re = boom();
        }
    }

    static boolean boom()
    {
        boolean re = false;
        copy();
        int count = 0;
        int sx = 1;
        int lx = 1;
        int color = tmp[1];
        for(int i=2;i<=n*n;i++)
        {
            if(tmp[i]==color)
            {
                sx = i;
            }
            else{
                int minus = sx - lx;
                if(minus>=3)
                {
                    re = true;
                    count = count + minus +1;
                    for(int j = sx;j>=lx;j--)
                    {
                        tmp[j] = 0;
                    }

                    result += (minus+1)*color;
                }
                sx = i;
                lx = i;
                color = tmp[i];
            }
        }
        for(int i=1;i<=count;i++)
        {
            ahead();
        }
        recopy();

        return re;
    }
    static void recopy()
    {
        idx = 0;
        keep = true;
        tx = sx;
        ty = sy;

        int first = 1;
        int second = 2;
        while(true)
        {
            keep = remove(0,first);
            if(!keep)
            {
                break;
            }
            keep = remove(1,first);

            if(!keep)
            {
                break;
            }
            keep = remove(2,second);

            if(!keep)
            {
                break;
            }
            keep = remove(3,second);

            if(!keep)
            {
                break;
            }

            first = first + 2;
            second = second + 2;
        }
    }
    static void copy()
    {
        tmp = new int[6000];
        idx = 0;
        keep = true;
        tx = sx;
        ty = sy;

        int first = 1;
        int second = 2;
        while(true)
        {
            keep = move(0,first);
            if(!keep)
            {
                break;
            }
            keep = move(1,first);

            if(!keep)
            {
                break;
            }
            keep = move(2,second);

            if(!keep)
            {
                break;
            }
            keep = move(3,second);

            if(!keep)
            {
                break;
            }

            first = first + 2;
            second = second + 2;
        }
    }
    static void blizard(int d,int s)
    {
        int count = 0;
        for(int i=1;i<=s;i++)
        {
            int nx = sx + i*dx[d];
            int ny = sy + i*dy[d];

            if(nx>=1&&nx<=n&&ny>=1&&ny<=n)
            {
                board[nx][ny] = 0;
                count++;
            }
        }
        copy();
        for(int i=1;i<=count;i++) {
            ahead();
        }
        recopy();
    }

    static int changeDir(int dir)
    {
        if(dir==0)
        {
            return 2;
        }

        else if(dir==1)
        {
            return 3;
        }

        else if(dir==2)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
    static void print()
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    static boolean remove(int dir, int k)
    {
        int ndir = changeDir(dir);
        for(int i=1;i<=k;i++) {

            board[tx][ty] = tmp[idx++];
            if(tx==1&&ty==1)
            {
                return false;
            }

            int nx = tx + movex[ndir];
            int ny = ty + movey[ndir];

            tx = nx;
            ty = ny;
        }

        return true;
    }
    static boolean move(int dir,int k)
    {

        int ndir = changeDir(dir);

        for(int i=1;i<=k;i++) {

            tmp[idx++] = board[tx][ty];
            if(tx==1&&ty==1)
            {
                return false;
            }
            int nx = tx + movex[ndir];
            int ny = ty + movey[ndir];

            tx = nx;
            ty = ny;

        }

        return true;
    }

    static void ahead()
    {
        int idx = 0;
        for(int i=1;i<n*n;i++)
        {
            if(tmp[i]==0) {
                idx = i;
                break;
            }
        }

        if(idx>0)
        {
            for(int i=idx;i<n*n;i++)
            {
                tmp[i] = tmp[i+1];
            }
        }
    }

    static void baby()
    {
       copy();
       int[] tmp2 = new int[6000];
       int idx2 = 1;
        int sx = 1;
        int lx = 1;
        int color = tmp[1];
        for(int i=2;i<=n*n;i++)
        {
            if(tmp[i]==color)
            {
                sx = i;
            }
            else{
                int a = sx - lx +1;
                int b = color;
                tmp2[idx2++] = a;
                tmp2[idx2++] = b;
                sx = i;
                lx = i;

                color = tmp[i];
            }
        }

        tmp = tmp2;
        recopy();
    }
}