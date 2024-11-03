class Solution {
    static int maxCount = 0;
    static int[][] e;
    static int[] w;
    
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        w = info;
        e = edges;
        
        boolean[] v = new boolean[info.length];
        
        go(0,v,0,0);
        
        answer = maxCount;
        
        return answer;
    }
    
    static void go(int cur, boolean[] v , int wc , int sc) {
        v[cur] = true;
        
        if(w[cur] == 0) {
            sc++;
            
            if(sc > maxCount) {
                maxCount = sc;
            }
        }else {
            wc++;
        }
        
        if(wc >= sc) {
            return;
        }
        
        for(int[] next : e) {
            if(v[next[0]] && !v[next[1]]) {
                boolean[] nextV = new boolean[v.length];
                
                for(int i = 0 , size = v.length ; i < size ; i++) {
                    nextV[i] = v[i];
                }
                
                go(next[1],nextV,wc,sc);
            }
        }
    }
}