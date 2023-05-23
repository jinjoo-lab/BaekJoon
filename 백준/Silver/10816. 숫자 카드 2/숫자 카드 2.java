
import java.io.*;
import java.util.*;

/*
*   뱍준 10815 (숫자 카드)
*   이분 탐색
* */
public class Main {
    static int n = 0;
    static int m = 0;
    static int[] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuffer sb = new StringBuffer();
        n = Integer.parseInt(st.nextToken());
        board = new int[n];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++)
        {
            board[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(board);

        st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<m;i++)
        {
            int num = Integer.parseInt(st.nextToken());
            int low = low(0,n-1,num);
            int high = high(0,n-1,num);

            sb.append((high - low) + " ");
        }sb.append("\n");
        System.out.println(sb);
        br.close();
    }

    static int high(int left,int right,int target)
    {
        int mid = -1;
        while(left <= right)
        {
            mid = (left + right) / 2;

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

    static int low(int left, int right , int target)
    {
        int mid = -1;
        while(left <= right)
        {
            mid = (left + right) / 2;

            if(board[mid] < target)
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
