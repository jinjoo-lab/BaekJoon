import java.util.*;
import java.io.*;

public class Main {

    static int n,m,k;

    static int[][] board;

    static char[] line;


    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        dp = new int[n+1][m+1];

        for(int i = 1 ; i <= n ; i++) {
            line = br.readLine().toCharArray();

            for(int j = 1 ; j <= m ; j++) {
                board[i][j] = line[j - 1] == '.' ? 0 : -1;
            }
        }

        for(int i = n ; i >= 1 ; i--) {
            for(int j = m ; j >= 1 ; j--) {

                if(board[i][j] == -1 || dp[i][j] == 1) {
                    continue;
                }

                if(i - 1 >= 1)
                    dp[i - 1][j] |= reverse(i,j);

                if(j - 1 >= 1)
                    dp[i][j - 1] |= reverse(i,j);


                for(int a = 1; a <=k ; a++) {
                    if(i - a >= 1 && j - a >= 1 && board[i - a][j - a] != -1 ){
                        dp[i - a][j - a] |= reverse(i,j);
                    }else
                        break;
                }

            }
        }




        StringBuilder sb = new StringBuilder();

        int count = Integer.parseInt(br.readLine());

        for(int i = 1 ; i <= count ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(dp[r][c] == 1)
                sb.append("First\n");
            else
                sb.append("Second\n");

        }


        System.out.print(sb);
        br.close();
    }


    static int reverse(int x,int y) {
        return dp[x][y] == 1 ? 0 : 1;
    }

    static void print(int[][] dp) {
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= m ; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
