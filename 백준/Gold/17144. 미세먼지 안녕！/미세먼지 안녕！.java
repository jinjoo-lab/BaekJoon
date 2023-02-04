import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int t = 0;
    static int[][] board = new int[51][51];
    static int[][] copy = new int[51][51];
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static Queue<point> dust = new LinkedList<>();

    static point up;
    static point down;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] >= 1) {
                    dust.add(new point(i, j));
                }

                else if(board[i][j]==-1)
                {
                    if(up==null){
                        up = new point(i,j);
                    }
                    else{
                        down = new point(i,j);
                    }
                }
            }
        }

       for(int i=1;i<=t;i++) {
            gas();
       }
       int score = result();
       System.out.println(score);


        br.close();
    }
    static void copy()
    {
        copy = new int[51][51];

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                copy[i][j] = board[i][j];
            }
        }
    }
    static int result()
    {
        int result1 = 0;
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                if(board[i][j]!=-1) {
                    result1 += board[i][j];
                }
            }
        }
        return result1;
    }
    static void ecopy()
    {
        board = new int[51][51];
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                board[i][j] = copy[i][j];
            }
        }
    }

    static void charge()
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                if(board[i][j]>=5)
                {
                    dust.add(new point(i,j));
                }
            }
        }
    }
    static void gas() {
        copy();

        while (!dust.isEmpty()) {
            point cur = dust.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 1 && ny >= 1 && nx <= n && ny <= m) {
                    if (board[nx][ny] != -1) {
                        copy[nx][ny] = copy[nx][ny] + board[cur.x][cur.y] / 5;
                        copy[cur.x][cur.y] = copy[cur.x][cur.y] - board[cur.x][cur.y]/5;
                    }
                }
            }
        }
        ecopy();
        moveup();
        movedown();
        charge();
    }

    static void moveup()
    {
        for(int i=up.x-1;i>1;i--)
        {
            board[i][1] = board[i-1][1];
        }
        for(int i=1;i<m;i++)
        {
            board[1][i] = board[1][i+1];
        }
        for(int i=1;i<up.x;i++)
        {
            board[i][m] = board[i+1][m];
        }
        for(int i=m;i>2;i--)
        {
            board[up.x][i] = board[up.x][i-1];
        }
        board[up.x][2] = 0;
    }
    static void movedown()
    {
        for(int i=down.x+1;i<n;i++)
        {
            board[i][1] = board[i+1][1];
        }

        for(int i=1;i<m;i++)
        {
            board[n][i] = board[n][i+1];
        }

        for(int i=n;i>down.x;i--)
        {
            board[i][m] = board[i-1][m];
        }
        for(int i=m;i>2;i--)
        {
            board[down.x][i] = board[down.x][i-1];
        }
        board[down.x][2] = 0;
    }
}

class point {
    int x;
    int y;

    point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}