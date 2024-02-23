import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int d = 0;
    static int k = 0;
    static int c = 0;

    static int[] board;

    static int[] count = new int[3001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new int[n+1];
        for(int i=1 ; i<=n ; i++){
            board[i] = Integer.parseInt(br.readLine());
        }


        int l = 1;
        int r = k;
        int max = 0;
        int num = 0;

        for(int i = 1 ; i <= k ;i++){
            if(count[board[i]] == 0){
                num++;
            }
            count[board[i]] += 1;
            max = Math.max(max , count[c] >= 1 ? num : num + 1);
        }

        while( l <= n){
            if(count[board[l]] == 1){
                num--;
            }
            count[board[l]] -= 1;

            int nextR = nextR(r);
            if(count[board[nextR]] == 0){
                num++;
            }
            count[board[nextR]] += 1;

            l += 1;
            r = nextR;

            max = Math.max(max , count[c] >= 1 ? num : num + 1);
        }

        System.out.println(max);
        br.close();
    }


    static int nextR(int r){
        if(r >= n)
            return r - n + 1;
        else
            return r + 1;
    }
}
