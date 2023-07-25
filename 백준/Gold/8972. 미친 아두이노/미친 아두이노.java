import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int l = 0;
    static int k = 0;

    static int[] dx = {0,1,1,1,0,0,0,-1,-1,-1};
    static int[] dy = {0,-1,0,1,-1,0,1,-1,0,1};
    static char[][] board;

    static int sx;
    static int sy;

    static Queue<Point> crazy = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n+1][m+1];

        for(int i=1;i<=n;i++)
        {
            String line = br.readLine();

            for(int j=1;j<=m;j++) {
                board[i][j] = line.charAt(j - 1);

                if (board[i][j] == 'I')
                {
                    sx = i;
                    sy = j;
                }

                if(board[i][j] == 'R')
                    crazy.add(new Point(i,j));
            }
        }
        String cal = br.readLine();


        boolean over = false;
        for(int i=0;i<cal.length();i++)
        {
            int cd = Integer.parseInt(cal.charAt(i)+"");

            boolean f = moveJ(cd);

            if(!f) {
                System.out.println("kraj " + (i + 1));
                over = true;
                break;
            }

            boolean s = moveC(sx,sy);

            if(!s){
                System.out.println("kraj "+(i+1));
                over = true;
                break;
            }

        }

        if(!over)
        {
            print();
        }

        br.close();
    }

    static boolean moveJ(int dir)
    {
        int nx = sx + dx[dir];
        int ny = sy + dy[dir];

        if(board[nx][ny] == 'R')
            return false;

        board[sx][sy] = '.';
        board[nx][ny] = 'I';

        sx = nx;
        sy = ny;

        return true;
    }

    static int cal(int cx,int cy,int x,int y)
    {
        return Math.abs(cx - x) + Math.abs(cy - y);
    }

    static void print()
    {
        StringBuilder sb = new StringBuilder();

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                sb.append(board[i][j]);
            }sb.append("\n");
        }

        System.out.println(sb);
    }

    static boolean moveC(int x,int y)
    {
        int[][] count = new int[n+1][m+1];

        while(!crazy.isEmpty())
        {
            Point cur = crazy.poll();
            int dis = 100000;
            int idx = 1;

            for(int i=1;i<=9;i++)
            {
                int tx = cur.x + dx[i];
                int ty = cur.y + dy[i];

                if(tx <1 || tx > n || ty < 1 || ty > m)
                    continue;

                int td = cal(tx,ty,x,y);

                if(td < dis)
                {
                    dis = td;
                    idx = i;
                }
            }

            int nx = cur.x + dx[idx];
            int ny = cur.y + dy[idx];

            if(board[nx][ny] == 'I')
                return false;

            count[nx][ny] +=1;
        }

        board = new char[n+1][m+1];

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                board[i][j] = '.';

                if(count[i][j] == 1) {
                    board[i][j] = 'R';
                    crazy.add(new Point(i, j));
                }
            }
        }

        board[sx][sy] = 'I';

        return true;
    }

}
class Point
{
    int x;
    int y;

    Point(int x,int y)
    {
        this.x = x;
        this.y = y;
    }
}