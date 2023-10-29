import java.io.*;
import java.util.*;

public class Main {
    static int a = 0;
    static int b = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        if(a == b){
            System.out.println(a +" "+ b);
            return;
        }

        int tmpMax = b / a;

        int sum = b;
        int min = 0;
        int max = 0;

        for(int i=1;i<=Math.sqrt(tmpMax);i++){

            if(tmpMax % i == 0){
                int f = i;
                int s = tmpMax / i;

                if(find(f,s) ==1){
                    if(f + s <= sum){
                        sum = f + s;
                        min = f;
                        max = s;
                    }
                }
            }

        }

        System.out.println((a*Math.min(min,max))+" "+(a*Math.max(min,max)));
        br.close();
    }

    static int find(int f,int s){
        if(s == 0)
            return f;

        return find(s,f % s);
    }
}
