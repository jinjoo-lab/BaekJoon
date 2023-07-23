import java.io.*;
import java.util.*;

public class Main {
    static int n  = 0;
    static int m = 0 ;
    static int k = 0;
    static char[][] board = new char[101][101];
    static boolean[][] visit = new boolean[101][101];
    static int[] mine = new int[101];
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};


    static Queue<point> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

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
        for(int i=1;i<=k;i++)
        {
            mine[i] = Integer.parseInt(st.nextToken());

        }
        for(int i=1;i<=k;i++)
        {
            if(i%2==0) {
                gang(mine[i], false);
                force();
            }
            else {
                gang(mine[i], true);
                force();
            }

        }
        print(board);

        br.close();
    }

    static void gang(int where,boolean left)
    {
        int row = n + 1 - where;

        if(left)
        {
            for(int i=1;i<=m;i++) {
                if (board[row][i] == 'x')
                {
                    board[row][i] = '.';
                    break;
                }
            }
        }

        else{
            for(int i=m;i>=1;i--) {
                if (board[row][i] == 'x')
                {
                    board[row][i] = '.';
                    break;
                }
            }
        }
    }

    static void force()
    {
        Queue<point> q = new LinkedList<>();
        visit = new boolean[101][101];

        for(int i=1;i<=m;i++)
        {
            if(board[n][i]=='x') {
                q.add(new point(n, i));
                visit[n][i] = true;
            }
        }

        while(!q.isEmpty())
        {
            point cur = q.poll();

            for(int i=0;i<4;i++)
            {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx>=1&&nx<=n&&ny>=1&&ny<=m)
                {
                    if(!visit[nx][ny]&&board[nx][ny]=='x')
                    {
                        visit[nx][ny] = true;
                        q.add(new point(nx,ny));
                    }
                }
            }
        }

        ArrayList<point> list = new ArrayList<>();
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                if(board[i][j]=='x'&&!visit[i][j])
                {
                    list.add(new point(i,j));
                }
            }
        }

        if(!list.isEmpty())
            down(list);

    }
    static void down(ArrayList<point> list)
    {
        for(point cur : list)
        {
            board[cur.x][cur.y] = '.';
        }

        int idx = 1;

        out : while(true)
        {
            for(point cur : list)
            {
                int nx = cur.x +idx;
                int ny = cur.y;

                if(board[nx][ny]=='x') {
                    idx = idx -1;
                    break out;
                }
                if(nx==n)
                    break out;
            }

            idx = idx + 1;
        }

        for(point cur : list)
        {
            board[cur.x+idx][cur.y] = 'x';
        }
    }
    static void print(char[][] board)
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

    static void print(boolean[][] board)
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