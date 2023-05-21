
import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(st.nextToken());
        int[] board = new int[n];
        st = new StringTokenizer(br.readLine(), " ");

        for(int i=0;i<n;i++)
        {
            board[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(board);
        st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=m;i++)
        {
            int num = Integer.parseInt(st.nextToken());
            bw.write(bsearch(board,0,n-1,num)+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static int bsearch(int[] board , int left,int right,int target)
    {
        while(left<=right)
        {
            int mid = (left + right) / 2;

            if(board[mid] > target)
            {
                right = mid -1;
            }

            else if(board[mid] < target)
            {
                left = mid + 1;
            }

            else{
                return 1;
            }
        }

        return 0;
    }
}