import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();

        int len = arr.length;
        int[] num = new int[26];

        for(int i=0;i<len;i++){
            num[arr[i] - 'A'] += 1;
        }

        boolean can = true;

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<26;i++){
            while(num[i] >= 2){
                char cur = (char)('A' + i);
                sb.append(cur);
                num[i] -= 2;
            }
        }

        StringBuilder middle = new StringBuilder();

        int count  = 0;
        for(int i=0;i<26;i++){
            if(num[i] == 1 && count == 0){
                char cur = (char)('A' + i);
                middle.append(cur);
                count += 1;
            }

            else if(num[i] == 1 && count >= 1){
                System.out.println("I'm Sorry Hansoo");
                return;
            }
        }

        String start = sb.toString();
        String reverse = sb.reverse().toString();

        System.out.println(start+middle+reverse);
        br.close();

    }

}
