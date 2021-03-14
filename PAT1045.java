import org.omg.PortableInterceptor.INACTIVE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PAT1045 {
    static List<Integer> ans = new ArrayList<>();
    static int[] order;
    static Set<Integer> order_set = new HashSet<>();
    static int max = 0;

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N_color = Integer.parseInt(br.readLine());
        String[] spl = br.readLine().split(" ");
        int N_order = Integer.parseInt(spl[0]);
        order = new int[N_order];
        for (int i = 1; i <= N_order; i++) {
            order[i - 1] = Integer.parseInt(spl[i]);
            order_set.add(Integer.parseInt(spl[i]));
        }
        String[] spl2 = br.readLine().split(" ");
        int N = Integer.parseInt(spl2[0]);
        int[] original = new int[N];
        for (int i = 1; i <= N; i++) {
            original[i - 1] = Integer.parseInt(spl2[i]);
        }
        dfs(original, 0);
        System.out.println(max);

    }

    private static void dfs (int[] original, int cur) {
        if (cur == original.length) {
            if (isInOder(0, 0)) {
                max = Math.max(max, ans.size());
            }
            return;
        }
        if (!order_set.contains(original[cur])) {
            dfs(original, cur + 1);
        } else {
            ans.add(original[cur]);
            dfs(original, cur + 1);
            ans.remove(ans.size() - 1);
            dfs(original, cur + 1);
        }
    }

    private static boolean isInOder (int start_order, int start_strb) {
        if (start_strb >= ans.size() && start_order < order.length) return true;
        if (start_order == order.length) return false;

        while (start_order < order.length && order[start_order] != ans.get(start_strb)) {
            start_order++;
        }
        while (start_strb < ans.size() - 1 && ans.get(start_strb) == ans.get(start_strb + 1)) {
            start_strb++;
        }
        return isInOder(start_order, start_strb + 1);
    }
}
