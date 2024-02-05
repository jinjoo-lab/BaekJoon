import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {

    static int n = 0;
    static int m = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        LinkedList<Integer> list;

        for(int t = 1; t <= 10 ; t++){

            list = new LinkedList<>();
            st = new StringTokenizer(br.readLine()," ");
            n = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine()," ");

            for(int i=1;i<=n;i++){
                list.add(Integer.parseInt(st.nextToken()));
            }

            st = new StringTokenizer(br.readLine()," ");
            m = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine()," ");
            for(int i = 1; i<= m ;i ++){
                char cur = st.nextToken().charAt(0);
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                LinkedList<Integer> tmpList = new LinkedList<>();
                for(int j=1;j<=y;j++){
                    tmpList.add(Integer.parseInt(st.nextToken()));
                }

                list.addAll(x,tmpList);
            }


            sb.append("#"+t+" ");
            for(int i = 0; i < 10 ; i++){
                sb.append(list.get(i)+" ");
            }sb.append("\n");

        }


        System.out.print(sb);
        br.close();
    }
}
