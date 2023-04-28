import java.util.*;
class Solution {
    static boolean can = false;
    static boolean[] visit = new boolean[9];
    public int solution(String[] board) {
        int answer = -1;
        StringBuilder sb = new StringBuilder();
        sb.append(board[0]);
        sb.append(board[1]);
        sb.append(board[2]);
        
        String[] line = sb.toString().split("");
        int[] result = new int[9];
        for(int i=0;i<9;i++)
        {
            if(line[i].equals("O"))
                result[i] = 1;
            else if(line[i].equals("X"))
                result[i] = 2;
        }
      
        int[] cur = new int[9];
        tictac(0,1,cur,result);
        if(can)
            answer = 1;
        else
            answer = 0;
        
        return answer;
    }
    
    int change(int turn)
    {
        if(turn==1)
            return 2;
    
        return 1;
    }
    static boolean isSame(int[] cur,int[] result)
    {
        for(int i=0;i<9;i++)
        {
            if(cur[i]!=result[i])
                return false;
        }
        
        return true;
    }
    void tictac(int k, int turn,int[] cur,int[] result)
    {
        if(isSame(cur,result))
        {
            can = true;
            return;
        }
        
        if(k==9)
        {
            if(isSame(cur,result))
            {
                can = true;
            }
            return;
        }
        
        if(garo(cur)||sero(cur)||cross(cur))
        {
            if(isSame(cur,result))
            {
                can = true;
            }
            return;
        }
        
        for(int i=0;i<9;i++)
        {
            if(!visit[i])
            {
                visit[i] = true;
                cur[i] = turn;
                tictac(k+1,change(turn),cur,result);
                cur[i] = 0;
                visit[i] = false;
            }
        }
        
    }
        
    void print(int[] arr)
    {
        for(int i=0;i<9;i++)
        {
            System.out.print(arr[i]);
        }System.out.println();
    }
    boolean garo(int[] arr)
    {
        if(arr[0]!=0&&arr[1]!=0&&arr[2]!=0)
        {
            if(arr[0]==arr[1]&&arr[1]==arr[2])
            {
                return true;
            }
        }

        if(arr[3]!=0&&arr[4]!=0&&arr[5]!=0)
        {
            if(arr[3]==arr[4]&&arr[4]==arr[5])
                return true;
        }

        if(arr[6]!=0&&arr[7]!=0&&arr[8]!=0)
        {
            if(arr[6]==arr[7]&&arr[7]==arr[8])
                return true;
        }

        return false;
    }

    boolean sero(int[] arr)
    {
        if(arr[0]!=0&&arr[3]!=0&&arr[6]!=0)
        {
            if(arr[0]==arr[3]&&arr[3]==arr[6])
                return true;
        }
        if(arr[1]!=0&&arr[4]!=0&&arr[7]!=0)
        {
            if(arr[1]==arr[4]&&arr[4]==arr[7])
                return true;
        }
        if(arr[2]!=0&&arr[5]!=0&&arr[8]!=0)
        {
            if(arr[2]==arr[5]&&arr[5]==arr[8])
                return true;
        }

        return false;
    }

    boolean cross(int[] arr)
    {
        if(arr[0]!=0&&arr[4]!=0&&arr[8]!=0)
        {
            if(arr[0]==arr[4]&&arr[4]==arr[8])
                return true;
        }
        if(arr[2]!=0&&arr[4]!=0&&arr[6]!=0)
        {
            if(arr[2]==arr[4]&&arr[4]==arr[6])
                return true;
        }

        return false;
    }
}