import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int n;
    static int m;
    static int k;
    static int[][] winterFood;
    static Ground[][] ground;
    static int[][] mv = {
            {1, 1}, {1, 0}, {1, -1},
            {0, 1}, {0, -1},
            {-1, 1}, {-1, 0}, {-1, -1}
    };

    static class Ground {
        int food = 5;
        List<Integer> treeAges = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        input();
        for (int i = 0; i < k; i++) {
            oneYear();
        }
        System.out.println(countTree());
    }

    private static int countTree() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cnt += ground[i][j].treeAges.size();
            }
        }
        return cnt;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        k = Integer.parseInt(input[2]);

        winterFood = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split(" ");
            winterFood[i] = Arrays.stream(row).mapToInt(Integer::parseInt).toArray();
        }

        ground = new Ground[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ground[i][j] = new Ground();
            }
        }
        for (int i = 0; i < m; i++) {
            String[] row = br.readLine().split(" ");
            int[] xyz = Arrays.stream(row).mapToInt(Integer::parseInt).toArray();
            ground[xyz[0]-1][xyz[1]-1].treeAges.add(xyz[2]);
        }
    }

    private static void oneYear() {
        springAndSummer();
        fallAndWinter();
    }

    private static void springAndSummer() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 봄
                int die = 0;
                ground[i][j].treeAges.sort(Integer::compareTo);
                for (int k = 0; k < ground[i][j].treeAges.size(); k++) {
                    int age = ground[i][j].treeAges.get(k);
                    if (ground[i][j].treeAges.get(k) > ground[i][j].food) {
                        die += (age/2);
                        ground[i][j].treeAges.remove(k);
                        k--;
                    } else {
                        ground[i][j].food -= age;
                        ground[i][j].treeAges.set(k, age+1);
                    }
                }
                // 여름
                ground[i][j].food += die;
            }
        }
    }

    private static void fallAndWinter() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 가을
                for (Integer age : ground[i][j].treeAges) {
                    if (age % 5 == 0) {
                        for (int l = 0; l < 8; l++) {
                            int nx = i + mv[l][0];
                            int ny = j + mv[l][1];
                            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                                continue;
                            }
                            ground[nx][ny].treeAges.add(1);
                        }
                    }
                }
                // 겨울
                ground[i][j].food += winterFood[i][j];
            }
        }
    }
}
