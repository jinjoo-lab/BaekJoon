import java.io.*;
import java.util.*;

public class Main {
    static int[][] board = new int[11][11];
    static int n = 0;
    static int m = 0;
    static Queue<point> q = new LinkedList<>();
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int result = -1;
    static boolean[][][][] visit = new boolean[11][11][11][11];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int rx=0; int ry = 0; int bx = 0; int by = 0;
        for(int i=1;i<=n;i++)
        {
            String[] line = br.readLine().split("");
            for(int j=1;j<=m;j++)
            {
                if(line[j-1].equals("#"))
                    board[i][j] = 1;
                else if(line[j-1].equals("."))
                    board[i][j] = 0;
                else if(line[j-1].equals("R"))
                {
                    rx = i;
                    ry = j;
                }
                else if(line[j-1].equals("B"))
                {
                    bx = i;
                    by = j;
                }
                else if(line[j-1].equals("O"))
                    board[i][j] = 2;
            }
        }
        point start = new point(rx,ry,bx,by,0);
        visit[rx][ry][bx][by] = true;
        q.add(start);
        bfs();
        System.out.println(result);
        br.close();
    }
    // right , left , down , up
    static void bfs()
    {
        while(!q.isEmpty())
        {
            point cur = q.poll();
            if(cur.c>=10)
                continue;

            for(int i=0;i<4;i++)
            {
                boolean rmove = true;
                boolean rgoal = false;
                int rx = cur.rx;
                int ry = cur.ry;

                while(rmove)
                {
                    rx = rx +dx[i];
                    ry = ry +dy[i];

                    if(rx>=1&&rx<=n&&ry>=1&&ry<=m)
                    {
                        if(board[rx][ry]==1)
                        {
                            rmove = false;
                            rx = rx - dx[i];
                            ry = ry - dy[i];
                        }
                        else if(board[rx][ry]==2)
                        {
                            rgoal = true;
                            rmove = false;
                        }
                    }
                }

                boolean bmove = true;
                boolean bgoal = false;
                int bx = cur.bx;
                int by = cur.by;

                while(bmove)
                {
                    bx = bx+dx[i];
                    by = by+dy[i];

                    if(bx>=1&&bx<=n&&by>=1&&by<=m)
                    {
                        if(board[bx][by]==1)
                        {
                            bmove = false;
                            bx = bx - dx[i];
                            by = by - dy[i];
                        }
                        else if(board[bx][by]==2)
                        {
                            bgoal = true;
                            bmove = false;
                        }
                    }
                }

                if(rgoal==true&&bgoal==false)
                {
                    if(result==-1)
                    {
                        result = cur.c+1;
                    }
                    else if(result>cur.c+1)
                    {
                        result = cur.c+1;
                    }
                }

                if(bgoal==false&&rgoal==false)
                {
                        if(rx==bx&&ry==by)
                        {
                            whof(cur,rx,ry,i);
                        }
                        else{
                            if(visit[rx][ry][bx][by]==false) {
                                visit[rx][ry][bx][by] = true;
                                q.add(new point(rx, ry, bx, by, cur.c + 1));
                            }
                        }
                }
            }
        }
    }

    static void whof(point cur,int x,int y,int i)
    {
        int rx=0; int ry=0; int bx = 0; int by=0;
        if(i==0) // right
        {
            if(cur.by>cur.ry)
            {
                rx = x; bx = x;
                ry = y-1; by = y;
            }
            else{
                rx = x; bx = x;
                ry = y; by = y-1;
            }
        }
        else if(i==1)
        {
            if(cur.by>cur.ry)
            {
                rx = x; bx = x;
                ry = y; by = y+1;
            }
            else{
                rx = x; bx = x;
                ry = y+1; by = y;
            }
        }
        else if(i==2){
            if(cur.bx>cur.rx) // blue 가 더 아레
            {
                   ry = y; by = y;
                   rx = x; bx = x+1;
            }
            else{
                ry = y; by = y;
                rx = x+1; bx = x;
            }
        }
        else{
            if(cur.bx>cur.rx)
            {
                ry =y; by = y;
                rx = x-1; bx = x;
            }
            else{
                ry =y; by = y;
                rx = x; bx = x-1;
            }
        }
        if(visit[rx][ry][bx][by]==false){
            visit[rx][ry][bx][by] = true;
            q.add(new point(rx,ry,bx,by,cur.c+1));
        }
    }
}
class point
{
    int rx;
    int ry;
    int bx;
    int by;
    int c;

    point(int rx,int ry,int bx,int by,int c)
    {
        this.rx = rx;
        this.ry = ry;
        this.bx = bx;
        this.by = by;
        this.c = c;
    }
}
