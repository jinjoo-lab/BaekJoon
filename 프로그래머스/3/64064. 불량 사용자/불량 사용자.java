class Solution {
    static int result = 0;
    static boolean[] v;
    static boolean[] tmpV;
    static int n;
    static int r;
    static boolean isR;
    static String[] u;
    static String[] b;
    
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        
        u = user_id;
        b = banned_id;
        
        n = user_id.length;
        r = banned_id.length;
        
        v = new boolean[n];
        tmpV = new boolean[r];
        
        comb(0,0);
        
        answer = result;
 
        return answer;
    }
    
    static void print() {
        for(int i = 0 ; i < n ; i++) {
            if(v[i])
                System.out.print(i+" ");
        }System.out.println();
    }
    
    
    static void go(int idx) {
        
        if(isR)
            return;
        
        if(idx == n) {
            isR = true;
            return;
        }
        
        if(!v[idx])
            go(idx + 1);        
        
        else {
            for(int i = 0 ; i < r ; i++) {
                if(!tmpV[i] && can(u[idx],b[i])) {
                    tmpV[i] = true;
                    go(idx + 1);
                    tmpV[i] = false;
                }
            }
        }
    }
    
    
    static boolean can(String f, String s) {
        
        if(f.length() != s.length())
            return false;
        
        char[] farr = f.toCharArray();
        char[] sarr = s.toCharArray();
        
        for(int i = 0 , size = farr.length ; i < size ; i++) {
            if(sarr[i] == '*')
               continue;
            
            if(farr[i] != sarr[i])
                return false;
        }
        
        
        return true;
    }
    
    static void comb(int idx,int num) {
       if(num == r) {
           isR = false;
           go(0);
           
           if(isR)
               result+= 1;
           
           return;
       }
       
        for(int i = idx ; i < n ; i++) {
            if(!v[i]) {
                v[i] = true;
                comb(i + 1,num + 1);
                v[i] = false;
            }
        }
      
    }
    

}