import java.util.*;
class Solution {
    
    static boolean[] v;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        v = new boolean[n+1];
        
        for(int i = 0 ; i < n ; i++){
            
            if(!v[i]){
                bfs(i,n,computers);
                answer++;
            }
            
        }
        
        return answer;
    }
    
    static void bfs(int start,int n,int[][] computers){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        v[start] = true;
        
        while(!q.isEmpty()){
            int cur = q.poll();
            
            for(int i = 0 ; i < n ; i++){
                if(i == cur)
                    continue;
                
                if(computers[cur][i] == 0)
                    continue;
                
                if(v[i])
                    continue;
                
                v[i] = true;
                q.add(i);
            }
        }
    }
}