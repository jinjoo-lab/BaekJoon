
import java.io.*;
import java.util.*;
public class Main {
    static int n = 0;
    static int[] board;

    static long result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        board = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<n;i++)
        {
            board[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(board);

        bs();
        System.out.println(result);
        br.close();
    }
    static int change(int num1)
    {
        return num1 * -1;
    }
    static void bs()
    {
        for(int i=0;i<n-1;i++)
        {
            for(int j=i+1;j<n;j++)
            {
                int idx1 = lower(j+1,n-1,change(board[i]+board[j]));
                int idx2 = upper(j+1,n-1,change(board[i]+board[j]));
                result += (idx2 - idx1);
            }
        }

    }

    static int lower(int left,int right,int target)
    {
        while(left <= right)
        {
            int mid = (left + right) / 2;

            if(board[mid]<target)
            {
                left = mid + 1;
            }

            else{
                right = mid - 1;
            }
        }

        return left;
    }

    static int upper(int left,int right,int target)
    {
        while(left <= right)
        {
            int mid = (left + right) /2;

            if(board[mid] <= target)
            {
                left = mid + 1;
            }

            else{
                right = mid -1;
            }
        }

        return left;
    }
}
