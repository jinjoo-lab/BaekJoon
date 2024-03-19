import java.util.*;

class Solution {
    
    static PriorityQueue<Integer> pq = new PriorityQueue<>(
        (x,y) -> y - x 
    );
    
    public long solution(int n, int[] works) {
        long answer = 0;
        
        for(int i = 0 , size = works.length ; i < size ; i++){
            pq.add(works[i]);
        }
        
        for(int i = 0 ; i < n ; i++){
            if(!pq.isEmpty()){
                
                int v = pq.poll();
                
                if(v - 1 == 0)
                    continue;
                
                pq.add(v - 1);
            }
        }
        
        while(!pq.isEmpty()){
            answer += (pq.peek() * pq.poll());
        }
        
        return answer;
    }
    
    
}