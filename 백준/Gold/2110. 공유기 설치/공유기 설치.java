/*
*   백준 2110 (공유기 설치)
*   이분 탐색 : 기준이 뭘까?
*   기준 : 거리
* */
import java.io.*;
import java.util.*;
public class Main {
    static int n = 0;
    static int m = 0;

    static int result = 0;
    static int[] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        board = new int[n];
        m = Integer.parseInt(st.nextToken());
        for(int i=0;i<n;i++)
        {
            board[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(board);
        bs(1,board[n-1] - board[0]);
        System.out.println(result);
        br.close();
    }

    static void bs(int left,int right)
    {
        while(left <= right)
        {
            int mid = (left + right) / 2;

            int count = 1;
            int idx = 0;
            for(int i=1;i<n;i++)
            {
                if(board[i] >= board[idx] + mid)
                {
                    count = count +1;
                    idx = i;
                }

                if(count==m)
                    break;
            }

            if(count >= m)
            {
                if(result < mid)
                    result = mid;
                left = mid + 1;
            }

            else{
                right = mid -1;
            }
        }


    }
}