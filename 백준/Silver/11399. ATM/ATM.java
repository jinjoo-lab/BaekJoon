import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int[] board = new int[n];

        for(int i=0;i<n;i++)
        {
            board[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(board);

        int score = 0;
        int result = 0;
        for(int i=0;i<n;i++)
        {
            score = score + board[i];
            result +=score;
        }

        System.out.println(result);


        br.close();
    }
}