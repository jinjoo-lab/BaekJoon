import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] coin = new int[n+1];

        for(int i=1;i<=n;i++)
        {
            coin[i] = Integer.parseInt(br.readLine());
        }

        int rest = m;
        long count = 0;

        for(int i = n;i >=1;i--)
        {
            if(rest >= coin[i])
            {
                count += rest / coin[i];
                rest = rest % coin[i];
            }
        }

        System.out.println(count);

        br.close();
    }
}