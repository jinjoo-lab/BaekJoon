import java.util.*;

class Solution {
    
    static int[] dx = {1,0,0,-1};
    static int[] dy = {0,-1,1,0};
    static String[] dd = {"d","l","r","u"};
    
    static int N,M,K;
    static boolean[][][] dp;
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        
        N = n;
        M = m;
        K = k;
        
        dp = new boolean[n+1][m+1][k+1];
        
        
        answer = go(x,y,r,c);
        
        return answer;
    }
    
    static String go(int x,int y,int r,int c) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(x,y,0,""));
        dp[x][y][0] = true;
        
        int nx = 0;
        int ny = 0;
        int nd = 0;
        String np = "";
        
        while(!q.isEmpty()) {
            Node cur = q.poll();
        
            if(cur.d == K) {
                if(cur.x == r && cur.y == c) {
                    return cur.p; 
                }
                continue;
            }
            
            for(int i = 0 ; i < 4 ; i++) {
                nx = cur.x + dx[i];
                ny = cur.y + dy[i];
                nd = cur.d + 1;
                np = cur.p+dd[i];
                
                if(isOut(nx,ny))
                    continue;
                        
                if(!dp[nx][ny][nd]) {
                    dp[nx][ny][nd] = true;
                    q.add(new Node(nx,ny,nd,np));
                }
            }
        }
        
        return "impossible";
    }
    
    static boolean isOut(int x,int y) {
        if(x < 1 || x > N || y < 1 || y > M)
            return true;
        
        return false;
    }
}
class Node {
    int x;
    int y;
    int d;
    String p;
    
    Node(int x,int y,int d,String p) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.p = p;
    }
}