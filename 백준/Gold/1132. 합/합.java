import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static Node[] sum = new Node[28];
    static boolean[] one = new boolean[28];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        for(int i=0;i<=27;i++){
            sum[i] = new Node(i,0l);
        }

        ArrayList<char[]> list = new ArrayList<>();

        for(int i=1;i<=n;i++){
            char[] arr = br.readLine().toCharArray();
            list.add(arr);


            one[arr[0] - 'A'] = true;

            for(int j=0;j<arr.length;j++){
                int cur = arr[j] - 'A';

                sum[cur].num += (long)Math.pow(10,arr.length - j - 1);
            }
        }

        Arrays.sort(sum,(x,y) -> Long.compare(y.num ,x.num));
        int count = 0;
        for(int i=0;i<28;i++){
            if(sum[i].num != 0l)
                count = count + 1;
        }

        int[] num = new int[28];
        int start = 9;

        if(count < 10){
           for(int i=0;i<count;i++){
               num[sum[i].idx] = start;
               start = start - 1;
           }
        }else{
            if(one[sum[9].idx]){
                int find = 9;

                for(int i=8;i>=0;i--){
                    if(!one[sum[i].idx]){
                        find = i;
                        break;
                    }
                }

                for(int i=find;i<9;i++){
                    Node tmp = new Node(sum[i].idx,sum[i].num);
                    sum[i] = sum[i+1];
                    sum[i+1] = tmp;
                }
            }

            for(int i=0;i<count;i++){
                num[sum[i].idx] = start;
                start = start - 1;
            }
        }

        long sum = 0;
        for(char[] arr : list){
            for(int i=0;i<arr.length;i++){
                sum += (num[arr[i] - 'A'] * Math.pow(10,arr.length - i - 1));
            }
        }
        System.out.println(sum);

        br.close();
    }
}
class Node{
    int idx;
    long num;

    Node(int idx,long num){
        this.idx = idx;
        this.num = num;
    }
}
