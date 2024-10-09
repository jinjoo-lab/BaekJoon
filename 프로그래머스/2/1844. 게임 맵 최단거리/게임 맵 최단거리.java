import java.util.*;

class Solution {
    
    static int n,m;
    
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int[][] v;
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        n = maps.length;
        m = maps[0].length;
        
        v = new int[n][m];
        
        Queue<int[]> q = new ArrayDeque<>();
        v[0][0] = 1;
        q.add(new int[]{0,0});
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            
            for(int i = 0 ; i < 4 ; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                
                if(isOut(nx,ny) || maps[nx][ny] == 0)
                    continue;
                
                if(v[nx][ny] == 0) {
                    v[nx][ny] = v[cur[0]][cur[1]] + 1;
                    q.add(new int[]{nx,ny});
                }
            }
        }
        
        answer = v[n-1][m-1] == 0 ? -1 : v[n-1][m-1];
        
        return answer;
    }
    
    static void print() {
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m ; j++) {
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    static boolean isOut(int x,int y) {
        if(x < 0 || x >= n || y < 0 || y >= m)
            return true;
        
        return false;
    }
}