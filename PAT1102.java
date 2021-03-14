import org.omg.CORBA.INTERNAL;
import sun.reflect.generics.tree.Tree;

import java.util.*;

public class PAT1102 {
    static Map<Integer, TreeNode> map = new HashMap<>();
    static StringBuilder strb_levelOrder = new StringBuilder();
    static StringBuilder strb_inOrder = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            map.put(i, new TreeNode(i));
            set.add(i);
        }

        for (int i = 0; i < N; i++) {
            TreeNode cur = map.get(i);
            String left = sc.next();
            if (left.equals("-")) {
                cur.left = null;
            } else {
                cur.left = map.get(Integer.valueOf(left));
                set.remove(Integer.valueOf(left));
            }
            String right = sc.next();
            if (right.equals("-")) {
                cur.right = null;
            } else {
                cur.right = map.get(Integer.valueOf(right));
                set.remove(Integer.valueOf(right));
            }
        }

        int root_id = set.iterator().next();
        TreeNode root = map.get(root_id);
        invertTree(root);
        levelOrder(root);
        inOrder(root);
        strb_levelOrder.deleteCharAt(strb_levelOrder.length() - 1);
        strb_inOrder.deleteCharAt(strb_inOrder.length() - 1);
        System.out.println(strb_levelOrder);
        System.out.println(strb_inOrder);

    }

    private static void levelOrder (TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            strb_levelOrder.append(cur.val).append(" ");
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
    }

    private class Com implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return 0;
        }
    }

    private static void inOrder (TreeNode cur) {
        if (cur == null) {
            return;
        }
        inOrder(cur.left);
        strb_inOrder.append(cur.val).append(" ");
        inOrder(cur.right);
    }


    private static void invertTree(TreeNode cur) {
        if (cur == null) {
            return;
        }

        TreeNode temp = cur.left;
        cur.left = cur.right;
        cur.right = temp;

        invertTree(cur.left);
        invertTree(cur.right);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode (int val) {
            this.val = val;
        }
    }
}
