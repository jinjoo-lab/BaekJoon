import java.util.*;
import java.io.*;

public class Main {

    static int n = 0;
    static int[] count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        count = new int[n+1];

        st = new StringTokenizer(br.readLine(), " ");

        PriorityQueue<Node> plus = new PriorityQueue<>(
                (x,y) -> x.idx - y.idx
        );

        PriorityQueue<Node> minus = new PriorityQueue<>(
                (x,y) -> x.idx - y.idx
        );

        for(int i=1;i<=n;i++){
            int tmp = Integer.parseInt(st.nextToken());

            if(tmp >= 0)
                plus.add(new Node(tmp,i));

            else{
                minus.add(new Node(tmp*-1,i));
            }
        }

        long result = 0;
        while(!plus.isEmpty()){
            Node tmp = plus.poll();

            int tmpV = tmp.v;
            int tmpI = tmp.idx;

            while(tmpV != 0){

                while(!minus.isEmpty()){
                    Node tmp2 = minus.peek();

                    int mV = tmp2.v;
                    int mI = tmp2.idx;

                    int rest = Math.min(tmpV , mV);
                    int nIdx = Math.abs(tmpI - mI);
                    result += rest * nIdx;
                    tmpV -= rest;
                    mV -= rest;

                    if(mV == 0){
                        minus.poll();
                    }

                    else{
                        tmp2.v = mV;
                    }

                    if(tmpV == 0)
                        break;
                }

            }

        }
        System.out.println(result);
        br.close();
    }
}
class Node{
    int v;
    int idx;

    Node(int v,int idx){
        this.v = v;
        this.idx = idx;
    }
}