import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        board = new int[n][2];

        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine()," ");

            int d = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            board[i][0] = d;
            board[i][1] = e;
        }

        Arrays.sort(board,(x,y) -> {
            if(x[1] == y[1]){
                return y[0] - x[0];
            }
            return y[1] - x[1];
        });

        int day = 1000_000_001;

        for(int i = 0 ;  i  < n  ; i++){
            if(day > board[i][1]){
                day = board[i][1];
            }
            day = day - board[i][0];
        }

        System.out.println(day);

        br.close();
    }
}
