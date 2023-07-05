import java.io.*;
import java.util.*;

public class Main {
    static LinkedHashSet<Integer> set = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        while(st.hasMoreTokens())
        {
            int num = Integer.parseInt(st.nextToken());
            set.add(num);
        }

        StringBuilder sb = new StringBuilder();
        for(int cur : set)
        {
            sb.append(cur+" ");
        }

        System.out.println(sb);
        br.close();
    }
}
