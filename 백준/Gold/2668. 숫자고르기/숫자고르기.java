import java.util.*;
import java.io.*;

public class Main {
    static int n = 0 ;
    static int[] visit;
    static boolean[] finish;

    static HashSet<Integer> set = new HashSet<>();
    static int count = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        int[] board = new int[n+1];

        for(int i=1;i<=n;i++)
        {
            board[i] = Integer.parseInt(br.readLine());
        }


        for(int i=1;i<=n;i++)
        {
            visit = new int[n+1];
            finish = new boolean[n+1];
            count = 1;

            search(i,board);
        }

        System.out.println(set.size());

        if(!set.isEmpty()) {
            int[] result = new int[set.size()];

            int idx = 0;
            for(int num : set)
                result[idx++] = num;

            Arrays.sort(result);

            for(int i=0;i<result.length;i++)
                System.out.println(result[i]);

        }
        br.close();
    }


    static void search(int x,int[] board)
    {
        visit[x] = count++;

        if(visit[board[x]] == 0)
        {
            search(board[x],board);
        }

        else if(!finish[board[x]])
        {
            set.add(board[x]);
        }

        finish[x] = true;
    }
}
