import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        board = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        for(int i=1;i<=n;i++){
            board[i] = Integer.parseInt(st.nextToken());
        }

        int[] v = new int[n+1];
        v[1] = 1;
        Queue<Integer> q= new LinkedList<>();
        q.add(1);

        int result = -1;
        while(!q.isEmpty()){
            int cur = q.poll();
            if(cur == n){
                result = v[cur] - 1;
                break;
            }

            for(int i=1;i<=board[cur];i++){
                int nx = cur + i;

                if(nx > n)
                    continue;

                if(v[nx] == 0 || v[nx] > v[cur] + 1){
                    v[nx] = v[cur] + 1;
                    q.add(nx);
                }
            }
        }
        System.out.println(result);

        br.close();
    }
}
