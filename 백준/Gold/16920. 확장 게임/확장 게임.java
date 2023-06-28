import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int k = 0;
    static int[] dp;

    static Queue<point>[] q;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        q = new Queue[k+1];
        for(int i=1;i<=k;i++)
        {
            q[i] = new LinkedList<>();
        }
        dp = new int[k+1];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=k;i++)
        {
            dp[i] = Integer.parseInt(st.nextToken());
        }

        int[][] board = new int[n+1][m+1];
        int[][] visit = new int[n+1][m+1];

        for(int i=1;i<=n;i++)
        {
            String line = br.readLine();
            for(int j=1;j<=m;j++)
            {
                char cur = line.charAt(j-1);

                if(cur=='.') {
                    board[i][j] = 0;
                    count = count + 1;
                }

                else if(cur=='#')
                    board[i][j] = 10; // 벽

                else {
                    board[i][j] = Integer.parseInt(cur + "");
                    q[board[i][j]].add(new point(i,j,0));
                    visit[i][j] = 1;
                }
            }
        }

        while(true)
        {
            bfs(board,visit);

            if(isOver())
                break;
        }


        resultPrint(board);
        br.close();
    }
    static boolean isOver()
    {
        for(int i=1;i<=k;i++)
        {
            if(!q[i].isEmpty())
                return false;
        }

        return true;
    }

    static void bfs(int[][] board , int[][] visit)
    {
        for(int i=1;i<=k;i++)
        {
            while(!q[i].isEmpty())
            {
                if(q[i].peek().d == dp[i]) {
                    break;
                }
                point cur = q[i].poll();

                for(int j=0;j<4;j++)
                {
                    int nx = cur.x + dx[j];
                    int ny = cur.y + dy[j];

                    if(nx<1||nx>n||ny<1||ny>m)
                        continue;

                    if((board[nx][ny]==0 || board[nx][ny]==board[cur.x][cur.y]) && (visit[nx][ny]==0 || visit[nx][ny] > visit[cur.x][cur.y] + 1))
                    {
                        visit[nx][ny] = visit[cur.x][cur.y] + 1;
                        board[nx][ny] = board[cur.x][cur.y];
                        q[i].add(new point(nx,ny,cur.d+1));
                    }
                }
            }

            Queue<point> tmpq = new LinkedList<>();
            while(!q[i].isEmpty())
            {
                point rest = q[i].poll();
                tmpq.add(new point(rest.x,rest.y,0));
            }

            q[i] = tmpq;
        }
    }

    static void resultPrint(int[][] board)
    {
        int[] rearr = new int[k+1];
        StringBuilder sb = new StringBuilder();

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                if(board[i][j]==10)
                    continue;

                rearr[board[i][j]] = rearr[board[i][j]]+1;
            }
        }

        for(int i=1;i<=k;i++)
        {
            sb.append(rearr[i]+" ");
        }sb.append("\n");

        System.out.print(sb.toString());
    }


    static void print(int[][] board)
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
class point
{
    int x;
    int y;
    int d;

    point(int x,int y,int d)
    {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}