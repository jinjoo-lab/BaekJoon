import java.util.*;
import java.io.*;

public class Main {

    static int t,n;
    static int[][] board;

    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(st.nextToken());

        for(int a = 1 ; a <= t ; a++){
            st = new StringTokenizer(br.readLine()," ");
            n = Integer.parseInt(st.nextToken());

            board = new int[n+1][2];
            dp = new int[n+1];

            for(int i = 1 ; i <= n ; i++){
                int tmp = Integer.parseInt(br.readLine());

                board[i][0] = i;
                board[i][1] = tmp;
            }

            dp[1] = board[1][1];
            int i = 2;
            int j=  1;

            while(i <= n){
                if(board[i][1] > dp[j]){
                    dp[j+1] = board[i][1];
                    j++;
                }else{
                    int idx = bs(1,j,board[i][1]);
                    dp[idx] = board[i][1];
                }

                i++;
            }

            sb.append(j+"\n");
        }
        System.out.print(sb);

        br.close();
    }

    static void print(){
        for(int i = 1 ; i <= n ; i++){
            System.out.print(dp[i]+" ");
        }
        System.out.println();
    }

    static int bs(int l,int r,int target){
        int mid = 0;

        while(l < r){
            mid = (l + r) / 2;

            if(dp[mid] > target){
                r = mid;
            }else{
                l = mid + 1;
            }
        }

        return r;
    }
}
