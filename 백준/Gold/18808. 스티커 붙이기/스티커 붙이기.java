import java.io.*;
import java.util.*;

public class Main {

    static int n = 0;
    static int m = 0;
    static int k = 0;
    static int[][] board;

    static Sticker[] sarr;

    static boolean[] keep;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        sarr = new Sticker[k+1];
        keep = new boolean[k+1];

        for(int i=1;i<=k;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Sticker s = new Sticker(x,y);
            for(int j=1;j<=x;j++)
            {
                st = new StringTokenizer(br.readLine()," ");
                for(int a = 1; a<= y; a++)
                {
                    s.board[j][a] = Integer.parseInt(st.nextToken());
                }
            }

            sarr[i] = s;
        }


        dfs(1);


        br.close();
    }
    static int count()
    {
        int result = 0;

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                if(board[i][j] == 1)
                    result = result + 1;
            }
        }

        return result;
    }

    static void dfs(int cur)
    {

        if(cur > k) {
            System.out.println(count());
            return;
        }

        Sticker tmp = sarr[cur];

        for(int a=0;a<4;a++)
        {
            if(keep[cur])
                break;

            if(a!=0)
            {
                tmp = turn(tmp);
            }

            loop1 : for(int i = 1;i <= n; i++)
            {
                for(int j=1 ; j<= m ;j++)
                {
                    if(canPost(tmp,i,j))
                    {
                        post(tmp,i,j);
                        keep[cur] = true;
                        dfs(cur+1);
                        break loop1;
                    }
                }
            }
        }

        if(!keep[cur])
        {
            dfs(cur+1);
        }

    }

    static boolean canPost(Sticker cur,int x,int y)
    {
        if(x + cur.x -1 > n)
            return false;

        if(y + cur.y - 1 > m)
            return false;

        int xdx = 1;
        for(int i=x;i<x+cur.x;i++)
        {
            int ydx = 1;
            for(int j=y;j<y+cur.y;j++)
            {
                if(cur.board[xdx][ydx] == 1 && board[i][j] == 1)
                    return false;
                ydx = ydx + 1;
            }
            xdx = xdx + 1;
        }
        return true;
    }

    static void post(Sticker cur,int x,int y)
    {
        int xdx = 1;
        for(int i=x;i<x+cur.x;i++)
        {
            int ydx = 1;
            for(int j=y;j<y+cur.y;j++)
            {
                if(board[i][j] == 0 && cur.board[xdx][ydx]==1)
                    board[i][j] = cur.board[xdx][ydx];
                ydx = ydx + 1;
            }

            xdx = xdx + 1;
        }

    }

    static Sticker turn(Sticker cur)
    {
        Sticker tmp = new Sticker(cur.y,cur.x);
        int sy = cur.x;
        int sx = 1;
        for(int i=1 ; i<= cur.x ; i++)
        {
            sx = 1;
            for(int j= 1;j <= cur.y ; j++)
            {
                tmp.board[sx][sy]= cur.board[i][j];
                sx = sx + 1;
            }
            sy = sy - 1;
        }

        return tmp;
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


}
class Sticker
{
    int x;
    int y;
    int[][] board;

    Sticker(int x,int y)
    {
        this.x = x;
        this.y = y;
        this.board = new int[x+1][y+1];
    }
}
