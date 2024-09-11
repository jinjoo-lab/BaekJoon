import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[][] board;
    static int[] dp;
    static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());

        board = new int[n][3];

        for(int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            board[i][0] = Integer.parseInt(st.nextToken());
            board[i][1] = Integer.parseInt(st.nextToken());
            board[i][2] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(board, (x,y) -> x[1] - y[1]);

        dp = new int[n + 1];

        dp[0] = board[0][2];
        result = dp[0];

        for(int i = 1 ; i < n ; i++) {

            int tmpV = find(i, board[i][0]);

            dp[i] = Math.max(dp[i - 1], tmpV + board[i][2]);

            result = Math.max(result, dp[i]);
        }

        System.out.println(result);


        br.close();
    }

    static int find(int idx, int target) {
        int l = 0;
        int r = idx - 1;
        int result = 0;

        while(l <= r) {
            int mid = (l + r) / 2;

            if(board[mid][1] <= target) {
                result = Math.max(result, dp[mid]);
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }

        return result;
    }
}
