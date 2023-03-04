import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] tmp = new int[51];
    static int[] num = new int[51];
    static boolean[] visit = new boolean[51];
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i=0;i<n;i++)
        {
            int cur = Integer.parseInt(br.readLine());
            tmp[i] = cur;
        }
        int idx = n;
        Arrays.sort(tmp,0,n);

        for(int i=0;i<n;i++)
        {
            num[i+1]  = tmp[i];
        }

        for(int i=1;i<n;i++) {
            if (!visit[i]) {
                visit[i] = true;
                if(num[i]<0)
                {
                    if(num[i+1]<=0)
                    {
                        visit[i+1] = true;
                        result += num[i]*num[i+1];
                    }

                    else if(num[i+1]>0)
                    {
                        result +=num[i];
                    }
                }

                else if(num[i]==0||num[i]==1)
                {
                    result += num[i];
                }

                else if(num[i]>1)
                {
                    idx = i;
                    visit[idx] = false;
                    break;
                }
            }
        }

        for(int i=n;i>=idx;i--)
        {
            if(!visit[i])
            {
                visit[i] = true;

                if(n==1)
                {
                    result +=num[i];
                    break;
                }

                if(i-1>=1&&!visit[i-1])
                {
                    result += num[i]*num[i-1];
                    visit[i-1] = true;
                }

                else{
                    result += num[i];
                }
            }
           // System.out.println(result);
        }

        System.out.println(result);
    }

    static void print()
    {
        for(int i=0;i<n;i++)
        {
            System.out.print(tmp[i]+" ");
        }
        System.out.println();
    }
}