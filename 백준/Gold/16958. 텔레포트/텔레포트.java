import java.util.*;
import java.io.*;

public class Main {

    static int n,t,m;
    static Node[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        arr = new Node[n+1];

        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int isIt = Integer.parseInt(st.nextToken());
            int tmpX = Integer.parseInt(st.nextToken());
            int tmpY = Integer.parseInt(st.nextToken());

            arr[i] = new Node(isIt,tmpX,tmpY);
        }

        go();

        st = new StringTokenizer(br.readLine()," ");
        m = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        for(int i = 1 ; i <= m ; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            sb.append(dis[v1][v2]+"\n");
        }

        System.out.print(sb);

        br.close();
    }

    static int[][] dis;

    static void go() {
        dis = new int[n+1][n+1];
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                dis[i][j] = 30000;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {

                if(i == j)
                    continue;

                int tmpK = (arr[i].isIt == 1 && arr[j].isIt == 1) ? t : 30000;
                dis[i][j] = Math.min(dis[i][j], Math.min(calDis(arr[i].x, arr[i].y, arr[j].x, arr[j].y), tmpK));
            }
        }

        for(int k = 1 ; k <= n ; k++) {
            for(int i = 1 ; i <= n ; i++) {
                for(int j = 1 ; j <= n ; j++) {
                    dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
                }
            }
        }

    }

    static void print(int[][] dis) {
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                System.out.print(dis[i][j]+" ");
            }
            System.out.println();
        }
    }

    static class Node {
        int isIt;
        int x;
        int y;

        Node(int isIt,int x,int y) {
            this.isIt = isIt;
            this.x = x;
            this.y = y;
        }
    }

    static int calDis(int x1,int y1,int x2,int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
