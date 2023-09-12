import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());


            while(!stack.isEmpty()){

                int tmp = stack.peek();

                if(y < tmp){
                   stack.pop();
                   num += 1;
                }

                else if( y == tmp){
                    stack.pop();
                }

                else{
                    break;
                }
            }

            if(y != 0)
                stack.push(y);
        }

        System.out.println(num + stack.size());
        br.close();
    }
}
