import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        Node[] arr = new Node[n];
        // index , color , size
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            arr[i] = new Node(i,c,s);
        }
        Arrays.sort(arr,(x,y) -> x.s - y.s);

        int[] answer = new int[n+1];
        int[] color = new int[n+1];
        int total = 0;
        int tmp = 0;

        for(int i=0;i<n;i++){
            Node cur = arr[i];

           while(arr[tmp].s < cur.s){
               total += arr[tmp].s;
               color[arr[tmp].c] += arr[tmp].s;
               tmp = tmp + 1;
           }

            answer[cur.i] = total - color[cur.c];
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            sb.append(answer[i]+"\n");
        }
        System.out.print(sb);
        br.close();
    }
}
class Node{
    int i;
    int c;
    int s;

    Node(int i,int c,int s){
        this.i = i;
        this.c = c;
        this.s = s;
    }
}
