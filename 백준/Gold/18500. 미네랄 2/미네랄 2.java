import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int k = 0;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static char[][] board;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n+1][m+1];


        for(int i=1;i<=n;i++)
        {
            String line = br.readLine();

            for(int j=1;j<=m;j++)
            {
                board[i][j] = line.charAt(j-1);
            }
        }

        k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        boolean left = true;

        for(int i=1;i<=k;i++)
        {
            int h = Integer.parseInt(st.nextToken());
            boolean isBreak = stone(left,h);
            left = !left;

            if(!isBreak)
                continue;

            force();

            int count = down();

            if(count !=0) {
                last(count);
            }

        }
        print();

        br.close();
    }
    static void last(int count)
    {
        char[][] tmp = new char[n+1][m+1];

        for(int i=n;i>=1;i--)
        {
            for(int j=1 ;j <= m; j++)
            {
                tmp[i][j] = '.';

                if(board[i][j] == 'x')
                {
                    if(visit[i][j])
                        tmp[i][j] = 'x';

                    else{
                        tmp[i + count][j] = 'x';
                    }
                }

            }
        }

        board = tmp;
    }
    static int down()
    {
        int move = 0;

        for(int i=1;i<=m;i++)
        {
            for(int j= n -1;j >=1;j--)
            {
                if(board[j][i] == 'x' && !visit[j][i])
                {
                    int count = 1;
                    int start = j + 1;
                    while(start <= n)
                    {
                        if(board[start][i] == 'x' && visit[start][i]) {
                            count = count - 1;
                            break;
                        }

                        if(start == n)
                            break;

                        start = start + 1;
                        count = count + 1;
                    }

                    if(move == 0 || move > count)
                    {
                        move = count;
                    }

                }
            }
        }

        return move;
    }

    static void force()
    {
        visit = new boolean[n+1][m+1];
        Queue<Point> q= new LinkedList<>();

        for(int i= 1;i<=m;i++)
        {
            if(board[n][i] == 'x')
            {
                visit[n][i] = true;
                q.add(new Point(n,i));
            }
        }

        while(!q.isEmpty())
        {
            Point cur = q.poll();

            for(int i=0;i<4;i++)
            {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > m)
                    continue;

                if(visit[nx][ny])
                    continue;

                if(board[nx][ny] == 'x') {
                    visit[nx][ny] = true;
                    q.add(new Point(nx, ny));
                }
            }
        }
    }

    static boolean stone(boolean left,int h)
    {
        boolean isBreak = false;

        if(left)
        {
            for(int i=1;i<=m;i++)
            {
                if(board[n - h + 1][i] == 'x')
                {
                    board[n - h + 1][i] = '.';
                    isBreak = true;
                    return isBreak;
                }
            }
        }

        else {
            for (int i = m; i >= 1; i--)
            {
                if(board[n - h + 1][i] == 'x')
                {
                    board[n - h + 1][i] = '.';
                    isBreak = true;
                    return isBreak;
                }
            }
        }

        return isBreak;
    }

    static void print()
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
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
