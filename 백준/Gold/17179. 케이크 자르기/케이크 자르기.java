import java.util.*;
import java.io.*;

public class Main {

    static int n,m,k;
    static int[] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[m+2];

        for(int i = 1 ; i <= m ; i++) {
            board[i] = Integer.parseInt(br.readLine());
        }
        board[m + 1] = k;

        for(int i = 1 ; i <= n ; i++) {
            int tmpCount = Integer.parseInt(br.readLine());
            int l = 0;
            int r = k;
            int mid = 0;
            int answer = 0;

            while(l <= r) {
                mid = (l + r) / 2;

                if(go(mid,tmpCount)) {
                    l = mid + 1;
                    answer = Math.max(answer,mid);
                }else {
                    r = mid - 1;
                }
            }

            sb.append(answer+"\n");
        }

        System.out.print(sb);
        br.close();
    }

    static boolean go(int target, int count) {
        int start = 0;
        int reCount = 0;

        for(int i = 1 ; i <= m + 1; i++) {
            if(board[i] - start >= target) {
                reCount++;
                start = board[i];
            }
        }

        return reCount > count;
    }
}
