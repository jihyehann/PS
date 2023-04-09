import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Parking> cars = new TreeMap<>();
        for (String r : records) {
            String[] c = r.split(" ");
            if (c[2].equals("IN")) {
                if (cars.containsKey(c[1])) {
                    cars.get(c[1]).in(c[0]);
                } else {
                    cars.put(c[1], new Parking(c[0]));
                }
            } else {
                cars.get(c[1]).out(c[0]);
            }
        }
        int[] answer = new int[cars.size()];
        int i = 0;
        for (String key : cars.keySet()) {
            if (!cars.get(key).isOut) {
                cars.get(key).out("23:59");
            } 
            int fee = fees[1];
            if (cars.get(key).sum > fees[0]) {
                fee += Math.ceil((double)(cars.get(key).sum - fees[0])/fees[2])*fees[3];
            } 
            answer[i++] = fee;
        }
        return answer;
    }
    
    
    class Parking {
        int sum = 0;
        String inTime; 
        boolean isOut = false;
        
        public Parking(String in) {
            inTime = in;
        }
        
        void in(String in) {
            inTime = in;
            isOut = false;
        }
        
        void out(String out) {
            sum += (Integer.parseInt(out.substring(0,2)) -  Integer.parseInt(inTime.substring(0,2)))*60;
            sum += Integer.parseInt(out.substring(3,5)) - Integer.parseInt(inTime.substring(3,5));
            isOut = true;
        }
    }
}