import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int k = 0;
    static int[][] cboard = new int[51][51];
    static int[][] mboard =new int[51][51];
    static int[][] sboard = new int[51][51];

    static int[][][] dboard = new int[51][51][8];
    static int[] dx = {-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,1,1,1,0,-1,-1,-1};
    static Queue<ball> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            ball b = new ball(x,y,m,s,d);
            q.add(b);
        }
        int result = 0;
        for(int i=1;i<=k;i++)
        {
            move();
            result =  div();
        }
        System.out.println(result);
    }

    static int over(int x)
    {
        if(x>n)
            return (x % n);
        else if(x<1) {
            int tmp = x%n;
            return (n + tmp);
        }
        else
            return x;
    }
    static boolean all(int x,int y)
    {
        int even = 0;
        int neven = 0;

        for(int i=0;i<8;i++)
        {
            if(i%2==0)
            {
                even +=dboard[x][y][i];
            }
            else{
                neven +=dboard[x][y][i];
            }
        }

        if(even==0||neven==0)
            return true;
        else
            return false;
    }
    static int div()
    {
        int result = 0;

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                if(cboard[i][j]==1) {
                    result += mboard[i][j];
                    int d = 0;
                    for (int k = 0; k < 8; k++)
                    {
                        if(dboard[i][j][k]==1)
                            d = k;
                    }
                    q.add(new ball(i,j,mboard[i][j],sboard[i][j],d));
                }
                else if(cboard[i][j]>=2)
                {
                    int nm = mboard[i][j] / 5;

                    if(nm==0)
                        continue;

                    int ns = sboard[i][j] / cboard[i][j];

                    if(all(i,j))
                    {
                        q.add(new ball(i,j,nm,ns,0));
                        q.add(new ball(i,j,nm,ns,2));
                        q.add(new ball(i,j,nm,ns,4));
                        q.add(new ball(i,j,nm,ns,6));
                        result+=nm*4;
                    }
                    else{
                        q.add(new ball(i,j,nm,ns,1));
                        q.add(new ball(i,j,nm,ns,3));
                        q.add(new ball(i,j,nm,ns,5));
                        q.add(new ball(i,j,nm,ns,7));
                        result+=nm*4;
                    }
                }
            }
        }
        return result;
    }
    static void move()
    {
        cboard = new int[51][51];
        mboard =new int[51][51];
        sboard = new int[51][51];
        dboard = new int[51][51][8];

        while(!q.isEmpty())
        {
            ball cur = q.poll();

            int nx = cur.x + (cur.s%n) * dx[cur.d];
            int ny = cur.y + (cur.s%n) * dy[cur.d];

            nx = over(nx);
            ny = over(ny);

            cboard[nx][ny] = cboard[nx][ny]+1;
            mboard[nx][ny] = mboard[nx][ny]+cur.m;
            sboard[nx][ny] = sboard[nx][ny]+cur.s;
            dboard[nx][ny][cur.d] =  dboard[nx][ny][cur.d]+1;
        }
    }
}
class ball
{
    int x;
    int y;
    int m;
    int s;
    int d;

    ball(int x,int y,int m,int s,int d)
    {
        this.x = x;
        this.y = y;
        this.m = m;
        this.s = s;
        this.d = d;
    }
}
