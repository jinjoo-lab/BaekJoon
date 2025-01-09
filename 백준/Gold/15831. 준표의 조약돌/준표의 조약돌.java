import java.util.*;
import java.io.*;

public class Main {

    static int n,b,w;
    static char[] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        board = br.readLine().toCharArray();

        int[][] sum = new int[n+1][2];

        for(int i = 1 ; i <= n ; i++) {
            char cur = board[i-1];

            if(cur == 'W') {
                sum[i][0] += sum[i-1][0];
                sum[i][1] += sum[i-1][1] + 1;
            }else {
                sum[i][0] += sum[i-1][0] + 1;
                sum[i][1] += sum[i-1][1];
            }

        }

        int l = 1;
        int r = 1;
        int result = 0;

        while(l <= r && r <= n) {

            int curB = sum[r][0] - sum[l][0] + (board[l - 1] == 'B' ? 1 : 0);
            int curW = sum[r][1] - sum[l][1] + (board[l - 1] == 'W' ? 1 : 0);

            if(curB <= b && curW >= w) {
                result = Math.max(result, r - l + 1);
            }

            while(curB > b && l <= r && l < n) {
               l++;
               curB = sum[r][0] - sum[l][0] + (board[l - 1] == 'B' ? 1 : 0);
               curW = sum[r][1] - sum[l][1] + (board[l - 1] == 'W' ? 1 : 0);

               if(curB <= b && curW >= w) {
                    result = Math.max(result, r - l + 1);
               }
            }

            r++;
        }

        System.out.println(result);
        br.close();
    }
}
