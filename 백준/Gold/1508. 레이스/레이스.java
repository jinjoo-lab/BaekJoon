import java.io.*;
import java.util.*;

public class Main {

    static int n = 0;
    static int m = 0;
    static int k = 0;

    static int[] arr;

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[k];
        st = new StringTokenizer(br.readLine()," ");
        for(int i=0;i<k;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        bs(1,n);

        br.close();
    }

    static void bs(int l,int r){
        String re = "";

        while(l <= r){
            StringBuilder sb = new StringBuilder();
            int mid = (l + r) / 2;
            int count = 1;
            int idx = 0;
            sb.append(1);

            for(int i=1;i<k;i++){
                if(count >= m){
                   sb.append(0);
                   continue;
                }

                if(arr[i] >= arr[idx] + mid){
                    sb.append(1);
                    count += 1;
                    idx = i;
                }else{
                    sb.append(0);
                }
            }

            if(count >= m){
                if(result < mid){
                    result = mid;
                    re = sb.toString();
                }

                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }


        System.out.println(re);
    }
}

