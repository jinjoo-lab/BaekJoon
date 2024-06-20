import java.util.*;

class Solution {
    
    static int[][] map;
    static int[][] dp ;
    static int DIV =  1_000_000_007;
    
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        map = new int[n+1][m+1];
        dp = new int[n+1][m+1];
        
        for(int i = 0, size = puddles.length ; i < size ; i++) {
            map[puddles[i][1]][puddles[i][0]] = -1;
        }
        
        dp[1][1] = 1;
        
        for(int i = 2 ; i <= m ; i++) {
            if(map[1][i] == -1)
                break;
            
            dp[1][i] = dp[1][i-1];
        }
        
        for(int i = 2 ; i<= n ;i++) {
            if(map[i][1] == -1)
                break;
            
            dp[i][1] = dp[i-1][1];
        }
        
        for(int i = 2 ; i <= n ; i++) {
            for(int j = 2; j <= m ; j++) {
                if(map[i][j] == -1)
                    continue;
                
                dp[i][j] = ((dp[i-1][j] % DIV) + (dp[i][j-1] % DIV)) % DIV;
            }
        }
        
        return dp[n][m];
    }
}