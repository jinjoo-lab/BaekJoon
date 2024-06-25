import java.util.*;
class Solution {
    static long answerW = Long.MAX_VALUE;
    
    
    public long solution(int n, int[] times) {
        long answer = 0;
        
        Arrays.sort(times);
        
        bs(n,times);
        answer = answerW;
        
        return answer;
    }
    
    static void bs(int n,int[] times) {
        long l = 0;
        long r = (long)times[times.length - 1] * n -1;
        long mid;
        
        while(l <= r) {
            mid = (l + r) / 2;
            
            long tmpV = cal(mid,times);
            
            if(n <= tmpV) {
                answerW = Math.min(answerW,mid);
                r = mid - 1;    
            }else {
                l = mid + 1;
            }
        }
        
    }
    
    
    static long cal(long target,int[] times) {
        long result = 0;
        
        for(int i = 0, size = times.length ; i < size ; i++) {
            result += (target / times[i]);
        }
        
        return result;
    }
}