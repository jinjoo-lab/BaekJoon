import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static double[] per;

    static int[] dx = {0,0,0,1,-1};
    static int[] dy = {0,1,-1,0,0};

    static boolean[][]  v= new boolean[32][32];
    static double result = 0.0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        per = new double[5];

        for(int i=1;i<=4;i++){
            per[i] = Double.parseDouble(st.nextToken()) / 100.0;
        }

        v[15][15] = true;
        travel(0,15,15,1.0);
        System.out.printf("%.9f",result);
        br.close();
    }
    static void travel(int count,int x,int y,double cur){

        if(count == n){
            result += cur;
            return;
        }

        for(int i=1;i<=4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(!v[nx][ny]){
                v[nx][ny] = true;
                travel(count+1,nx,ny,cur*per[i]);
                v[nx][ny] = false;
            }
        }
    }
}
