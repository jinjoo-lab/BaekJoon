import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int m = 0;

    static int[] choo;

    static int[] go;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        choo = new int[n];
        st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < n ; i++){
            choo[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine()," ");
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine()," ");
        go = new int[m];
        for(int i= 0 ; i< m ; i++){
            go[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] dp = new boolean[n+1][40001];

        dp[0][0] = true;
        dp[0][choo[0]] = true;

        for(int i = 1; i < n; i ++){

            for(int j=0; j <= 40000; j ++){

                dp[i][j] = dp[i-1][j] || dp[i][j];

                if (dp[i-1][j]){

                    if(j + choo[i] <= 40000){
                        dp[i][j + choo[i]] = true;
                    }

                    dp[i][Math.abs(j - choo[i])] = true;
                }
            }
        }

        for(int i= 0 ; i < m ; i++){
            if(dp[n-1][go[i]])
                sb.append("Y ");

            else
                sb.append("N ");
        }
        System.out.println(sb);

        br.close();
    }

}
