class Solution {
    int answer = 0;
    int[] numbers;
    int target;
    
    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        dfs(0, -numbers[0]);
        dfs(0, numbers[0]);
        
        return answer;
    }
    
    void dfs(int index, int now) {
        if (index == numbers.length-1) {
            if (now == target) answer++;
            return;
        }
        dfs(index+1, now + numbers[index+1]);
        dfs(index+1, now - numbers[index+1]);
    }
}