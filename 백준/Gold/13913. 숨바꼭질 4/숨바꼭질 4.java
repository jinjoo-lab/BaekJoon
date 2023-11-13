import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static int[] v = new int[100001];
    static int[] past = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());


        Queue<Integer> q = new LinkedList<>();
        v[n] = 1;
        q.add(n);

        while(!q.isEmpty()) {
            int cur = q.poll();

            if(cur == m){
                sb.append(v[cur]-1+"\n");

                Stack<Integer> stack = new Stack<>();
                int s = m;
                while(s != n){
                    stack.push(s);
                    s = past[s];
                }stack.push(n);

                while(!stack.isEmpty()){
                    sb.append(stack.pop()+" ");
                }sb.append("\n");

                System.out.print(sb);
                return;
            }

            int n1 = cur + 1;
            int n2 = cur - 1;
            int n3 = cur * 2;

            if(n1 >= 0 && n1 <= 100000){
                if(v[n1] == 0){
                    v[n1] = v[cur] + 1;
                    past[n1] = cur;
                    q.add(n1);
                }
            }

            if(n2 >= 0 && n2 <= 100000){
                if(v[n2] == 0){
                    v[n2] = v[cur] + 1;
                    past[n2] = cur;
                    q.add(n2);
                }
            }

            if(n3 >= 0 && n3 <= 100000){
                if(v[n3] == 0){
                    v[n3] = v[cur] + 1;
                    past[n3] = cur;
                    q.add(n3);
                }
            }
        }

        br.close();
    }
}
