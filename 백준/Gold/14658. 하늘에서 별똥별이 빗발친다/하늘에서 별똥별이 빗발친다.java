import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int l = 0;
    static int k = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int[][] star = new int[k][2];

        for(int i=0;i<k;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            star[i][0] = x;
            star[i][1] = y;

        }

        int max = 0;

        for(int i=0 ; i< k ; i++)
        {
            int x = star[i][0];

            for(int j=0;j<k;j++)
            {
                int y = star[j][1];

                max = Math.max(max,find(x,y,star));
            }
        }

        System.out.println(k - max);

        br.close();
    }

    static int find(int x,int y,int[][] star)
    {
        int result = 0;
        
        for(int[] cur : star)
        {
            if(cur[0] >= x && cur[0] <= x+ l && cur[1] >= y && cur[1] <= y + l)
            {
                result = result + 1;
            }
        }

        return result;
    }
}
