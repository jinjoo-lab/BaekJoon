
import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int result = 0;

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

        for(int i=0;i<n;i++)
        {
            bsearch(board,0,n-1,i);
        }

        System.out.println(result);
        br.close();
    }

    static void bsearch(int[] board,int left, int right,int target)
    {
        while(left < right)
        {
            if(left==target)
                left = left + 1;

            if(right == target)
                right = right - 1;

            if(left >= right)
                break;

            if(board[left] + board[right] == board[target])
            {
                result = result + 1;
                return;
            }

            else if(board[left] + board[right] > board[target])
            {
                right = right -1;
            }

            else{
                left = left + 1;
            }

        }
    }
}
