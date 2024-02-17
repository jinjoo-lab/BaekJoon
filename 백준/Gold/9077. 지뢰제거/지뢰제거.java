import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int t = 0;

    static int n = 0;

    static int result = 0;
    static boolean[][] board = new boolean[10002][10002];
    static int[][] dp = new int[10002][10002];
    static int maxX,maxY;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());


        for(int a = 1 ; a <= t ; a++){
            st = new StringTokenizer(br.readLine()," ");
            n = Integer.parseInt(st.nextToken());

            for(int i= 0 ; i<= 10001; i ++){
                Arrays.fill(board[i],false);
                Arrays.fill(dp[i],0);
            }

            result = 0;
            maxX = 0;
            maxY = 0;

            for(int i = 1 ; i <=n ; i++){
                st = new StringTokenizer(br.readLine()," ");

                int x = Integer.parseInt(st.nextToken()) + 1;
                int y = Integer.parseInt(st.nextToken()) + 1;

                maxX = Math.max(maxX,x);
                maxY = Math.max(maxY,y);

                board[x][y] = true;
            }

            for(int i = 1 ; i <= 10001; i ++){
                for(int j= 1 ; j<= 10001; j ++){
                    dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + (board[i][j] ? 1 : 0);

                    if(i >= 11 && j >= 11){
                        result = Math.max(result,dp[i][j] - dp[i-11][j] - dp[i][j-11] + dp[i-11][j-11]);
                    }
                }
            }

            sb.append(result+"\n");
        }
        System.out.println(sb);
        br.close();
    }
}
