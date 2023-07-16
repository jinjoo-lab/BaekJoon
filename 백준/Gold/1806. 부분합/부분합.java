import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static long m = 0;
    static long[] board;
    static long[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Long.parseLong(st.nextToken());
        board = new long[n+1];
        sum = new long[n+1];
        st = new StringTokenizer(br.readLine());

        for(int i=1;i<=n;i++)
        {
            board[i] = Long.parseLong(st.nextToken());
            sum[i] =  sum[i-1]+ board[i];
        }

        ts();
        br.close();
    }

    static void ts()
    {
        int r = 1;
        int len = 100001;

        for(int l=1;l<=n;l++)
        {
            while(r <= n)
            {
                long tmp = sum[r] - sum[l] + board[l];

                if(tmp >= m)
                {
                    len = Math.min(len,r - l + 1);
                    break;
                }

                else{
                    r = r + 1;
                }
            }
        }

        if(len == 100001)
            System.out.println(0);
        else
            System.out.println(len);
    }
}