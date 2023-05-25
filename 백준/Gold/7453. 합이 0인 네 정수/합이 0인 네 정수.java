import java.io.*;
import java.util.*;
public class Main {
    static int n = 0;
    static int[][] board;

    static long result = 0 ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        board = new int[4][n];
        for(int i=0;i<n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            board[0][i] = Integer.parseInt(st.nextToken());
            board[1][i] = Integer.parseInt(st.nextToken());
            board[2][i] = Integer.parseInt(st.nextToken());
            board[3][i] = Integer.parseInt(st.nextToken());
        }

        int[] first = new int[n*n + 3];
        int[] second = new int[n*n + 3];

        int idx = 0 ;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                first[idx] = board[0][i] + board[1][j];
                second[idx] = board[2][i] + board[3][j];
                idx = idx + 1;
            }
        }

        Arrays.sort(first,0,idx);
        Arrays.sort(second, 0,idx);
        search(idx,first,second);
        System.out.println(result);
        br.close();
    }

    static void search(int end,int[] first, int[] second)
    {
        int left = 0;
        int right = end -1;

        while(left < end && right >=0)
        {
            int f = first[left];
            int s = second[right];

            int sum = f + s;
            if(sum==0)
            {
                int fc = 1;
                int sc = 1;

                while(left < end-1 && first[left] == first[left+1])
                {
                    left = left + 1;
                    fc = fc + 1;
                }

                while(right > 0 && second[right] == second[right -1])
                {
                    right = right -1;
                    sc = sc + 1;
                }

                result += (long)((long)fc * (long)sc);
            }

            if(sum < 0)
            {
                left = left + 1;
            }

            else{
                right = right - 1;
            }
        }
    }
}