import java.io.*;
import java.util.*;

public class Main {
    static int k = 0;
    static int RED = 1;
    static int BLUE = 2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        k = Integer.parseInt(st.nextToken());

        for(int i=1;i<=k;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            ArrayList<Integer>[] list = new ArrayList[n+1];
            for(int j=0;j<=n;j++)
            {
                list[j] = new ArrayList<>();
            }

            for(int j=1;j<=m;j++)
            {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                list[a].add(b);
                list[b].add(a);
            }
            int[] visit = new int[n+1];

            boolean tmp = true;
            for(int j=1;j<=n;j++) {
                if(visit[j]==0) {
                    tmp = bfs(list, visit, j);

                    if(!tmp)
                    {
                        System.out.println("NO");
                        break;
                    }
                }
            }
            if(tmp)
            {
                System.out.println("YES");
            }
        }
        br.close();
    }
    // red = 1
    // blue = 2
    static boolean bfs(ArrayList<Integer>[] list,int[] visit,int start)
    {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = RED;

        while(!q.isEmpty())
        {
            int cur = q.poll();
            int color = 0;

            if(visit[cur]==RED)
            {
                color = BLUE;
            }
            else{
                color = RED;
            }

            for(int i=0;i<list[cur].size();i++)
            {
                int tmp = list[cur].get(i);

                if(visit[tmp]==visit[cur])
                    return false;

                if(visit[tmp]==0)
                {
                    visit[tmp] = color;
                    q.add(tmp);
                }
            }
        }

       return true;
    }
}