import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int m = 0;

    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][2];

        st = new StringTokenizer(bf.readLine(), " ");

        int total = 0;

        for(int i=1;i<=n;i++){
            board[i][0] = Integer.parseInt(st.nextToken());

            total += board[i][0];
        }

        st = new StringTokenizer(bf.readLine(), " ");

        for(int i=1;i<=n;i++){
            board[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[total + 1];

        for(int i=1;i<=total;i++){
            dp[i] = Integer.MAX_VALUE;
        }

        int result = Integer.MAX_VALUE;

        for(int i=1;i<=n;i++){

            int curV = board[i][0];
            int curC = board[i][1];

            for(int j=total;j>=curV;j--){

                if(dp[j-curV] == Integer.MAX_VALUE){
                    continue;
                }

                if(dp[j] > dp[j - curV] + curC){
                    dp[j] = dp[j - curV] + curC;

                    if(j >= m){
                        result = Math.min(result,dp[j]);
                    }
                }

            }

        }


        System.out.println(result);
        bf.close();
    }
}
class Node{
    int v;
    int c;

    Node(int v,int c){
        this.v = v;
        this.c = c;
    }
}
