import sun.reflect.generics.tree.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PAT1043 {

    static Queue<Integer> comp = new LinkedList<>();
    static Queue<Integer> comp_mir = new LinkedList<>();
    static boolean isBinaryTree = true;
    static StringBuilder strb = new StringBuilder();

    public static void main (String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] spl = br.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(spl[i]);
        }
        TreeNode root = new TreeNode(arr[0]);
        for (int i = 1; i < n; i++) {
            buildTree(root, arr[i]);
        }

        dfs_pre_mir(root);
        dfs_pre(root);

        boolean flag1 = true;
        for (int i = 0; i < n; i++) {
            if (arr[i] != comp.remove()) {
                flag1 = false;
                break;
            }
        }

        boolean flag2 = true;
        for (int i = 0; i < n; i++) {
            if (arr[i] != comp_mir.remove()) {
                flag2 = false;
                break;
            }
        }

        if (!flag1 && !flag2) {
            isBinaryTree = false;
        }

        if (isBinaryTree) {
            if (flag1) {
                dfs_post(root);
            }
            if (flag2) {
                dfs_post_mir(root);
            }
            strb.deleteCharAt(strb.length() - 1);
            System.out.println("YES");
            System.out.println(strb);
        } else {
            System.out.println("NO");
        }

    }

    private static void dfs_pre (TreeNode root) {
        if (root == null) return;
        comp.add(root.val);
        dfs_pre(root.left);
        dfs_pre(root.right);
    }

    private static void dfs_post (TreeNode root) {
        if (root == null) return;

        dfs_post(root.left);
        dfs_post(root.right);

        strb.append(root.val);
        strb.append(" ");
    }


    private static void dfs_pre_mir (TreeNode root) {
        if (root == null) return;
        comp_mir.add(root.val);
        dfs_pre_mir(root.right);
        dfs_pre_mir(root.left);
    }

    private static void dfs_post_mir (TreeNode root) {
        if (root == null) return;

        dfs_post_mir(root.right);
        dfs_post_mir(root.left);

        strb.append(root.val);
        strb.append(" ");
    }

    private static TreeNode buildTree (TreeNode cur, int num) {
        if (cur == null) {
            return new TreeNode(num);
        }
        if (num < cur.val) {
            cur.left = buildTree(cur.left, num);
        } else {
            cur.right = buildTree(cur.right, num);
        }
        return cur;
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
