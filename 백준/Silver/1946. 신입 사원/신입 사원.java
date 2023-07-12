import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=n;i++)
        {
            m = Integer.parseInt(br.readLine());
            int[][] board = new int[m][2];

            for(int j=0;j<m;j++)
            {
                st = new StringTokenizer(br.readLine(), " ");
                board[j][0] = Integer.parseInt(st.nextToken());
                board[j][1] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(board, (x,y) ->
                x[0] - y[0]);

            int count = 1;
            int mb = board[0][1];

            for(int j=1;j<m;j++)
            {
               if(mb > board[j][1])
               {
                   count = count + 1;
               }
               mb = Math.min(mb,board[j][1]);
            }
            sb.append(count + "\n");
        }
        System.out.print(sb);
        br.close();
    }
}
