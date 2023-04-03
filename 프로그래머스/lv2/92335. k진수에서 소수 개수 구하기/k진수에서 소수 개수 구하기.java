class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        StringBuilder num = new StringBuilder();
        while (n > 0) {
            if (n%k == 0) {
                if (isPrime(num.reverse().toString())) answer++;
                num.setLength(0);
            } else {
                num.append(n%k);
            }
            n/=k;
        }
        if (isPrime(num.reverse().toString())) answer++;
        return answer;
    }

    boolean isPrime(String strNum) {
        if (strNum.isEmpty()) return false;
        long num = Long.parseLong(strNum);
        if (num < 2) return false;
        for (long i=2; i<=Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}