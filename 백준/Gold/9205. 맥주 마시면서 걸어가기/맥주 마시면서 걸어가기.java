import java.io.*;
import java.util.*;

public class Main {
    static int k = 0;
    static int n = 0;

    static int sx = 0;
    static int sy = 0;
    static int lx = 0;
    static int ly = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        k = Integer.parseInt(br.readLine());
        for(int a = 1;a<=k;a++) {

            n = Integer.parseInt(br.readLine());

            ArrayList<point> arr = new ArrayList<>();


            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            sx = Integer.parseInt(st.nextToken());
            sy = Integer.parseInt(st.nextToken());

            arr.add(new point(sx,sy));

            for(int i=1;i<=n;i++)
            {
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                arr.add(new point(x,y));
            }

            st = new StringTokenizer(br.readLine(), " ");
            lx = Integer.parseInt(st.nextToken());
            ly = Integer.parseInt(st.nextToken());

            arr.add(new point(lx,ly));

            ArrayList<Integer>[] board = new ArrayList[n+3];

            for(int i=0;i<=n+2;i++)
            {
                board[i] = new ArrayList<>();
            }

            for(int i=0;i<n+1;i++)
            {
                for(int j=i+1;j<=n+1;j++)
                {
                    if(dis(arr.get(i) , arr.get(j)) <= 1000)
                    {
                        board[i].add(j);
                        board[j].add(i);
                    }
                }
            }

            sb.append(bfs(board)+"\n");
        }
        System.out.print(sb);
        br.close();
    }
    static String bfs(ArrayList<Integer>[] board)
    {
        boolean[] visit = new boolean[n+3];
        visit[0] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);

        while(!q.isEmpty())
        {
            int cur = q.poll();

            if(cur==n+1)
                return "happy";


            for(Integer next : board[cur])
            {
                if(!visit[next])
                {
                    visit[next] = true;
                    q.add(next);
                }
            }
        }

        return "sad";
    }

    static int dis(point a,point b)
    {
        int x = Math.abs(Math.max(a.x,b.x) - Math.min(a.x,b.x));
        int y = Math.abs(Math.max(a.y,b.y) - Math.min(a.y,b.y));

        return (x + y);
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