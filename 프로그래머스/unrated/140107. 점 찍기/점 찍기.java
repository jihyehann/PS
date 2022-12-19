class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        long limit = (long) d*d;
        for (long i = 0; i <= d; i+=k) {
            answer += ((long) Math.sqrt(limit - i * i)/k + 1);
        }
        return answer;
    }
}