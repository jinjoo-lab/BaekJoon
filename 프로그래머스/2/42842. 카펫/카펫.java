import java.util.*;

class Solution {
    
    static int B,Y,T;
    static int[] aa = new int[2];
    
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        
        B = brown;
        Y = yellow;
        
        T = B + Y;
        
        go();
        
        answer = aa;
        
        return answer;
    }
    
    static void go() {
        
        int tmp = (int)Math.sqrt(T) + 1;
        
        for(int i = 1 ; i <= tmp ; i++) {
            if(T % i == 0) {
                int garo = Math.max(i,T/i);
                int sero = Math.min(i,T/i);
                
                int tB = (garo + garo + sero + sero) - 4;
                
                if(B == tB) {
                    aa[0] = garo;
                    aa[1] = sero;
                }
            }
        }
        
    }
}

