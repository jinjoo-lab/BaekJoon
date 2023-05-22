
import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int rl = -1;
    static int rr = -1;
    static int[] board;
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

        search();

        System.out.println(board[rl] + " " + board[rr]);

        br.close();
    }

    static void search()
    {
        int left = 0;
        int right = n-1;

        while(left < right)
        {
            int sum = board[left] + board[right];
            if(sum==0) {
                rl = left;
                rr = right;
                break;
            }
            else if(sum < 0) {
                if (rl == -1 && rr == -1) {
                    rl = left;
                    rr = right;
                } else if (Math.abs(board[rl] + board[rr]) >= Math.abs(sum)) {
                    rl = left;
                    rr = right;
                }

                left = left + 1;
            }

            else{
                if(rl==-1&&rr==-1)
                {
                    rl = left;
                    rr = right;
                }

                else if(Math.abs(board[rl]+board[rr]) >= Math.abs(sum))
                {
                    rl = left;
                    rr = right;
                }

                right = right -1;
            }
        }
    }
}
