import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String line = null;
        while((line = br.readLine())!= null){
            dp(line);
        }
        bw.flush();
        bw.close();
    }

    private static void dp(String line) throws IOException {
        int N = Integer.parseInt(line);
        Queue<Node> pq = new LinkedList<>();
        int sum = 0;
        for (int i = 0 ; i < N ; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            pq.add(new Node(input[0],input[1]));
            sum += input[0] * input[1];
        }
        if (sum % 2 != 0) {
            bw.write(0+"\n");
            return;
        }
        boolean[] dp = new boolean[sum/2+1];
        dp[0] = true;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            for (int i = sum/2 ; i >= cur.v ; i--) {
                if (i == cur.v || dp[i-cur.v]) {
                    for(int k=0;k<=cur.c;k++){
                        if(i + (k*cur.v) <= sum/2){
                            dp[i + (k*cur.v)] = true;
                            if ((i + (k*cur.v)) == sum/2) {
                                bw.write(1+"\n");
                                return;
                            }
                        }
                    }
                }
            }
        }

        bw.write(0+"\n");
    }
}
class Node{
    int v;
    int c;

    Node(int v,int c){
        this.v = v;
        this.c = c;
    }
}