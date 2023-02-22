import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int k = 0;
    static int m = 0;
    static int[][] board = new int[101][101];
    static int[][] visit = new int[101][101];
    static int[] dx = {0,0,1,0,-1}; // 오 , 아, 왼 , 위
    static int[] dy = {0,1,0,-1,0};
    static Queue<point> q = new LinkedList<>();
    static ArrayList<change> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        k = Integer.parseInt(st.nextToken());
        for(int i=1;i<=k;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x][y] = 1;
        }
        st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());

        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int sec = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();

            if(dir.equals("D"))
            {
                list.add(new change(sec,1)); // right
            }
            else{
                list.add(new change(sec,2)); // left
            }
        }

        visit[1][1] = 1;
        q.add(new point(1,1,1,1,0));
        move();
        br.close();
    }
    static void print()
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                System.out.print(visit[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void move()
    {
        int result = 0;
        while(!q.isEmpty())
        {
            point cur = q.poll();
            int ndir = visit[cur.x][cur.y];
            int nx = cur.x + dx[ndir];
            int ny = cur.y + dy[ndir];

            if(nx>=1&&nx<=n&&ny>=1&&ny<=n)
            {
                int ntx = cur.tx;
                int nty = cur.ty;
                int ntdir = visit[ntx][nty];
                if(visit[nx][ny]>0)
                {
                    result = cur.sec + 1;
                    break;
                }

                else{
                    visit[nx][ny] = ndir;

                    if(board[nx][ny]==1)
                    {
                        board[nx][ny]=0;
                    }

                    else{
                        visit[ntx][nty] = 0;
                        ntx = ntx + dx[ntdir];
                        nty = nty + dy[ntdir];
                    }

                    for(change tmp : list)
                    {
                        if(tmp.sec==cur.sec+1)
                        {
                            ndir = changeDir(ndir,tmp.dir);
                            visit[nx][ny]= ndir;
                            break;
                        }
                    }

                    q.add(new point(nx,ny,ntx,nty,cur.sec+1));
                }
            }
            else{
                result = cur.sec + 1;
                break;
            }
        }

        System.out.println(result);
    }

    static int changeDir(int dir,int where)
    {
        if(where==1)
        {
            if(dir==4)
                return 1;
            else
                return dir+1;
        }

        else
        {
            if(dir==1)
                return 4;
            else
                return dir-1;
        }
    }
}
class point{
    int x;
    int y;
    int tx;
    int ty;
    int sec;

    point(int x,int y,int tx,int ty,int sec)
    {
        this.x =x;
        this.y =y;
        this.tx = tx;
        this.ty = ty;
        this.sec = sec;
    }

    @Override
    public String toString() {
        return x+" "+y+" "+tx+" "+ty;
    }
}
class change{
    int sec;
    int dir;

    change(int sec,int dir)
    {
        this.sec = sec;
        this.dir = dir;
    }
}