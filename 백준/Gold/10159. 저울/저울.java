import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static boolean[][] big = new boolean[101][101];
    static boolean[][] small = new boolean[101][101];

    static boolean[] visit =new boolean[101];

    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            big[x][y] = true;
            small[y][x] = true;
        }

        visit[1] = true;
        for(int i=1;i<=n;i++) {
            search(i);
            search2(i);
            System.out.println(n -1 -result);
            visit = new boolean[101];
            result = 0;
        }
    }
    
    static void search(int k)
    {
        for(int i=1;i<=n;i++)
        {
            if(k==i)
                continue;

            if(!visit[i])
            {
                if(big[k][i])
                {
                    visit[i] = true;
                    result++;
                    search(i);
                }
            }
        }
    }

    static void search2(int k)
    {
        for(int i=1;i<=n;i++)
        {
            if(k==i)
                continue;

            if(!visit[i])
            {
                if(small[k][i])
                {
                    visit[i] = true;
                    result++;
                    search2(i);
                }
            }
        }
    }
}