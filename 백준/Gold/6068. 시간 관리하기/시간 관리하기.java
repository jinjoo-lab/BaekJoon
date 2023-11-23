import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n = 0;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        Node[] arr = new Node[n];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            arr[i] = new Node(t,e);
        }

        Arrays.sort(arr,(x,y) ->
        {
            if(x.e == y.e)
                return y.t - x.t;
            return y.e - x.e;
        });

        int curT = arr[0].e;

        for(int i=0;i<n;i++){
            Node cur = arr[i];

            if(curT >= cur.e){
                curT = cur.e;
                curT -= cur.t;
            }else if(curT < cur.t ){
                System.out.println(-1);
                return;
            }else{
                curT -= cur.t;
            }
            
            if(curT < 0){
                System.out.println(-1);
                return;
            }
        }

        System.out.println(curT);
    }
}
class Node{
    int t;
    int e;

    Node(int t,int e){
        this.t = t;
        this.e = e;
    }
}
