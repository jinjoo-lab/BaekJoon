import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];

        for(int i=1 ; i<=n ; i++){
            st = new StringTokenizer(br.readLine(), " ");

            for(int j=1 ; j<=n; j++){
                int tmp = Integer.parseInt(st.nextToken());

                board[i][j] = board[i-1][j] + board[i][j-1] - board[i-1][j-1] + tmp;
            }
        }

        int result = Integer.MIN_VALUE;

        for(int i=1 ;i<=n;i++){
            for(int j=1;j<=n;j++){
                int idx = 0;

                while(i+idx <=n && j+idx <= n){

                    result = Math.max(result,board[i+idx][j+idx] - board[i-1][j+idx] - board[i+idx][j-1] + board[i-1][j-1]);
                    idx++;
                }
            }
        }

        System.out.println(result);

        br.close();
    }
}
