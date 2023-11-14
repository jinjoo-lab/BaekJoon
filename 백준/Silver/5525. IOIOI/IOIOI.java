import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        char[] arr = br.readLine().toCharArray();

        int result = 0;
        int count = 0;

        for(int i=1;i<m-1;i++){
            if(arr[i-1] == 'I' && arr[i] =='O' && arr[i+1] =='I'){
                count += 1;

                if(count == n){
                    count -= 1;
                    result += 1;
                }

                i ++;
            }else{
                count = 0;
            }
        }

        System.out.println(result);
        br.close();
    }

}
