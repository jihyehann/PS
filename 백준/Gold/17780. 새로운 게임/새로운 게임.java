import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N, K;
    static Square[][] mat;
    static Piece[] pieces;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        mat = new Square[N][N];
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                mat[i][j] = new Square(Integer.parseInt(input[j]));
            }
        }
        pieces = new Piece[K];
        for (int i = 0; i < K; i++) {
            input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0])-1;
            int y = Integer.parseInt(input[1])-1;
            mat[x][y].pieces.add(i);
            pieces[i] = new Piece(x, y, Integer.parseInt(input[2])-1);
        }

        int answer = 0;
        int[][] mv = {{0,1}, {0,-1}, {-1,0}, {1,0}};
        while (answer < 1000) {
            answer++;
            for (int i = 0; i < K; i++) {
                Piece p = pieces[i];
                if (mat[p.x][p.y].pieces.get(0) != i) continue;

                int nx = p.x + mv[p.dir][0];
                int ny = p.y + mv[p.dir][1];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || mat[nx][ny].color == 2) {
                    if (p.dir % 2 == 0) p.dir++;
                    else p.dir--;
                    nx = p.x + mv[p.dir][0];
                    ny = p.y + mv[p.dir][1];
                }
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || mat[nx][ny].color == 2) continue;

                if (mat[p.x][p.y].pieces.size() + mat[nx][ny].pieces.size() >= 4) {
                    System.out.println(answer);
                    return;
                }

                int x = p.x;
                int y = p.y;
                if (mat[nx][ny].color == 0) {
                    for (int j = 0; j < mat[x][y].pieces.size(); j++) {
                        mat[nx][ny].pieces.add(mat[x][y].pieces.get(j));
                        pieces[mat[x][y].pieces.get(j)].x = nx;
                        pieces[mat[x][y].pieces.get(j)].y = ny;
                    }
                }
                else { // 순서 거꾸로 이동
                    for (int j = mat[p.x][p.y].pieces.size()-1; j >= 0 ; j--) {
                        mat[nx][ny].pieces.add(mat[x][y].pieces.get(j));
                        pieces[mat[x][y].pieces.get(j)].x = nx;
                        pieces[mat[x][y].pieces.get(j)].y = ny;
                    }
                }
                mat[x][y].pieces.clear();
            }
        }
        System.out.println(-1);
    }
    static class Square{
        int color;
        List<Integer> pieces = new ArrayList<>();

        public Square(int color) {
            this.color = color;
        }
    }

    static class Piece {
        int x;
        int y;
        int dir;

        public Piece(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}
