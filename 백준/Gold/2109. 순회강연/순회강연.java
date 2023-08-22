import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][2];

        for(int i=1;i<=n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            board[i-1][0] = Integer.parseInt(st.nextToken());
            board[i-1][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(board,(x,y) -> {
            if(x[0] == y[0])
                return x[1] - y[1];

            return y[0] - x[0];
        });




        int result = 0;
        boolean[] visit = new boolean[10001];

        for(int i=0;i<n;i++){
            int tmp = board[i][1];

            for(int j= tmp;j>=1;j--){
                if(!visit[j]){
                    visit[j] = true;
                    result += board[i][0];
                    break;
                }
            }
        }

        System.out.println(result);
        br.close();
    }

}