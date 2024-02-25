
import java.io.*;
import java.util.*;

public class Main {

    static int[] board;

    static int[] result;

    static int[] duplicated;
    static int n = 0;

    static long count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        board = new int[n+1];
        result = new int[n+1];
        duplicated = new int[n+1];

        for(int i=1;i<=n;i++){
            board[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();

        for(int i=1;i<=n;i++){
            

            while(!stack.isEmpty()){
                if(board[stack.peek()] < board[i]){
                    count += duplicated[stack.peek()] + 1;
                    stack.pop();
                }else if(board[stack.peek()] == board[i]){
                    duplicated[i] += duplicated[stack.peek()] + 1;
                    count += duplicated[stack.peek()] + 1;
                    stack.pop();
                } else{
                    count++;
                    break;
                }
            }

            stack.push(i);
        }

        System.out.println(count);

        br.close();
    }
}