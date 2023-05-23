
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
            int tmp = bs(0,n-1,num);
            sb.append(tmp+" ");
        }
        sb.append("\n");

        System.out.println(sb.toString());
        br.close();
    }

    static int bs(int left,int right,int target)
    {
        while(left <= right)
        {
            int mid = (left + right) / 2;

            if(board[mid]==target)
                return 1;

            else if(board[mid] > target)
            {
                right = mid - 1;
            }

            else{
                left = mid  + 1;
            }
        }

        return 0;
    }
}
