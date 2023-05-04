import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int l = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        int[] board = new int[n];

        for(int i=0;i<n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            board[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(board);
        long result = 0;

        for(int i=0;i<n;i++)
        {
            result+=Math.abs((i+1) - board[i]);
        }
        System.out.println(result);
        br.close();
    }

    static void print(int[] board)
    {
        for(int i=0;i<n;i++)
        {
            System.out.println(board[i]);
        }
    }
}