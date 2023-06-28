import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        root = new int[n+1];
        for(int i=0;i<=n;i++)
        {
            root[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int cal = Integer.parseInt(st.nextToken());
            int x= Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(cal==0)
            {
                if(find(x) != find(y))
                {
                    union(x,y);
                }
            }

            else{
                if(find(x) != find(y))
                {
                    sb.append("NO\n");
                }

                else{
                    sb.append("YES\n");
                }
            }
        }

        System.out.print(sb.toString());
        br.close();
    }

    static int find(int x)
    {
        if(root[x] == x)
            return x;

        else
            return root[x] = find(root[x]);
    }

    static void union(int x,int y)
    {
        x = find(x);
        y = find(y);

        if(x < y)
            root[y] = x;
        else
            root[x] = y;
    }
}