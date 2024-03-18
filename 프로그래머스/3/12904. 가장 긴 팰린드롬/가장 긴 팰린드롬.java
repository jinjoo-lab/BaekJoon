import java.util.*;

class Solution
{
    static char[] arr;
    static int[][] dp;
    
    public int solution(String s)
    {
        int answer = 1;
        arr = s.toCharArray();
        int n = s.length();
        
        dp = new int[n][n];
        
        for(int i = 0 ; i < n ; i++){
            dp[i][i] = 1;
        }
        
        for(int i = 0 ; i < n -1 ; i++){
            if(arr[i] == arr[i+1]){
                dp[i][i+1] = 2;
                answer = 2;
            }
        }
        
        for(int k = 2 ; k < n ; k++){
            for(int i = 0 ; i < n ; i++){
                int j = i + k;
                
                if(j >= n)
                    continue;
                
                if(arr[i] == arr[j]){
                    if(dp[i+1][j-1] >= 1){
                        dp[i][j] = dp[i+1][j-1] + 2;
                        answer = Math.max(answer,dp[i][j]);
                    }
                }
            }
        }
        
        return answer;
    }
}