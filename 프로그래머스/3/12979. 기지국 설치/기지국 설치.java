import java.util.*;

class Solution {
    
    static int P;
    
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int start = 1;
        
        P = w * 2 + 1;
        
        for(int i = 0 , size = stations.length ; i < size ; i++) {
            int tmpP = stations[i];
            
            if(tmpP - w > start) {
                int rest =  (tmpP - w - start) % P > 0 ? 1 : 0;
                answer += (tmpP - w - start) / P;
                answer += rest;
            }
            start = tmpP + w + 1;
        }
        
        if(n >= start) {
            answer += (n - start + 1) / P;
            answer +=  (n - start + 1) % P > 0 ? 1 : 0;
        }

        return answer;
    }
}