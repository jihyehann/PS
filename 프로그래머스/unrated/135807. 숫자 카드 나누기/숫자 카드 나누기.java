class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int aGcd = getGcd(arrayA);
        for (int k : arrayB) {
            if (k >= aGcd && k % aGcd == 0) {
                aGcd = 0;
                break;
            }
        }

        int bGcd = getGcd(arrayB);
        for (int j : arrayA) {
            if (j >= bGcd && j % bGcd == 0) {
                bGcd = 0;
                break;
            }
        }
        return Math.max(aGcd, bGcd);
    }

    private int getGcd(int[] array) {
        int gcd = array[0];
        for (int i = 1; i < array.length; i++) {
            gcd = GCD(gcd, array[i]);
        }
        return gcd;
    }

    // 최대공약수
    int GCD(int a, int b){
        while(true){
            int r = a % b;
            if(r == 0) return b;
            a = b;
            b = r;
        }
    }
}