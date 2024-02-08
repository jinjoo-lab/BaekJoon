import java.util.*;
import java.io.*;

public class Main {

    static int[] dx = {0,1,-1,0,0};
    static int[] dy = {0,0,0,1,-1};
    static int n = 0;

    static final long MAX = Long.MAX_VALUE;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");

        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());

        board = new int[n+1][2];

        for(int i = 1 ; i <= n ; i++){
            st = new StringTokenizer(br.readLine()," ");
            int tmpX = Integer.parseInt(st.nextToken());
            int tmpY = Integer.parseInt(st.nextToken());

            board[i][0] = tmpX;
            board[i][1] = tmpY;
        }

        long[][] dp = new long[n+1][5];

        for(int i = 1 ;i <= n ; i++){
            for(int j= 0 ; j< 5 ;j ++){
                dp[i][j] = MAX;
            }
        }

        for(int i = 0 ; i < 5 ;i ++){

            int nx = board[1][0] + dx[i];
            int ny = board[1][1] + dy[i];

            if(outOfRange(nx,ny))
                continue;

            dp[1][i] = calDis(sx,sy,nx,ny);

        }


        for(int i=2;i<=n;i++){

            int curX = board[i][0];
            int curY = board[i][1];

            for(int j=0;j<5;j++){

                int nX = curX + dx[j];
                int nY = curY + dy[j];

                if(outOfRange(nX,nY))
                    continue;

                long min = MAX;

                for(int k = 0 ; k < 5 ; k++){
                    if(dp[i-1][k] == MAX)
                        continue;

                    int pastX = board[i-1][0] + dx[k];
                    int pastY = board[i-1][1] + dy[k];

                    if(outOfRange(pastX,pastY))
                        continue;

                    min = Math.min(min,dp[i-1][k] + calDis(pastX,pastY,nX,nY));
                }

                dp[i][j] = min;

            }
        }

        long result = dp[n][0];

        for(int i=1;i<5;i++){
            result = Math.min(result,dp[n][i]);
        }

        System.out.println(result);

        br.close();
    }

    static boolean outOfRange(int nx,int ny){
        if(nx < 0 || nx > 100_000 || ny < 0 || ny > 100_000)
            return true;

        return false;
    }

    static long calDis(int sx,int sy,int lx,int ly){
        return Math.abs(sx - lx) + Math.abs(sy - ly);
    }
}
