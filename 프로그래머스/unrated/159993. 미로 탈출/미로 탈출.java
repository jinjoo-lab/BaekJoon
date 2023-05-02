import java.util.*;
class Solution {
    static point start;
    static point exit;
    static point lebber;
    
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    public int solution(String[] maps) {
        int answer = 0;
        int lx = maps.length -1 ;
        int ly = maps[0].length() - 1;
        int[][] board = new int[lx+1][ly+1];
        int[][] visit = new int[lx+1][ly+1];
        for(int i=0;i<maps.length;i++)
        {
            String[] line = maps[i].split("");
            for(int j=0;j<line.length;j++)
            {
                if(line[j].equals("S")){
                    board[i][j] = 2;
                    start = new point(i,j);
                }
                
                else if(line[j].equals("E"))
                {
                    board[i][j] = 3;
                    exit = new point(i,j);
                }
                
                else if(line[j].equals("L"))
                {
                    board[i][j] = 4;
                    lebber = new point(i,j);
                }
                
                else if(line[j].equals("X"))
                {
                    board[i][j] = 1;
                }
            }
        }
        answer = bfs(start.x,start.y,board,visit,lx,ly);
        
        return answer;
    }
    
    public int bfs(int x,int y,int[][] board,int[][] visit,int lx,int ly)
    {
        point last = null;
        int lcount = 0;
        int ecount = 0;
        
        Queue<point> q = new LinkedList<>();
        q.add(new point(x,y));
        visit[x][y] = 1;
        
        while(!q.isEmpty())
        {
            point cur = q.poll();
            if(board[cur.x][cur.y]==4)
            {
                last = cur;
                lcount = visit[cur.x][cur.y] -1 ;
            }
            
            for(int i=0;i<4;i++)
            {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if(nx>=0&&nx<=lx&&ny>=0&&ny<=ly)
                {
                    if(visit[nx][ny]==0&&board[nx][ny]!=1)
                    {
                        visit[nx][ny] = visit[cur.x][cur.y] + 1;
                        q.add(new point(nx,ny));
                    }
                }
            }
        }
       
        if(lcount==0)
        {
            return -1;
        }
        
        else{
            q = new LinkedList<>();
            int[][] v2 = new int[lx+1][ly+1];
            v2[last.x][last.y] = 1;
            
            q.add(last);
            
            while(!q.isEmpty())
            {
            point cur = q.poll();
                
            if(board[cur.x][cur.y]==3)
            {
                ecount = v2[cur.x][cur.y] -1 ;
            }
            
            for(int i=0;i<4;i++)
            {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if(nx>=0&&nx<=lx&&ny>=0&&ny<=ly)
                {
                    if(v2[nx][ny]==0&&board[nx][ny]!=1)
                    {
                        v2[nx][ny] = v2[cur.x][cur.y] + 1;
                        q.add(new point(nx,ny));
                    }
                }
            }
            }
        }
        
        if(ecount==0)
            return -1;
        return lcount+ecount;
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