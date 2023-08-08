import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[][] board;

    static boolean[][] visit;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0}; // r , l , d , u
    static int[][] fx = {{0,1,2},{0,1,3},{0,2,3},{1,2,3}};

    static int[][][] s = {
            {{0,1},{0,2},{0,3}},
            {{1,0},{2,0},{3,0}},
            {{0,1},{1,0},{1,1}},
            {{1,0},{2,0},{2,1}},
            {{1,0},{2,0},{2,-1}},
            {{1,0},{2,0},{0,1}},
            {{1,0},{2,0},{0,-1}},
            {{0,1},{0,2},{1,2}},
            {{0,1},{0,2},{-1,2}},
            {{0,1},{0,2},{-1,0}},
            {{0,1},{0,2},{1,0}},
            {{1,0},{1,1},{2,1}},
            {{0,1},{-1,1},{-1,2}},
            {{0,1},{1,1},{1,2}},
            {{1,0},{1,-1},{2,-1}}
    }; // 15 3 2

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        visit = new boolean[n+1][m+1];

        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");

            for(int j=1;j<=m;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                result = Math.max(result , fuck(i,j));
                result = Math.max(result,search(i,j));
            }
        }
        System.out.println(result);
        br.close();
    }

    static int search(int x,int y)
    {
        int result = 0;

        for(int i=0;i<15;i++)
        {
            int start = board[x][y];
            boolean complete = false;

            for(int j=0;j<3;j++)
            {
                int nx = x + s[i][j][0];
                int ny = y + s[i][j][1];

                if(nx < 1 || nx > n || ny < 1 || ny > m)
                {
                    break;
                }

                else{
                    start += board[nx][ny];
                    complete = true;
                }
            }

            if(complete)
            {
                result = Math.max(result , start);
            }
        }

        return result;
    }

    static int fuck(int x,int y){
        int result = 0;

        for(int i=0;i<4;i++)
        {
            int start = board[x][y];
            boolean complete = false;

            for(int j=0;j<3;j++)
            {
                int nx = x + dx[fx[i][j]];
                int ny = y + dy[fx[i][j]];

                if(nx < 1 || nx > n || ny < 1 || ny > m)
                {
                    break;
                }

                else{
                    start += board[nx][ny];
                    complete = true;
                }
            }

            if(complete)
            {
                result = Math.max(result , start);
            }

        }
        return result;
    }


}