import java.util.*;

class Solution {
    
    static int N;
    static int rl = 1;
    static int rr = 0;
    
    static HashMap<String,Integer> map = new HashMap<>();
    static HashMap<String,Integer> map2 = new HashMap<>();
    
    static int[] gemsInt;
    
    
    public int[] solution(String[] gems) {
        int[] answer = {};
        gemsInt = new int[gems.length + 1];
        int num = 1;
        rr = gems.length;
        
        for(int i = 1 , size = gems.length ; i <= size ; i++) {
            if(map.containsKey(gems[i - 1])) {
                map.put(gems[i-1],map.get(gems[i-1]) + 1);
                map2.put(gems[i-1],map2.get(gems[i-1]) + 1);
            }else {
                map.put(gems[i-1], 1);
                map2.put(gems[i-1], 1);
            }
        }
    
        N = map.size();
        

        
        go3(gems);
        
        answer = new int[2];
        answer[0] = rl;
        answer[1] = rr;
        
        return answer;
    }
    

    
    static void go3(String[] gems) {
        HashMap<String,Integer> map = new HashMap<>();
        int l = 1;
        int r = 1;
        
        while(l <= r && r <= gems.length) {
            if(map.containsKey(gems[r - 1])) {
                map.put(gems[r - 1], map.get(gems[r - 1]) + 1);
            }else {
                map.put(gems[r - 1], 1);
            }
            
            if(map.size() == N) {
                
                while(map.get(gems[l - 1]) > 1) {
                    map.put(gems[l - 1], map.get(gems[l - 1]) - 1);
                    l++;
                }
                
                if(Math.abs(r - l) < Math.abs(rr - rl)) {
                    rl = l;
                    rr = r;
                }else if(Math.abs(r - l) == Math.abs(rr - rl)) {
                    if(rl > l) {
                        rl = l;
                        rr = r;
                    }
                }
            }
            
            r++;
        }
    }
}