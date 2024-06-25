class Solution {
    
    static int n;
    static int[][] dp = new int[501][501];
    static int[] dx = {-1,0};
    public int solution(int[][] triangle) {
        int answer = 0;
        n = triangle.length;
        dp[0][0] = triangle[0][0];
        
        for(int i = 1 ; i < n ; i++) {
            for(int j = 0 ; j < triangle[i].length ; j++) {
                for(int k = -1 ; k <= 0 ; k++) {
                    if(j + k < 0)
                        continue;
                    
                    dp[i][j] = Math.max(dp[i][j],dp[i-1][j + k] + triangle[i][j]);
                }
            }
        }
        
        for(int j = 0 ; j < n ; j++) {
            answer = Math.max(answer,dp[n-1][j]);
        }
        
        return answer;
    }
}