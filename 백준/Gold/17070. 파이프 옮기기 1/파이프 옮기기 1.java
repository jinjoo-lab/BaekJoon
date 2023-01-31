import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[][] board = new int[18][18];
    static int[] dx = {0, 1, 1};
    static int[] dy = {1, 0, 1};
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1,2,0);
        System.out.println(result);
    }
    static void dfs(int x,int y,int dir)
    {
        if(x==n&&y==n)
        {
            result++;
            return;
        }

        if(dir==0)
        {
            int nx = x + dx[0];
            int ny = y + dy[0];

            if(nx>=1&&ny>=1&&nx<=n&&ny<=n)
            {
                if(board[nx][ny]==0)
                {
                    dfs(nx,ny,0);
                }
            }
            for(int i=0;i<3;i++) {
                nx = x + dx[i];
                ny = y + dy[i];


                if (nx >= 1 && ny >= 1 && nx <= n && ny <= n) {
                    if (board[nx][ny] == 1) {
                        return;
                    }
                } else {
                    return;
                }
            }
            dfs(nx, ny, 2);
        }
        else if(dir==1)
        {
            int nx = x + dx[1];
            int ny = y + dy[1];

            if(nx>=1&&ny>=1&&nx<=n&&ny<=n)
            {
                if(board[nx][ny]==0)
                {
                    dfs(nx,ny,1);
                }
            }
            for(int i=0;i<3;i++) {
                nx = x + dx[i];
                ny = y + dy[i];


                if (nx >= 1 && ny >= 1 && nx <= n && ny <= n) {
                    if (board[nx][ny] == 1) {
                        return;
                    }
                } else {
                    return;
                }
            }
            dfs(nx, ny, 2);
        }
        else if(dir==2)
        {
            int nx = x + dx[0];
            int ny = y + dy[0];

            if(nx>=1&&ny>=1&&nx<=n&&ny<=n)
            {
                if(board[nx][ny]==0)
                {
                    dfs(nx,ny,0);
                }
            }
            nx = x + dx[1];
            ny = y + dy[1];

            if(nx>=1&&ny>=1&&nx<=n&&ny<=n)
            {
                if(board[nx][ny]==0)
                {
                    dfs(nx,ny,1);
                }
            }
            for(int i=0;i<3;i++) {
                nx = x + dx[i];
                ny = y + dy[i];


                if (nx >= 1 && ny >= 1 && nx <= n && ny <= n) {
                    if (board[nx][ny] == 1) {
                        return;
                    }
                } else {
                    return;
                }
            }
            dfs(nx, ny, 2);
        }
    }
}
