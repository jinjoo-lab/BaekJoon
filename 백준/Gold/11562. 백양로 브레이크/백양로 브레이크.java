import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n+1][n+1];

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(i == j){
                    arr[i][j] = 0;
                }

                else{
                    arr[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if(w == 0){
                arr[x][y] = 0;
                arr[y][x] = 1;
            }else{
                arr[x][y] = 0;
                arr[y][x] = 0;
            }
        }

        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){

                    if(arr[i][k] < Integer.MAX_VALUE && arr[k][j] < Integer.MAX_VALUE){
                        arr[i][j] = Math.min(arr[i][j],arr[i][k] + arr[k][j]);
                    }

                }
            }
        }

        int num = Integer.parseInt(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for(int i=1;i<=num;i++){
            st = new StringTokenizer(br.readLine(), " ");

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            sb.append(arr[s][e] + "\n");
        }


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

