import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new int[n];

        for(int i = 0 ; i < n ; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = arr[0];
        int i = 1;
        int j = 0;

        while(i < n) {
            if(arr[i] > dp[j]) {
                dp[++j] = arr[i];
            }else {
                int idx = bs(0,j,arr[i]);
                dp[idx] = arr[i];
            }

            i++;
        }

        System.out.println(n - (j + 1));

        br.close();
    }

    static void print() {
        for(int i = 0 ; i < n ; i++) {
            System.out.print(dp[i]+" ");
        }
        System.out.println();
    }

    static int bs(int l,int r,int target) {
        int mid = 0;

        while(l <= r) {
            mid = (l+r)/2;

            if(dp[mid] > target) {
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }

        return l;
    }
}
