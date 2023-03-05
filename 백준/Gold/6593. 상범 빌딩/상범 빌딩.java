import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {1,-1,0,0,0,0};
    static int[] dy = {0,0,1,-1,0,0};
    static int[] dz = {0,0,0,0,1,-1};
    static int[][][] board = new int[31][31][31];
    static point start;
    static point last;

    static int l = 0;
    static int r = 0;
    static int c = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ArrayList<String> resultList = new ArrayList<>();
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
             l = Integer.parseInt(st.nextToken());
             r = Integer.parseInt(st.nextToken());
             c = Integer.parseInt(st.nextToken());

            if(l==0&&r==0&&c==0)
                break;
            board = new int[31][31][31];
            for(int i=1;i<=l;i++)
            {
                for(int j=1;j<=r;j++)
                {
                    String[] line = br.readLine().split("");
                    for(int k=1;k<=c;k++)
                    {
                        if(line[k-1].equals("S"))
                        {
                            board[i][j][k] = 1;
                            start = new point(i,j,k);
                        }

                        else if(line[k-1].equals("#"))
                        {
                            board[i][j][k] = -1;
                        }

                        else if(line[k-1].equals("E"))
                        {
                            board[i][j][k] = 2;
                            last = new point(i,j,k);
                        }
                    }
                }
                br.readLine();
            }
            int result = bfs();

            if(result==-1)
            {
                resultList.add("Trapped!\n");
            }
            else{
                resultList.add("Escaped in "+(result-1)+" minute(s).\n");
            }
        }
        for(String re : resultList)
        {
            bw.write(re);
        }

        bw.flush();
        bw.close();
        br.close();
    }
    static int bfs()
    {
        int[][][] visit = new int[31][31][31];
        Queue<point> q= new LinkedList<>();
        visit[start.x][start.y][start.z] = 1;
        q.add(start);

        while(!q.isEmpty())
        {
            point cur = q.poll();

            for(int i=0;i<6;i++)
            {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nz = cur.z + dz[i];

                if(nx>=1&&nx<=l&&ny>=1&&ny<=r&&nz>=1&&nz<=c)
                {
                    if(board[nx][ny][nz]==2)
                    {
                        q.clear();
                        return visit[cur.x][cur.y][cur.z] + 1;
                    }

                    if(visit[nx][ny][nz]==0&&board[nx][ny][nz]==0)
                    {
                        visit[nx][ny][nz] = visit[cur.x][cur.y][cur.z] + 1;
                        q.add(new point(nx,ny,nz));
                    }
                }
            }
        }

        return -1;
    }
}
class point{
    int x;
    int y;

    int z;
    point(int x,int y,int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}