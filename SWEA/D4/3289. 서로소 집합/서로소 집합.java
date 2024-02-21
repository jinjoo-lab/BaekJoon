import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int t = 0;
    static int n = 0;

    static int m = 0;

    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());

        for(int a = 1; a<=t ;a++) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            root = new int[n+1];

            for(int i = 1 ; i <= n ; i++){
                root[i] = i;
            }

            sb.append("#"+a+" ");
            for(int i = 1 ; i <= m ; i++){
                st = new StringTokenizer(br.readLine(), " ");
                int type =  Integer.parseInt(st.nextToken());
                int x =  Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                if(type == 0){
                    union(x,y);
                }else{
                    if(find(x) == find(y)){
                        sb.append(1);
                    }else{
                        sb.append(0);
                    }
                }
            }sb.append("\n");

        }

        System.out.print(sb);
        br.close();
    }

    static int find(int x){
        if(x == root[x])
            return root[x];

        return root[x] = find(root[x]);
    }

    static void union(int x,int y){
        x = find(x);
        y = find(y);

        if(x < y)
            root[x] = y;
        else
            root[y] = x;
    }
}
