import java.util.*;
import java.io.*;

public class Main {
    static int n = 0;
    static int m = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");

        ArrayList<Integer> plus = new ArrayList<>();
        ArrayList<Integer> minus = new ArrayList<>();

        int max = 0;
        for(int i=0;i<n;i++){
            int cur = Integer.parseInt(st.nextToken());

            if(cur > 0)
                plus.add(cur);

            else
                minus.add(cur*-1);

            max = Math.max(Math.abs(cur),max);
        }

        Collections.sort(plus,(x,y) -> y - x);
        Collections.sort(minus,(x,y) -> y - x);

        int result = 0;
        int i = 0;

        if(!plus.isEmpty()){
        while(true){

            result += plus.get(i) * 2;
            i += m;

            if(i >= plus.size()) {
                break;
            }
        }
        }

        i = 0;
        if(!minus.isEmpty()) {
            while (true) {

                result += minus.get(i) * 2;
                i += m;

                if (i >= minus.size()) {
                    break;
                }
            }
        }

        System.out.println(result - max);


        br.close();
    }
}