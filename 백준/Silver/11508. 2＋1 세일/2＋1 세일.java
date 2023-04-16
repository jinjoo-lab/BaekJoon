import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static Integer[] board = new Integer[100010];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        for(int i=1;i<=n;i++)
        {
            board[i] = Integer.parseInt(br.readLine());
        }
        if(n==1||n==2)
        {
            System.out.println(board[1]+ board[2]);
        }

        else {
            int result = 0;
            Arrays.sort(board, 1, n + 1, Collections.reverseOrder());
            for(int i=1;i<=n;i++)
            {
                if(i%3==0)
                    continue;

                result +=board[i];
            }
            System.out.println(result);
        }
        br.close();
    }
}