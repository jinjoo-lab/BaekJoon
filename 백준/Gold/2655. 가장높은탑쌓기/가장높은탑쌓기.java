import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        board = new int[n][4];

        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine());

            int l = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            board[i-1][0]  = i;
            board[i-1][1] = l;
            board[i-1][2] = h;
            board[i-1][3] = v;
        }

        Arrays.sort(board,(x,y) -> y[1] - x[1]);

        int[][] dp = new int[n+1][3];

        ArrayList<Integer>[] list = new ArrayList[n+1];
        for(int i =1 ; i <= n ; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 1 ; i <= n ; i++) {
            int curH = board[i-1][2];
            int curV = board[i-1][3];

            dp[i][0] = curH;
            dp[i][1] = 1;
            dp[i][2] = curV;
            list[i].add(board[i-1][0]);

            for(int j = i - 1 ; j > 0 ; j--) {
                if(curV < dp[j][2]) {
                    if(dp[i][0] < dp[j][0] + curH) {
                        dp[i][0] = dp[j][0] + curH;
                        dp[i][1] = dp[j][1] + 1;
                        dp[i][2] = curV;
                        list[i].clear();
                        list[i].addAll(list[j]);
                        list[i].add(board[i-1][0]);
                    }
                }
            }
        }

        get(dp,list);
        br.close();
    }

    static void print(int[][] dp) {
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 0 ; j < 3; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void get(int[][] dp,ArrayList<Integer>[] list) {
        int maxH = dp[1][0];
        int maxV = dp[1][1];
        int idx = 1;

        for(int i = 1 ; i <= n ; i++) {
            if(dp[i][0] > maxH) {
                maxH = dp[i][0];
                maxV = dp[i][1];
                idx = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(maxV+"\n");

        for(int i = list[idx].size() - 1 ; i >= 0 ; i--) {
            sb.append(list[idx].get(i)+"\n");
        }
        System.out.println(sb);
    }
}
