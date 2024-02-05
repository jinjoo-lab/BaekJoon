

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static int n = 0;
    static int m = 0;

    static LinkedList<Integer> list = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=1;i<=n;i++){
            list.add(i);
        }

        sb.append("<");

        int tmpIdx = m-1;

        while(!list.isEmpty()){

            int removeV = list.remove(tmpIdx);

            sb.append(removeV);

            if(list.size() >= 1)
                sb.append(", ");

            tmpIdx += m-1;

            if(list.size() == 0)
                break;

            if(tmpIdx >= list.size()){
                tmpIdx %= list.size();
            }
        }

        sb.append(">");
        System.out.println(sb);

        br.close();
    }
}