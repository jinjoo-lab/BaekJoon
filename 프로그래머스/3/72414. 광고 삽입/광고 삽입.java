import java.util.*;

class Solution {
    
    static int n;
    static int[] sum;
    static int at;
    static int[] tArr;
    
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        
        n = toTime(play_time);
        at = toTime(adv_time);
        
        sum = new int[n + 2];
        
        for(int i = 0 , size = logs.length ; i < size ; i++) {
            StringTokenizer st = new StringTokenizer(logs[i],"-");
            
            int stt = toTime(st.nextToken());
            int ett = toTime(st.nextToken());
            
            sum[stt] += 1;
            sum[ett] -= 1;
        }
        
        for(int i = 1 ; i <= n + 1 ; i++) {
            sum[i] += sum[i-1];
        }
        
        long max = 0;
        int rere = 0;
        long cur = max;
        
        for(int i = at; i <= n ; i++) {
            
            cur += sum[i] - sum[i - at];
            
            if(cur > max) {
                max = cur;
                rere = i - at + 1;
            }
        }
        

        answer = toStr(rere);
        
        return answer;
    }
    static String toStr(int time) {
        int tt = time / 3600;
        time %= 3600;
        int mm = time / 60;
        time %= 60;
        int ss = time;
        
        String ts = (tt <= 9) ? "0"+tt : tt+"";
        String ms = (mm <= 9) ? "0"+mm : mm+"";
        String sss = (ss <= 9) ? "0"+ss : ss+"";
        
        return ts+":"+ms+":"+sss;
    }
    
    static int toTime(String cur) {
        int re = 0;
        
        StringTokenizer st = new StringTokenizer(cur,":");
        
        re += 3600 * Integer.parseInt(st.nextToken());
        re += 60 * Integer.parseInt(st.nextToken());
        re += Integer.parseInt(st.nextToken());
        
        return re;
    }
}