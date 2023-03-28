import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M;
    static int[][] mat;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        mat = new int[N][M];
        int R = Integer.parseInt(input[2]);
        for (int i = 0; i < N; i++) {
            mat[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        input = br.readLine().split(" ");
        for (int i = 0; i < R; i++) {
            int num = Integer.parseInt(input[i]);
            switch (num) {
                case 1: upDown(); break;
                case 2: leftRight(); break;
                case 3: rotateR(); break;
                case 4: rotateL(); break;
                case 5: clockwise(); break;
                default: counterclockwise();
            }
        }
        print();
    }

    static void print() {
        for (int[] row : mat) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    static void upDown() {
        int[][] newMat = new int[N][M];
        for (int i = 0; i < N; i++) {
            newMat[i] = mat[N-i-1];
        }
        mat = newMat;
    }

    static void leftRight() {
        int[][] newMat = new int[N][M];
        for (int j = 0; j < M; j++) {
            for (int i = 0; i < N; i++) {
                newMat[i][j] = mat[i][M-j-1];
            }
        }
        mat = newMat;
    }

    static void rotateR() {
        int[][] newMat = new int[M][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newMat[j][N-1-i] =  mat[i][j];
            }
        }
        mat = newMat;
        N = mat.length;
        M = mat[0].length;
    }

    static void rotateL() {
        int[][] newMat = new int[M][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newMat[M-j-1][i] =  mat[i][j];
            }
        }
        mat = newMat;
        N = mat.length;
        M = mat[0].length;
    }

    static int[][] mv = {{0,1},{1,0},{0,-1},{-1,0}};
    static void clockwise() {
        int[][] newMat = new int[N][M];
        int x = 0;
        int y = 0;
        int dir = 0;
        while (dir < 3) {
            for (int i = x; i < x + N/2; i++) {
                for (int j = y; j < y + M/2; j++) {
                    int nx = i + mv[dir][0] * N/2;
                    int ny = j + mv[dir][1] * M/2;
                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                        dir++;
                        nx = i + mv[dir][0] * N/2;
                        ny = j + mv[dir][1] * M/2;
                    }
                    newMat[nx][ny] = mat[i][j];
                }
            }
            x = x + mv[dir][0] * N/2;
            y = y + mv[dir][1] * M/2;
        }
        mat = newMat;
    }

    static void counterclockwise() {
        int[][] newMat = new int[N][M];
        int x = 0;
        int y = M/2;
        int dir = 0;
        while (dir < 3) {
            for (int i = x; i < x + N/2; i++) {
                for (int j = y; j < y + M/2; j++) {
                    int nx = i + mv[dir][0] * N/2;
                    int ny = j - mv[dir][1] * M/2;
                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                        dir++;
                        nx = i + mv[dir][0] * N/2;
                        ny = j - mv[dir][1] * M/2;
                    }
                    newMat[nx][ny] = mat[i][j];
                }
            }
            x = x + mv[dir][0] * N/2;
            y = y - mv[dir][1] * M/2;
        }
        mat = newMat;
    }
}
