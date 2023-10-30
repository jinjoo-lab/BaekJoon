import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] board;
    static int[] count = new int[1000001];
    static boolean[] isIt = new boolean[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        board = new int[n+1];

        st = new StringTokenizer(br.readLine(), " ");

        for(int i=1;i<=n;i++){
            board[i] = Integer.parseInt(st.nextToken());
            isIt[board[i]] = true;
        }


        for(int i=1;i<=n;i++){
            int j= 2;

            while(board[i] * j <= 1000000){
                if(isIt[board[i] * j]){
                    count[board[i] * j] -= 1;
                    count[board[i]] += 1;
                 }

                j = j + 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=n;i++){
            sb.append(count[board[i]]+" ");
        }

        System.out.println(sb);
        br.close();
    }
}

