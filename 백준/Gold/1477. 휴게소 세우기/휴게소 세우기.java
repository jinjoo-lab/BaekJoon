import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int m = 0;
    static int l = 0;

    static int result;


    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        int[] arr = new int[n + 2];
        arr[0] = 0;
        arr[1] = l;

        st = new StringTokenizer(bf.readLine(), " ");
        for(int i=2;i<n+2;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int[] minus = new int[n+1];
        for(int i=0;i<n+1;i++){
            minus[i] = arr[i+1] - arr[i] ;
        }

        Arrays.sort(minus);

        result = l;

        find(1,l,minus);
        System.out.println(result);

        bf.close();
    }

    static void find(int l,int r,int[] minus){

        int mid = 0;
        int count = 0;

        while(l <= r){
            mid = (l + r) / 2;
            count = 0;

            for(int i=0;i<n+1;i++){
                if(mid >= minus[i])
                    continue;

                else{
                    int value = minus[i] / mid;
                    int rest = minus[i] % mid;

                    if(rest == 0){
                        count += value -1;
                    }else{
                        count += value;
                    }
                }
            }

            if(count <= m){
                result = Math.min(result,mid);
                r = mid - 1;
            }else{
                l = mid + 1;
            }

        }
    }
}


