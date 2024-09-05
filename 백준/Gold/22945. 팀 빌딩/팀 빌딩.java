import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        board = new int[n+1];

        st = new StringTokenizer(br.readLine()," ");
        for (int i = 1; i <= n; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }

        int l = 1;
        int r = n;
        int result = 0;

        while(l < r) {

            int v = Math.min(board[l], board[r]);
            int len = r - l - 1;

            int tmpV = v * len;
            result = Math.max(result, tmpV);

            if(board[r] >= board[l]) {
                l++;
            }else {
                r--;
            }
        }

        System.out.println(result);
        br.close();
    }
}
