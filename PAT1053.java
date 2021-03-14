import sun.reflect.generics.tree.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PAT1053 {
    static Map<Integer, TreeNode> map = new HashMap<>();
    static List<List<Integer>> ans = new ArrayList<>();

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info1 = br.readLine().split(" ");
        int N = Integer.parseInt(info1[0]);
        int N_leaf = Integer.parseInt(info1[1]);
        int target = Integer.parseInt(info1[2]);
        String[] weights_s = br.readLine().split(" ");
        int[] weights = new int[N];
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(weights_s[i]);
        }
        for (int i = 0; i < N_leaf; i++) {
            String[] line = br.readLine().split(" ");
            int fa_node_id = Integer.parseInt(line[0]);
            int N_child = Integer.parseInt(line[1]);
            if (!map.containsKey(fa_node_id)) {
                map.put(fa_node_id, new TreeNode(fa_node_id, weights[fa_node_id]));
            }
            for (int j = 2; j < line.length; j++) {
                int child_id = Integer.parseInt(line[j]);
                if (!map.containsKey(child_id)) {
                    map.put(child_id, new TreeNode(child_id, weights[child_id]));
                }
                map.get(fa_node_id).children.add(child_id);
            }
        }

        dfs(map.get(0), 0, target, new ArrayList<>());
        Collections.sort(ans, new Comparator2());
        for (List<Integer> l : ans) {
            StringBuilder strb = new StringBuilder();
            for (int i : l) {
                strb.append(i);
                strb.append(" ");
            }
            strb.deleteCharAt(strb.length() - 1);
            System.out.println(strb);
        }
    }

    private static void dfs (TreeNode cur, int sum, int target, List<Integer> temp) {

        sum += cur.val;
        temp.add(cur.val);
        if (cur.children.size() == 0 && sum == target) {
            ans.add(new ArrayList<>(temp));
            temp.remove(temp.size() - 1);
            return;
        }
        if (sum > target) {
            temp.remove(temp.size() - 1);
            return;
        }

        if (cur.children.size() != 0) {
            for (int child : cur.children) {
                dfs (map.get(child), sum, target, temp);
            }
        }

        temp.remove(temp.size() - 1);

    }

    private static class TreeNode {
        int id;
        int val;
        List<Integer> children;

        TreeNode (int id, int val) {
            this.id = id;
            this.val = val;
            children = new ArrayList<>();
        }

    }

    private static class Comparator2 implements Comparator<List<Integer>> {

        @Override
        public int compare(List<Integer> o1, List<Integer> o2) {
            int min_len = Math.min(o1.size(), o2.size());
            for (int i = 0; i < min_len; i++) {
                if (o1.get(i) > o2.get(i)) return -1;
                if (o1.get(i) < o2.get(i)) return 1;
                if (o1.get(i) == o2.get(i)) continue;
            }
            return 0;
        }
    }
}
