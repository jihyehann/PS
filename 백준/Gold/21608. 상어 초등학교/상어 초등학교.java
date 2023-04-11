import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] stu;
    static int[][] mat;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stu = new int[N*N+1][4];
        mat = new int[N][N];
        for (int i = 0; i < N*N; i++) {
            String[] input = br.readLine().split(" ");
            int index = Integer.parseInt(input[0]);
            for (int j = 1; j < 5; j++) {
                stu[index][j-1] = Integer.parseInt(input[j]);
            }
            seat(index);
        }
        System.out.println(getScore());
    }

    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    static int getScore() {
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int index = mat[i][j];
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int x = i+dir[k][0];
                    int y = j+dir[k][1];
                    if (x < 0 || x >= N || y < 0 || y >= N) continue;
                    if (Arrays.stream(stu[index]).anyMatch(n -> n == mat[x][y])) cnt++;
                }
                if (cnt == 0) continue;
                answer += Math.pow(10, cnt-1);
            }
        }
        return answer;
    }

    static void seat(int s) { // s 학생의 자리 정하기
        PriorityQueue<Position> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (mat[i][j] != 0) continue;
                int like = 0;
                int empty = 0;
                for (int k = 0; k < 4; k++) {
                    int x = i+dir[k][0];
                    int y = j+dir[k][1];
                    if (x < 0 || x >= N || y < 0 || y >= N) continue;
                    if (mat[x][y] == 0) empty++;
                    else if (Arrays.stream(stu[s]).anyMatch(n -> n == mat[x][y])) like++;
                }
                pq.add(new Position(i,j,like, empty));
            }
        }
        mat[pq.peek().x][pq.peek().y] = s;
    }

    static class Position implements Comparable<Position> {
        int x, y, like, empty;

        public Position(int x, int y, int like, int empty) {
            this.x = x;
            this.y = y;
            this.like = like;
            this.empty = empty;
        }

        @Override
        public int compareTo(Position o) {
            if (like == o.like) {
                if (empty == o.empty) {
                    if (x == o.x) {
                        return y - o.y;
                    }
                    return x - o.x;
                }
                return o.empty - empty;
            }
            return o.like - like;
        }
    }
}
