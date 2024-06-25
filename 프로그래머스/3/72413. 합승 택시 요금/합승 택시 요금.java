import java.util.*;

class Solution {
    
    static int[][] dis = new int[301][301];
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                dis[i][j] = Integer.MAX_VALUE;
                
                if(i == j)
                    dis[i][j] = 0;
            }
        }
        
        for(int i = 0 ; i < fares.length ; i++) {
            int v1 = fares[i][0];
            int v2 = fares[i][1];
            
            dis[v1][v2] = fares[i][2];
            dis[v2][v1] = dis[v1][v2];
        }
        
        
        for(int k = 1 ; k <= n ; k++) {
            for(int i = 1 ; i <= n ; i++) {
                for(int j = 1 ; j <= n ; j++) {
                
                    
                    if(dis[i][k] != Integer.MAX_VALUE && dis[k][j] != Integer.MAX_VALUE){ 
                        if(dis[i][j] > dis[i][k] + dis[k][j]) {
                            dis[i][j] = dis[i][k] + dis[k][j];
                            dis[j][i] = dis[i][j];
                        }
                    }
                }
            }
        }
        
        answer = Integer.MAX_VALUE;
        
        for(int tmp = 1 ; tmp <= n ; tmp++) {
            
            if(dis[s][tmp] == Integer.MAX_VALUE || dis[tmp][a] == Integer.MAX_VALUE || dis[tmp][b] == Integer.MAX_VALUE)
                continue;
            
            int tmpV = dis[s][tmp];
            
            tmpV += dis[tmp][a];
            tmpV += dis[tmp][b];
            
            
            answer = Math.min(answer,tmpV);
        }
        
        
        return answer;
    }
    
    static void print(int n) {
        for(int i = 1 ; i <= n ; i++) {
            for(int j =1 ; j <= n ; j++) {
                System.out.print(dis[i][j]+" ");
            }System.out.println();
        } System.out.println();
    }
}