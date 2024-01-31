import java.util.*;
import java.io.*;
public class Main {

    static int n = 0;
    static int m = 0;
    static int r = 0;
    static int result = 0;
    static int[] words; // int형만 저장한다.

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        words = new int[n+1];

        for(int i=1;i<=n;i++){
            char[] arr = bf.readLine().toCharArray();

            for(int j=0 , size = arr.length; j < size ; j++){
                words[i] |= 1 << (arr[j] - 'a'); // 각 단어가 포함한 알파벳만 마스킹
            }
        }


        if(m < 5) {
            System.out.println(0); // a,n,t,i,c 는 무조건 배워야 하기 때문
            return;
        }

        if(m == 26){
            System.out.println(n); // 다 배울수 있으면 굳이 탐색?
            return;
        }

        // 초기 값은 5개를 배운채로 go
        int v = (1 << 'a' - 'a') | (1 << 'n' - 'a') | 1 << ('t' - 'a') | 1 << ('i' - 'a') | 1 << ('c' - 'a');

        // 배울수 있는 개수 감소
        r = m - 5;


        go(0,0,v);
        System.out.println(result);
        bf.close();
    }

    static void go(int depth,int start,int v){

        if(depth == r){

            int count = 0;

            for(int i=1;i<=n;i++){

                // 해당 조합으로 해당 단어를 만들수 있는가
                if((words[i] & v) == words[i]) {
                    count++;
                }
            }

            // 갱신
            result = Math.max(result,count);
            return;
        }


        for(int i=start;i<26;i++){

            // a,n,t,i,c는 건너뛰기
            if(i == 'a' - 'a' || i == 'n' - 'a' || i == 't' - 'a' || i == 'i' - 'a' || i == 'c' - 'a')
                continue;

            // 배우고 다음으로 재귀
            go(depth + 1 , i+1,v | 1 << i);
        }
    }
}