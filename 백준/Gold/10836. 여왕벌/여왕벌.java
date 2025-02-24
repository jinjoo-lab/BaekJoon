import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int[][] board;

    static int[] all;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];

        for(int i=1;i<=n;i++)
        {
            for(int j=1; j<=n;j++)
            {
                board[i][j] = 1;
            }
        }

        int[] plus = new int[2*n + 1];
        all = new int[n];

        for(int i=1;i<=m;i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int idx = 1;

            int z = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            for (int j = 1; j <= z; j++) {
                plus[idx] += 0;
                idx = idx + 1;
            }

            for (int j = 1; j <= o; j++) {
                plus[idx] += 1;
                idx = idx + 1;
            }

            for (int j = 1; j <= t; j++) {
                plus[idx] += 2;
                idx = idx + 1;
            }
        }
        inIt(plus);
        plus(plus);

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                if(i != 1 && j != 1)
                     board[i][j] += all[j-1];
                sb.append(board[i][j]+" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void inIt(int[] plus)
    {
        int idx = 1;
        for(int i=n;i>=1;i--)
        {
            board[i][1] += plus[idx];
            idx = idx + 1;
        }

        for(int j=2;j<=n;j++)
        {
            board[1][j] += plus[idx];
            idx = idx + 1;
        }
    }

    static void plus(int[] plus)
    {
        int[] second = new int[n];

         second[1] = Math.max(plus[n-1], Math.max(plus[n], plus[n+1]));
         all[1] += second[1];

        for(int i=2;i<n;i++)
        {
            second[i] = Math.max(second[i-1],plus[n+i]);
            all[i] += second[i];
        }
    }
}
