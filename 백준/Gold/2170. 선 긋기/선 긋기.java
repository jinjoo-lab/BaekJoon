import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        Node[] arr = new Node[n];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine(), " ");

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            arr[i-1] = new Node(s,e);
        }

        Arrays.sort(arr,(x,y) -> {

            if(x.s == y.s)
                return y.e - x.e;

            return x.s - y.s;
        });

        int s = arr[0].s;
        int e = arr[0].e;

        int result = 0;

        for(int i=1;i<n;i++){
            Node cur = arr[i];

            if(cur.s <= e && cur.e >= e){
                e = Math.max(e,cur.e);
            }

            else if(cur.s > e){
                result += (e - s);

                s = cur.s;
                e = cur.e;
            }
        }

        result += (e - s);

        System.out.println(result);
        br.close();
    }
}
class Node{
    int s;
    int e;

    Node(int s,int e){
        this.s = s;
        this.e = e;
    }
}

