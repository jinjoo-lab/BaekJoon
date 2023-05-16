
import java.io.*;
import java.util.*;

public class Main {
    static int n  = 0;
    static int m = 0 ;
    static int[][] board = new int[51][51];
    static int[][] visit = new int[51][51];

    // 서 북 동 남
    static int[] dx = {0,-1,0,1};
    static int[] dy = {-1,0,1,0};
    static int[] w = {1,2,4,8};

    static int first = 1;
    static int second = 0;
    static int three = 0;
    static HashMap<Integer,Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine()," ");
            for(int j=1;j<=m;j++)
            {
                board[i][j] = 15 - Integer.parseInt(st.nextToken());
            }
        }
        int start = 1;
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                if(visit[i][j]==0)
                {
                    int tmp = bfs(i,j,start);
                    map.put(start,tmp);
                    first = start;
                    second = Math.max(second , tmp);
                    start = start + 1;
                }
            }
        }

        third();

        System.out.println(first);
        System.out.println(second);
        System.out.println(three);
        br.close();
    }

    static int bfs(int x,int y,int num)
    {
        int count = 1;
        Queue<point> q = new LinkedList<>();
        visit[x][y] = num;
        q.add(new point(x,y));

        while(!q.isEmpty())
        {
            point cur = q.poll();
            int tmp = board[cur.x][cur.y];

            for(int i=3;i>=0;i--)
            {
                if(tmp-w[i] < 0)
                    continue;

                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx>=1&&nx<=n&&ny>=1&&ny<=m)
                {
                    if(visit[nx][ny]==0)
                    {
                        visit[nx][ny] = num;
                        q.add(new point(nx,ny));
                        count = count + 1;
                    }
                }

                tmp = tmp - w[i];
            }
        }

        return count;
    }

    static void third()
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                for(int k=0;k<4;k++)
                {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(nx>=1&&ny>=1&&nx<=n&&ny<=m)
                    {
                        if(visit[i][j]!=visit[nx][ny])
                        {
                            int tmp = map.get(visit[i][j]) + map.get(visit[nx][ny]);

                            three = Math.max(three, tmp);
                        }
                    }
                }
            }
        }
    }

    static void print()
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                System.out.print(visit[i][j]+" ");
            }
            System.out.println();
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
