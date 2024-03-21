import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int l = 0;
        int r = B.length - 1;
        
        
        for(int i = A.length - 1; i >= 0; i--){
            
            if(A[i] >= B[r]) {
                l = l - 1;
            }
            
            else {
                r = r - 1;
                answer += 1;
            }
        }
        
        return answer;
    }
    
    
}