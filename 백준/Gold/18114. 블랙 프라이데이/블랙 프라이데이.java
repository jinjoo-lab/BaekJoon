import java.util.*;
import java.io.*;

public class Main {

    static int n,k;
    static int[] board;
    static boolean result = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n];
        st = new StringTokenizer(br.readLine()," ");
        for(int i = 0 ; i < n ; i++) {
            board[i] = Integer.parseInt(st.nextToken());

            if(board[i] == k) {
                result = true;
            }
        }

        Arrays.sort(board);

        for(int i = 1; i < n ; i++) {
            for(int j = i - 1 ; j >= 0 ; j--) {
                if(k == board[i] + board[j]) {
                    result = true;
                }
            }
        }

        for(int i = 0 ; i < n - 1 ; i++) {
            for(int j = i + 1 ; j < n ; j++) {
                go(i,j,k - (board[i] + board[j]));
            }
        }


        System.out.println(result ? 1 : 0);

        br.close();
    }

    static void go(int v1, int v2, int t) {
        int l = 0;
        int r = n - 1;
        int mid = 0;

        while(l == v1 || l == v2) {
            l++;
        }

        while (r == v1 || r == v2) {
            r--;
        }

        while(l <= r) {
            mid = (l + r) / 2;

            if(mid != v1 && mid != v2) {
                if(board[mid] == t) {
                    result = true;
                    return;
                }
            }

            if(board[mid] >= k) {
                r = mid - 1;

                while(r == v1 || r == v2) {
                    r--;
                }
            }else {
                l = mid + 1;

                while(l == v1 || l == v2) {
                    l++;
                }
            }
        }
    }
}
