import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int[][] board;

    static boolean[] v;

    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());

        board = new int[n+1][2];
        v = new boolean[n+1];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(bf.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            board[i][0] = x;
            board[i][1] = y;
        }


        comb(1,0);
        System.out.println(result);
        bf.close();
    }

    static void comb(int depth,int cnt){
        if(depth > n){

            if(cnt == 0)
                return;

            int sum1 = 1;
            int sum2 = 0;
            for(int i=1;i<=n;i++){
                if(v[i]){
                    sum1 *= board[i][0];
                    sum2 += board[i][1];
                }

            }

            result = Math.min(result,Math.abs(sum1 - sum2));
            return;
        }


        v[depth] = true;
        comb(depth + 1,cnt + 1);
        v[depth] = false;
        comb(depth+1,cnt);

    }
}