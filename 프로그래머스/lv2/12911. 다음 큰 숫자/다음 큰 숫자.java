import java.util.*;
class Solution {
    public int solution(int n) {
        int answer = 0;
        int n_cnt = Integer.bitCount(n);
        int b_cnt = 0;
        while(true){
            n++;
            b_cnt = Integer.bitCount(n);
            if(n_cnt == b_cnt){
                answer = n;
                break;
            }            
        }
                
        return answer;
    }
}