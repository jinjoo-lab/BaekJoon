import java.util.*;

class Solution {
    static ArrayDeque<Integer> q1 = new ArrayDeque<>();
    static ArrayDeque<Integer> q2 = new ArrayDeque<>();
    
    public int solution(int[] queue1, int[] queue2) {
        int answer = -1;
        
        long sum1 = 0;
        long sum2 = 0;
        
        for(int i = 0 ; i < queue1.length ; i ++){
            sum1 += queue1[i];
            q1.add(queue1[i]);
        }
        
        for(int i = 0 ; i < queue2.length ; i ++){
            sum2 += queue2[i];
            q2.add(queue2[i]);
        }
        
        int n = q1.size();
        
        
        for(int i = 0 ; i < 4*n ; i++) {
            if(sum1 == sum2) {
                answer = i;
                break;
            }else if(sum1 > sum2) {
                sum1 -= q1.getFirst();
                sum2 += q1.getFirst();
                q2.addLast(q1.removeFirst());
            }else{
                sum1 += q2.getFirst();
                sum2 -= q2.getFirst();
                q1.addLast(q2.removeFirst());
            }
        }
        
        return answer;
    }
}