class Solution
{
    public int solution(int [][]board)
    {
        int answer = 0;
        int[][] dp = new int[board.length][board[0].length];
        
        for(int i=0;i<board[0].length;i++)
        {
            if(board[0][i]==1)
                dp[0][i] = 1;
        }
        
        for(int i=0;i<board.length;i++)
        {
            if(board[i][0]==1)
                dp[i][0] = 1;
        }
        
        for(int i=1;i<board.length;i++)
        {
            for(int j=1;j<board[i].length;j++)
            {
                if(board[i][j]==1)
                {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1]) + 1;
                }
            }
        }
        
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[i].length;j++)
            {
                if(answer < dp[i][j])
                    answer = dp[i][j];
            }
        }
        return answer*answer;
    }
    
    public void print(int[][] dp)
    {
        for(int i=0;i<dp.length;i++)
        {
            for(int j=0;j<dp[i].length;j++)
            {
                System.out.print(dp[i][j]+" ");
            }System.out.println();
        }
    }
}