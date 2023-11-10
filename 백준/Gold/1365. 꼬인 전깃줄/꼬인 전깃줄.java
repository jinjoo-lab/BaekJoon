import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int num[];

    static int result[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        num = new int[n];
        result = new int[n];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<n;i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        result[0] = num[0];
        int i = 1;
        int j= 0;

        while(i < n){

            if(num[i] > result[j]){
                result[j+1] = num[i];
                j += 1;
            }else{
                int idx = bs(0,j,num[i]);
                result[idx] = num[i];
            }

            i += 1;
        }
        System.out.println((n - (j + 1)));


        br.close();
    }

    static int bs(int l, int r, int target){
        int mid;

        while(l < r){
            mid = (l + r) / 2;

            if(result[mid] < target){
                l = mid + 1;
            }else{
                r = mid;
            }
        }

        return r;
    }
}
