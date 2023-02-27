import java.util.*;
import java.io.*;
class Main {
    static int n = 0;
    static int m = 0;
    static char[][] board = new char[1001][1001];
    static int[][] jvisit = new int[1001][1001];
    static int[][] fvisit = new int[1001][1001];
    static Queue<pair> jq = new LinkedList<>();
    static Queue<pair> fq = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        boolean complete = false;
        String[] line = bf.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);

        for(int i=1;i<=n;i++)
        {
            line = bf.readLine().split("");
            for(int j=1;j<=m;j++)
            {
                board[i][j] = line[j-1].charAt(0);
                if(board[i][j]=='J') {
                    jq.add(new pair(i, j));
                    jvisit[i][j]=1;
                    if(i==1||i==n||j==1||j==m)
                        complete = true;
                }
                else if(board[i][j]=='F') {
                    fq.add(new pair(i, j));
                    fvisit[i][j]=1;
                }
            }
        }
        if(complete)
            System.out.println(1);
        else{
            while(true)
            {
                bfs2();
                int result = bfs();
                if(result>0) {
                    System.out.println(result);
                    break;
                }
                else if(result==-1)
                {
                    System.out.println("IMPOSSIBLE");
                    break;
                }
            }
        }
        bf.close();
    }
    static int bfs()
    {
        int[] dx ={0,0,-1,1};
        int[] dy = {1,-1,0,0};
        int size = jq.size();
        if(size==0)
            return -1;
        while(size!=0)
        {
            pair cur = jq.poll();
            size--;

            for(int i=0;i<4;i++)
            {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx==1||ny==1||nx==n||ny==m)
                {
                    if(board[nx][ny]=='.')
                    {
                        return jvisit[cur.x][cur.y]+1;
                    }
                }
                if(nx>=1&&nx<=n&&ny>=1&&ny<=m)
                {
                    if(jvisit[nx][ny]==0&&board[nx][ny]=='.')
                    {
                        jvisit[nx][ny]=jvisit[cur.x][cur.y]+1;
                        board[nx][ny]='J';
                        jq.add(new pair(nx,ny));
                    }
                }
            }
        }
        return 0;
    }
    static void bfs2()
    {
        int[] dx ={0,0,-1,1};
        int[] dy = {1,-1,0,0};
        int size = fq.size();
        while(size!=0)
        {
            pair cur = fq.poll();
            size=size-1;
            for(int i=0;i<4;i++)
            {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx>=1&&nx<=n&&ny>=1&&ny<=m)
                {
                    if(fvisit[nx][ny]==0&&board[nx][ny]=='.')
                    {
                        fvisit[nx][ny]=1;
                        board[nx][ny]='F';
                        fq.add(new pair(nx,ny));
                    }
                }
            }
        }
    }
}
class pair{
    int x;
    int y;
    pair(int x,int y)
    {
        this.x =x;
        this.y =y;
    }
}