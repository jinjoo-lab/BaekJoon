import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int l = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][2];
        for(int i=0;i<n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            board[i][0] = start;
            board[i][1] = end;
        }

        Arrays.sort(board,(o1,o2) ->
        {
           if(o1[0]==o2[0])
               return o1[1] - o2[1];
           else
               return o1[0] - o2[0];
        });

        int cur = 0; // 현재 지점
        int count = 0; // 널빤지 개수

        for(int i=0;i<n;i++)
        {
            if(board[i][0] > cur)
            {
                cur = board[i][0];
            }

            if(board[i][1] >= cur)
            {
                while(board[i][1] > cur)
                {
                    cur += l;
                    count = count + 1;
                }
            }
        }

        System.out.println(count);
        br.close();
    }

    static void print(int[][] board)
    {
        for(int i=0;i<n;i++)
        {
            System.out.println(board[i][0]+" "+board[i][1]);
        }
    }
}