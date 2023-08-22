import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int[] cal;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i=1;i<=n;i++){
            m = Integer.parseInt(br.readLine());
            cal = new int[m];

            travel(1,sb);
            sb.append("\n");
        }

        System.out.print(sb.toString());


        br.close();
    }

    static String makeResult(){
        StringBuilder sb = new StringBuilder();

        sb.append(1);

        for(int i=2;i<=m;i++){
            if(cal[i-1] == 2)
                sb.append('+');

            else if(cal[i-1] == 3)
                sb.append('-');

            else
                sb.append(' ');

            sb.append(i);
        }


        return sb.toString();
    }

    static void travel(int cur,StringBuilder sb){
        if(cur == m){

            if(sum()){
                String tmp = makeResult();
                sb.append(tmp+"\n");
            }

            return;
        }

        for(int i=1;i<=3;i++){
            cal[cur] = i;
            travel(cur+1,sb);
        }
    }

    static boolean sum(){
        int result = 0;

        Stack<Integer> stack = new Stack<>();
        stack.push(1);

        for(int i=2;i<=m;i++){

            if(cal[i-1] == 2){
                stack.push(i);
            }

            else if(cal[i-1] == 3){
                stack.push(i*-1);
            }

            else{
                int cur = stack.pop();
                cur = cur * 10;

                if(cur > 0)
                    cur += i;

                else{
                    cur -= i;
                }

                stack.push(cur);
            }
        }

        while(!stack.isEmpty()){
            result += stack.pop();
        }


        if(result == 0)
            return true;

        return false;
    }
}

