import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        board = new int[n][3];

        for(int i = 0 ;i < n ;i++){
            st = new StringTokenizer(br.readLine()," ");

            board[i][0] = i + 1;
            board[i][1] = Integer.parseInt(st.nextToken());
            board[i][2] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(board,(x,y) -> y[1] - x[1]);

        int[] result = new int[n+1];

        result[board[0][0]] = 1;

        for(int i=1; i<n ;i++){

            int count = 1;

            for(int j=i-1;j>=0;j--){
                if(board[i][1] < board[j][1] && board[i][2] < board[j][2]){
                    count += 1;
                }
            }

            result[board[i][0]] = count;
        }

        for(int i=1 ;i <=n ;i ++){
            sb.append(result[i]+" ");
        }
        System.out.println(sb);
        br.close();
    }
}
