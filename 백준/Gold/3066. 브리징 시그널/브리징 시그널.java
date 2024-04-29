import java.util.*;
import java.io.*;

public class Main {
    static int t,n;
    static int[] input,dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(st.nextToken());

        for(int tk = 1 ; tk <= t ; tk++){
            st = new StringTokenizer(br.readLine()," ");
            n = Integer.parseInt(st.nextToken());

            input = new int[n+1];
            dp = new int[n+1];

            for(int i = 1 ; i <= n ; i++){
                input[i] = Integer.parseInt(br.readLine());
            }

            dp[1] = input[1];
            int i = 2;
            int j = 1;

            while(i <= n){
                if(input[i] > dp[j]){
                    dp[j+1] = input[i];
                    j++;
                }else{
                    int idx = binarySearch(1,j,input[i]);
                    dp[idx] = input[i];
                }

                i++;
            }

            sb.append(j+"\n");
        }
        System.out.print(sb);
        br.close();
    }
    static int binarySearch(int left,int right,int target){
        int mid = 0;

        while(left <= right){
            mid = (left + right) / 2;

            if(dp[mid] > target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }
}
