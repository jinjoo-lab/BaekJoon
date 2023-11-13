import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] arr;
    static int[] month = {0,31,28,31,30,31,30,31,31,30,31,30,31};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());

        Node[] arr = new Node[n];


        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine()," ");
            int sm = Integer.parseInt(st.nextToken());
            int sd = Integer.parseInt(st.nextToken());
            int em = Integer.parseInt(st.nextToken());
            int ed = Integer.parseInt(st.nextToken());

            int s = 0;
            int e = 0;

            for(int j=1;j<sm;j++){
                s += month[j];
            }
            s += sd;

            for(int j=1;j<em;j++){
                e += month[j];
            }e += ed;


            arr[i] = new Node(s,e);
        }

        Arrays.sort(arr,(x,y) -> {
            if(x.s == y.s)
                return y.e - x.e;

            return x.s - y.s;
        });

        // 60 , 334

        int start = 60;
        int max = 0;
        int count = 0;
        int idx = 0;

        while(start < 335){

            boolean find = false;

            for(int i = idx;i < n ;i ++){

                if(arr[i].s > start){
                    break;
                }

                if(arr[i].e > max){
                    max = arr[i].e;
                    find = true;
                    idx = idx + 1;
                }
            }

            if(!find){
                break;
            }

            else{
                start = max;
                count = count + 1;
            }
        }

        if(max < 335)
            System.out.println(0);
        else{
            System.out.println(count);
        }
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