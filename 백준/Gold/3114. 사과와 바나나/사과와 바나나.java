import java.util.*;
import java.io.*;

public class Main {
    static int n,m;

    static int[][] banana;
    static int[][] apple;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        banana = new int[n+1][m+1];
        apple = new int[n+1][m+1];

        for(int i = 1 ; i <= n ; i++){

            st = new StringTokenizer(br.readLine()," ");

            for(int j = 1 ; j <= m ; j++){

                String curLine = st.nextToken();

                char w = curLine.charAt(0);
                int num = Integer.parseInt(curLine.substring(1));

                if(w == 'B'){
                    banana[i][j] = num;
                }else{
                    apple[i][j] = num;
                }
            }
        }

        int[][][] dp = new int[n+2][m+2][2];

        for(int j = 1 ; j <= m ; j++){
            for(int i = 1 ; i <= n ; i++){
                dp[i][j][0] = dp[i-1][j][0] + banana[i][j];
            }

            for(int i = n ; i >= 1 ; i--){
                dp[i][j][1] = dp[i+1][j][1] + apple[i][j];
            }
        }

        int[][] reArr = new int[n+1][m+1];

        reArr[1][1] = dp[2][1][1];

        for(int j = 2 ; j <= m ; j++){
            reArr[1][j] = reArr[1][j-1] + dp[2][j][1];
        }

        for(int i = 2 ; i <= n ; i++){
            reArr[i][1] = reArr[i-1][1] - dp[i][1][1] + dp[i+1][1][1];
        }

        for(int i = 2 ; i <= n ; i++){
            for(int j = 2 ; j <= m ; j++){

                // ->
                int val1 = reArr[i][j-1] + dp[i+1][j][1] + dp[i-1][j][0];

                // \
                int val2 = reArr[i-1][j-1] + dp[i+1][j][1] + dp[i-1][j][0];

                // |
                int val3 = reArr[i-1][j] - dp[i][j][1] + dp[i+1][j][1];

                reArr[i][j] = Math.max(Math.max(val1,val2),val3);
            }
        }

        System.out.println(reArr[n][m]);


        br.close();
    }
    static void print(int[][][] arr){
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                System.out.print(arr[i][j][0]+" ");
            }
            System.out.println();
        }
        System.out.println();

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                System.out.print(arr[i][j][1]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void print(int[][] arr){
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
