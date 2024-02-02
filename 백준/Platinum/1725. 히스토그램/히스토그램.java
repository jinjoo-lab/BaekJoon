import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;

    static int[] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        board = new int[n+2];

        for(int i=1;i<=n;i++){
            board[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        int ans = 0;

        for(int i=1;i<=n+1;i++){

            while(!stack.isEmpty() && board[stack.peek()] > board[i]){

                int tmp = stack.peek();
                stack.pop();

                ans = Math.max(ans,board[tmp] * (i - stack.peek() - 1));

            }

            stack.push(i);
        }

        System.out.println(ans);
        br.close();
    }
}
