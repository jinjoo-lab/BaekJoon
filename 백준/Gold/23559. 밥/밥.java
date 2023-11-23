import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n = 0;
    static int max = 0;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        Node[] arr = new Node[n];
        max = Integer.parseInt(st.nextToken());
        int result = 0;

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            arr[i] = new Node(t,e);
            max -= 1000;
            result += e;
        }

        if(max == 0){
            System.out.println(result);
            return;
        }

        Arrays.sort(arr,(x,y) ->{
            return (y.f - y.o) - (x.f - x.o);
        });


        for(int i=0;i<n;i++){
            Node tmp = arr[i];

            if(tmp.o >= tmp.f){continue;}

            if(max < 4000)
                break;

            int plus = tmp.f - tmp.o;

            result += plus;
            max -= 4000;

        }

        System.out.println(result);
    }
}
class Node{
    int f;
    int o;

    Node(int f,int o){
        this.f = f;
        this.o = o;
    }
}
