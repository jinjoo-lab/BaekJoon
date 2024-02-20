import java.util.*;
import java.io.*;
import java.awt.Point;
public class Main {
    static int num=0;
    static int n = 0;
    static int m = 0;
    static int[][] board = new int[101][101];
    static int[][] visit =  new int[101][101];
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static Queue<pair> q = new LinkedList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i= 1;i<=n;i++)
        {
            st = new StringTokenizer(bf.readLine()," ");
            for(int j=1;j<=m;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j]==1)
                    num=num+1;

                if(i==1||i==n||j==1||j==m)
                    q.add(new pair(i,j));
            }
        }
        int hour=0;
        while(true)
        {
            if(num==0)
                break;

            int result =  bfs();
            hour=hour+1;

            if(num-result==0)
            {
                break;
            }
            num = num -result;
        }
        System.out.println(hour);
        System.out.println(num);

        bf.close();
    }

    static int bfs() {
        int result = 0;
       Queue<pair> sub =new LinkedList<>();
       while(!q.isEmpty())
       {
           pair cur = q.poll();
           for(int i=0;i<4;i++)
           {
               int nx = cur.x+ dx[i];
               int ny = cur.y + dy[i];

               if(nx>=1&&nx<=n&&ny>=1&&ny<=m)
               {
                   if(visit[nx][ny]==0&&board[nx][ny]==0)
                   {
                       visit[nx][ny]=1;
                       q.add(new pair(nx,ny));
                   }
                   else if(visit[nx][ny]==0&&board[nx][ny]==1)
                   {
                       visit[nx][ny]=1;
                       board[nx][ny]=0;
                       result = result +1;
                       sub.add(new pair(nx,ny));
                   }
               }
           }
       }
       q = new LinkedList<>(sub);
       return result;
    }

}
class pair
{
    int x;
    int y;
    pair(int x, int y)
    {
        this.x =x;
        this.y = y;
    }
}