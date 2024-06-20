import java.util.*;
class Solution {
    static int n;    
    public int solution(int[] stones, int k) {
        
        PriorityQueue<Node> pq = new PriorityQueue<>(
        (x,y) -> {
            
            if(x.v == y.v) {
                return x.idx - y.idx;
            }
            
            return y.v - x.v;
        }
    );  
        
        int answer = 0;
        n = stones.length;
        
        for(int i = 0 ; i < k ; i++) {
            pq.add(new Node(i,stones[i]));
            
            answer = Math.max(answer,stones[i]);
        }
        
        int s = 0;
        int l = k;
        
        while(l < n) {
            int tmp = stones[l];
            
            while(!pq.isEmpty() && pq.peek().idx <= s) {
                pq.poll();
            }
                
            pq.add(new Node(l,tmp));
            answer = Math.min(answer,pq.peek().v);
            
            s += 1;
            l += 1;
        }
        
        return answer;
    }
}

class Node{
    int idx;
    int v;
    
    Node(int idx,int v) {
        this.idx = idx;
        this.v = v;
    }
}