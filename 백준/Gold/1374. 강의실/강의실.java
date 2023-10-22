import java.io.*;
import java.util.*;

public class Main {

    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        Time[] arr = new Time[n];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            arr[i] = new Time(s,l);
        }

        Arrays.sort(arr,(x,y) -> {
            if(x.s == y.s)
                return x.l - y.l;

            return x.s - y.s;});

        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (x,y) -> x - y
        );

        for(int i=0;i<n;i++){

            if(pq.isEmpty()){
                pq.add(arr[i].l);
            }

            else if(pq.peek() <= arr[i].s){
                pq.poll();
                pq.add(arr[i].l);
            }

            else{
                pq.add(arr[i].l);
            }
        }

        System.out.println(pq.size());
        br.close();
    }
}
class Time{
    int s;
    int l;

    Time(int s,int l){
        this.s = s;
        this.l = l;
    }
}
