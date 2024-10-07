import java.util.*;
import java.io.*;

public class Main {

    static boolean[] v = new boolean[10001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int n = Integer.parseInt(st.nextToken());

        Node[] arr = new Node[n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine()," ");
            arr[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(arr,(x,y) -> {
            if(y.p == x.p)
                return x.d - y.d;

            return y.p - x.p;
        });

        int result = 0;


        for(int i = 0; i < n; i++) {
            int curD = arr[i].d;
            int curP = arr[i].p;

            for(int j = curD ; j >= 1 ; j--) {
                if(!v[j]) {
                    v[j] = true;
                    result += curP;
                    break;
                }
            }
        }


        System.out.println(result);
        br.close();
    }

    static class Node {
        int p;
        int d;

        Node(int p ,int d) {
            this.p = p;
            this.d = d;
        }
    }
}
