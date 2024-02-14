import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    static boolean[] isSelected;

    static String line;

    static ArrayList<Garo> list;

    static char[] tmpArr;

    static HashSet<String> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        line = br.readLine();

        tmpArr = line.toCharArray();
        int len = tmpArr.length;

        Stack<Integer> stack = new Stack<>();
        list = new ArrayList<>();

        for(int i=0; i< len;i++){
            if(tmpArr[i] == '('){
                stack.push(i);
            }else if(tmpArr[i] == ')'){
                int sIdx = stack.pop();
                int eIdx = i;

                list.add(new Garo(sIdx,eIdx));
            }
        }

        int listSize = list.size();
        isSelected = new boolean[listSize];

        subSet(0,listSize,0);

        ArrayList<String> result = new ArrayList<>(set);

        Collections.sort(result,(x,y) -> x.compareTo(y));

        sb = new StringBuilder();
        for(String cur : result){
            sb.append(cur+"\n");
        }
        System.out.print(sb);
        br.close();
    }
    static void make(int N){
        sb = new StringBuilder();
        boolean[] tmpV = new boolean[tmpArr.length];

        for(int i=0;i<N;i++){
            if(isSelected[i]){
                tmpV[list.get(i).sIdx] = true;
                tmpV[list.get(i).eIdx] = true;
            }
        }

        for(int i=0,size = tmpV.length; i < size; i++){
            if(!tmpV[i]) sb.append(tmpArr[i]);
        }

        set.add(sb.toString());
    }
    static void subSet(int idx,int N,int count){

        if(idx == N){

            if(count == 0)
                return;

            make(N);
            return;
        }

        isSelected[idx] = true;
        subSet(idx+1,N,count + 1);
        isSelected[idx] = false;
        subSet(idx+1,N,count);
    }

}


class Garo{
    int sIdx;
    int eIdx;

    Garo(int sIdx,int eIdx){
        this.sIdx = sIdx;
        this.eIdx = eIdx;
    }
}
