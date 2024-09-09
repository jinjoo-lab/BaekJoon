import java.util.*;

class Solution {
    
    static int N;
    static int rl = -1;
    static int rr = -1;
    
    static HashMap<String,Integer> map = new HashMap<>();
    static HashMap<String,Integer> map2 = new HashMap<>();
    
    static int[] gemsInt;
    
    
    public int[] solution(String[] gems) {
        int[] answer = {};
        gemsInt = new int[gems.length + 1];
        int num = 1;
        
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
        
        go1(gems);
        print(rl,rr);
        
        go2(gems);
        
        go3(gems);
        
        answer = new int[2];
        answer[0] = rl;
        answer[1] = rr;
        
        return answer;
    }
    
    static void go2(String[] gems) {
        int l = 1;
        int r = gems.length;
        
        while(true) {
            if(map2.get(gems[l - 1]) > 1) {
                map2.put(gems[l - 1], map2.get(gems[l - 1]) - 1);
                l++;
            } else {
                break;
            }
        }
        
        while(true) {
            if(map2.get(gems[r - 1]) > 1) {
                map2.put(gems[r - 1], map2.get(gems[r - 1]) - 1);
                r--;
            }else {
                break;
            }
        }
        
        print(l,r);
        
        if(Math.abs(r - l) < Math.abs(rr - rl)) {
            rl = l;
            rr = r;
        }
    }
    
    static void go1(String[] gems) {
        int l = 1;
        int r = gems.length;
        
        while(true) {
            if(map.get(gems[r - 1]) > 1) {
                map.put(gems[r - 1], map.get(gems[r - 1]) - 1);
                r--;
            }else {
                break;
            }
        }
        
        
        while(true) {
            if(map.get(gems[l - 1]) > 1) {
                map.put(gems[l - 1], map.get(gems[l - 1]) - 1);
                l++;
            } else {
                break;
            }
        }
        
        rl = l;
        rr = r;
        
    }
    
    static void print(int l,int r){
        System.out.println(l+" "+r);        
    }
    
    static void go3(String[] gems) {
        HashMap<String,Integer> map = new HashMap<>();
        map.put(gems[0], 1);
        int l = 1;
        int r = 2;
        
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