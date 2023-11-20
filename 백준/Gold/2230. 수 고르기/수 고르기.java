import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n = 0;
    static int m = 0;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];

        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int result = -1;
        int l = 0;
        int r = 0;

        while(l <= r && r < n){
            int minus = Math.abs(arr[r] - arr[l]);

            if(minus < m){
                r = r + 1;
            }else{
                if(result == -1){
                    result = minus;
                }else{
                    result = Math.min(result,minus);
                }

                l = l + 1;
            }
        }

        System.out.println(result);
        br.close();
    }

}
