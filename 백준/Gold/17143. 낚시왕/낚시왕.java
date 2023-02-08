import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int k = 0;
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, 1, -1};

    static long result = 0;

    static point[][] board = new point[101][101];
    static Queue<point> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());


        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            point p = new point(x, y, s, d, z);

            board[x][y] = p;
        }
        for (int i = 1; i <= m; i++) {
            get(i);
            copy();
            move();

        }
        System.out.println(result);
        br.close();
    }

    static void get(int man) {
        for(int i=1;i<=n;i++)
        {
            if(board[i][man]!=null)
            {
                result = result + board[i][man].z;
                board[i][man] = null;
                return;
            }
        }
    }

    static int change(int d) {
        if (d == 1)
            return 2;
        if (d == 2)
            return 1;
        if (d == 3)
            return 4;
        if (d == 4)
            return 3;
        return 0;
    }

    static void copy()
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                if(board[i][j]!=null)
                {
                    q.add(board[i][j]);
                }
            }
        }
    }
    static void move() {
        point[][] tmp = new point[101][101];

        while(!q.isEmpty())
        {
            point cur = q.poll();

            int ny = cur.y;
            int nx = cur.x;
            int nd = cur.d;

            if(cur.d==1||cur.d==2)
            {
                for (int j = 0; j < cur.s; j++) {
                    nx = nx + dx[nd];
                    if (nx == 0) {
                        nx = 2;
                        nd = change(nd);
                    } else if (nx == n + 1) {
                        nx = n - 1;
                        nd = change(nd);
                    }
                }
            }
            else if(cur.d==3||cur.d==4)
            {
                for (int j =0; j < cur.s; j++) {
                    ny = ny + dy[nd];
                    if (ny == 0) {
                        ny = 2;
                        nd = change(nd);
                    } else if (ny == m + 1) {
                        ny = m - 1;
                        nd = change(nd);
                    }
                }
            }

            if(tmp[nx][ny]==null||tmp[nx][ny].z<cur.z)
            {
                point p = new point(nx,ny,cur.s,nd,cur.z);
                tmp[nx][ny] = p;
            }
        }

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                board[i][j] = tmp[i][j];
            }
        }
    }
}

class point {
    int x;
    int y;
    int s;
    int d;
    int z;

    point(int x, int y, int s, int d, int z) {
        this.x = x;
        this.y = y;
        this.s = s;
        this.d = d;
        this.z = z;
    }

}
