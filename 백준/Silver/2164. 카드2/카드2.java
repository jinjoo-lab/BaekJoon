import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;

    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());

        for(int i=1;i <=n;i++){
            q.add(i);
        }

        while(true){

            if(q.size() > 1){
                q.poll();
            }

            if(q.size() == 1)
                break;
            
            q.add(q.poll());
        }

        System.out.println(q.poll());
        br.close();
    }
}


