import java.util.*;

class Solution {
    
    static ArrayList<Integer>[] graph;
    static boolean[] v;
    static Queue<Integer> q = new ArrayDeque<>();
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        graph = new ArrayList[n+1];
        v = new boolean[n+1];
        
        for(int i = 1 ; i <= n ; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0 , size = edge.length ;  i < size ; i++){
            int v1 = edge[i][0];
            int v2 = edge[i][1];
            
            graph[v1].add(v2);
            graph[v2].add(v1);
        }
        
        q.add(1);
        v[1] = true;
        
        while(true){
            answer = bfs();
            
            if(q.size() == 0)
                break;
        }
                
        return answer;
    }
    
    static int bfs(){
        
        int size = q.size();
        int result = size;
        
        while(size != 0){
            size--;
            int cur = q.poll();
            
            for(int next : graph[cur]){
                if(!v[next]){
                    v[next] = true;
                    q.add(next);
                }
            }
        }
        
        return result;
    }
}