import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int t = 0;
    static int n = 0;
    static int m = 0;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());

        for(int k=1;k<=t;k++){
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            Node[] arr = new Node[m];

            for(int i=0;i<m;i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                arr[i] = new Node(x,y);
            }

            Arrays.sort(arr,(x,y) -> {
                if(x.y == y.y)
                    return x.x - y.x;

                return x.y - y.y;
            });

            int count = 0;
            boolean[] v = new boolean[n+1];
            for(int i=0;i<m;i++){
                int start = arr[i].x;
                int end = arr[i].y;

                for(int j= start;j<=end;j++){
                    if(!v[j]){
                        v[j] = true;
                        count += 1;
                        break;
                    }
                }
            }

            sb.append(count+"\n");
        }

        System.out.print(sb);
        br.close();
    }

}
class Node{
    int x;
    int y;

    Node(int x,int y){
        this.x = x;
        this.y = y;
    }
}