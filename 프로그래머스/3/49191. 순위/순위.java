import java.util.*;
class Solution {
    
    static boolean[][] board;
    static boolean[][] board2;
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        board = new boolean[n+1][n+1];
        board2 = new boolean[n+1][n+1];
        
        for(int i = 0, size = results.length ; i < size ; i++) {
            int w = results[i][0];
            int l = results[i][1];
            board[w][l] = true;
            board2[l][w] = true;
        }
        
        for(int k = 1 ; k <= n; k++) {
            for(int i = 1 ; i <= n ; i++) {
                for(int j = 1 ; j <= n ; j++) {
                    if(i == j)
                        continue;
                    
                    if(board[i][k] && board[k][j]) {
                        board[i][j] = true;
                    }
                    
                    if(board2[i][k] && board2[k][j]) {
                        board2[i][j] = true;
                    }
                }
            }
        }
        
        for(int i = 1 ; i <= n ; i++) {
            
            int count = 0;
            
            for(int j = 1 ; j <= n; j++) {
                if(board[i][j] || board2[i][j])
                    count++;
            }
            
            if(count == n - 1)
                answer++;
        }
        
        return answer;
    }
}