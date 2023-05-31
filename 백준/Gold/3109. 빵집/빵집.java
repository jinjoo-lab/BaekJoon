import java.io.*;
import java.util.*;
public class Main {

    static int[] dx ={-1,0,1};
    static int[] dy ={1,1,1};

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] board = new char[n+1][m+1];
        boolean[][] visit = new boolean[n+1][m+1];

        for(int i=1;i<=n;i++)
        {
            String line = br.readLine();
            for(int j=1;j<=m;j++)
            {
                board[i][j] = line.charAt(j-1);
            }
        }

        for(int i=1;i<=n;i++)
        {
            if(board[i][1]=='.')
            {
                visit[i][1] = true;
                search(board,visit,i,1,n,m);
            }
        }
        System.out.println(result);
        br.close();
    }

    static boolean search(char[][] board,boolean[][] visit,int x,int y,int n,int m)
    {
        if(y==m)
        {
            result = result + 1;
            return true;
        }

        for(int i=0;i<3;i++)
        {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx>=1&&nx<=n&&ny>=1&&ny<=m) {
                if (board[nx][ny] == '.' && !visit[nx][ny]) {
                    visit[nx][ny] = true;
                    boolean re = search(board, visit, nx, ny, n, m);
                    if(re){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    static void print(int n,int m,boolean[][] visit)
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                if(visit[i][j])
                    System.out.print(1+" ");
                else
                    System.out.print(0+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
