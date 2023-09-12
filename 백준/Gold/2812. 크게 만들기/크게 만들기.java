import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int last = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        last = m;

        char[] arr = br.readLine().toCharArray();
        Stack<Integer> stack = new Stack<>();

        for(int i=0;i<arr.length;i++){

            int cur = arr[i] - '0';

            while(m > 0 && !stack.isEmpty()){
                int tmp = stack.peek();

                if(tmp < cur && m > 0){
                    m--;
                    stack.pop();
                }

                else{
                    break;
                }
            }

            stack.push(cur);
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        sb.reverse();


        System.out.println(sb.substring(0,n-last));

        br.close();
    }
}
