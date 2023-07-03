import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    static HashMap<String,Integer> pocket1;
    static HashMap<Integer,String> pocket2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        pocket1 = new HashMap<>();
        pocket2 = new HashMap<>();

        for(int i=1;i<=n;i++) {
            String line = br.readLine();
            pocket1.put(line, i);
            pocket2.put(i, line);
        }

        StringBuilder sb = new StringBuilder();

        for(int i=1;i<=m;i++)
        {
            String q = br.readLine();

            if(pocket1.containsKey(q))
            {
                sb.append(pocket1.get(q)+"\n");
            }

            else{
                sb.append(pocket2.get(Integer.parseInt(q))+"\n");
            }
        }

        System.out.print(sb);
        br.close();
    }
}