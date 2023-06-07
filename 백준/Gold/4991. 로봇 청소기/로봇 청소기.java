import java.io.*;
import java.util.*;

public class Main {

    static int n = 0;
    static int m = 0;
    static int[][] board;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static int[] dust = {1,2,4,8,16,32,64,128,256,512};

    static int result = Integer.MAX_VALUE;

    static int allDust = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true)
        {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            if(n==0&&m==0)
                break;

            board = new int[n+1][m+1];
            result = Integer.MAX_VALUE;
            int start = 1;
            allDust = 0;
            int sx = 0;
            int sy = 0;
            for(int i=1;i<=n;i++)
            {
                String line = br.readLine();
                for(int j=1;j<=m;j++)
                {
                    if(line.charAt(j-1)=='o')
                    {
                        sx = i;
                        sy = j;
                    }

                    else if(line.charAt(j-1)=='x')
                    {
                        board[i][j] = -1;
                    }

                    else if(line.charAt(j-1)=='*')
                    {
                        board[i][j] = start;
                        allDust += start;
                        start = start * 2;
                    }
                }
            }
            search(sx,sy);

            if(result==Integer.MAX_VALUE)
                result = -1;
            sb.append(result+"\n");
        }
        System.out.print(sb);

        br.close();
    }

    static void search(int sx,int sy)
    {
        Queue<point> q = new LinkedList<>();
        int[][][] visit = new int[n+1][m+1][2000];
        visit[sx][sy][0] = 1;
        q.add(new point(sx,sy,0));

        while(!q.isEmpty())
        {
            point cur = q.poll();
            if(cur.dust==allDust)
            {
                result = Math.min(result , visit[cur.x][cur.y][cur.dust] - 1);
                continue;
            }

            for(int i=0;i<4;i++)
            {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<1||nx>n||ny<1||ny>m)
                    continue;

                if(board[nx][ny]==-1)
                    continue;

                if(board[nx][ny]==0)
                {
                    if(visit[nx][ny][cur.dust]==0|| visit[nx][ny][cur.dust] > visit[cur.x][cur.y][cur.dust] + 1)
                    {
                        visit[nx][ny][cur.dust] = visit[cur.x][cur.y][cur.dust] + 1;
                        q.add(new point(nx,ny,cur.dust));
                    }
                }

                else if(board[nx][ny]>=1){

                    if(!isIt(board[nx][ny],cur.dust)) {
                        int tmp = cur.dust + board[nx][ny];

                        if (visit[nx][ny][tmp] == 0 || visit[nx][ny][tmp] > visit[cur.x][cur.y][cur.dust] + 1)
                        {
                            visit[nx][ny][tmp] = visit[cur.x][cur.y][cur.dust] + 1;
                            q.add(new point(nx,ny,tmp));
                        }
                    }

                    else{
                        if(visit[nx][ny][cur.dust]==0||visit[nx][ny][cur.dust] > visit[cur.x][cur.y][cur.dust] + 1)
                        {
                            visit[nx][ny][cur.dust] = visit[cur.x][cur.y][cur.dust] + 1;
                            q.add(new point(nx,ny,cur.dust));
                        }
                    }
                }
            }
        }
    }

    static boolean isIt(int nowDust,int cur)
    {
        int idx = 0;
        for(int i=0;i<=9;i++)
        {
            if(dust[i]==nowDust) {
                idx = i;
                break;
            }
        }

        for(int i=9;i>=0;i--)
        {
            if(dust[i] > cur)
                continue;

            if(i==idx&&cur>=dust[i])
            {
                return true;
            }

            else if(cur >= dust[i])
            {
                cur -= dust[i];
            }
        }

        return false;
    }

}
class point
{
    int x;
    int y;

    int dust;

    point(int x,int y,int dust)
    {
        this.x = x;
        this.y = y;
        this.dust = dust;
    }
}
