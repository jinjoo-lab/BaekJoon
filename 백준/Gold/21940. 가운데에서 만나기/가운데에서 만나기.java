import java.util.*;
import java.io.*;

public class Main {
    static int n,m,k;
    static int[][] board;

    static int[] input;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];

        for(int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int num = board[v1][v2] == 0 ? c : Math.min(board[v1][v2],c);

            board[v1][v2] = num;
        }

        st = new StringTokenizer(br.readLine()," ");
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");
        input = new int[k+1];

        for(int i = 1 ; i <= k ; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        for(int k = 1 ; k <= n ; k++) {
            for(int i = 1 ; i <= n ; i++) {
                for(int j = 1 ; j <= n ; j++) {
                    if(i == j)
                        continue;

                    if(board[i][k] == 0 || board[k][j] == 0)
                        continue;

                    int num = board[i][j] == 0 ? board[i][k] + board[k][j] : Math.min(board[i][j], board[i][k] + board[k][j]);

                    board[i][j] = num;
                }
            }
        }

        int[] result = new int[n+1];

        int min = Integer.MAX_VALUE;

        for(int i = 1 ; i <= n ; i++) {
            int nextNum = cal(i);

            result[i] = nextNum;

            if(result[i] == -1)
                continue;

            min = Math.min(min,result[i]);
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1 ; i <= n ; i++) {
            if(min == result[i])
                sb.append(i+" ");
        }sb.append("\n");

        System.out.print(sb.toString());

        br.close();
    }

    static int cal(int start) {

        int result = 0;

        for(int i = 1 ; i <= k ; i++) {

            int nextPoint = input[i];

            if(start == nextPoint)
                continue;

            if(board[start][nextPoint] == 0 || board[nextPoint][start] == 0)
                return -1;

            result = Math.max(result,board[start][nextPoint] + board[nextPoint][start]);
        }

        return result;
    }
}
