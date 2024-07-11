import java.util.*;
import java.io.*;

public class Main {
    static int result = Integer.MAX_VALUE;
    static int n;
    static int[] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        board = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i = 0 ; i < n ; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(board);

        for(int i = 0 ; i < n - 1 ; i++) {
            for(int j = i + 1 ; j < n ; j++) {
                search(i,j);
            }
        }

        System.out.println(result);

        br.close();
    }

    static void search(int idx1,int idx2) {
        int l = 0;
        int r = n - 1;
        int target = board[idx1] + board[idx2];

        while(l < r) {
            while(l == idx1 || l == idx2)
                l+= 1;

            while(r == idx2 || r == idx1)
                r-= 1;

            if(l >= r)
                break;

            int tmpV = board[l] + board[r];

            result = Math.min(result, Math.abs(tmpV - target));

            if(tmpV == target) {
                result = 0;
                return;
            }

            if(tmpV > target) {
                r = r - 1;
            }else {
                l = l + 1;
            }
        }
    }
}
