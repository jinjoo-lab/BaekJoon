import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static long k;

    static long[] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        k = Long.parseLong(st.nextToken());

        board = new long[n];

        st = new StringTokenizer(br.readLine()," ");
        for(int i = 0 ; i < n ; i++) {
            board[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(board);

        int l = 0;
        int r = n - 1;
        int count = 0;

        while(r >= 0) {
            if(board[r] >= k) {
                count++;
                r--;
            }else {
                break;
            }
        }

        while(l < r) {
            long curV = board[l] + board[r];
            if(curV >= k) {
                count++;
                l++;
                r--;
            }else {
                l++;
            }
        }

        System.out.println(count == 0 ? -1 : count);
        br.close();
    }
}
