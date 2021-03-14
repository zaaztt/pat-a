import java.util.*;

public class PAT1094 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        Map<Integer, TreeNode> map = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            map.put(i, new TreeNode(i));
        }
        for (int i = 0; i < M; i++) {
            int id = sc.nextInt();
            int n = sc.nextInt();
            for (int j = 0; j < n; j++) {
                int child_id = sc.nextInt();
                map.get(id).children.add(map.get(child_id));
            }
        }
        map.get(1).level = 1;
        setLevel(map.get(1));
        int[] counts = new int[101];
        for (int i : map.keySet()) {
            int level = map.get(i).level;
            counts[level]++;
        }
        int max_level = 0;
        int max_count = 0;
        for (int i = 1; i < 101; i++) {
            if (counts[i] > max_count) {
                max_count = counts[i];
                max_level = i;
            }
        }
        System.out.println(max_count + " " + max_level);
    }

    private static void setLevel (TreeNode root) {
        if (root == null) {
            return;
        }
        for (TreeNode node : root.children) {
            node.level = root.level + 1;
            setLevel(node);
        }
    }

    private static class TreeNode {
        int id;
        List<TreeNode> children;
        int level;
        TreeNode (int id) {
            this.id = id;
            children = new ArrayList<>();
        }
    }
}
