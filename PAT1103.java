import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PAT1103 {
    static List<Integer> ans = new ArrayList<>();
    static int min = 0;
    static int K;
    static int P;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        K = sc.nextInt();
        P = sc.nextInt();
        int max_num = (int)Math.sqrt(N);
        backtrack(N, max_num, 0, new ArrayList<>());
        if (ans.size() == 0) {
            System.out.println("Impossible");
            return;
        }
        System.out.print(N + " = ");
        StringBuilder strb = new StringBuilder();
        for (int i : ans) {
            strb.append(i).append('^').append(P).append(" + ");
        }
        strb.delete(strb.length() - 3, strb.length());
        System.out.println(strb);
    }

    private static void backtrack(int target, int pre, int sum, List<Integer> temp) {
        if (temp.size() == K && sum == target && pre > min) {
            min = pre;
            ans = new ArrayList<>(temp);
            return;
        }
        if (sum > target) {
            return;
        }
        if (temp.size() == K) {
            return;
        }
        for (int i = pre; i >= 1; i--) {
            sum += Math.pow(i, P);
            temp.add(i);
            backtrack(target, i, sum, temp);
            sum -= Math.pow(i, P);
            temp.remove(temp.size() - 1);
        }
    }
}
