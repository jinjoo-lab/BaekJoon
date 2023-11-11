import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] num;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());

        num = new int[n];
        st = new StringTokenizer(br.readLine()," ");
        for(int i=0;i<n;i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        result = new int[n];
        result[0] = num[0];

        int i = 1;
        int j = 0;

        while(i < n){
            if(result[j] < num[i]){
                result[j+1] = num[i];
                j = j + 1;
            }else{
                int idx = bs(0,j,num[i]);
                result[idx] = num[i];
            }

            i = i + 1;
        }
        System.out.println(j+1);
        br.close();
    }

    static int bs(int l,int r,int t){
        int mid;

        while(l < r){
            mid = (l + r) / 2;

            if(result[mid] < t){
                l = mid + 1;
            }else{
                r = mid;
            }
        }

        return r;
    }
}

