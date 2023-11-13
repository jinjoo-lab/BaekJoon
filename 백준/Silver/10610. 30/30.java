import java.io.*;
import java.util.*;

public class Main {
    static int result = -1;
    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        char[] arr = br.readLine().toCharArray();

        int len = arr.length;
        boolean contain = false;
        int sum = 0;

        for(int i=0;i<len;i++){
            sum += arr[i] - '0';

            if(arr[i] == '0')
                contain = true;
        }

        if(contain && sum%3==0){
            Integer[] numArr = new Integer[len];
            for(int i=0;i<len;i++){
                numArr[i] = arr[i] - '0';
            }

            Arrays.sort(numArr,(x,y) -> Integer.compare(y,x));
            for(int i=0;i<len;i++){
                sb.append(numArr[i]);
            }sb.append("\n");
            System.out.print(sb);
        }else{
            System.out.println(-1);
        }

        br.close();
    }

}


