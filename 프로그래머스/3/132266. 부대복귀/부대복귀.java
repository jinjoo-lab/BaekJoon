import java.util.*;

class Solution {
    
    static ArrayList<Integer>[] graph;
    
    static int[] dis;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        
        graph = new ArrayList[n+1];
        
        for(int i = 1 ; i <= n ; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0 , size = roads.length; i < size ; i++){
            int v1 = roads[i][0];
            int v2 = roads[i][1];
            
            graph[v1].add(v2);
            graph[v2].add(v1);
        }
        
        search(destination,n);
        
        for(int i = 0 ; i < sources.length ; i++){
            answer[i] = dis[sources[i]] == Integer.MAX_VALUE ? -1 : dis[sources[i]];
        }
        
        return answer;
    }
    
    static void search(int start,int n){
        
        PriorityQueue<int[]> q = new PriorityQueue<>(
            (x,y) -> x[1] - y[1]
        );
        
        q.add(new int[]{start,0});
        
        dis = new int[n+1];
        for(int i = 1 ; i<= n ; i++){
            dis[i] = Integer.MAX_VALUE;
        }
        
        dis[start] = 0;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            for(int next : graph[cur[0]]){
                
                if(dis[next] > cur[1] + 1){
                    dis[next] = cur[1] + 1;
                    q.add(new int[]{next,dis[next]});
                }
                
            }
        }
    }
}