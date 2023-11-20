import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n = 0;
    static int[][] arr;

    static int[] result;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n][2];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[i][0] = x;
            arr[i][1] = y;
        }

        Arrays.sort(arr,(x,y) -> x[0] - y[0]);
        result = new int[n];

        result[0] = arr[0][1];
        int i = 1;
        int j = 0;

        while(i < n){
            if(result[j] < arr[i][1]){
                result[j+1] = arr[i][1];
                j = j + 1;
            }else{
                int idx = bs(0,j,arr[i][1]);

                result[idx] = arr[i][1];
            }

            i = i + 1;
        }
        System.out.println(n - (j + 1));


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
