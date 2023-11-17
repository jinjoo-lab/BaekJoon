import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        Country[] arr = new Country[n];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine(), " ");

            int num = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[i-1] = new Country(num,g,s,b);
        }

        Arrays.sort(arr,(x,y) -> {
            if(x.g == y.g){

                if(x.s == y.s)
                    return y.b - x.b;

                return y.s - x.s;
            }

            return y.g - x.g;
        });

        int num = 1;

        if(arr[0].num == m){
            System.out.println(1);
            return;
        }

        int count = 1;

        for(int i = 1; i < n ;i ++){
            Country cur = arr[i];

            if(arr[i-1].g != arr[i].g || arr[i-1].s != arr[i].s || arr[i-1].b != arr[i].b){
                num += count;
                count = 1;
            }else{
                count += 1;
            }

            if(cur.num == m){
                System.out.println(num);
                break;
            }
        }

        br.close();
    }
}
class Country{
    int num;
    int g;
    int s;
    int b;

    Country(int num,int g,int s,int b){
        this.num = num;
        this.g = g;
        this.s = s;
        this.b = b;
    }
}
