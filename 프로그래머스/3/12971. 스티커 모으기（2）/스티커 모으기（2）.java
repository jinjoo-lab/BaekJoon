class Solution {
    static int result = 0;
    static int n;
    static int[][] dp;
    
    public int solution(int sticker[]) {
        int answer = sticker[0];
        n = sticker.length;
        
        dp = new int[n + 1][2];
        
        dp[0][0] = sticker[0];
        dp[0][1] = 0;
        
        for(int i = 1 ; i < n - 1 ; i++) {
            
            if(i > 1){
                dp[i][0] = Math.max(Math.max(dp[i-2][1],dp[i-2][0]),dp[i-1][1]) + sticker[i];
            }else{
                dp[i][0] = dp[i-1][1] + sticker[i];
            }
            dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1]);
            
            result = Math.max(result,Math.max(dp[i][0],dp[i][1]));
        }
        
        dp = new int[n+1][2];
        
        for(int i = 1 ; i < n ; i++) {
            if(i > 1){
                dp[i][0] = Math.max(Math.max(dp[i-2][1],dp[i-2][0]),dp[i-1][1]) + sticker[i];
            }else{
                dp[i][0] = dp[i-1][1] + sticker[i];
            }
            dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1]);
            
            result = Math.max(result,Math.max(dp[i][0],dp[i][1]));
        }

        answer = Math.max(answer,result);
        return answer;
    }
}