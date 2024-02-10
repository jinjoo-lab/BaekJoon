import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int k = 0;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static long[] startNum;
    static int curX = 0;
    static int curY = 0;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        char[] move = br.readLine().toCharArray();

        startNum = new long[2*n+2];

        startNum[1] = 1;

        int idx = 1;

        for(int i=2 ; i <= n;i++){
            startNum[i] = startNum[i-1] + idx++;
        }

        for(int i=n+1; i <= 2*n-1; i++){
            startNum[i] = startNum[i-1] + idx--;
        }


        long result = 1;

        // R L U D

         for(int i = 0 ; i < k ; i++){
             char curMove = move[i];

             if(curMove == 'D'){
                 curX += dx[3];
                 curY += dy[3];
             }else if(curMove == 'R'){
                 curX += dx[0];
                 curY += dy[0];
             }else if(curMove == 'L'){
                 curX += dx[1];
                 curY += dy[1];
             }else{
                 curX += dx[2];
                 curY += dy[2];
             }

             int totalNum = curX + curY;

             long curStart = startNum[totalNum + 1];

             // 짝수 라인
             if(totalNum % 2 == 0){

                 if(totalNum < n ){
                     result += curStart + curY;
                 }else{
                     result += curStart + Math.abs(n - curX - 1);
                 }

             }else{
                 if(totalNum < n){
                     result += curStart + curX;
                 }else{
                    result += curStart + Math.abs(n - curY - 1);
                 }
             }
         }

        System.out.println(result);
        br.close();
    }
}



