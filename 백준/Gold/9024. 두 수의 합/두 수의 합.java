import java.util.*;
import java.io.*;

public class Main {

    static int t;
    static int n,m;

    static int[] board = new int[1_000_001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(st.nextToken());

        for(int a = 1 ; a <= t ; a++){
            st = new StringTokenizer(br.readLine()," ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine()," ");

            board = new int[n];
            for(int i = 0 ; i < n ; i++){
                board[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(board,0,n);
            int tmpCount = find();
            sb.append(tmpCount+"\n");
        }

        System.out.print(sb);
        br.close();
    }

    static int find() {
        int count = 0;
        int result = board[0] + board[n-1];

        int l = 0;
        int r = n - 1;
        int sum;

        while(l < r) {
            sum = board[l] + board[r];

            int abs1 = Math.abs(result - m);
            int abs2 = Math.abs(sum - m);

            if(abs1 == abs2) {
                count++;
            }else if(abs1 > abs2) {
                count = 1;
                result = sum;
            }

            if(sum < m) {
                l = l + 1;
            }else if(sum >= m) {
                r = r - 1;
            }
        }


        return count;
    }
}
