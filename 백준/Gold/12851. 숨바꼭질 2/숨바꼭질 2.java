import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        if(n == m){
            System.out.println(0);
            System.out.println(1);
            return;
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(n);

        int[] v = new int[100001];
        v[n] = 1;
        int result = 0;

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int i=0;i<3;i++){
                int nx = cur;

                if(i == 0){
                    nx += 1;
                }

                if(i == 1){
                    nx -= 1;
                }

                if(i == 2){
                    nx = nx * 2;
                }

                if(nx < 0 || nx > 100000)
                    continue;

                if(nx == m){

                    if(v[m] == 0 || v[nx] > v[cur] + 1){
                        v[nx] = v[cur] + 1;
                        result = 1;
                    }

                    else if(v[nx] == v[cur] + 1){
                        result += 1;
                    }
                    continue;
                }

                if(v[nx] == 0 || v[nx] >= v[cur] + 1){
                    v[nx] = v[cur] + 1;
                    q.add(nx);
                }
            }
        }

        System.out.println(v[m] - 1);
        System.out.println(result);
    }
}
