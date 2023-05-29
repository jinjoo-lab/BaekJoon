
import java.io.*;
import java.util.*;
public class Main {
    static int n = 0;
    static int jin = 0;
    static int[] score = new int[17];
    static int[][] board = new int[17][17];

    static boolean[] visit = new boolean[17];

    static int re = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=n;i++)
        {
            score[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1;j<=n;j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine(), " ");
        jin = Integer.parseInt(st.nextToken()) + 1;

        int start = 0;
        if(n%2==0)
            start = 1;

        game(start,n,n%2);

        System.out.println(re);
        br.close();
    }
    static int day()
    {
        int idx = 0;
        int result = 0;
        for(int i=1;i<=n;i++)
        {
            if(!visit[i])
            {
                if(result < score[i])
                {
                    result = score[i];
                    idx =i;
                }
            }
        }

        return idx;
    }

    static void game(int cur,int rest,int day)
    {
        if(rest==1&&!visit[jin])
        {
            re = Math.max(re,cur);
            return;
        }

        if(visit[jin])
        {
            re = Math.max(re,cur-1);
            return;
        }

        if(day==1)
        {
            int result = day();
            visit[result] = true;
            game(cur+1,rest-1,2);
            visit[result] = false;
        }

        else{
            for(int i=1;i<=n;i++)
            {
                if(i==jin)
                    continue;

                if(!visit[i])
                {
                    visit[i] = true;
                    scoreUp(i);
                    game(cur,rest-1,1);
                    visit[i] = false;
                    scoreDown(i);
                }
            }
        }

    }

    static void scoreUp(int cur)
    {
        for(int i=1;i<=n;i++)
        {
            if(i==cur)
                continue;

            score[i] += board[cur][i];
        }
    }

    static void scoreDown(int cur)
    {
        for(int i=1;i<=n;i++)
        {
            if(i==cur)
                continue;

            score[i] -= board[cur][i];
        }
    }
}
