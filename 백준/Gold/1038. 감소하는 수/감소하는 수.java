import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static ArrayList<Long> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());

        if(n >=0 && n<= 9){
            System.out.println(n);
            return;
        }

        list.add(0l);
        for(long i=1;i<=9;i++){
            list.add(i);
            go(i,1);
        }

        list.sort((x,y) -> Long.compare(x,y));

        if(list.size() - 1 < n){
            System.out.println(-1);
        }else{
            System.out.println(list.get(n));
        }

        br.close();
    }

    static void go(long num,int idx){
        if(idx > 10)
            return;

        for(int i=0;i<num % 10;i++){
            long tmp = (num * 10) + i;
            list.add(tmp);

            go(tmp,idx+1);
        }
    }

}
