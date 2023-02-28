import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int[] board = new int[200001];
    static HashMap<Integer,Long> map = new HashMap<>();
    static long result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");
        for(int i=1;i<=n;i++)
        {
            board[i] = board[i-1] + Integer.parseInt(st.nextToken());

            if(board[i]==m)
            {
                result = result+1;
            }
        }

        for(int i=1;i<=n;i++)
        {
            if(map.containsKey(board[i] - m))
            {
                result += map.get(board[i] - m);
            }

            if(map.containsKey(board[i]))
            {
                map.put(board[i],map.get(board[i])+1l);
            }
            else{
                map.put(board[i],1l);
            }
        }

        System.out.println(result);

        br.close();
    }
}