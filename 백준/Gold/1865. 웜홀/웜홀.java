import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int t = 0;

    static int[] dis;
    static int n , m , w;

    static int[][] edge;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());

        for(int k = 1; k <= t ; k++){
            st = new StringTokenizer(br.readLine()," ");

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            dis = new int[n+1];

            edge = new int[2*m + w + 1][3];
            int idx = 1;

            for(int i =1 ; i <= m ; i++){
                st = new StringTokenizer(br.readLine()," ");

                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                int v3 = Integer.parseInt(st.nextToken());

                edge[idx][0] = v1;
                edge[idx][1] = v2;
                edge[idx][2] = v3;
                idx++;
                edge[idx][0] = v2;
                edge[idx][1] = v1;
                edge[idx][2] = v3;
                idx++;
            }

            for(int i =1 ; i <= w ; i++){
                st = new StringTokenizer(br.readLine()," ");

                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                int v3 = Integer.parseInt(st.nextToken());

                edge[idx][0] = v1;
                edge[idx][1] = v2;
                edge[idx][2] = v3 * -1;
                idx++;
            }

            boolean find = false;

            for(int j = 1 ; j <= n ; j++) {

                find = false;

                for (int i = 1; i <= n; i++) {
                    dis[i] = Integer.MAX_VALUE;
                }
                dis[j] = 0;
                find = find();

                if(dis[j] < 0){
                    find = true;
                }

                if(find){
                    break;
                }
            }

            if(find){
                sb.append("YES\n");
            }else
                sb.append("NO\n");

        }

        System.out.print(sb);
        br.close();
    }

    static boolean find(){

        int size = m*2 + w + 1;

        for(int i = 1 ; i <= n ; i++){

            boolean isIt = false;

            for(int j = 1; j < size; j++){
                int[] cur = edge[j];

                int curNode = cur[0];
                int nextNode = cur[1];
                int cost = cur[2];

                if(dis[curNode] != Integer.MAX_VALUE && dis[nextNode] > dis[curNode] + cost){
                    dis[nextNode] = dis[curNode] + cost;
                    isIt = true;
                    if(i == n){
                        return true;
                    }
                }
            }

            if(!isIt){
                break;
            }
        }

        return false;
    }
}
