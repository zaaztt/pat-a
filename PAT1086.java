import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class PAT1086 {
    static List<Integer> preorder;
    static List<Integer> inorder;
    static StringBuilder strb = new StringBuilder();
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.valueOf(sc.nextLine());
        Stack<Integer> stack = new Stack<>();
        preorder = new ArrayList<>();
        inorder = new ArrayList<>();
        for (int i = 0; i < 2 * N; i++) {
            String[] line = sc.nextLine().split(" ");
            if (line[0].equals("Push")) {
                int num = Integer.valueOf(line[1]);
                stack.push(num);
                preorder.add(num);
            } else {
                inorder.add(stack.pop());
            }
        }
        TreeNode root = constructTree(0, N - 1, 0, N - 1);
        postOrder(root);
        strb.deleteCharAt(strb.length() - 1);
        System.out.println(strb);
    }

    private static void postOrder (TreeNode cur) {
        if (cur == null) {
            return;
        }
        postOrder(cur.left);
        postOrder(cur.right);
        strb.append(cur.val);
        strb.append(" ");
    }

    private static TreeNode constructTree (int preL, int preR, int inL, int inR) {
        if (preL > preR) {
            return null;
        }
        TreeNode cur = new TreeNode(preorder.get(preL));
        int temp = 0;
        for (temp = inL; temp <= inR; temp++) {
            if (inorder.get(temp) == preorder.get(preL)) {
                break;
            }
        }
        cur.left = constructTree(preL + 1, preL + temp - inL, inL, temp - 1);
        cur.right = constructTree(preL + temp - inL + 1, preR, temp + 1, inR);
        return cur;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
