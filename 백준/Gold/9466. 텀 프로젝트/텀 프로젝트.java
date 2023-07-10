import java.io.*;
import java.util.*;

public class Main {

    static int n = 0;
    static int m = 0;

    static int[] visit;
    static boolean[] finish;

    static int result = 0;

    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            m = Integer.parseInt(st.nextToken());
            int[] board = new int[m+1];

            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1;j<=m;j++)
            {
                board[j] = Integer.parseInt(st.nextToken());
            }
            visit = new int[m+1];
            finish = new boolean[m+1];
            count = 0;
            result = 0;

            for(int j=1;j<=m;j++)
            {
                if(visit[j] == 0)
                    search(board,j);
            }

            sb.append((m - result)+"\n");
        }

        System.out.println(sb.toString());
        br.close();
    }

    static void search(int[] board,int cur)
    {
        visit[cur] = count++;

        if(visit[board[cur]] == 0)
            search(board, board[cur]);

        else if(!finish[board[cur]])
        {
            result = result + 1;
            int next = board[cur];
            for(int i = next ; i != cur ; i = board[i])
            {
                result = result + 1;
            }
        }

        finish[cur] = true;
    }

}
