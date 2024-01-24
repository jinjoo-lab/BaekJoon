import java.util.*;
import java.io.*;
public class Main {
    static int t = 0;
    static int n = 0;

    static PriorityQueue<Long> pq;

    static int[] board;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>(
                (x,y) -> Long.compare(x,y)
        );



        for(int i=1;i<=t;i++){
            st = new StringTokenizer(bf.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            pq.clear();

            st = new StringTokenizer(bf.readLine(), " ");
            for(int j=0;j<n;j++){
                pq.add(Long.parseLong(st.nextToken()));
            }

            long answer = 0;

            while(pq.size() >= 2){
                long tmp1 = pq.poll();

                long tmp2 = pq.poll();

                answer += tmp1 + tmp2;

                pq.add(tmp1 + tmp2);
            }

            sb.append(answer+"\n");
        }

        System.out.print(sb);
        bf.close();
    }
}