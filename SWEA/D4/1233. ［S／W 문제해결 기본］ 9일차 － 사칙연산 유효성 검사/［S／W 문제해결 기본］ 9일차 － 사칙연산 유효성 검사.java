import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int n = 0;
    static char[] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for(int a = 1; a <= 10 ; a++){
            st = new StringTokenizer(br.readLine()," ");
            n = Integer.parseInt(st.nextToken());

            board = new char[n+1];

            for(int i=1;i<=n;i++){
                st = new StringTokenizer(br.readLine()," ");
                int idx = Integer.parseInt(st.nextToken());

                char cur = st.nextToken().charAt(0);
                board[idx] = cur;

            }

            int tmpResult = bfs();
            sb.append("#"+a+" "+tmpResult+"\n");
        }

        System.out.print(sb);

        br.close();
    }

    static int bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.add(1);

        while(!q.isEmpty()){
            int cur = q.poll();
            boolean hasChild = false;

            int left = cur*2;
            if(left <= n){
                hasChild = true;
                q.add(left);
            }

            int right = cur*2 + 1;
            if(right <= n){
                hasChild = true;
                q.add(right);
            }

            if(!hasChild){
                if(board[cur] < '0' || board[cur] > '9'){
                    return 0;
                }
            }
        }

        return 1;
    }
}
