import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PAT1048 {

    static Set<Integer> set = new HashSet<>();
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        boolean isDup = false;
        int dup_count = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info1 = br.readLine().split(" ");
        int N = Integer.parseInt(info1[0]);
        int M = Integer.parseInt(info1[1]);
        String[] coins = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {

            int coin = Integer.parseInt(coins[i]);
            if (coin == M - coin) {
                dup_count++;
            }
            set.add(coin);
            list.add(coin);
        }
        if (dup_count >= 2) {
            isDup = true;
        }
        Collections.sort(list);
        for (int i : list) {
            if (i > M / 2) {
                System.out.println("No Solution");
                return;
            }
            if (set.contains(M - i) && i != M - i) {
                System.out.println(i + " " + (M - i));
                return;
            }
            if (i == M - i && isDup == true) {
                System.out.println(i + " " + (M - i));
                return;
            }
        }

    }
}
