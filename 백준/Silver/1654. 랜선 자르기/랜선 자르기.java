import java.io.*;
import java.util.*;
public class Main {
    static int n = 0;
    static long m = 0;

    static long result = 0;
    static long[] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        board = new long[n];
        m = Long.parseLong(st.nextToken());
        for(int i=0;i<n;i++)
        {
            board[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(board);
        bs(1,board[n-1]);
        System.out.println(result);
        br.close();
    }

    static void bs(long left,long right)
    {
        while(left <= right)
        {
            long mid = (left + right) / 2;
            long count = 0;
            for(int i=0;i<n;i++)
            {
                long num = board[i] / mid;
                count += num;
            }

            if(count>=m)
            {
                if(result<mid)
                {
                    result = mid;
                }

                left = mid+1;
            }

            else{
                right = mid -1;
            }
        }


    }
}
