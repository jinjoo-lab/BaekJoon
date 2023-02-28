import java.io.*;
import java.util.*;

public class Main {
    static int result = 0;
    static int[][] board = new int[6][6];
    static boolean[] comb = new boolean[26];
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=1;i<=5;i++)
        {
            String[] line = br.readLine().split("");
            for(int j=1;j<=5;j++)
            {
                if(line[j-1].equals("Y"))
                    board[i][j] = 1;
            }
        }

        makeGroup(0,0);
        System.out.println(result);
        br.close();
    }
    static void print()
    {
        for(int i=0;i<25;i++)
        {
            if(comb[i])
            {
                System.out.print(i+" ");
            }
        }
        System.out.println();
    }
    static void makeGroup(int idx,int count)
    {
        if(count==7)
        {
            bfs();
            return;
        }

        for(int i=idx;i<25;i++)
        {
            comb[i] = true;
            makeGroup(i+1,count+1);
            comb[i] = false;
        }
    }
    /*
    * 0 1 2 3 4
    * 5 6 7 8 9
    * 11 12 13 14 15
    * */
    static void bfs()
    {
        Queue<point> q= new LinkedList<>();
        boolean[][] visit = new boolean[6][6];
        boolean[][] visit2 = new boolean[6][6];
        boolean isIn = false;
        int lee = 0;
        int count = 0;
        for(int i=0;i<25;i++)
        {
            if(comb[i])
            {
                int x = i/5 + 1;
                int y = i%5 + 1;

                visit[x][y] = true;

                if(!isIn)
                {
                    isIn = true;
                    q.add(new point(x,y));
                    visit2[x][y] = true;
                    count = 1;
                    if(board[x][y]==0)
                        lee = 1;
                }
            }
        }
        while(!q.isEmpty())
        {
            point cur = q.poll();
            for(int i=0;i<4;i++)
            {
                int nx = cur.x+ dx[i];
                int ny = cur.y+ dy[i];
                if(nx>=1&&nx<=5&&ny>=1&&ny<=5){
                    if(visit[nx][ny]&&!visit2[nx][ny])
                    {
                        visit2[nx][ny] =true;
                        if(board[nx][ny]==0)
                            lee++;
                        count = count +1;
                        q.add(new point(nx,ny));
                    }
                }
            }
        }

        if(lee>=4&&count==7)
        {
            result = result + 1;
        }
    }
}
class point
{
    int x;
    int y;
    point(int x,int y)
    {
        this.x =x;
        this.y =y;
    }
}