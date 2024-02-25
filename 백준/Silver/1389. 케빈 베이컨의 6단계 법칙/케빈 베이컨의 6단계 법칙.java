
import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];

        for(int i = 1 ; i <= m ; i++){
            st = new StringTokenizer(br.readLine()," ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            board[x][y] = 1;
            board[y][x] = 1;
        }

        for(int k = 1 ; k <= n ; k++){
            for(int i = 1 ; i <= n ; i++){
                for(int j = 1 ; j <= n ; j++){

                    if(i == j)
                        continue;

                    if(board[i][k] >= 1 && board[k][j] >= 1){
                        if(board[i][j] == 0){
                            board[i][j] = board[i][k] + board[k][j];
                        }else{
                            board[i][j] = Math.min(board[i][j],board[i][k] + board[k][j]);
                        }
                        board[j][i] = board[i][j];
                    }
                }
            }
        }

        int reIdx = 1;
        int reSum = Integer.MAX_VALUE;

        for(int i = 1 ; i <= n ; i++){

            int tmp = 0;

            for(int j = 1 ; j <= n ;j++){
                tmp += board[i][j];
            }
            if(tmp < reSum){
                reSum = tmp;
                reIdx = i;
            }
        }

        System.out.println(reIdx);
        br.close();
    }
}
