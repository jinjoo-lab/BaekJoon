import java.util.*;
import java.io.*;

public class Main {
    static HashMap<Character,ArrayList<Integer>> map = new HashMap<>();
    static String s;
    static String p;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        p = br.readLine();

        int sIdx = 0;
        int lIdx = 1;
        int count = 0;
        while(lIdx <= p.length()){
            String sub = p.substring(sIdx,lIdx);
            if(s.contains(sub))
            {
                lIdx = lIdx + 1;
            }

            else{
                count = count + 1;
                sIdx = lIdx -1;
                lIdx = sIdx + 1;
            }
        }
        count = count + 1;

        System.out.println(count);
        br.close();
    }
}
