import java.util.*;
import java.io.*;

public class Main {

    static long n;
    static int k;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        String line = st.nextToken();
        k = Integer.parseInt(st.nextToken());
        n = Long.parseLong(line) + 1;

        int idx = 0;

        while(true) {
            char[] tmpArr = String.valueOf(n).toCharArray();
            int tmpLen = tmpArr.length - 1;
            int tmpCount = calCount(tmpArr);

            if(tmpCount >= k)
                break;

            while(tmpLen > idx && tmpArr[tmpLen - idx] == '5'){
                idx++;
            }

            long tmpV = (long)(Math.pow(10,idx));
            n += tmpV;

        }

        System.out.println(n);

        br.close();
    }

    static int calCount(char[] arr){
        int count = 0;
        for(int i = 0 , size = arr.length ; i < size ; i++){
            if(arr[i] == '5')
                count++;
        }
        return count;
    }
}
