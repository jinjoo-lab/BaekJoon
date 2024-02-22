
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main {
    static int n = 0;

    static int m = 0;

    static char[] arr;

    static boolean[] v;

    static StringBuilder sb = new StringBuilder();

    static HashSet<String> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");



        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new char[m+1];
        v = new boolean[m+1];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1 ; i <= m ; i++){
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr,1,m+1);

        travel(0,0,0,1);

        ArrayList<String> result = new ArrayList<>(set);

        Collections.sort(result);

        for(String cur : result){
           sb.append(cur+"\n");
        }

        System.out.print(sb);

        br.close();
    }

    static void travel(int idx,int x,int y,int cnt){
        if(idx == n){
            if(x >= 1 && y >= 2){
                StringBuilder tmp = new StringBuilder();
                for(int i = 1 ; i <= m ;i++){
                    if(v[i]) tmp.append(arr[i]);
                }

                set.add(tmp.toString());
            }
            return;
        }

        for(int i = cnt ; i <= m ; i++){
            if(isEven(arr[i])){
                v[i] = true;
                travel(idx + 1 , x + 1, y,i+1);
                v[i] = false;
            }else{
                v[i] = true;
                travel(idx + 1 , x, y + 1,i+1);
                v[i] = false;
            }
        }
    }

    static boolean isEven(char cur){
        if(cur == 'a' || cur == 'e' || cur == 'i' || cur == 'o' || cur == 'u')
            return true;

        return false;
    }
}
