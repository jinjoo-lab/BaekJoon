
import java.util.*;
import java.io.*;

public class Solution {

    static int t,n,m;
    static int[][] board;

    static int[][] board2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        t = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        for(int a = 1 ; a <= t ; a++){
            st = new StringTokenizer(br.readLine()," ");
            n = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine()," ");
            m = Integer.parseInt(st.nextToken());

            board = new int[n+1][n+1];
            board2 = new int[n+1][n+1];

            for(int i = 1 ; i <= m ; i++){
                st = new StringTokenizer(br.readLine()," ");

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                board[x][y] = 1;
                board2[y][x] = 1;
            }

            for(int k = 1 ; k <= n ; k++){
                for(int i = 1 ; i <= n ; i++){
                    for(int j = 1 ; j <= n ; j++){
                        if(board[i][k] == 1 && board[k][j] == 1){
                            board[i][j] = 1;
                        }

                        if(board2[i][k] == 1 && board2[k][j] == 1){
                            board2[i][j] = 1;
                        }
                    }
                }
            }


            int result = 0;
            for(int i = 1 ; i <= n ; i++) {

                int count = 0;

                for (int j = 1; j <= n; j++) {
                    if(board[i][j] == 1)
                        count++;

                    if(board2[i][j] == 1)
                        count++;
                }

                if(count == n - 1)
                    result++;
            }

            sb.append("#"+a+" "+result+"\n");
        }

        System.out.print(sb);
        br.close();
    }
}
