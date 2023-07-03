import java.io.*;
import java.util.*;

public class Main {
    static long n = 0;
    static long m = 0;
    static long k = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Long.parseLong(st.nextToken());
        m = Long.parseLong(st.nextToken());
        k = Long.parseLong(st.nextToken());

        HashMap<Long,Long> map = new HashMap<>();
        map.put(0l,1l);

        System.out.println(get(map , n));

        br.close();
    }

    static long get(HashMap<Long,Long> map,long key)
    {
        if(map.containsKey(key))
            return map.get(key);

        else{
            long tmp1 = 0;
            long tmp2 = 0;

            if(map.containsKey(key / m))
            {
                tmp1 = map.get(key / m);
            }

            else{
                tmp1 = get(map, key /m);
                map.put(key / m ,tmp1);
            }

            if(map.containsKey(key / k))
            {
                tmp2 = map.get(key / k);
            }

            else{
                tmp2 = get(map, key / k);
                map.put(key /k , tmp2);
            }

            map.put(key , tmp1 + tmp2);

            return map.get(key);
        }
    }
}