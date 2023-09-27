import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static long[] board;

    static int rl = 0;
    static int rm = 0;
    static int rr = 0;

    static long result = 3000000001l;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        board = new long[n];
        for(int i=0;i<n;i++){
            board[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(board);

        for(int i=0;i<=n-3;i++){
            search(i);
        }
        System.out.println(board[rl]+" "+board[rm]+" "+board[rr]);
        br.close();
    }

    static void search(int left){
        int l = left + 1;
        int r = n - 1;

        while(l < r){
            long tmp = board[l] + board[r];

            if(Math.abs(board[left] + tmp) < Math.abs(result)){
                result = board[left] + tmp;
                rl = left;
                rm = l;
                rr = r;
            }

            if(board[left] + tmp >= 0){
                r = r - 1;
            }

            else{
                l = l + 1;
            }

        }
    }
}
