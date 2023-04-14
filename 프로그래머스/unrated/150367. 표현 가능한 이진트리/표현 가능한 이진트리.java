class Solution {
    int index;
    char[] tr;
    
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i=0; i<numbers.length; i++) {
            // 포화이진트리 문자열 구하기
            String tree = Long.toBinaryString(numbers[i]);
            int tmp = (int) (Math.log(tree.length()+1)/Math.log(2));
            int size = (int) Math.pow(2, tmp)-1;
            if (tree.length() > size) {
                size = (int) Math.pow(2,tmp+1)-1;
            } 
            StringBuilder sb = new StringBuilder();
            for (int j=0; j<size-tree.length(); j++) sb.append("0");
            tree = sb.append(tree).toString();
            
            tr = new char[size+1];
            index = 0;
            answer[i] = preOrder(1, tree) ? 1 : 0;
        }
        return answer;
    }
    
    boolean preOrder(int num, String tree) {
        if (num*2+1 > tree.length()) {
            tr[num] = tree.charAt(index);
            index++;
            return true;
        }
        if (!preOrder(num*2, tree)) return false;
        tr[num] = tree.charAt(index);
        index++;
        if (!preOrder(num*2+1, tree)) return false;
        if (tr[num] == '0') {
            if (tr[num*2] == '1' || tr[num*2+1] == '1') return false;
        }
        return true;
    }
}