import java.util.*;
import java.io.*;

public class Main {

    static boolean[][][][][] dp;
    static char[] arr;
    static int n;
    static boolean find = false;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = br.readLine().toCharArray();
        n = arr.length;

        int aCount = 0;
        int bCount = 0;
        int cCount = 0;

        for(int i = 0 ; i < n ; i++) {
            if(arr[i] == 'C') {
                cCount++;
            }else if(arr[i] == 'B') {
                bCount++;
            }else if(arr[i] == 'A') {
                aCount++;
            }
        }

        dp = new boolean[aCount + 1][bCount + 1][cCount + 1][3][3];


        dfs(aCount, bCount, cCount, 0, 0, "");

        if(!find) {
            System.out.println(-1);
        }

        br.close();
    }

    static void dfs(int a, int b, int c, int pp, int p, String v) {

        if(find)
            return;

        if(a == 0 && b == 0 && c == 0) {
            find = true;
            System.out.println(v);
            return;
        }

        if(dp[a][b][c][pp][p]) {
            return;
        }

        dp[a][b][c][pp][p] = true;

        if (a > 0) {
            dfs(a - 1, b , c ,p, 0,v +"A");
        }

        if( b > 0 && p != 1) {
            dfs(a , b - 1, c, p, 1, v+"B");
        }

        if( c > 0 && pp != 2 && p != 2) {
            dfs(a, b, c - 1, p , 2 , v +"C");
        }
    }
}
