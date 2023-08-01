import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int[] num;

    static ArrayList<Integer>[] graph;

    static boolean[] visit;

    static int[] color;

    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        num = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i=1;i<=n;i++)
        {
            num[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList[n+1];
        visit = new boolean[n+1];
        color = new int[n+1];
        for(int i=1;i<=n;i++)
        {
            graph[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine()," ");
            int num = Integer.parseInt(st.nextToken());

            for(int j=1;j<=num;j++)
            {
                graph[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        go(1,0,0);

        if(result == Integer.MAX_VALUE)
        {
            System.out.println(-1);
        }
        else{
            System.out.println(result);
        }
        br.close();
    }

    static void go(int start,int r,int b)
    {
        if(r>=n || b >=n)
            return;

        if(r+b == n)
        {
            int tmp = search();
            if(tmp != -1)
                result = Math.min(result,tmp);
            return;
        }

        for(int i=start;i<=n;i++)
        {
            if(!visit[i])
            {
                visit[i] = true;
                color[i] = 1;
                go(i+1,r+1,b);
                color[i] = 2;
                go(i+1,r,b+1);
                color[i] = 0;
                visit[i] = false;
            }
        }
    }

    static void printColor() {
        for (int i = 1; i <= n; i++) {
            System.out.print(color[i] + " ");
        }
        System.out.println();
    }

    static int search()
    {
        Queue<Integer> qr = new LinkedList<>();
        Queue<Integer> qb = new LinkedList<>();

        boolean[] v = new boolean[n+1];

        int rsum = 0;
        int bsum = 0;
        for(int i=1;i<=n;i++)
        {
            if(color[i] == 1)
            {
                qr.add(i);
                v[i] = true;
                rsum += num[i];
                break;
            }
        }

        for(int i=1;i<=n;i++)
        {
            if(color[i] == 2)
            {
                qb.add(i);
                v[i] = true;
                bsum += num[i];
                break;
            }
        }

        while(!qr.isEmpty())
        {
            Integer cur = qr.poll();

            for(int next : graph[cur])
            {
                if(!v[next] && color[next] == 1)
                {
                    v[next] = true;
                    rsum += num[next];
                    qr.add(next);
                }
            }
        }

        while(!qb.isEmpty())
        {
            Integer cur = qb.poll();

            if(!v[cur])
            {
                v[cur] = true;
                bsum += num[cur];
            }

            for(int next : graph[cur])
            {
                if(!v[next] && color[next] == 2)
                {
                    v[next] = true;
                    bsum += num[next];
                    qb.add(next);
                }
            }
        }

        for(int i=1;i<=n;i++)
        {
            if(!v[i])
            {
                return -1;
            }
        }

        return Math.abs(rsum - bsum);
    }

}
