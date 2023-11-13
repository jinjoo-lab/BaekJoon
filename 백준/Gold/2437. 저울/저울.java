import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine()," ");
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int result = 0;
        for(int i=0;i<n;i++){
            if(result + 1 >= arr[i]){
                result += arr[i];
            }
            else{
                break;
            }
        }
        System.out.println(result+1);

        br.close();
    }

}