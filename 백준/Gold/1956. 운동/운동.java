import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int m = 0;

    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];

        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            board[x][y] = v;
        }

        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    if(board[i][k] > 0 && board[k][j] > 0){
                        if(board[i][j] == 0){
                            board[i][j] = board[i][k] + board[k][j];
                        }

                        else{
                            board[i][j] = Math.min(board[i][j] , board[i][k] + board[k][j]);
                        }
                    }

                }
            }
        }

        int result = -1;

        for(int i=1;i<=n;i++){
            if(board[i][i] == 0)
                continue;

            if(result == -1)
                result = board[i][i];

            else{
                result = Math.min(result , board[i][i]);
            }
        }

        System.out.println(result);
        br.close();
    }
}