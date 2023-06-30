import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[][] board;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n+1][m+1];

        ArrayList<point> zero = new ArrayList<>();
        for(int i=1;i<=n;i++)
        {
            String line = br.readLine();
            for(int j=1;j<=m;j++)
            {
                board[i][j] = Integer.parseInt(line.charAt(j-1)+"");

                if(board[i][j]==0)
                    zero.add(new point(i,j));
            }
        }

        int[][] visit = new int[n+1][m+1];

        int idx = 1;
        HashMap<Integer,Integer> map = new HashMap<>();

        for(point cur : zero)
        {
            if(visit[cur.x][cur.y]==0)
            {
                int count = bfs(cur.x,cur.y,board,visit,idx);
                map.put(idx,count);
                idx = idx + 1;
            }
        }

        int[][] result = new int[n+1][m+1];

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                if(board[i][j]==0)
                    result[i][j] = 0;

                else{
                    HashSet<Integer> numbers = new HashSet<>();
                    for(int k=0;k<4;k++)
                    {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if(nx < 1|| nx > n|| ny < 1|| ny > m)
                            continue;

                        if(visit[nx][ny]==0)
                            continue;

                        numbers.add(visit[nx][ny]);
                    }

                    result[i][j] = 1;
                    for(int cur : numbers)
                    {
                        result[i][j] += map.get(cur);
                    }
                    
                    result[i][j] = result[i][j] % 10;
                }
            }
        }

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                bw.write(result[i][j]+"");
            }bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }


    static int bfs(int x,int y,int[][] board,int[][] visit,int num)
    {
        visit[x][y] = num;
        int count = 1;
        Queue<point> q = new ArrayDeque<>();
        q.add(new point(x,y));

        while(!q.isEmpty())
        {
            point cur = q.poll();

            for(int i=0;i<4;i++)
            {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1|| nx> n|| ny<1|| ny> m)
                    continue;

                if(visit[nx][ny]!=0)
                    continue;

                if(board[nx][ny]==0)
                {
                    visit[nx][ny] = num;
                    q.add(new point(nx,ny));
                    count = count + 1;
                }
            }
        }

        return count;
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
