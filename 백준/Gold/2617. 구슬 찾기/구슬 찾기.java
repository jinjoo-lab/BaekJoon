import java.io.*;
import java.util.*;

public class Main {
    static int n= 0;
    static int m= 0;

    static boolean[] visit;

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        boolean[][] big_a = new boolean[n+1][n+1];
        boolean[][] small_a = new boolean[n+1][n+1];

        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");

            int big = Integer.parseInt(st.nextToken());
            int small = Integer.parseInt(st.nextToken());

            big_a[small][big] = true;
            small_a[big][small] = true;
        }

        int count = 0;
        for(int i=1; i<= n; i++) {
            visit = new boolean[n + 1];
            visit[i] = true;
            result = 0;
            search(i,big_a);
            int tmp1 = result;

            visit = new boolean[n + 1];
            visit[i] = true;
            result = 0;
            search(i,small_a);

            int tmp2 = result;

            if(tmp1 > (n/2) || tmp2 > (n/2))
            {
                count = count + 1;
            }
        }

        System.out.println(count);
        br.close();
    }

    static void search(int start,boolean[][] big_a)
    {
        for(int i=1;i<=n;i++)
        {
            if(big_a[start][i])
            {
                if(!visit[i])
                {
                    visit[i] = true;
                    result = result + 1;
                    search(i,big_a);
                }
            }
        }
    }

}
