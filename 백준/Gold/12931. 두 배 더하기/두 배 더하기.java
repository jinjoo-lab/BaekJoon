import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        arr = new int[n+1];
        st = new StringTokenizer(br.readLine(), " ");

        for(int i=1;i<=n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int count = 0;

        while(true){
            int sum = 0;
            for(int i=1;i<=n;i++){
                sum += arr[i];
            }

            if(sum == 0)
                break;

            if(can()){
                for(int i=1;i<=n;i++){
                    arr[i] = arr[i] / 2;
                }

                count = count + 1;
            }

            else{
                for(int i=1;i<=n;i++){
                    if(arr[i] % 2 == 1){
                        arr[i] -= 1;
                        count = count + 1;
                    }
                }
            }
        }

        System.out.println(count);
        br.close();
    }

    static boolean can(){
        for(int i=1;i<=n;i++){
            if(arr[i] % 2 == 1)
                return false;
        }

        return true;
    }
}