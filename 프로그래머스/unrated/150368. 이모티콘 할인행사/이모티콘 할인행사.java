class Solution {
    int[][] users;
    int[] emoticons;
    int[] discount;
    int[] answer = new int[] {0,0};

    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        discount = new int[emoticons.length];
        setDiscount(0);
        return answer;
    }
    
    void setDiscount(int i) {
        if (i == discount.length) {
            setAnswer();
            return;
        }
        for (int j=10; j<=40; j+=10) {
            discount[i] = j;
            setDiscount(i+1);
        }
    }
    
    void setAnswer() {
        int cnt = 0;
        int amount = 0;
        for (int i = 0; i<users.length; i++) {
            int cost = 0;
            for (int j=0; j<emoticons.length; j++) {
                if (discount[j] >= users[i][0]) {
                    cost += (emoticons[j]*(100 - discount[j])/100);
                }
            }
            if (cost >= users[i][1]) {
                cnt++;
            } else {
                amount += cost;
            }
        }
        if (cnt > answer[0] || (cnt == answer[0] && amount > answer[1])) {
            answer[0] = cnt;
            answer[1] = amount;
        } 
    }
}