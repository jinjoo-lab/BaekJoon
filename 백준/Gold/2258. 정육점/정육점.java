import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        Node[] arr = new Node[n];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            arr[i] = new Node(s,p);
        }

        Arrays.sort(arr,(x,y) -> {
            if(x.p == y.p)
                return y.s - x.s;

            return
                x.p - y.p;});
        // 가격으로 오름 차순 , 무게로 내림 차순

        int tmp = 0; // size
        int price = 0; // price
        int past = 0;

        int result = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            tmp += arr[i].s;

            if(arr[past].p == arr[i].p){
                price += arr[i].p;
                past = i;
            }

            else if(arr[past].p < arr[i].p){
                price = arr[i].p;
                past = i;
            }

            if(tmp >= m){
                result = Math.min(result,price);
            }
        }

        if(tmp < m){
            System.out.println(-1);
        }
        else
            System.out.println(result);

        br.close();
    }
}
class Node{
    int s;
    int p;

    Node(int s,int p){
        this.s = s;
        this.p = p;
    }
}
