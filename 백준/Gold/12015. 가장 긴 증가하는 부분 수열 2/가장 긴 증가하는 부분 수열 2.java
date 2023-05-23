
import java.io.*;
import java.util.*;
public class Main {
    static int n = 0;
    static int m = 0;
    static int[] board;
    static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        board = new int[n];
        result = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<n;i++)
        {
            board[i] = Integer.parseInt(st.nextToken());
        }
        int i = 1;
        int j = 0;

        result[0] = board[0];

        while(i < n)
        {
            if(board[i] > result[j])
            {
                result[j+1] = board[i];
                j = j + 1;
            }

            else{
                int tmp = bs(0,j,board[i]);
                result[tmp] = board[i];
            }

            i = i + 1;
        }

        System.out.println(j+1);
        br.close();
    }
    static void print()
    {
        for(int i=0;i<n;i++)
        {
            System.out.print(result[i]+" ");
        }
        System.out.println();
    }

    static int bs(int left,int right,int target)
    {
        int mid = 0;
        while(left < right)
        {
            mid = (left + right) / 2;

            if(result[mid] < target)
            {
                left = mid + 1;
            }

            else{
                right = mid;
            }
        }

        return right;
    }

}