import java.util.*;
import java.io.*;

public class Main {
    static int[][] board;
    static int n = 0;
    static int[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n+1][n+1];
        for(int i=1;i<=n;i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");

            for(int j=1;j<=n;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int re = Integer.MAX_VALUE;

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                re = Math.min(re,cal(i,j));
            }
        }

        System.out.println(re);

        br.close();
    }

    static int cal(int x,int y)
    {
        int minus = Integer.MAX_VALUE;

        for(int d1 = 1;d1<=n;d1++)
        {
            for(int d2=1;d2<=n;d2++)
            {
                if(x+d1+d2 > x && x+d1+d2 <=n && y- d1>=1 && y + d2 <=n)
                {
                    visit = new int[n+1][n+1];
                    five(x,y,d1,d2);
                    first(x,y,d1);
                    second(x,y,d2);
                    third(x,y,d1,d2);
                    four(x,y,d1,d2);

                    minus = Math.min(minus, sum());
                }
            }
        }

        return minus;
    }
    static int sum()
    {
        int[] sum = new int[6];

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                if(visit[i][j]==0 || visit[i][j] == 5)
                    sum[5] += board[i][j];

                else{
                    sum[visit[i][j]] += board[i][j];
                }
            }
        }

        int max = Math.max(Math.max(Math.max(sum[1],sum[2]),Math.max(sum[3],sum[4])),sum[5]);
        int min = Math.min(Math.min(Math.min(sum[1],sum[2]),Math.min(sum[3],sum[4])),sum[5]);

        return max - min;
    }

    static void print()
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                System.out.print(visit[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void first(int x,int y,int d1)
    {
        for(int i=1;i<x + d1;i++)
        {
            for(int j=1;j<=y;j++)
            {
                if(visit[i][j] == 5)
                    break;

                visit[i][j] = 1;
            }
        }
    }

    static void second(int x,int y,int d2)
    {

        for(int i=1;i<=x+d2;i++)
        {
            for(int j=n;j>y;j--)
            {
                if(visit[i][j] == 5)
                    break;
                visit[i][j] = 2;
            }
        }
    }

    static void third(int x,int y,int d1,int d2)
    {

        for(int i=x+d1;i<=n;i++)
        {
            for(int j=1;j<y-d1+d2;j++)
            {
                if(visit[i][j] == 5)
                    break;

                visit[i][j] = 3;
            }
        }

    }

    static void four(int x,int y,int d1,int d2)
    {
        for(int i=x+d2+1;i<=n;i++)
        {
            for(int j=n;j>=y-d1+d2;j--)
            {
                if(visit[i][j] == 5)
                    break;
                visit[i][j] = 4;
            }
        }
    }

    static void five(int x,int y,int d1,int d2)
    {
        visit[x][y] = 5;

        for(int i=1;i<=d1;i++)
        {
            visit[x+i][y-i] = 5;
        }

        for(int j=1;j<=d2;j++)
        {
            visit[x+j][y+j] = 5;
        }

        for(int i=1;i<=d2;i++)
        {
            visit[x+d1+i][y-d1+i] = 5;
        }

        for(int i=1;i<=d1;i++)
        {
            visit[x+d2+i][y+d2-i] = 5;
        }
    }

}