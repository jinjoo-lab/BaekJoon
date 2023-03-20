
import java.io.*;
import java.util.*;

public class Main {
    static int[][] board = new int[21][21];
    static int n = 0;
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1;j<=n;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        travel(0,board);
        System.out.println(result);
        br.close();
    }
    static int count(int[][] arr)
    {
        int result = 0;
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                result = Math.max(result,arr[i][j]);
            }
        }

        return result;
    }
    static void travel(int c,int[][] arr)
    {
        if(c==5) {
            int count = count(arr);
            if(result<count)
            {
                result = count;
            }
            return;
        }

        for(int i=0;i<4;i++)
        {
            if(i==0)
            {
                travel(c+1,up(arr));
            }

            else if(i==1)
            {
                travel(c+1,down(arr));
            }

            else if(i==2)
            {
                travel(c+1,right(arr));
            }
            else{
                travel(c+1,left(arr));
            }
        }
    }

    static int[][] up(int[][] cur)
    {
        cur = forceUP(cur);
        for(int j=1;j<=n;j++)
        {
            for(int i=1;i<n;i++) {
                if(cur[i][j]==cur[i+1][j])
                {
                    cur[i][j] = cur[i][j] + cur[i+1][j];
                    cur[i+1][j] = 0;
                }
            }
        }
        cur = forceUP(cur);
        return cur;

    }
    static int[][] forceUP(int[][] cur)
    {
        int[][] re = new int[21][21];
        for(int j=1;j<=n;j++)
        {
            int idx = 1;
            for(int i=1;i<=n;i++)
            {
                if(cur[i][j]!=0)
                {
                    re[idx][j] = cur[i][j];
                    idx = idx +1;
                }
            }
        }

        return re;
    }
    static int[][] forceDown(int[][] cur)
    {
        int[][] re = new int[21][21];

        for(int j=1;j<=n;j++)
        {
            int idx = n;
            for(int i=n;i>=1;i--)
            {
                if(cur[i][j]!=0)
                {
                    re[idx][j] = cur[i][j];
                    idx = idx-1;
                }
            }
        }

        return re;
    }
    static int[][] down(int[][] cur)
    {
        cur = forceDown(cur);
        for(int j=1;j<=n;j++)
        {
            for(int i=n;i>1;i--)
            {
                if(cur[i][j]==cur[i-1][j])
                {
                    cur[i][j] = cur[i][j]+cur[i-1][j];
                    cur[i-1][j] = 0;
                }
            }
        }
        cur = forceDown(cur);
        return cur;
    }
    static int[][] forceRight(int[][] cur)
    {
        int[][] re = new int[21][21];
        for(int i=1;i<=n;i++)
        {
            int idx = n;
            for(int j=n;j>=1;j--)
            {
                if(cur[i][j]!=0)
                {
                    re[i][idx] = cur[i][j];
                    idx = idx -1;
                }
            }
        }


        return re;
    }
    static int[][] right(int[][] cur)
    {
        cur = forceRight(cur);
        for(int i=1;i<=n;i++)
        {
            for(int j=n;j>1;j--)
            {
                if(cur[i][j]==cur[i][j-1])
                {
                    cur[i][j] = cur[i][j]+cur[i][j-1];
                    cur[i][j-1] = 0;
                }
            }
        }
        cur = forceRight(cur);

        return cur;
    }

    static int[][] forceLeft(int[][] cur)
    {
        int[][] re = new int[21][21];
        for(int i=1;i<=n;i++)
        {
            int idx = 1;
            for(int j=1;j<=n;j++)
            {
                if(cur[i][j]!=0)
                {
                    re[i][idx] = cur[i][j];
                    idx = idx+1;
                }
            }
        }


        return re;
    }

    static int[][] left(int[][] cur)
    {
        cur = forceLeft(cur);

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<n;j++)
            {
                if(cur[i][j]==cur[i][j+1])
                {
                    cur[i][j] = cur[i][j] + cur[i][j+1];
                    cur[i][j+1] = 0;
                }
            }
        }

        cur = forceLeft(cur);
        return cur;
    }
}
