import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        ArrayList<Integer> lM = new ArrayList<>();
        ArrayList<Integer> bM = new ArrayList<>();
        ArrayList<Integer> lW = new ArrayList<>();
        ArrayList<Integer> bW = new ArrayList<>();

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=n;i++)
        {
            int tmp = Integer.parseInt(st.nextToken());

            if(tmp > 0)
            {
                bM.add(tmp);
            }

            else
                lM.add(tmp);
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1;i<=n;i++)
        {
            int tmp = Integer.parseInt(st.nextToken());

            if(tmp > 0)
            {
                bW.add(tmp);
            }

            else
                lW.add(tmp);
        }

        Collections.sort(lM,(x,y) -> x - y);
        Collections.sort(bM,(x,y) -> y -x);
        Collections.sort(lW,(x,y) -> x - y);
        Collections.sort(bW,(x,y) -> y -x);

        int count = 0;

        count += sum(lM,bW);
        count += sum(lW,bM);

        System.out.println(count);

        br.close();
    }

    static int sum(ArrayList<Integer> s , ArrayList<Integer> b)
    {
        int count = 0;
        int sLen = s.size();
        int bLen = b.size();

        int sIdx = 0;
        int lIdx = 0;

        while(sIdx < sLen && lIdx < bLen)
        {
            if(Math.abs(s.get(sIdx)) > Math.abs(b.get(lIdx)))
            {
                count = count + 1;
                sIdx = sIdx + 1;
                lIdx = lIdx + 1;
            }

            else{
                lIdx = lIdx + 1;
            }
        }

        return count;
    }
}