import java.util.*;
import java.io.*;

public class Main {
    static HashSet<String> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        set = new HashSet<>();
        String last = br.readLine();
        String start = br.readLine();

        Queue<String> q= new LinkedList<>();
        q.add(start);

        int result = 0;

        while(!q.isEmpty())
        {
            String tmp = q.poll();

            if(tmp.charAt(tmp.length() -1) == 'A') {
                String tmp2 = tmp.substring(0, tmp.length() - 1);
                set.add(tmp2);

                if(tmp2.length() > last.length())
                    q.add(tmp2);
            }
            if(tmp.charAt(tmp.length() -1) == 'B')
            {
                String tmp3 = new StringBuffer(tmp.substring(0,tmp.length()-1)).reverse().toString();
                set.add(tmp3);

                if(tmp3.length() > last.length())
                    q.add(tmp3);
            }

            if(set.contains(last))
            {
                result = 1;
                break;
            }
        }

        System.out.println(result);

        br.close();
    }
}