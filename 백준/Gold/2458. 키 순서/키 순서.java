import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;



    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] adj = new int[n+1][n+1];

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            adj[x][y] = 1; // x가 y보다 작다
            adj[y][x] = 2; // y가 x보다 크다
        }

        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){

                    if(i == j)
                        continue;

                    if(adj[i][k] == 1 && adj[k][j] == 1){
                        adj[i][j] = 1;
                        adj[j][i] = 2;
                    }

                    if(adj[i][k] == 2 && adj[k][j] == 2){
                        adj[i][j] = 2;
                        adj[j][i] = 1;
                    }

                }
            }
        }
        int result = 0;

        for(int i=1;i<=n;i++){
            int count = 0;

            for(int j=1;j<=n;j++){
                if(adj[i][j] == 0)
                    count += 1;
            }

            if(count == 1){
                result += 1;
            }
        }


        System.out.println(result);


        br.close();
    }
}
