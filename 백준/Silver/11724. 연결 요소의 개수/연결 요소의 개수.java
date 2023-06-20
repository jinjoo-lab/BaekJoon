import java.io.*;
import java.util.*;

public class Main {
    static int n= 0;
    static int m= 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        boolean[][] board = new boolean[n+1][n+1];
        boolean[] visit = new boolean[n+1];
        for(int i=1;i<=m;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            board[start][end] = true;
            board[end][start] = true;
        }

        int count = 0;

        for(int i=1;i<=n;i++)
        {
            if(!visit[i])
            {
                bfs(i,board,visit);
                count = count + 1;
            }
        }

        System.out.println(count);
        br.close();
    }

    static void bfs(int start,boolean[][] board,boolean[] visit)
    {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;

        while(!q.isEmpty())
        {
            Integer cur = q.poll();

            for(int i=1;i<=n;i++)
            {
                if(i==cur)
                    continue;

                if(board[i][cur] || board[cur][i])
                {
                    if(visit[i]==false) {
                        visit[i] = true;
                        q.add(i);
                    }
                }
            }
        }
    }

}
