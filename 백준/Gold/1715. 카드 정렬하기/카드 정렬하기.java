import java.io.*;
import java.util.*;

public class Main {

    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (x,y) -> x -y
        );


        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            pq.add(Integer.parseInt(st.nextToken()));
        }

        int result = 0;
        while(!pq.isEmpty())
        {
            int cur = pq.poll();
            int cur2 = 0;

            if(!pq.isEmpty()) {
                cur2 = pq.poll();
                result += (cur + cur2);
                pq.add((cur + cur2));
            }

            else{
                break;
            }
        }
        System.out.println(result);


        br.close();
    }


}