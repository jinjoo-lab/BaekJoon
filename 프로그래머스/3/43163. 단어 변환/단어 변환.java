import java.util.*;

class Solution {
    
    static HashSet<String> contain = new HashSet<>();
    static HashMap<String,Integer> v = new HashMap<>();
    static HashSet<Character>[] graph = new HashSet[11];
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        for(int i = 0 ; i <= 10 ; i++) {
            graph[i] = new HashSet<>();
        }
        
        for(int i = 0 , size = words.length ; i < size ; i++) {
            contain.add(words[i]);
            
            char[] arr = words[i].toCharArray();
            
            for(int j = 0 , len = arr.length ; j < len ; j++) {
                graph[j].add(arr[j]);
            }
        }
        
        v.put(begin,0);
        Queue<String> q = new ArrayDeque<>();
        q.add(begin);
        
        String tmpS;
        
        while(!q.isEmpty()) {
            String curS = q.poll();
            
            if(curS.equals(target)) {
                answer = v.get(target);
                break;
            }
            
            char[] cur = curS.toCharArray();
            
            for(int i = 0 , size = cur.length ; i < size ; i++) {
                
                char curP = cur[i];
                
                for(Character next : graph[i]) {
                    cur[i] = next;
                    tmpS = new String(cur);
                    
                    if(contain.contains(tmpS)) {
                        if(!v.containsKey(tmpS) || v.get(tmpS) > v.get(curS) + 1) {
                            v.put(tmpS,v.get(curS) + 1);
                            q.add(tmpS);
                        }
                    }
                }
                
                cur[i] = curP;
            }
        }
        
        
        return answer;
    }
    
    
}