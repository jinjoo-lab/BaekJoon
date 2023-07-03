import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static HashSet<String> hear = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=1;i<=n;i++)
        {
            String line = br.readLine();
            hear.add(line);
        }

        int count = 0;
        PriorityQueue<String> pq = new PriorityQueue<>(
                (x,y) -> x.compareTo(y)
        );
        for(int i=1;i<=m;i++)
        {
            String line2 = br.readLine();
            if(hear.contains(line2)){
                count = count + 1;
                pq.add(line2);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty())
        {
            sb.append(pq.poll()+"\n");
        }
        System.out.println(count);
        System.out.println(sb);
        br.close();
    }
}
