import java.util.*;

class Solution {
    
    static long result = Long.MIN_VALUE;
    
    static int n;
    
    public long solution(int[] sequence) {
        long answer = 0;
        
        n = sequence.length;
        
        int[] seq1 = new int[n];
        int[] seq2 = new int[n];
        
        int idx = -1;
        
        for(int i = 0 ; i < n ; i++){
            seq1[i] = sequence[i] * idx;
            idx *= -1;
        }
        
        idx = 1;
        
        for(int i = 0 ; i < n ; i++){
            seq2[i] = sequence[i] * idx;
            idx *= -1;
        }
    
        find(seq1);
        find(seq2);
        
        answer = result;
        
        return answer;
    }
    
    static void print(int[] arr){
        for(int i = 0 ; i < n ; i++){
            System.out.print(arr[i]+" ");
        }System.out.println();
    }
    
    static void find(int[] arr){
        int l = 0;
        int r = 1;
        long tmp = arr[0];
        result = Math.max(result,tmp);
        
        while(l <= r && r < n){
            if(tmp + arr[r] < arr[r]){
                tmp = arr[r];
                result = Math.max(tmp,result);
                l = r;
                r = r + 1;
            }else{
                tmp += arr[r];
                result = Math.max(tmp,result);
                r = r + 1;
            }
        
        }
    }
}