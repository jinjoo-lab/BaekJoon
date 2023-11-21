import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n = 0;

    static int[] arr;

    static int[] dp;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n];
        dp[0] = arr[0];
        int i = 1;
        int j = 0;

        while(i < n){

            if(dp[j] < arr[i]){
                dp[j+1] = arr[i];
                j += 1;
            }else{
                int idx = bs(0,j,arr[i]);
                dp[idx] = arr[i];
            }


            i += 1;
        }

        System.out.println(n - (j+1));

        br.close();
    }

    static int bs(int l,int r,int t){
        int mid;

        while(l < r){
            mid = (l + r) / 2;

            if(dp[mid] < t){
                l = mid + 1;
            }else{
                r = mid;
            }
        }

        return r;
    }
}

