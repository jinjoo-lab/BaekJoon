import java.io.*;
import java.util.*;
public class Main {

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int result = Integer.MAX_VALUE;
    static int sx = 0;
    static int sy = 0;

    static int[] key = {1,2,4,8,16,32};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] board = new char[n + 1][m + 1];
        boolean[][][] visit = new boolean[n+1][m+1][200];

        for (int i = 1; i <= n; i++) {
            String line = br.readLine();
            for (int j = 1; j <= m; j++) {
                board[i][j] = line.charAt(j - 1);
                if(board[i][j]=='0')
                {
                    sx = i;
                    sy = j;
                }
            }
        }

        bfs(sx,sy,board,visit,n,m);
        if(result==Integer.MAX_VALUE)
            result = -1;

        System.out.println(result);

    }

    static void bfs(int x,int y,char[][] board,boolean[][][] visit,int n,int m)
    {
        Queue<point> q = new LinkedList<>();
        q.add(new point(x,y,0,0));
        visit[x][y][0] = true;

        while(!q.isEmpty())
        {
            point cur = q.poll();
            if(board[cur.x][cur.y]=='1')
            {
                result = Math.min(result,cur.move);
            }

            for(int i=0;i<4;i++)
            {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<1||nx>n||ny<1||ny>m)
                    continue;

                if(board[nx][ny]!='#')
                {
                    if(board[nx][ny]>='A'&&board[nx][ny]<='F')
                    {
                        if(have(cur.k,board[nx][ny]))
                        {
                            if(!visit[nx][ny][cur.k])
                            {
                                visit[nx][ny][cur.k] = true;
                                q.add(new point(nx,ny,cur.k,cur.move+1));
                            }
                        }
                    }

                    else if(board[nx][ny]>='a'&&board[nx][ny]<='f')
                    {
                        if(!have(cur.k,board[nx][ny]))
                        {
                            int tmp = cur.k + key[convert(board[nx][ny])];
                            if(!visit[nx][ny][tmp])
                            {
                                visit[nx][ny][tmp] = true;
                                q.add(new point(nx,ny,tmp,cur.move+1));
                            }
                        }
                        else{
                            if(!visit[nx][ny][cur.k])
                            {
                                visit[nx][ny][cur.k] = true;
                                q.add(new point(nx,ny,cur.k,cur.move+1));
                            }
                        }
                    }

                    else{
                        if(!visit[nx][ny][cur.k])
                        {
                            visit[nx][ny][cur.k] = true;
                            q.add(new point(nx,ny,cur.k,cur.move+1));
                        }
                    }
                }
            }
        }
    }
    static boolean have(int cur,char target)
    {
        int t = convert(target);

        for(int i=5;i>=0;i--)
        {
            if(i==t)
            {
                if(cur>=key[i])
                    return true;
            }
            if(cur>=key[i])
            {
                cur = cur - key[i];
            }
        }

        return false;
    }

    static int convert(char a)
    {
        if(a>='a'&&a<='f')
            return a - 'a';
        else
            return a - 'A';
    }
}
class point
{
    int x;
    int y;

    int k;
    int move;
    point(int x,int y,int k,int move)
    {
        this.x = x;
        this.y = y;
        this.k = k;
        this.move = move;
    }
}
