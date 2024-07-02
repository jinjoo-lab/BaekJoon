import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int m;

    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        board = new int[n+1][2];
        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            board[i][0] = v1;
            board[i][1] = v2;
        }


        st = new StringTokenizer(br.readLine()," ");
        m = Integer.parseInt(st.nextToken());

        int result = 0;

        for(int i = 1 ; i <= m ; i++) {
            if(getResult(i) != 0) {
                result = getResult(i);
                break;
            }
        }

        System.out.println(result);
        br.close();
    }

    static int getResult(int num) {
        int result = 0;
        int tmpM = m;
        int tmpNum = (board[1][0] * num) + board[1][1];
        tmpM -= num;

        for(int i = 2 ; i <= n ; i++) {
            int tmpA = (tmpNum - board[i][1]);

            if(tmpA == 0 || tmpA % board[i][0] != 0)
                return 0;

            tmpM -= (tmpA / board[i][0]);
        }

        if(tmpM == 0) {
            return tmpNum;
        }

        return 0;
    }

}
