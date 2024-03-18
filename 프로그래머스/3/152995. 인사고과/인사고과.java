import java.util.*;

class Solution {
    
    static ArrayList<int[]> list = new ArrayList<>();
    
    public int solution(int[][] scores) {
        int answer = 0;
        
        int num = scores.length;
        
        int[][] board = new int[num][4];
        
        for(int i = 0 ; i < num ; i++){
            board[i][0] = i;
            board[i][1] = scores[i][0];
            board[i][2] = scores[i][1];
            board[i][3] = board[i][1] + board[i][2];
        }
        
        Arrays.sort(board,(x,y) -> {
            
            if(x[1] == y[1])
                return y[2] - x[2];
            
            return y[1] - x[1];
        });
        
    
        can(board,num); 
        
        answer = result();
        
        return answer;
    }
    
    static int result(){
        
        int count = 1;
        
        Collections.sort(list,(x,y) -> {
            return (y[3] - x[3]);
        });
        
        int size = list.size();
        
        
        if(list.get(0)[0] == 0)
            return 1;
        
        
        int dup = 0;
        
        for(int i = 1 ; i < size ; i++){
            int[] past = list.get(i-1);
            int[] tmp = list.get(i);
            
            if(tmp[0] == 0){
                
                if(tmp[3] == past[3]){
                    return count;
                }
                
                else{
                    return count + dup + 1;
                }
            }
            
            if(tmp[3] == past[3]){
                dup++;
            }else{
                count += dup + 1;
                dup = 0;
            }
        }
        
        return -1;
    }
    
    
    static void can(int[][] board,int n){
        
        int fv = board[0][1];
        int sv = board[0][2];
        list.add(board[0]);
        
        int idx = 1;
        int up = -1;
        
        while(idx < n){
            
            if(board[idx][1] == fv){
                if(up <= board[idx][2]){
                    list.add(board[idx]);
                }
            }
            
            else{
                up = Math.max(up,sv);
                
                if(board[idx][2] >= up){
        
                    list.add(board[idx]);
                }
                
                fv = board[idx][1];
                sv = board[idx][2];
            }
            
            idx += 1;
        }
    }
}