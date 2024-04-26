import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static char[] line;

    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        line = new char[n+1];
        dp = new int[n+1][n+1][2];

        char[] arr = br.readLine().toCharArray();
        for(int i = 1 ; i <= n ; i++){
            line[i] = arr[i-1];
        }


        for(int i = 1 ; i <= n ; i+=2){
            dp[i][i][0] = line[i] - '0';
            dp[i][i][1] = dp[i][i][0];
        }

        for(int k = 2; k <= n ; k+= 2){
            for(int i = 1; i <= n; i+=2){
                if(i + k > n)
                    break;

                dp[i][i+k][0] = Integer.MIN_VALUE;
                dp[i][i+k][1] = Integer.MAX_VALUE;

                for(int j = i ; j < i + k ; j+=2){
                    char op = line[j + 1];

                    int calF = cal(dp[i][j][0],dp[j+2][i+k][0],op);
                    int calS = cal(dp[i][j][1],dp[j+2][i+k][0],op);
                    int calT = cal(dp[i][j][1],dp[j+2][i+k][1],op);
                    int calFo = cal(dp[i][j][0],dp[j+2][i+k][1],op);

                    int num1 = dp[i][i+k][0] = Math.max(dp[i][i+k][0],Math.max(Math.max(calF,calS),Math.max(calT,calFo)));
                    int num2 = dp[i][i+k][1] = Math.min(dp[i][i+k][1],Math.min(Math.min(calF,calS),Math.min(calT,calFo)));
                    //System.out.println(i+" "+j+" , "+(j+2)+" "+(i+k)+": "+num1+" , "+num2);
                }
            }
        }

        // System.out.println(dp[3][n][0]+" "+dp[3][n][1]);

        System.out.println(Math.max(dp[1][n][0],dp[1][n][1]));

        br.close();
    }

    static int cal(int v1,int v2,char op){
        if(op == '+')
            return v1 + v2;
        if(op == '-')
            return v1 - v2;
        return v1 * v2;
    }
}
