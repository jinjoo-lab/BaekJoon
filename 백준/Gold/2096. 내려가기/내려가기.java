import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int k = 0;
    static int[][] board = new int[100001][3];
    static int[][] max = new int[100001][3];
    static int[][] min = new int[100001][3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            board[i][0] = Integer.parseInt(st.nextToken());
            board[i][1] = Integer.parseInt(st.nextToken());
            board[i][2] = Integer.parseInt(st.nextToken());
        }

        max[1][0] = board[1][0];
        max[1][1] = board[1][1];
        max[1][2] = board[1][2];

        min[1][0] = board[1][0];
        min[1][1] = board[1][1];
        min[1][2] = board[1][2];

        for(int i=2;i<=n;i++)
        {
            max[i][0] = Math.max(max[i-1][0],max[i-1][1]) + board[i][0];
            max[i][1] = Math.max(Math.max(max[i-1][0],max[i-1][1]),max[i-1][2]) + board[i][1];
            max[i][2] = Math.max(max[i-1][1],max[i-1][2]) + board[i][2];

            min[i][0] = Math.min(min[i-1][0],min[i-1][1]) + board[i][0];
            min[i][1] = Math.min(Math.min(min[i-1][0],min[i-1][1]),min[i-1][2]) + board[i][1];
            min[i][2] = Math.min(min[i-1][1],min[i-1][2]) + board[i][2];
        }

        int big = Math.max(Math.max(max[n][0],max[n][1]),max[n][2]);
        int small = Math.min(Math.min(min[n][0],min[n][1]),min[n][2]);
        System.out.println(big+" "+small);
        br.close();
    }
}
