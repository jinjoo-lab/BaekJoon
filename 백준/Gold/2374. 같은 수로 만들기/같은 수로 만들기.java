import java.util.*;
import java.io.*;

public class Main {

    static int n;

    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        long max = 0;
        arr = new int[n+1];
        for(int i = 1 ; i <= n ; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();
        long result = 0;

        for(int i = 1 ; i <= n ; i++) {
            max = Math.max(max,arr[i]);



            if(!stack.isEmpty()) {

                int peek = stack.peek();

                if(peek > arr[i]) {
                    stack.pop();
                    stack.push(arr[i]);
                }else if(peek < arr[i]){
                    result += arr[i] - stack.pop();
                    stack.push(arr[i]);
                }
            }else {
                stack.push(arr[i]);
            }

        }

        while(!stack.isEmpty()) {
            result += (max - stack.pop());
        }

        System.out.println(result);


        br.close();
    }
}
