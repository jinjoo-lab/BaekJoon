import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][2];

        for(int i=0;i<n;i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            board[i][0] = start;
            board[i][1] = end;
        }

        Arrays.sort(board,(o1,o2) ->{
            if(o1[0] == o2[0])
                return o1[1] - o2[1];

            else
                return o1[0] - o2[0];
        });

        PriorityQueue<Integer> q = new PriorityQueue<>();
        q.add(board[0][1]);
        for(int i=1;i<n;i++)
        {
            if(q.peek()<=board[i][0])
            {
                q.poll();
                q.add(board[i][1]);
            }

            else{
                q.add(board[i][1]);
            }
        }

        System.out.println(q.size());
        br.close();
    }

    static void print(int[][] board)
    {
        for(int i=0;i<n;i++)
        {
            System.out.println(board[i][0]+" "+board[i][1]);
        }
    }
}