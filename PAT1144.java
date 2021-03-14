import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PAT1144 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<List<Integer>> total_list = new ArrayList<>();
        for (int i = 0; i <= 1000; i++) {
            total_list.add(new ArrayList<>());
        }
        for (int i = 0; i < N; i++) {
            int n = sc.nextInt();
            int integer_part = n / 100;
            int remain_part = n % 100;
            if (n < 0) {
                continue;
            } else if (n >= 100001) {
                continue;
            } else {
                total_list.get(integer_part).add(remain_part);
            }
        }
        for (int i = 0; i < 1000; i++) {
            if (total_list.get(i).size() < 100) {
                int smallest;
                if (i == 0) {
                    smallest = findSmallest(total_list.get(i), true);
                } else {
                    smallest = findSmallest(total_list.get(i), false);
                }

                if (smallest == 0 && i == 0) {
                    continue;
                } else {
                    System.out.println(i * 100 + smallest);
                    return;
                }
            }
        }
    }

    private static int findSmallest (List<Integer> list, boolean first) {
        Collections.sort(list);
        boolean[] b = new boolean[100];
        for (int i : list) {
            b[i] = true;
        }
        for (int i = 0; i < 100; i++) {
            if (i == 0 && first) {
                continue;
            }
            if (b[i] == false) {
                return i;
            }
        }
        return -1;
    }
}
