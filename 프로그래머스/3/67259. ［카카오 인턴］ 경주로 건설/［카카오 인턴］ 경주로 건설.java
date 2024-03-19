import java.util.*;
class Solution {
    
    static int n = 0;
    
    static int cost = 100;
    static int corner = 500;
    
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    
    public int solution(int[][] board) {
        int answer = 0;
        n = board.length;
        
        answer = bfs(board);
        
        
        return answer;
    }
    
    public int getCost(int dir,int i){
        
        if(dir == -1)
            return 100;
        
        if(dir == 0 || dir == 1){
            if(i == 0 || i == 1){
                return 100;
            }
            
            return 600;
        }
        
        if(dir == 2 || dir == 3){
            if(i == 2 || i == 3)
                return 100;
            
            return 600;
        }
        
        return 100;
    }
    
    public void print(int[][] dis){
        
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                System.out.print(dis[i][j]+" ");
            }System.out.println();
        }
        
    }
    
    public int bfs(int[][] board){
        int[][][] dis = new int[n][n][4];
        
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                for(int k = 0 ; k < 4 ; k++)
                    dis[i][j][k] = Integer.MAX_VALUE;
            }
        }
        
        dis[0][0][0] = 0;
        dis[0][0][1] = 0;
        dis[0][0][2] = 0;
        dis[0][0][3] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (x,y) -> x[2] - y[2]
        );
    
        pq.add(new int[]{0,0,0,-1});
        
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            
            if(cur[0] == n - 1 && cur[1] == n - 1)
                return cur[2];
            
            for(int i = 0 ; i < 4 ; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                
                if(nx < 0 || nx > n - 1 || ny < 0 || ny > n -1)
                    continue;
                
                if(board[nx][ny] == 1)
                    continue;
                
                int cost = getCost(cur[3],i);
                
                if(dis[nx][ny][i] > cur[2] + cost){
                    dis[nx][ny][i] = cur[2] + cost;
                    pq.add(new int[]{nx,ny,dis[nx][ny][i],i});
                }
            }
        }
        
        return -1;
    }
}