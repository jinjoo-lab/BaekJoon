import java.io.*;
import java.util.*;

public class Main {

    static int n = 0;
    static int m = 0;


    static int[] qx  = {0,0,-1,1,1,1,-1,-1};
    static int[] qy = {1,-1,0,0,1,-1,-1,1};

    static int[] kx = {-1,1,-1,1,-2,-2,2,2};
    static int[] ky = {-2,2,2,-2,1,-1,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] board = new int[n+1][m+1];
        boolean[][] visit = new boolean[n+1][m+1];

        st = new StringTokenizer(br.readLine(), " ");
        int num = Integer.parseInt(st.nextToken());
        for(int i=1;i<=num;i++)
        {
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            board[x][y] = 1; // Queen
        }

        st = new StringTokenizer(br.readLine(), " ");
        num = Integer.parseInt(st.nextToken());
        for(int i=1;i<=num;i++)
        {
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            board[x][y] = 2; // Knight
        }

        st = new StringTokenizer(br.readLine(), " ");
        num = Integer.parseInt(st.nextToken());
        for(int i=1;i<=num;i++)
        {
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            board[x][y] = -1; // Pawn
        }

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                if(board[i][j]==1||board[i][j]==2||board[i][j]==-1)
                    move(i,j,board,visit);
            }
        }

        System.out.println(count(visit));
        
        br.close();
    }
    static void print(boolean[][] visit)
    {
        for(int i=1;i<=n;i++) {
            for (int j = 1; j <= m; j++) {
                if(visit[i][j])
                {
                    System.out.print(1+" ");
                }
                else{
                    System.out.print(0+" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    static int count(boolean[][] visit)
    {
        int count = 0;

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                if(!visit[i][j])
                    count = count + 1;
            }
        }

        return count;
    }
    static void move(int x,int y,int[][] board,boolean[][] visit)
    {
        visit[x][y] = true;

        if(board[x][y]==1)
        {
            int idx = 1;
            boolean[] keep = new boolean[8];

            while(true)
            {
                for(int i=0;i<8;i++)
                {
                    if(keep[i])
                        continue;

                    int nx = x + idx*qx[i];
                    int ny = y + idx*qy[i];

                    if(nx<1||nx>n||ny<1||ny>m) {
                        keep[i] = true;
                        continue;
                    }

                    if(board[nx][ny]==-1 || board[nx][ny]==2) {
                        keep[i] = true;
                        continue;
                    }
                    visit[nx][ny] = true;
                }

                int tmp = 0;
                for(int i=0;i<8;i++)
                {
                    if(keep[i])
                         tmp = tmp + 1;
                }

                if(tmp==8)
                    break;

                else{
                    idx = idx + 1;
                }
            }
        }

        else if(board[x][y]==2)
        {
            for(int i=0;i<8;i++)
            {
                int nx = x + kx[i];
                int ny = y + ky[i];

                if(nx<1||nx>n||ny<1||ny>m)
                    continue;

                visit[nx][ny] = true;
            }
        }
    }
}
