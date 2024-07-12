import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static long[][] board;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        long d = 0;
        long v = 0;
        long total = 0;

        board = new long[n][2];

        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            d = Long.parseLong(st.nextToken());
            v = Long.parseLong(st.nextToken());

            board[i-1][0] = d;
            board[i-1][1] = v;

            total += (board[i-1][1]);
        }

        Arrays.sort(board,(x,y) -> Long.compare(x[0], y[0]));

        long result = 0;
        long tmpResult = 0;

        for(int i = 0 ; i < n ; i++) {
            tmpResult += (board[i][1]);

            if(tmpResult >= (total + 1 ) / 2) {
                result = board[i][0];
                break;
            }
        }

        System.out.println(result);

        br.close();
    }
}
