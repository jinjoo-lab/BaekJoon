import java.io.*;
import java.util.*;

public class Main {

    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> plus = new PriorityQueue<>((x,y) -> y - x);
        PriorityQueue<Integer> minus = new PriorityQueue<>((x,y) -> x - y);
        Queue<Integer> q = new LinkedList<>();

        for(int i=1;i<=n;i++){
            int cur = Integer.parseInt(br.readLine());

            if(cur == 0){
                q.add(cur);
            }

            else if(cur >= 1){
                plus.add(cur);
            }

            else if(cur < 0){
                minus.add(cur);
            }

        }

        long result = 0;

        while(!plus.isEmpty()){
            long tmp = plus.poll();

            if(!plus.isEmpty() && plus.peek() != 1){
                tmp = tmp * plus.poll();
            }

            result += tmp;
        }

        while(!minus.isEmpty()){
            long tmp = minus.poll();

            if(!minus.isEmpty()){
                tmp = tmp * minus.poll();
            }

            else if(q.size() >= 1){
                tmp = tmp * q.poll();
            }

            result += tmp;
        }

        System.out.println(result);
        br.close();
    }
}
