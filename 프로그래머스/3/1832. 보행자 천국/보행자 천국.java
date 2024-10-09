class Solution {
    static int MOD = 20170805;
    
    static int[][][] dp;
    static int[][] map;
    
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        
        map = cityMap;
        dp = new int[m][n][2];
        
        dp[0][0][0] = 1; // -- 
        dp[0][0][1] = 1; // |
        
        for(int i = 1; i < n ; i++) {
            
            if(map[0][i] == 1)
                break;
            
            dp[0][i][0] = 1; 
        }
        
        for(int i = 1 ; i < m ; i++) {
            
            if(map[i][0] == 1)
                break;
            
            dp[i][0][1] = 1;
        }
        
        for(int i = 1 ; i < m ; i++) {
            for(int j = 1 ; j < n ; j++) {
                if(map[i][j] == 1) 
                    continue;
                
                dp[i][j][0] += dp[i][j-1][0];
                
                dp[i][j][0] %= MOD;
                
                dp[i][j][1] += dp[i -1][j][1];
            
                dp[i][j][1] %= MOD;
                
                if(map[i-1][j] != 2) {
                    dp[i][j][1] += dp[i-1][j][0];
                    dp[i][j][1] %= MOD;
                }
                
                if(map[i][j-1] != 2) {
                    dp[i][j][0] += dp[i][j-1][1];
                    dp[i][j][0] %= MOD;
                }
            }
        }
        
        answer = ((dp[m-1][n-1][0] + dp[m-1][n-1][1]) % MOD);
        
        return answer;
    }
    
    static void print(int m,int n) {
        for(int i = 0 ; i < m ; i++) {
            for(int j = 0 ; j < n ; j++) {
                System.out.print(dp[i][j][0]+" "+dp[i][j][1]+", ");
            }
            System.out.println();
        }
    }
}