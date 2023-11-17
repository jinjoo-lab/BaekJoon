import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static LinkedList<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine(), " ");

            int num = Integer.parseInt(st.nextToken());

            list = new LinkedList<>();
            int count = 0;

            for(int j=0;j<20;j++){
                int tmp = Integer.parseInt(st.nextToken());
                int tmpIdx = -1;

                for(int k = 0 ; k < list.size(); k ++){

                    if(list.get(k) > tmp){
                        tmpIdx = k;
                        break;
                    }
                }

                if(tmpIdx == -1){
                    list.add(tmp);
                }else{
                    count += (list.size() - tmpIdx);
                    list.add(tmpIdx,tmp);
                }
            }

            sb.append(num+" "+count+"\n");
        }

        System.out.print(sb);
        br.close();
    }
}

