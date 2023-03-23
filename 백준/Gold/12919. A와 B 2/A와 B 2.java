
import java.io.*;
import java.util.*;

public class Main {
    static String start;
    static String last;
    static boolean done = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        start = br.readLine();
        last = br.readLine();

        dfs(last);
        if(done)
            System.out.println(1);
        else{
            System.out.println(0);
        }
        br.close();
    }

    static void dfs(String cur)
    {
        if(cur.equals(start)) {
            done = true;
            return;
        }

        if(cur.length()<=start.length()) {
            return;
        }

        if(cur.charAt(cur.length()-1)=='A')
        {
            dfs(cur.substring(0,cur.length()-1));
        }

        if(cur.charAt(0)=='B')
        {
            StringBuffer sb = new StringBuffer(cur.substring(1));
            dfs(sb.reverse().toString());
        }
    }
}