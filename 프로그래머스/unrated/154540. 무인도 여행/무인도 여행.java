import java.util.*;
class Solution {
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    public int[] solution(String[] maps) {
        int[] answer = {};
        ArrayList<Integer> list = new ArrayList<>();
        int lx = maps.length-1;
        int ly = maps[0].length() - 1;
        int[][] board = new int[maps.length][maps[0].length()];
        boolean[][] visit = new boolean[maps.length][maps[0].length()];        
        for(int i=0;i<maps.length;i++)
        {
            String[] line = maps[i].split("");
            
            for(int j=0;j<line.length;j++)
            {
                if(line[j].equals("X"))
                    continue;
                
                else{
                    board[i][j] = Integer.parseInt(line[j]);
                }
            }
        }
        
        for(int i=0;i<=lx;i++)
        {
            for(int j=0;j<=ly;j++)
            {
                if(!visit[i][j]&&board[i][j]>=1)
                {
                    int result = bfs(i,j,board,visit,lx,ly);
                    list.add(result);
                }
            }
        }
        answer = new int[list.size()];
        
        if(list.size()==0)
        {
            answer = new int[1];
            answer[0] = -1;
        }
        else{
            for(int i=0;i<list.size();i++)
            {
                answer[i] = list.get(i);
            }
            Arrays.sort(answer);
        }
        return answer;
    }
    
    public int bfs(int i,int j,int[][] board,boolean[][] visit,int lx,int ly)
    {
        int result = board[i][j];
        Queue<point> q = new LinkedList<>();
        q.add(new point(i,j));
        visit[i][j] = true;
        
        while(!q.isEmpty())
        {
            point cur = q.poll();
            for(int k=0;k<4;k++)
            {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];
                
                if(nx>=0&&nx<=lx&&ny>=0&&ny<=ly)
                {
                    
                    if(!visit[nx][ny] && board[nx][ny]>=1)
                    {
                        visit[nx][ny] = true;
                        q.add(new point(nx,ny));
                        result = result + board[nx][ny];
                    }
                    
                    
                }
            }
        }
        
        return result;
    }
    
    public void print(int[][] arr)
    {
        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr[i].length;j++)
            {
                System.out.print(arr[i][j]+" ");
            }System.out.println();
        }
    }
}

class point
{
    int x;
    int y;
    
    point(int x,int y)
    {
        this.x = x;
        this.y = y;
    }
}