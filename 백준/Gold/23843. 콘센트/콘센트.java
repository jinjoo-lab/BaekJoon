import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int m = 0;

    static Integer[] input;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        input = new Integer[n];

        st = new StringTokenizer(bf.readLine()," ");

        for(int i=0;i<n;i++){
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input,(x,y) -> Integer.compare(y,x));

        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (x,y) -> x - y
        );

        for(int i=0;i<m;i++){
            pq.add(0);
        }

        int result = 0;

        for(int i=0;i<n;i++){
            int curT = input[i];
            int nextT = pq.poll() + curT;

            result = Math.max(result,nextT);
            pq.add(nextT);
        }

        System.out.println(result);
        bf.close();
    }
}
