import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int k = 0;
    static int[][] board = new int[210][210];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i=1;i<=201;i++)
        {
            board[i][1] = 1;
            board[i][2] = i+1;
        }

        for(int i=1;i<=201;i++)
        {
            board[1][i] = i;
        }

        for(int i=2;i<=201;i++)
        {
            for(int j=3;j<=k;j++)
            {
                board[i][j] = (board[i][j-1] + board[i-1][j]) % 1000000000;
            }
        }

        System.out.println(board[n][k] % 1000000000);
        br.close();
    }
}