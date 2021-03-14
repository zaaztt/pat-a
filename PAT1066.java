import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PAT1066 {
    public static class AVLTree {
        TreeNode root;

        private class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            int height;
            TreeNode(int val){
                this.val = val;
                height = 1;
            }
        }

        AVLTree () {
            root = null;
        }

        TreeNode insertInit(int val) {
            return root = insert(root, val);
        }

        TreeNode insert(TreeNode cur, int val) {
            if (cur == null) return new TreeNode(val);
            if (val < cur.val) {
                cur.left = insert(cur.left, val);
            } else {
                cur.right = insert(cur.right, val);
            }
            if (getHeight(cur.left) - getHeight(cur.right) > 1) {
                if (getHeight(cur.left.left) - getHeight(cur.left.right) > 0) {
                    cur = rotateRight(cur);
                } else {
                    cur.left = rotateLeft(cur.left);
                    cur = rotateRight(cur);
                }
            }
            if (getHeight(cur.left) - getHeight(cur.right) < -1) {
                if (getHeight(cur.right.left) - getHeight(cur.right.right) < 0) {
                    cur = rotateLeft(cur);
                } else {
                    cur.right = rotateRight(cur.right);
                    cur = rotateLeft(cur);
                }
            }
            return cur;
        }

        int getHeight (TreeNode cur) {
            if (cur == null) return 0;
            return Math.max(getHeight(cur.left), getHeight(cur.right)) + 1;

        }

        TreeNode rotateRight (TreeNode cur) {
            TreeNode temp = cur.left;
            cur.left = temp.right;
            temp.right = cur;
            return temp;
        }

        TreeNode rotateLeft (TreeNode cur) {
            TreeNode temp = cur.right;
            cur.right = temp.left;
            temp.left = cur;
            return temp;
        }

    }

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        String[] line = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(line[i]);
        }
        AVLTree avlTree = new AVLTree();
        for (int i : arr) {
            avlTree.insertInit(i);
        }
        System.out.println(avlTree.root.val);
    }
}
