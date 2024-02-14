import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int r = 0;
    static int c = 0;

    static int count = 0;

    static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int N = (int)Math.pow(2,n);

        divide(N,r+1,c+1);
        br.close();
    }

    static void divide(int size,int x ,int y){


        if(size == 0){
            System.out.println(count);
            return;
        }


        int half = size / 2;
        int upSize = (size * size) / 4;

        if(x <= half && y <= half){
            divide(half,x,y);
        }else if(x <= half && y > half){
            count += upSize;
            divide(half,x,y - half);
        }else if(x > half && y <= half){
            count += upSize;
            count += upSize;
            divide(half,x - half,y);
        }else{
            count += upSize;
            count += upSize;
            count += upSize;
            divide(half,x - half,y - half);
        }
    }

}
