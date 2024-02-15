import java.io.*;
import java.util.*;

public class Main {

    static int n = 0;
    static int m = 0;
    static int[][] board;
    static int[][] visit;
    static boolean[][] finish;

    static int count = 1;
    static int[] dx = {0, 0,1,-1};
    static int[] dy = {1, -1,0,0}; // R , L , D , U

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n + 1][m + 1];
        visit = new int[n+1][m+1];
        finish = new boolean[n+1][m+1];

        for(int i=1;i<=n;i++)
        {
            String line = br.readLine();

            for(int j=1;j<=m;j++)
            {
                char cur = line.charAt(j-1);

                if(cur == 'R')
                {
                    board[i][j] = 0;
                }

                else if(cur == 'L')
                {
                    board[i][j] = 1;
                }

                else if(cur == 'D')
                    board[i][j] = 2;

                else
                    board[i][j] = 3;
            }
        }

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                if(visit[i][j]==0)
                {
                    search(i,j);
                }
            }
        }
        System.out.println(result);
        br.close();
    }

    static void search(int x,int y)
    {
        visit[x][y] = count++;

        int nx = x + dx[board[x][y]];
        int ny = y + dy[board[x][y]];

        if(visit[nx][ny] == 0)
        {
            search(nx,ny);
        }

        else if(!finish[nx][ny])
        {
            result = result + 1;
        }

        finish[x][y] = true;
    }

}
