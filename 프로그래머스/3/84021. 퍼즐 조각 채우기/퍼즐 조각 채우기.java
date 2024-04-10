import java.util.*;

class Solution {
    static boolean[] isUsed;
    static boolean[] game;
    static int[][] board;
    static int[][] board2;
    static boolean[][] v;
    static int n;
    static int[][] Table;
    static int[][] gameBoard;
    static ArrayList<int[]>[][] pos;
    
    static ArrayList<int[]> startIdx = new ArrayList<>();
    
    static void print(int[][] board){
        for(int i = 0 ; i < n ;i++){
            for(int j = 0 ; j < n ; j++){
                System.out.print(board[i][j]+" ");
            }System.out.println();
        }System.out.println();
    }
    
    static boolean isOut(int x,int y){
        if(x < 0 || x >= n || y < 0 || y >= n)
            return true;
        
        return false;
    }
    
    static void turn(){
        int[][] tmpBoard = new int[n][n];
        
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                tmpBoard[i][j] = board[n - j - 1][i];
            }
        }
        
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                board[i][j] = tmpBoard[i][j];
            }
        }
    }
    
    static void bfs(int x,int y,int num ,int what,int[][] Table,boolean[][] v,int[][] board){
        
        int count = 1;
    
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x,y});
        v[x][y] = true;
        board[x][y] = num;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            for(int i = 0 ; i < 4 ; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                
                if(isOut(nx,ny) || v[nx][ny])
                    continue;
                    
                if(Table[nx][ny] == what){
                    v[nx][ny] = true;
                    count++;
                    board[nx][ny] = num;
                    q.add(new int[]{nx,ny});
                }
            }
        }
        
        if(what == 0)
            startIdx.add(new int[]{x,y,count});
    }
    
        static boolean[][] tmpV;
    
    static void find(int x,int y,int num,int turn,int[][] board){
        tmpV[x][y] = true;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x,y});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            for(int i = 0 ; i< 4 ; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                
                if(isOut(nx,ny))
                    continue;
                
                if(tmpV[nx][ny] || board[nx][ny] != num)
                    continue;
                
                tmpV[nx][ny] = true;
                q.add(new int[]{nx,ny});
                pos[num][turn].add(new int[]{nx - x,ny - y});
            }
        }
      
    }
    
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    
    static int idx;
    static int idx2;
    static int gameIdx;
    
    static boolean[][] v2;
    
    public int solution(int[][] game_board, int[][] table) {
        int answer = -1;
        
        Table = table;
        gameBoard = game_board;
        
        n = table.length;
        
        board = new int[n][n];
        board2 = new int[n][n];
        v = new boolean[n][n];
        v2 = new boolean[n][n];
        
        idx = 1;
        idx2 = 1;
        
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                if(!v[i][j] && Table[i][j] == 1){
                    bfs(i,j,idx,1,Table,v,board);
                    idx++;
                }
                
                if(!v2[i][j] && gameBoard[i][j] == 0){
                    bfs(i,j,idx2,0,gameBoard,v2,board2);
                    idx2++;
                }
            }
        }
        
        gameIdx = 1;
        
        isUsed = new boolean[idx+1];
        game = new boolean[idx2+1];
        pos = new ArrayList[idx+1][4];
        
        for(int i = 0 ; i <= idx ; i++){
            for(int j = 0 ; j < 4 ; j++){
                pos[i][j] = new ArrayList<>();
            }
        }
        
        for(int k = 0 ; k < 4 ; k++){
            
            tmpV = new boolean[n][n];
        
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < n ; j++){
                    if(!tmpV[i][j] && board[i][j] >= 1){
                       
                        
                        find(i,j,board[i][j],k,board);
                    }
                }
            }
            
     
            
            turn();
        }
        
        
    
        go();
    
    
        answer = result;
        return answer;
    }
    
    static int result = 0;
    
    static void go(){
        
        
        for(int i = 0 ; i < startIdx.size() ; i++){
            
            int[] startGameIdx = startIdx.get(i);
     
            loop : for(int j = 1 ; j < idx ; j++){
                
                if(isUsed[j])
                    continue;
                
                for(int k = 0; k < 4 ; k++){
                    ArrayList<int[]> curList = pos[j][k];
                    
                    
                    if(curList.size() + 1 != startGameIdx[2]){
                        break;
                    }
                    
                    if(canPost(startGameIdx[0],startGameIdx[1],j,k)){
                                                    
                        result += startGameIdx[2];
                        isUsed[j] = true;
                        break loop;
                    }
                }
            }
        }
    }
        
    
    static boolean canPost(int x,int y,int i,int j){
        for(int[] point : pos[i][j]){
             int nx = x + point[0];
             int ny = y + point[1];
                        
             if(isOut(nx,ny)){
                return false;
             }
            
            if(gameBoard[nx][ny] == 1)
                return false;
                         
        }
        return true;
    }
    
}