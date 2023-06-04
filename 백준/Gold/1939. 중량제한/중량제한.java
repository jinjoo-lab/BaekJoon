import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int result = 0;
    static ArrayList<point>[] board = new ArrayList[10001];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int max = 0;
        for(int i=0;i<=n;i++)
            board[i] = new ArrayList<>();

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            max = Math.max(max, c);
            board[a].add(new point(b,c));
            board[b].add(new point(a,c));
        }

        st = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        bs(1,max,start,end);
        System.out.println(result);
    }

    static void bs(int left,int right,int start,int end)
    {
        while(left <= right)
        {
            int mid = (left + right) / 2;
            boolean tmp = search(mid ,start,end);

            if(tmp)
            {
                result = Math.max(result , mid);
                left = mid + 1;
            }

            else{
                right = mid - 1;
            }
        }
    }

    static boolean search(int w,int s,int e)
    {
        boolean visit[] = new boolean[n+1];
        Queue<point> queue = new LinkedList<>();
        queue.add(new point(s,0));
        visit[s] = true;

        while(!queue.isEmpty())
        {
            point cur = queue.poll();
            if(cur.d==e)
                return true;

            for(int i=0;i<board[cur.d].size();i++)
            {
                point next = board[cur.d].get(i);
                if(!visit[next.d]&&next.w >= w)
                {
                    visit[next.d] = true;
                    queue.add(new point(next.d, 0));
                }
            }
        }

        return false;
    }

}
class point
{
    int d;
    int w;

    point(int d,int w)
    {
        this.d = d;
        this.w = w;
    }
}