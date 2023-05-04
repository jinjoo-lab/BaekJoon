import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int l = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String first = br.readLine();
        String pattern = br.readLine();
        int plen = pattern.length() -1;
        int idx = 0;
        int answer = 0;
        while(idx <= first.length()-1)
        {
            if(idx>=0&&idx+plen <= first.length() -1)
            {
                if(first.substring(idx,idx + plen +1).equals(pattern)) {
                    answer = answer + 1;
                    idx = idx + plen + 1;
                }

                else{
                    idx = idx + 1;
                }
            }

            else{
                break;
            }
        }
        System.out.println(answer);
        br.close();
    }
}