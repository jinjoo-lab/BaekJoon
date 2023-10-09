import java.util.*;
class Solution {
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static char[][] board;
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for(int i=0;i<5;i++){
            int re = result(places[i]);
            answer[i] = re;
        }
        return answer;
    }
    
    public int result(String[] line){
        board = new char[5][5];
        ArrayList<Node> list = new ArrayList<>();
        
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                board[i][j] = line[i].charAt(j);
                
                if(board[i][j] == 'P'){
                    list.add(new Node(i,j));
                }
            }
        }
        
        for(int i=0;i<list.size();i++){
          
            boolean tmp = check(list.get(i).x,list.get(i).y);
            
            if(!tmp)
                return 0;
        }
        
        return 1;
    }
     
    public boolean check(int sx,int sy){
        Queue<Node> q= new LinkedList<>();
        boolean[][] v = new boolean[5][5];
        q.add(new Node(sx,sy));
        v[sx][sy] = true;
       
        
        while(!q.isEmpty()){
            Node cur = q.poll();
            
            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if(nx < 0 || nx > 4 || ny < 0 || ny > 4)
                    continue;
                
                if(v[nx][ny])
                    continue;
                
                if(board[nx][ny] !='X'){
                    
                    int dis = Math.abs(sx - nx) + Math.abs(sy - ny);
                    
                    if(dis <= 2){
                        if(board[nx][ny] =='P'){
                            return false;
                        }
                        
                        v[nx][ny] = true;
                        q.add(new Node(nx,ny));
                    }
                }
            }
        }
        
        return true;
    }
}
class Node{
    int x;
    int y;
    
    Node(int x,int y){
        this.x = x;
        this.y = y;
    }
}