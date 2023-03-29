import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[][] board = new int[55][55];
    static int[] turnx = {-1,-1,-1,0,0,1,1,1};
    static int[] turny = {-1,0,1,-1,1,-1,0,1};
    static int mx = 0;
    static int my = 0;
    static int sdir = -1;
    static int ldir = -1;
    static int lx = 0;
    static int ly = 0;
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        int count = 0;
        int last = 0;
        int smx = 0;
        int smy = 0;
        int lmx = 0;
        int lmy = 0;
        for (int i = 1; i <= n; i++) {
            String[] line = br.readLine().split("");
            for (int j = 1; j <= n; j++) {
                if (line[j - 1].equals("B")) {
                    if (count == 0) {
                        smx = i;
                        smy = j;
                    }

                    if (count == 1) {
                        mx = i;
                        my = j;
                    }
                    board[i][j] = 2;
                    count = count + 1;
                } else if (line[j - 1].equals("E")) {
                    if (last == 0) {
                        lmx = i;
                        lmy = j;
                    }
                    if (last == 1) {
                        lx = i;
                        ly = j;
                    }
                    board[i][j] = 3;
                    last = last + 1;
                } else
                    board[i][j] = Integer.parseInt(line[j - 1]);
            }
        }
        if (smx == mx) {
            sdir = 1;
        } else {
            sdir = 2;
        }

        if (lmx == lx) {
            ldir = 1;
        } else {
            ldir = 2;
        }

        bfs();
        System.out.println(result);
        br.close();
    }

    static void bfs()
    {
        Queue<point> q = new LinkedList<>();
        int[][][] visit = new int[55][55][3];
        visit[mx][my][sdir] = 1;
        q.add(new point(mx,my,sdir));

        while(!q.isEmpty())
        {
            point cur = q.poll();
            if(cur.x==lx&&cur.y==ly&&cur.dir==ldir)
            {
                if(result==0||result>visit[cur.x][cur.y][cur.dir])
                {
                    result = visit[cur.x][cur.y][cur.dir]-1;
                }
            }

            if(result!=0&&result<visit[cur.x][cur.y][cur.dir])
                continue;

            for(int i=0;i<5;i++)
            {
                if(i==0)
                {
                    if(cur.dir==1&&cur.x>=2&&cur.y>1&&cur.y<n)
                    {
                        if(board[cur.x-1][cur.y-1]!=1&&board[cur.x-1][cur.y]!=1&&board[cur.x-1][cur.y+1]!=1)
                        {
                            if(visit[cur.x-1][cur.y][cur.dir]==0||visit[cur.x][cur.y][cur.dir]+1<visit[cur.x-1][cur.y][cur.dir]) {
                                visit[cur.x-1][cur.y][cur.dir] = visit[cur.x][cur.y][cur.dir]+1;
                                q.add(new point(cur.x-1,cur.y,cur.dir));
                            }
                        }
                    }
                    else if(cur.dir==2&&cur.x>=3){
                        if(board[cur.x-2][cur.y]!=1&&board[cur.x-1][cur.y]!=1&&board[cur.x][cur.y]!=1)
                        {
                            if(visit[cur.x-1][cur.y][cur.dir]==0||visit[cur.x][cur.y][cur.dir]+1<visit[cur.x-1][cur.y][cur.dir]) {
                                visit[cur.x-1][cur.y][cur.dir] = visit[cur.x][cur.y][cur.dir]+1;
                                q.add(new point(cur.x-1,cur.y,cur.dir));
                            }
                        }
                    }
                }

                else if(i==1)
                {
                    if(cur.dir==1&&cur.x<n&&cur.y>1&&cur.y<n)
                    {
                        if(board[cur.x+1][cur.y-1]!=1&&board[cur.x+1][cur.y]!=1&&board[cur.x+1][cur.y+1]!=1)
                        {
                            if(visit[cur.x+1][cur.y][cur.dir]==0||visit[cur.x][cur.y][cur.dir]+1<visit[cur.x+1][cur.y][cur.dir])
                            {
                                visit[cur.x+1][cur.y][cur.dir] = visit[cur.x][cur.y][cur.dir]+1;
                                q.add( new point(cur.x+1,cur.y,cur.dir));
                            }
                        }
                    }
                    else if(cur.dir==2&&cur.x<n-1){
                        if(board[cur.x+2][cur.y]!=1&&board[cur.x+1][cur.y]!=1&&board[cur.x][cur.y]!=1)
                        {
                            if(visit[cur.x+1][cur.y][cur.dir]==0||visit[cur.x][cur.y][cur.dir]+1<visit[cur.x+1][cur.y][cur.dir])
                            {
                                visit[cur.x+1][cur.y][cur.dir] = visit[cur.x][cur.y][cur.dir]+1;
                                q.add( new point(cur.x+1,cur.y,cur.dir));
                            }
                        }
                    }
                }

                else if(i==2)
                {
                    if(cur.dir==1&&cur.y>=3)
                    {
                        if(board[cur.x][cur.y-2]!=1&&board[cur.x][cur.y-1]!=1&&board[cur.x][cur.y]!=1)
                        {
                            if(visit[cur.x][cur.y-1][cur.dir]==0||visit[cur.x][cur.y][cur.dir] +1 < visit[cur.x][cur.y-1][cur.dir])
                            {
                                visit[cur.x][cur.y-1][cur.dir] = visit[cur.x][cur.y][cur.dir] +1;
                                q.add(new point(cur.x,cur.y-1,cur.dir));
                            }
                        }
                    }

                    else if(cur.dir==2&&cur.y>=2&&cur.x>1&&cur.x<n) {
                        if(board[cur.x-1][cur.y-1]!=1&&board[cur.x][cur.y-1]!=1&&board[cur.x+1][cur.y-1]!=1)
                        {
                            if(visit[cur.x][cur.y-1][cur.dir]==0||visit[cur.x][cur.y][cur.dir] +1 < visit[cur.x][cur.y-1][cur.dir])
                            {
                                visit[cur.x][cur.y-1][cur.dir] = visit[cur.x][cur.y][cur.dir] +1;
                                q.add(new point(cur.x,cur.y-1,cur.dir));
                            }
                        }
                    }
                }

                else if(i==3)
                {
                    if(cur.dir==1&&cur.y<n-1)
                    {
                        if(board[cur.x][cur.y+2]!=1&&board[cur.x][cur.y+1]!=1&&board[cur.x][cur.y]!=1)
                        {
                            if(visit[cur.x][cur.y+1][cur.dir]==0||visit[cur.x][cur.y][cur.dir]+1<visit[cur.x][cur.y+1][cur.dir])
                            {
                                visit[cur.x][cur.y+1][cur.dir] = visit[cur.x][cur.y][cur.dir]+1;
                                q.add(new point(cur.x,cur.y+1,cur.dir));
                            }
                        }
                    }

                    else if(cur.dir==2&&cur.y<n&&cur.x>1&&cur.x<n)
                    {
                        if(board[cur.x-1][cur.y+1]!=1&&board[cur.x][cur.y+1]!=1&&board[cur.x+1][cur.y+1]!=1)
                        {
                            if(visit[cur.x][cur.y+1][cur.dir]==0||visit[cur.x][cur.y][cur.dir]+1<visit[cur.x][cur.y+1][cur.dir])
                            {
                                visit[cur.x][cur.y+1][cur.dir] = visit[cur.x][cur.y][cur.dir]+1;
                                q.add(new point(cur.x,cur.y+1,cur.dir));
                            }
                        }
                    }
                }

                else if(i==4)
                {
                    boolean keep = true;
                    for(int j=0;j<8;j++)
                    {
                        int nx = cur.x + turnx[j];
                        int ny = cur.y + turny[j];

                        if(nx<1||nx>n||ny<1||ny>n) {
                            keep = false;
                            break;
                        }

                        else if(board[nx][ny]==1) {
                            keep = false;
                            break;
                        }
                    }
                    if(cur.dir==1&&keep)
                    {
                        if(visit[cur.x][cur.y][2]==0||visit[cur.x][cur.y][cur.dir]+1<visit[cur.x][cur.y][2])
                        {
                            visit[cur.x][cur.y][2] = visit[cur.x][cur.y][cur.dir]+1;
                            q.add(new point(cur.x,cur.y,2));
                        }
                    }

                    else if(cur.dir==2&&keep){
                        if(visit[cur.x][cur.y][1]==0||visit[cur.x][cur.y][cur.dir]+1<visit[cur.x][cur.y][1])
                        {
                            visit[cur.x][cur.y][1] = visit[cur.x][cur.y][cur.dir]+1;
                            q.add(new point(cur.x,cur.y,1));
                        }
                    }
                }
            }
        }
    }
}
class point
{
    int x;
    int y;
    int dir;

    point(int x,int y,int dir)
    {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}