import java.io.*;
import java.util.*;
public class Main {
    static int n = 0;
    static int m = 0;
    static int k = 0;
    static int[][] board = new int[51][51];
    static int[][] tmp = new int[51][51];
    static boolean[] visit = new boolean[18];
    static turn[] arr = new turn[18];
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            for(int j=1;j<=m;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1;i<=k;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            turn t = new turn(r,c,s);
            arr[i] = t;

        }
        travel(1);
        System.out.println(result);
        br.close();

    }
    static void travel(int cur)
    {
        if(cur>k)
        {
            int first = Integer.MAX_VALUE;
            for(int i=1;i<=n;i++)
            {
                int tmp =0;
                for(int j=1;j<=m;j++)
                    tmp +=board[i][j];

                first = Math.min(first,tmp);
            }

            result = Math.min(result,first);
            return;
        }
        for(int i=1;i<=k;i++)
        {
            if(!visit[i])
            {
                visit[i] = true;
                move(arr[i].r-arr[i].s,arr[i].c-arr[i].s,arr[i].r+arr[i].s,arr[i].c+arr[i].s);
                travel(cur + 1);
                re_move(arr[i].r-arr[i].s,arr[i].c-arr[i].s,arr[i].r+arr[i].s,arr[i].c+arr[i].s);
                visit[i] = false;
            }
        }
    }
    static void print()
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    static void copy()
    {
        tmp = new int[51][51];

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
                tmp[i][j] = board[i][j];
        }
    }

    static void re()
    {
        for(int i=1;i<=n;i++) {
            for (int j = 1; j <= m; j++)
                board[i][j] = tmp[i][j];
        }
    }

    static void move(int sx,int sy,int lx,int ly)
    {
        copy();

        while(true)
        {
            for(int i=ly;i>sy;i--)
            {
                board[sx][i] = tmp[sx][i-1];
            }

            for(int i=sx;i<lx;i++)
            {
                board[i][sy] = tmp[i+1][sy];
            }

            for(int i=sy;i<ly;i++)
            {
                board[lx][i] = tmp[lx][i+1];
            }

            for(int i=lx;i>sx;i--)
            {
                board[i][ly] = tmp[i-1][ly];
            }

            sx = sx +1;
            sy = sy + 1;
            lx = lx -1;
            ly = ly -1;

            if(lx<=sx||ly<=sy)
                break;
        }
    }

    static void re_move(int sx,int sy,int lx,int ly)
    {
        copy();

        while(true)
        {
            for(int i=sy;i<ly;i++)
            {
                board[sx][i] = tmp[sx][i+1];
            }

            for(int i=lx;i>sx;i--)
            {
                board[i][sy] = tmp[i-1][sy];
            }

            for(int i=ly;i>sy;i--)
            {
                board[lx][i] = tmp[lx][i-1];
            }

            for(int i=sx;i<lx;i++)
            {
                board[i][ly] = tmp[i+1][ly];
            }

            sx = sx +1;
            sy = sy + 1;
            lx = lx -1;
            ly = ly -1;

            if(lx<=sx||ly<=sy)
                break;
        }
    }
}
class turn
{
    int r;
    int c;
    int s;

    turn(int r,int c,int s)
    {
        this.r = r;
        this.c = c;
        this.s = s;
    }
}