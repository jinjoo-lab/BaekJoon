import java.util.*;

class Solution {
    
    static int[] root;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(
        (x,y) -> x[2] - y[2]
    );
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        root = new int[n];
        
        for(int i = 0 ; i < n ; i++){
            root[i] = i;
        }
        
        for(int i = 0 , size = costs.length ; i < size ; i++){
            pq.add(costs[i]);
        }
        
        int count = 0;
        
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            
            if(find(cur[0]) != find(cur[1])){
                union(cur[0],cur[1]);
                count++;
                answer += cur[2];
            }
            
            if(count == n - 1)
                break;
        }
        
        return answer;
    }
    
    static int find(int x){
        if(x == root[x])
            return root[x];
        
        return root[x] = find(root[x]);
    }
    
    static void union(int x,int y){
        x = find(x);
        y = find(y);
        
        if(x < y)
            root[y] = x;
        else
            root[x] = y;
    }
}