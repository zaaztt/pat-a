import sun.reflect.generics.tree.Tree;

import java.util.*;

public class PAT1099 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Map<Integer, TreeNode> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.put(i, new TreeNode(i));
        }
        for (int i = 0; i < N; i++) {
            int left_id = sc.nextInt();
            int right_id = sc.nextInt();
            TreeNode cur = map.get(i);
            if (left_id != -1) {
                cur.left = map.get(left_id);
            }
            if (right_id != -1) {
                cur.right = map.get(right_id);
            }
        }
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = sc.nextInt();
        }
        Arrays.sort(a);
        setNodes(map.get(0));
        TreeNode root = map.get(0);
        constructTreeVal(root, a, 0, N - 1);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder strb = new StringBuilder();
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
            strb.append(cur.val).append(' ');
        }
        strb.deleteCharAt(strb.length() - 1);
        System.out.println(strb);
    }

    private static void constructTreeVal (TreeNode cur, int[] a, int start, int end) {
        if (start > end) {
            return;
        }
        cur.val = a[start + cur.left_nodes];
        constructTreeVal(cur.left, a, start, start + cur.left_nodes - 1);
        constructTreeVal(cur.right, a, start + cur.left_nodes + 1, end);

    }

    private static int setNodes(TreeNode cur) {
        if (cur == null) {
            return 0;
        }
        cur.left_nodes = setNodes(cur.left);
        cur.right_nodes = setNodes(cur.right);
        return 1 + cur.left_nodes + cur.right_nodes;
    }

    private static class TreeNode {
        int val;
        int id;
        TreeNode left;
        int left_nodes;
        TreeNode right;
        int right_nodes;
        TreeNode (int id) {
            this.id = id;
        }
    }
}
