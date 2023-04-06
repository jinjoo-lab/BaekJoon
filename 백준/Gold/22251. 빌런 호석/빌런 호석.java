import java.util.*;
class Main{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        int p = scan.nextInt();
        int x = scan.nextInt();
        int count = 0;
        int[][] arr = {{1, 1, 1, 0, 1, 1, 1},
                {0, 0, 1, 0, 0, 1, 0},
                {1, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 1, 0, 1, 1},
                {0, 1, 1, 1, 0, 1, 0},
                {1, 1, 0, 1, 0, 1, 1},
                {1, 1, 0, 1, 1, 1, 1},
                {1, 0, 1, 0, 0, 1, 0},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 1, 1}};
        String now = String.valueOf(x);
        String s = "";
        while(now.length() + s.length() < k){
            s += "0";
        }
        s += now;
        for(int i = 1; i <= n; i++){
            if(i == x)
                continue;
            String str = "";
            String value = String.valueOf(i);
            while(str.length() + value.length() < k){
                str += "0";
            }
            str += value;
            int sum = 0;
            for(int j = 0; j < str.length(); j++){
                int []a = arr[s.charAt(j) - '0'];
                int []b = arr[str.charAt(j) -'0'];
                for(int t = 0; t < a.length; t++){
                    if(a[t] != b[t])
                        sum++;
                }
            }
            if(sum <= p)
                count++;
        }
        System.out.println(count);
    }
}