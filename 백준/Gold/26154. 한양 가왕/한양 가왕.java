import java.util.*;
import java.io.*;

public class Main {
    static int n,m;

    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());


        int max = 0;
        board = new int[n+1][2];
        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            board[i][0] = Math.max(x,y);
            board[i][1] = Math.min(x,y);

            max = Math.max(max,board[i][0]);
        }

        if(m == 0 || n == 1) {
            System.out.println(makeResult());
            return;
        }

        String result = "";

        if(m <= n) {
            for(int i = 1 ; i <= m ; i++) {
                go();
            }

            result = makeResult();
        }else{
            for(int i = 1 ; i <= m%n + n ; i++) {
                go();
            }

            result = makeResult();
        }

        System.out.println(result);
        br.close();
    }

    static String makeResult() {
        StringBuilder sb = new StringBuilder();

        for(int i = 1 ; i <= n ; i++) {
            sb.append(board[i][1]+" "+board[i][0]+"\n");
        }

        return sb.toString();
    }

    static void go() {
        int minF = board[1][1];

        for(int i = 2 ; i <= n ; i ++) {
            if(i == 2) {
                board[1][1] = board[i][0];
                continue;
            }

            board[i-1][0] = board[i][0];
        }

        board[n][0] = minF;

        for(int i = 1 ; i <= n ; i++) {

            int min = board[i][0];
            int max = board[i][1];

            board[i][0] = Math.max(min,max);
            board[i][1] = Math.min(min,max);
        }

    }

    static void print() {
        for(int i =1 ; i <= n ; i++) {
            System.out.println(board[i][0] +" "+ board[i][1]);
        }
        System.out.println();
    }
}
