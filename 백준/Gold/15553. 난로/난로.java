import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int result = 0;
        int[] arr = new int[n];

        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(br.readLine());

            if(i == n -1) {
                result = arr[i] - arr[0] + 1;
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (x,y) -> y - x
        );

        for(int i=n-1;i>=1;i--){
            pq.add(arr[i] - arr[i-1] - 1);
        }

        for(int i=0;i<m - 1;i ++){

            if(!pq.isEmpty()){
                int tmp = pq.poll();
                result -= tmp;
            }else{
                break;
            }
        }

        System.out.println(result);
        br.close();
    }
}

