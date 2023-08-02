import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int[] sarr;
    static int[] last;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        sarr = new int[n+1];
        last = new int[n+1];
        int[] dif = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i=1;i<=n;i++)
        {
            sarr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine()," ");
        for(int i=1;i<=n;i++)
        {
            last[i] = Integer.parseInt(st.nextToken());

            dif[i] = last[i] - sarr[i];
        }

        int result = 0;
        boolean keep = true;
        while(keep)
        {
            int over = over(dif[1]);
            int plus = Math.abs(dif[1]);
            int past = 1;

            for(int i=2;i<=n;i++)
            {
                int tover  = over(dif[i]);

                if(tover == over)
                {
                    plus = Math.min(plus, Math.abs(dif[i]));
                }

                else{
                    result += plus;
                    for(int j = past; j<=i-1;j++)
                    {
                        if(over == 1)
                            dif[j] -=plus;

                        else if(over == -1)
                            dif[j] +=plus;
                    }
                    plus = Math.abs(dif[i]);
                    over = tover;
                    past = i;
                }
            }
            for(int j= past;j<=n;j++)
            {
                if(over == 1)
                    dif[j] -=plus;

                else if(over == -1)
                    dif[j] +=plus;
            }
            result += plus;
            keep = keeping(dif);
        }

        System.out.println(result);

        br.close();
    }
    static void print(int[] dif)
    {
        for(int i=1;i<=n;i++)
        {
            System.out.print(dif[i]+" ");
        }
        System.out.println();
    }

    static boolean keeping(int[] dif)
    {
        for(int i=1;i<=n;i++)
        {
            if(dif[i] !=0)
                return true;
        }

        return false;
    }
    static int over(int cur)
    {
        if(cur > 0)
            return 1;

        else if(cur == 0)
            return 0;

        else
            return -1;
    }
}