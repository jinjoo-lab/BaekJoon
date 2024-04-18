
import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[][] board;

    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        board = new int[n][2];

        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine()," ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int max = Math.max(x,y);
            int min = Math.min(x,y);

            board[i][0] = min;
            board[i][1] = max;

        }

        Arrays.sort(board,(x,y) ->{
            if(x[1] == y[1]){
                return y[0] - x[0];
            }

            return y[1] - x[1];
        });


        if(n == 0){
            System.out.println(0);
            return;
        }

        dp = new int[n];

        dp[0] = 1;
        int result = 1;

        for(int i = 1 ; i < n ; i++){

            dp[i] = 1;

            for(int j = i - 1 ; j >= 0 ; j--){
                if(board[i][1] <= board[j][1] && board[i][0] <= board[j][0]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            result = Math.max(result,dp[i]);
        }

        System.out.println(result);

        br.close();
    }

    static void print(){
        for(int i = 0 ; i < n ; i++){
            System.out.println(board[i][0]+" "+board[i][1] +" : "+dp[i]);
        }
    }
}
