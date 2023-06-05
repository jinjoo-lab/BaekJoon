import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static char[][] board;
    static boolean[][] visit;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static int result = 45;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        for(int k=1;k<=n;k++)
        {
            board = new char[6][10];
            visit = new boolean[6][10];

            for(int i=1;i<=5;i++)
            {
                String line = br.readLine();
                for(int j=1;j<=9;j++)
                {
                    board[i][j] = line.charAt(j-1);
                }
            }
            result = 45;
            count = 0;
            travel(0);
            sb.append(result+" "+count+"\n");

            if(k!=n)
                br.readLine();
        }

        System.out.println(sb);

        br.close();
    }


    static void travel(int cur)
    {
        for(int i=1;i<=5;i++)
        {
            for(int j=1;j<=9;j++)
            {
                if(board[i][j]=='o')
                {
                    for(int k=0;k<4;k++)
                    {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if(nx<1||nx>5||ny<1||ny>9)
                            continue;

                        if(board[nx][ny]=='.')
                            continue;

                        if(board[nx][ny]=='o')
                        {
                            int n2x = nx + dx[k];
                            int n2y = ny + dy[k];

                            if(n2x>=1&&n2x<=5&&n2y>=1&&n2y<=9)
                            {
                                if(board[n2x][n2y]=='.')
                                {
                                    board[nx][ny]='.';
                                    board[n2x][n2y]='o';
                                    board[i][j] = '.';
                                    travel(cur + 1);
                                    board[nx][ny]='o';
                                    board[i][j] = 'o';
                                    board[n2x][n2y] = '.';
                                }
                            }
                        }
                    }
                }
            }
        }
        int tmp = sum();
        if(result > tmp)
        {
            result = tmp;
            count = cur;
        }
    }

    static int sum()
    {
        int count = 0;

        for(int i=1;i<=5;i++)
        {
            for(int j=1;j<=9;j++)
            {
                if(board[i][j]=='o')
                    count = count + 1;
            }
        }

        return count;
    }
}
