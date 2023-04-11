class Solution {
    int[][] arr;
    public int[] solution(int[][] arr) {
        this.arr = arr;
        return solve(0, 0, arr.length);
    }
    
    int[][] mv = {{0,0}, {0,1}, {1,0}, {1,1}};
    int[] solve(int x, int y, int n) {
        if (n == 1) {
            if (arr[x][y] == 1) {
                return new int[] {0,1};
            }
            return new int[] {1,0};
        }
        int[] result = new int[2];
        for (int i=0; i<4; i++) {
            int[] tmp = solve(x + mv[i][0] * n/2, y + mv[i][1] * n/2, n/2);
            result[0] += tmp[0];
            result[1] += tmp[1];
        }
        if (result[0] == 0) {
            return new int[] {0, 1};
        } else if(result[1] == 0) {
            return new int[] {1, 0};
        } else {
            return result;
        }
    }
}