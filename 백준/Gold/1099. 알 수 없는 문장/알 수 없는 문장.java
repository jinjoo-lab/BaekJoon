import java.util.*;
import java.io.*;

public class Main {

    static String line;
    static int n;
    static int len;
    static String[] input;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        line = br.readLine();
        len = line.length();

        n = Integer.parseInt(br.readLine());
        input = new String[n+1];

        for(int i  = 1 ; i <= n ; i++) {
            input[i] = br.readLine();
        }

        dp = new int[len+1][len+1];

        for(int i = 0 ; i < len ; i++) {
            for(int j = 0 ; j < len ; j++) {
                dp[i][j] = 100;
            }
        }

        for(int i = 1 ; i <= n ; i++) {
            go(input[i]);
        }

        for(int k = 0 ; k < len - 1 ; k++) {
            for(int i = 0 ; i < len ; i++) {
                for(int j = i ; j < len ; j++) {
                    if(dp[i][k] != 100 && dp[k + 1][j] != 100) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                    }
                }
            }
        }

        System.out.println(dp[0][len - 1] == 100 ? -1 : dp[0][len - 1]);


        br.close();
    }

    static void print() {
        for(int i = 0 ; i < len ; i++) {
            for(int j = 0 ; j < len ; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void go(String cur) {
        char[] arr = cur.toCharArray();

        for(int i = 0 ; i <= len - arr.length ; i++) {
            char[] tmp = line.substring(i, i + arr.length).toCharArray();

            int tmpV = calValue(arr,tmp);

            dp[i][i + arr.length - 1] = Math.min(dp[i][i + arr.length - 1], tmpV);
        }
    }

    static int calValue(char[] arr1, char[] arr2) {

        int[] vv1 = new int[28];
        int[] vv2 = new int[28];

        int count = 0;

        for(int i = 0 , size = arr1.length ; i < size ; i++) {

            vv1[arr1[i] - 'a']++;
            vv2[arr2[i] - 'a']++;

            if(arr1[i] != arr2[i]) {
                count++;
            }
        }

        for(int i = 0 ; i <= 27 ; i++) {
            if(vv1[i] != vv2[i])
                return 100;
        }

        return count;
    }
}
