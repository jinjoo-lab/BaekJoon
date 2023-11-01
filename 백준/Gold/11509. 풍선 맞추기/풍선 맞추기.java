import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] board;
    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        board = new int[n+1];
        count = new int[1000001];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=n;i++){
            board[i] = Integer.parseInt(st.nextToken());
        }

        int num = 0;

        for(int i=1;i<=n;i++){
            if(count[board[i]] == 0){
                num = num + 1;
            }
            else{
                count[board[i]]-= 1;
            }

            if(board[i] - 1 >= 1)
                count[board[i]-1] += 1;
        }

        System.out.println(num);

        br.close();
    }
}
