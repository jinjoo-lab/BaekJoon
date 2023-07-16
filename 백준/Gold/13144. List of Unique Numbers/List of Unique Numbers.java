import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static boolean[] v = new boolean[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        int[] board = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }

        search(board);

        br.close();
    }

    static void search(int[] board) {
        long result = 0;
        int left = 1;
        int right = 1;

        for(left =1 ; left <= n ; left ++)
        {
            while(right <= n)
            {
                if(v[board[right]])
                    break;

                v[board[right]] = true;
                right = right + 1;
            }

            result += (right - left);
            v[board[left]] = false;
        }

        System.out.println(result);
    }

}