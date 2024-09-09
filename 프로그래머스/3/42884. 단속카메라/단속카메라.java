import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 1;
        
        Arrays.sort(routes, (x,y) -> {
            if(x[0] == y[0])
                return x[1] - y[1];
            
            return x[0] - y[0];
        });
        
        
        int rE = routes[0][1];
        
        for(int i = 1 , size = routes.length ; i < size ; i++) {
            int curS = routes[i][0];
            int curE = routes[i][1];
            
            if(rE >= curS) {
                rE = Math.min(rE,curE);
            }else {
                answer++;
                rE = curE;
            }
        }
        
        
        return answer;
    }
}