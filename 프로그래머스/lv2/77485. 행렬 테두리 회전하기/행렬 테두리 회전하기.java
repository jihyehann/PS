import java.util.Arrays;

class Solution {
    static int[][] matrix;
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        matrix = new int[rows+1][columns+1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                matrix[i][j] = (i-1)*columns + j;
            }
        }
        for (int i = 0; i < queries.length; i++) {
            answer[i] = rotate(queries[i]);
        }
        return answer;
    }

    private int rotate(int[] query) {
        int x1 = query[0];
        int y1 = query[1];
        int x2 = query[2];
        int y2 = query[3];

        int now = matrix[x1][y1];
        matrix[x1][y1] = matrix[x1+1][y1];
        int minNum = matrix[x1+1][y1];

        // 위
        for (int j = y1; j < y2; j++) {
            int next = matrix[x1][j+1];
            matrix[x1][j+1] = now;
            minNum = Math.min(minNum, now);
            now = next;
        }
        // 오른쪽
        for (int i = x1+1; i < x2; i++) {
            int next = matrix[i][y2];
            matrix[i][y2] = now;
            minNum = Math.min(minNum, now);
            now = next;
        }
        // 아래
        for (int j = y2; j >= y1; j--) {
            int next = matrix[x2][j];
            matrix[x2][j] = now;
            minNum = Math.min(minNum, now);
            now = next;
        }
        // 왼쪽
        for (int i = x2-1; i > x1; i--) {
            int next = matrix[i][y1];
            matrix[i][y1] = now;
            minNum = Math.min(minNum, now);
            now = next;
        }
        return minNum;
    }
}
